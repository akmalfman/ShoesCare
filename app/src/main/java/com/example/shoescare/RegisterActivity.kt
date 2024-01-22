package com.example.shoescare
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.shoescare.databinding.ActivityRegisterBinding
import com.example.shoescare.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val conf_password = binding.confPass.text.toString()

            //Validasi email
            if (email.isEmpty()) {
                binding.email.error = "Email Harus Diisi"
                binding.email.requestFocus()
                return@setOnClickListener
            }

            //Validasi email tidak sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.email.error = "Email Tidak Valid"
                binding.email.requestFocus()
                return@setOnClickListener
            }

            //Validasi password
            if (password.isEmpty()) {
                binding.password.error = "Password Harus Diisi"
                binding.password.requestFocus()
                return@setOnClickListener
            }

            //Validasi panjang password
            if (password.length < 6) {
                binding.password.error = "Password Minimal 6 Karakter"
                binding.password.requestFocus()
                return@setOnClickListener
            }

            //Validasi panjang password
            if (conf_password.length < 6) {
                binding.confPass.error = "Password Minimal 6 Karakter"
                binding.confPass.requestFocus()
                return@setOnClickListener
            }

            //Validasi konfigurasu
            if (conf_password!=password) {
                binding.confPass.error = "Password Harus Sama"
                binding.confPass.requestFocus()
                return@setOnClickListener
            }
            registerFirebase(email, password)
        }

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerFirebase(email: String, password: String) {
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                //register student in database
                val user = User(
                    null,
                    binding.email.text.toString(),
                    binding.password.text.toString()
                )
                database = FirebaseDatabase.getInstance("https://shoescare-5ba2a-default-rtdb.asia-southeast1.firebasedatabase.app/")
                val ref = database.getReference("user")
                val id = FirebaseAuth.getInstance().currentUser!!.uid
                ref.child(id).setValue(user).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}