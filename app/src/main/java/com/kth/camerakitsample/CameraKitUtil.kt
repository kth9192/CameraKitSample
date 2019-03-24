package com.kth.camerakitsample

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.camerakit.CameraKitView
import java.io.File
import java.io.FileOutputStream

class CameraKitUtil {

    fun getCallback(context: Context) : CameraKitView.ImageCallback{

        var captureCallback: CameraKitView.ImageCallback = object: CameraKitView.ImageCallback {
            override fun onImage(cameraKitView: CameraKitView?, capturedImage: ByteArray?) {
                val savedPhoto = saveBitmapToJpeg(context , byteArrayToBitmap(capturedImage), "image")

                //rest로 파일을 보내기 위한 작업
//                          val map = HashMap<String, RequestBody>()
//                          val image = RequestBody.create(MediaType.parse("image/*"), savedPhoto)
//                          map["MEM_IMG\"; filename=\"image.jpg"] = image
            }

        }

        return captureCallback
    }


    fun byteArrayToBitmap(byteArray: ByteArray?): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)
    }

    fun saveBitmapToJpeg(context: Context, bitmap: Bitmap, name: String): File {

        val storage = context.cacheDir // 임시파일 저장 경로
        val fileName = "$name.jpg"  // 파일이름

        val tempFile = File(storage, fileName)

        tempFile.createNewFile()

        val out = FileOutputStream(tempFile)

        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out)  // 넘거 받은 bitmap을 jpeg(손실압축)으로 저장해줌

        out.close() //필수

        return tempFile
    }

}