package info.dvkr.screenstream.ui.activity

import android.app.PictureInPictureParams
import android.hardware.Camera
import android.os.Build
import android.os.Bundle
import android.util.Rational
import android.view.View
import android.view.View.GONE
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import info.dvkr.screenstream.R
import info.dvkr.screenstream.ui.view.CameraPreview
import kotlinx.android.synthetic.main.activity_pip_camera.*

class PiPCameraActivity : AppCompatActivity() {
    private lateinit var pictureInPictureParamsBuilder : PictureInPictureParams.Builder

    private lateinit var mPreview : CameraPreview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pip_camera)
        val mCamera = getCameraInstance()

        mPreview = mCamera?.let {
            // Create our Preview view
            CameraPreview(this, it)
        }!!

        // Set the Preview view as the content of our activity.
        mPreview.also {
            camera_preview.addView(it)
        }
    }

    private fun getCameraInstance(): Camera? {
        return try {
            Camera.open() // attempt to get a Camera instance
        } catch (e: Exception) {
            // Camera is not available (in use or does not exist)
            null // returns null if camera is unavailable
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun startPip(view: View) {
        view.visibility = GONE
        pictureInPictureParamsBuilder = PictureInPictureParams.Builder()
        val aspectRatio = Rational(mPreview.width, mPreview.height)
        pictureInPictureParamsBuilder.setAspectRatio(aspectRatio).build()
        enterPictureInPictureMode(pictureInPictureParamsBuilder.build())
    }
}
