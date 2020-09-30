package qboinstitute.com.apppatitas.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_registro.*
import org.json.JSONObject
import qboinstitute.com.apppatitas.R

class RegistroActivity : AppCompatActivity() {

    private lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        queue = Volley.newRequestQueue(this)
        btnirlogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        btnregistrarme.setOnClickListener {
            btnregistrarme.isEnabled = false
            if(validarFormulario(it)){
                RegistrarUsuarioWS(it)
            }else{
                btnregistrarme.isEnabled = true
            }
        }
    }

    private fun RegistrarUsuarioWS(vista: View) {
        val urlwsregistro = "http://www.kreapps.biz/patitas/persona.php"
        val parametros = JSONObject()
        parametros.put("nombres", etnombrereg.text.toString())
        parametros.put("apellidos", etapellidoreg.text.toString())
        parametros.put("email", etemailreg.text.toString())
        parametros.put("celular", etcelularreg.text.toString())
        parametros.put("usuario", etusuarioreg.text.toString())
        parametros.put("password", etpasswordreg.text.toString())
        val request = JsonObjectRequest(
            Request.Method.PUT,
            urlwsregistro,
            parametros,{response->
                if(response.getBoolean("rpta")){
                    setearControles()
                }
                mostrarMensaje(vista, response.getString("mensaje"))
                btnregistrarme.isEnabled = true
            },{
                Log.e("REGISTRO", it.message.toString())
                btnregistrarme.isEnabled = true
            }
        )
        queue.add(request)
    }

    private fun validarFormulario(vista: View): Boolean{
        var respuesta = true
        when{
            etnombrereg.text.toString().trim().isEmpty() -> {
                etnombrereg.isFocusableInTouchMode = true
                etnombrereg.requestFocus()
                mostrarMensaje(vista, "Ingrese su nombre")
                respuesta = false
            }
            etapellidoreg.text.toString().trim().isEmpty() -> {
                etapellidoreg.isFocusableInTouchMode = true
                etapellidoreg.requestFocus()
                mostrarMensaje(vista, "Ingrese su apellido")
                respuesta = false
            }
            etemailreg.text.toString().trim().isEmpty() -> {
                etemailreg.isFocusableInTouchMode = true
                etemailreg.requestFocus()
                mostrarMensaje(vista, "Ingrese su email")
                respuesta = false
            }
            etcelularreg.text.toString().trim().isEmpty() -> {
                etcelularreg.isFocusableInTouchMode = true
                etcelularreg.requestFocus()
                mostrarMensaje(vista, "Ingrese su celular")
                respuesta = false
            }
            etusuarioreg.text.toString().trim().isEmpty() -> {
                etusuarioreg.isFocusableInTouchMode = true
                etusuarioreg.requestFocus()
                mostrarMensaje(vista, "Ingrese su usuario")
                respuesta = false
            }
            etpasswordreg.text.toString().trim().isEmpty() -> {
                etpasswordreg.isFocusableInTouchMode = true
                etpasswordreg.requestFocus()
                mostrarMensaje(vista, "Ingrese su password")
                respuesta = false
            }

        }
        return respuesta
    }

    private fun setearControles(){
        etnombrereg.setText("")
        etapellidoreg.setText("")
        etcelularreg.setText("")
        etemailreg.setText("")
        etusuarioreg.setText("")
        etpasswordreg.setText("")
    }

    fun mostrarMensaje(vista: View, mensaje: String){
        Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG).show()
    }

}