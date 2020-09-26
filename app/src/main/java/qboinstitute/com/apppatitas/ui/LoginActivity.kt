package qboinstitute.com.apppatitas.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import qboinstitute.com.apppatitas.R


class LoginActivity : AppCompatActivity() {

    private lateinit var queue: RequestQueue
    private lateinit var preferencias: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        queue = Volley.newRequestQueue(this)
        preferencias = getSharedPreferences("appPatitas", MODE_PRIVATE)

        chkrecordar.setOnClickListener {
            setearValoresDeRecordar(it)
        }

        btnlogin.setOnClickListener {
            btnlogin.isEnabled = false
            if(validarUsuarioPassword()){
                autenticarUsuarioWS(it, etusuario.text.toString(), etpassword.text.toString())
            }else{
                mostrarMensaje(it, "Ingrese usuario y password.")
            }
        }
    }

    private fun setearValoresDeRecordar(vista: View) {
        if(vista is CheckBox){
            val checked: Boolean = vista.isChecked
            when(vista.id){
                R.id.chkrecordar -> {
                    if(!checked){
                        if(verificarValorSharedPreferences()){
                            etusuario.setText("")
                            etpassword.setText("")

                        }

                    }
                }
            }
        }

    }

    private fun autenticarUsuarioWS(vista: View, usuario: String, password: String) {
        val urlwslogin = "http://www.kreapps.biz/patitas/login.php"
        val parametros = JSONObject()
        parametros.put("usuario", usuario)
        parametros.put("password", password)
        val request = JsonObjectRequest(Request.Method.POST,
        urlwslogin, parametros,{ response ->
                if(response.getBoolean("rpta")){
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }else{
                    mostrarMensaje(vista, response.getString("mensaje"))
                }
                btnlogin.isEnabled = true
            },{
                Log.e("LOGIN", it.toString())
                btnlogin.isEnabled = true
            })
        queue.add(request)
    }

    fun verificarValorSharedPreferences(): Boolean{
        return preferencias.getBoolean("recordardatos", false)
    }

    fun validarUsuarioPassword(): Boolean{
        var respuesta = true
        if(etusuario.text.toString().trim().isEmpty()){
            etusuario.isFocusableInTouchMode = true
            etusuario.requestFocus()
            respuesta = false
        }else if(etpassword.text.toString().trim().isEmpty()){
            etpassword.isFocusableInTouchMode = true
            etpassword.requestFocus()
            respuesta = false
        }
        return respuesta
    }

    fun mostrarMensaje(vista: View, mensaje: String){
        Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG).show()
    }


}