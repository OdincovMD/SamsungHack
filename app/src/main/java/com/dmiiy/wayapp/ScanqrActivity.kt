package com.dmiiy.wayapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener
import android.location.Location

class ScanqrActivity : AppCompatActivity(), ZBarScannerView.ResultHandler {
    private lateinit var zbView: ZBarScannerView
    private lateinit var fusedLocationClient: FusedLocationProviderClient
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

    //Обработка результата
    override fun handleResult(result: Result?) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val res: String? = result?.contents
        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener(this, OnSuccessListener<Location> { location ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    if (res.equals("1")){
                        val intent = Intent(this, Weather::class.java)
                        intent.putExtra("lat", latitude.toString())
                        intent.putExtra("lon", longitude.toString())
                        startActivity(intent)
                    }
                }
            })
        } else {
            requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
        if (res.equals("1")){
            val intent = Intent(this, Trip1::class.java)
            startActivity(intent)
        } else if (res.equals("2")){
            val intent = Intent(this, Trip2::class.java)
            startActivity(intent)
        } else if (res.equals("3")){
            val intent = Intent(this, Trip3::class.java)
            startActivity(intent)
        } else if (res.equals("4")){
            val intent = Intent(this, Trip4::class.java)
            startActivity(intent)
        } else{
            val intent = Intent(this, MapsActivity::class.java)
            Toast.makeText(applicationContext, "QR-код не распознан", Toast.LENGTH_LONG).show()
            startActivity(intent)
        }
    }
}