package com.example.sentimentanalysis.auth

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.sentimentanalysis.sentiment.MainActivity
import com.example.sentimentanalysis.R
import com.example.sentimentanalysis.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignUpBinding
    private lateinit var progressDialog: Dialog
    private lateinit var dialogText: TextView
    private lateinit var auth:FirebaseAuth
    private lateinit var email:String
    private lateinit var pwd:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progressDialog= Dialog(this)
        progressDialog.setContentView(R.layout.dialog_layout)
        progressDialog.setCancelable(false)
        progressDialog.window?.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialogText=progressDialog.findViewById(R.id.dialog_text)
        dialogText.text="Creating New User..."

        auth=FirebaseAuth.getInstance()

        binding.btnback.setOnClickListener {
            finish()
        }

        binding.btnSignup.setOnClickListener {
            if(validate()){
                signUpNewUser()
            }

        }


    }

    private fun validate(): Boolean {
        val name= binding.etName.text.toString().trim()
        email= binding.etEmail.text.toString().trim()
        pwd= binding.etPass.text.toString().trim()
        val cpwd=binding.etcPass.text.toString().trim()
        if(name.isEmpty()){
            binding.etName.error="Enter Name"
            return false
        }
        if(email.isEmpty()){
            binding.etEmail.error="Enter Email"
            return false
        }
        if(pwd.isEmpty()){
            binding.etPass.error="Enter Password"
            return false
        }
        if(cpwd.isEmpty()){
            binding.etcPass.error="Enter Password"
            return false
        }
        if(pwd.compareTo(cpwd)!=0){
            Toast.makeText(this,"Password Mismatch", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun signUpNewUser() {
        progressDialog.show()
        auth.createUserWithEmailAndPassword(email, pwd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    progressDialog.dismiss()
                    Toast.makeText(this@SignUpActivity,"Sign Up Successful",Toast.LENGTH_SHORT).show()
                    val i =Intent(this@SignUpActivity, MainActivity::class.java)
                    startActivity(i)
                    this@SignUpActivity.finish()
                } else {
                    progressDialog.dismiss()
                    Toast.makeText(this@SignUpActivity,"Authentication Failed",Toast.LENGTH_SHORT).show()
                }
            }

    }
}