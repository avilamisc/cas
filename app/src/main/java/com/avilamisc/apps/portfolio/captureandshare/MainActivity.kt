package com.avilamisc.apps.portfolio.captureandshare

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }

    override fun onClick(v: View?) {

        if (v?.id == R.id.ma_btnPreview){
            takePicture()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ma_btnPreview.setOnClickListener(this)

    }

    fun takePicture(){
        val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (captureIntent
                .resolveActivity(packageManager) != null) {
            startActivityForResult(captureIntent, REQUEST_IMAGE_CAPTURE)
        } else{
            // "No existe aplicación para manejar la acción", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK){
            val extras: Bundle? = data?.extras
            val bitmap: Bitmap = extras?.get("data") as Bitmap
            ma_ivPreview.setImageBitmap(bitmap)
        }
    }
}
