package com.kk.esport

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnLaunch = findViewById<Button>(R.id.btn_launch)
        val tvStatus = findViewById<TextView>(R.id.tv_status)

        btnLaunch.setOnClickListener {
            tvStatus.text = "Status: Membersihkan Cache MLBB..."
            
            try {
                val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
                am.killBackgroundProcesses("com.mobile.legends")

                val cacheDir = File("/storage/emulated/0/Android/data/com.mobile.legends/cache")
                if (cacheDir.exists()) {
                    cacheDir.deleteRecursively()
                }

                Toast.makeText(this, "Cache Dibersihkan! Membuka ML...", Toast.LENGTH_SHORT).show()
                tvStatus.text = "Status: Berhasil! Silakan antre Rank."

                val launchIntent = packageManager.getLaunchIntentForPackage("com.mobile.legends")
                if (launchIntent != null) {
                    startActivity(launchIntent)
                } else {
                    Toast.makeText(this, "MLBB tidak terinstall", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
