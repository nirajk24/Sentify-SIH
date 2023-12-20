package com.example.sentimentanalysis

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.text.Collator.getDisplayName
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Video.Media
import android.provider.OpenableColumns
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.sentimentanalysis.databinding.ActivityAudioInputBinding
import java.io.File
import java.io.FileOutputStream

@Suppress("DEPRECATION")
class AudioInputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAudioInputBinding
    private val PICK_AUDIO_REQUEST = 1
    private val PERMISSION_REQUEST = 2
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    private lateinit var audioFile : Media
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAudioInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this@AudioInputActivity)
            .load(R.drawable.multiemotion)
//            .apply(RequestOptions().downsample(DownsampleStrategy.AT_MOST))
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into(binding.ivBottomgif)


        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                openFilePicker()
            }
        }

        //audio upload
        binding.etAudio.setOnClickListener {
            if (checkPermission()) {
                openFilePicker()
            } else {
                requestPermissions()
            }
        }
    }




    private fun checkPermission():Boolean {
    val readStoragePermission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
    return readStoragePermission == PackageManager.PERMISSION_GRANTED
}
private fun requestPermissions() {
    requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)

}private fun openFilePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "audio/*" // Set the MIME type to restrict to audio files
        startActivityForResult(intent, PICK_AUDIO_REQUEST)
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openFilePicker()
            }
        }
    }


        private fun getDisplayName(uri: Uri): String {
            val cursor = contentResolver.query(uri, null, null, null, null)
            return cursor?.use {
                it.moveToFirst()
                val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                it.getString(nameIndex)
            } ?: ""
        }

        private fun getAudioPath(uri: Uri): String {
            var path = ""
            contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                cursor.moveToFirst()
                path = cacheDir.absolutePath + File.separator + cursor.getString(nameIndex)
                val inputStream = contentResolver.openInputStream(uri)
                val outputStream = FileOutputStream(File(path))
                inputStream?.copyTo(outputStream)
            }
            return path
        }
}