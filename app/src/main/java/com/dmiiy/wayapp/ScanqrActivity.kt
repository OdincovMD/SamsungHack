package com.dmiiy.wayapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView

class ScanqrActivity : AppCompatActivity(), ZBarScannerView.ResultHandler {
    private lateinit var zbView: ZBarScannerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        zbView = ZBarScannerView(this)
        setContentView(zbView)
    }

    override fun onResume() {
        super.onResume()
        zbView.setResultHandler(this)
        checkCameraPermission()
        zbView.startCamera()
    }

    //Разрешение на использование камеры
    private fun checkCameraPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA), 1)

        }
    }


    override fun onPause() {
        super.onPause()
        zbView.stopCamera()
    }

    override fun handleResult(result: Result?) {
        val intent = Intent(this, tr1_1::class.java)
        startActivity(intent)
    }
}