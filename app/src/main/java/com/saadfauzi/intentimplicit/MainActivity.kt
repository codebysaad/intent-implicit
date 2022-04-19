package com.saadfauzi.intentimplicit

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.saadfauzi.intentimplicit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainACtivity"
    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnOpenWeb.setOnClickListener { openWeb() }
            btnOpenLocation.setOnClickListener { openLocation() }
            btnShareText.setOnClickListener { shareText() }
            btnOpenCam.setOnClickListener { openCam() }
        }
    }

    private fun openWeb(){
        val urlWeb = binding.edtOpenWeb.text.toString()
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlWeb))
        startActivity(intent)
        Log.d(TAG, "Intent Implicit Web")
    }

    private fun openLocation(){
        val locationName = binding.edtOpenLocation.text.toString()
        val uriMaps = Uri.parse("geo:0,0?q=$locationName")
        val intent = Intent(Intent.ACTION_VIEW, uriMaps)
        startActivity(intent)
        Log.d(TAG, "Intent Implicit Location")
    }

    private fun shareText(){
        val message = binding.edtShareText.text.toString()
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
        }
        val chooser = Intent.createChooser(intent, null)
        startActivity(chooser)
        Log.d(TAG, "Intent Implicit Share Text")
    }

    private fun openCam(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
            Log.d(TAG, "Intent Implicit Open Cam")
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CODE)
        }
    }
}