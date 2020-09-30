package qboinstitute.com.apppatitas.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_registro.*
import qboinstitute.com.apppatitas.R

class RegistroActivity : AppCompatActivity() {

    private lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        queue = Volley.newRequestQueue(this)
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