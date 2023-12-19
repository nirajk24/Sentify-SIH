package com.example.sentimentanalysis

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.sentimentanalysis.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var progressDialog:Dialog
    private lateinit var dialogText: TextView
    private lateinit var binding:ActivityLoginBinding
    private lateinit var auth:FirebaseAuth


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

        auth=FirebaseAuth.getInstance()
        if(auth.currentUser!=null){
            val i = Intent(this@LoginActivity,MainActivity::class.java)
            startActivity(i)
            this@LoginActivity.finish()
        }

        val signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(getString(R.string.your_web_client_id))
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            .build()

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
        progressDialog.show()
        auth.signInWithEmailAndPassword(binding.etMail.text.toString().trim(), binding.etPassword.text.toString().trim())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    progressDialog.dismiss()
                    Toast.makeText(this@LoginActivity,"Login Success",Toast.LENGTH_SHORT).show()
                    val i = Intent(this@LoginActivity,MainActivity::class.java)
                    startActivity(i)
                    this@LoginActivity.finish()
                } else {
                    progressDialog.dismiss()
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@LoginActivity, task.exception!!.message.toString(),Toast.LENGTH_SHORT).show()
                }
            }
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