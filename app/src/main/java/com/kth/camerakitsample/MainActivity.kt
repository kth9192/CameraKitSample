package com.kth.camerakitsample

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.camerakit.CameraKitView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.FileOutputStream
import android.os.Environment.getExternalStorageDirectory
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File
import java.util.HashMap


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        capture.setOnClickListener { v ->
            (
                    camera.captureImage(object : CameraKitView.ImageCallback {
                        override fun onImage(cameraKitView: CameraKitView?, capturedImage: ByteArray?) {

//                    val savedPhoto = File(getExternalStorageDirectory(), "photo.jpg")
                            val savedPhoto = saveBitmapToJpeg(themedContext, byteArrayToBitmap(capturedImage), "image")

                            val map = HashMap<String, RequestBody>()
                            val image = RequestBody.create(MediaType.parse("image/*"), savedPhoto)
                            map["MEM_IMG\"; filename=\"image.jpg"] = image
                        }
                    }))
        }

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

    override fun onStart() {
        super.onStart()
        camera.onStart()
    }

    override fun onPause() {
        super.onPause()
        camera.onPause()
    }

    override fun onResume() {
        super.onResume()
        camera.onResume()
    }

    override fun onStop() {
        super.onStop()
        camera.onStop()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        camera.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
