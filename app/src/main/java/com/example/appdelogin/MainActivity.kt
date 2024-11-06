package com.example.appdelogin

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Inicializando as views
        progressBar = findViewById(R.id.progressBar)
        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        resultTextView = findViewById(R.id.resultado)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Botão de cadastrar
        findViewById<Button>(R.id.register_button).setOnClickListener { cadastrar(it) }

        // Botão de login
        findViewById<Button>(R.id.login_button).setOnClickListener { login(it) }
    }

    private fun showProgress(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun cadastrar(view: View) {
        showProgress(true)

        val url = "https://linuxjf.com/ava/api/usuario/create.php"
        val params = JSONObject()
        try {
            params.put("email", usernameEditText.text.toString())
            params.put("senha", passwordEditText.text.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, params,
            Response.Listener { response ->
                resultTextView.text = "Resposta: $response"
                showProgress(false)
            },
            Response.ErrorListener { error: VolleyError ->
                resultTextView.text = "Algo deu errado!"
                showProgress(false)
            }
        )

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    private fun login(view: View) {
        showProgress(true)

        val url = "https://linuxjf.com/ava/api/usuario/login.php"
        val params = JSONObject()
        try {
            params.put("email", usernameEditText.text.toString())
            params.put("senha", passwordEditText.text.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST, url, params,
            Response.Listener { response ->
                resultTextView.text = "Resposta: $response"
                showProgress(false)
            },
            Response.ErrorListener { error: VolleyError ->
                resultTextView.text = "Algo deu errado!"
                showProgress(false)
            }
        )

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
}
