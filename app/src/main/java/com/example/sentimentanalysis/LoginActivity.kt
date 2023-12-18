package com.example.sentimentanalysis

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import com.example.sentimentanalysis.databinding.ActivityLoginBinding
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
        auth.signInWithEmailAndPassword(binding.etMail.text.toString().trim(), binding.etPassword.text.toString().trim())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
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