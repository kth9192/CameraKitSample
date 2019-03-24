package com.kth.camerakitsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var cameraKitUtil: CameraKitUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cameraKitUtil = CameraKitUtil()

        capture.setOnClickListener {
                    camera.captureImage(cameraKitUtil.getCallback(themedContext))
        }
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
