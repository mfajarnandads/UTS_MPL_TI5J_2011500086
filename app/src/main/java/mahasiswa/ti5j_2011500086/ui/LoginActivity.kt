package mahasiswa.ti5j_2011500086.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import mahasiswa.ti5j_2011500086.MainActivity
import mahasiswa.ti5j_2011500086.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val edun = findViewById<EditText>(R.id.edun)
        val edpw = findViewById<EditText>(R.id.edpw)
        val btnlogin = findViewById<Button>(R.id.btnlogin)

        val sp = getSharedPreferences("login", Context.MODE_PRIVATE)
        if(sp.contains("nama") && sp.getString("nama", "") != null) {
            val i = Intent(this@LoginActivity, MainActivity::class.java)
            i.putExtra("nama",sp.getString("nama", ""))
            startActivity(i)
            finish()
        }
        btnlogin.setOnClickListener {
            val nama = "${edun.text}"
            val pass = "${edpw.text}"
            if (nama.isNotEmpty() && pass.isNotEmpty()) {
                if (pass == "Fajar") {
                    val editor = sp.edit()
                    editor.putString("nama", "${edun.text}")
                    editor.apply()

                    val i = Intent(this@LoginActivity, MainActivity::class.java)
                    i.putExtra("nama", sp.getString("nama", ""))
                    startActivity(i)
                    finish()

                    Toast.makeText(this@LoginActivity, "Login berhasil", Toast.LENGTH_SHORT).show()
                } else {
                    edun.setText("")
                    edpw.setText("")
                    edun.requestFocus()
                    Toast.makeText(this@LoginActivity, "Password salah", Toast.LENGTH_SHORT).show()
                }
            } else {
                edun.setText("")
                edpw.setText("")
                edun.requestFocus()
                Toast.makeText(
                    this@LoginActivity,
                    "Username dan password tidak boleh kosong!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}