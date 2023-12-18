package com.example.sentimentanalysis

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import com.example.sentimentanalysis.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var progressDialog:Dialog
    private lateinit var dialogText: TextView
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog= Dialog(this)
        progressDialog.setContentView(R.layout.dialog_layout)
        progressDialog.setCancelable(false)
        progressDialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialogText=progressDialog.findViewById(R.id.dialog_text)
        dialogText.text="Signing In..."


        binding.btnLogin.setOnClickListener {
            if(validateData()){
                login()
            }
        }
        binding.btnSP.setOnClickListener {
            val i= Intent(this@LoginActivity,SignUpActivity::class.java)
            startActivity(i)
        }

    }

    private fun login() {
        TODO("Not yet implemented")
    }

    private fun validateData(): Boolean {
        if(binding.etMail.text.toString().isEmpty()){
            binding.etMail.error = "Enter E-mail Id"
            return false

        }
        if(binding.etPassword.text.toString().isEmpty()){
            binding.etPassword.error="Enter Password"
            return false
        }
        return true
    }
}