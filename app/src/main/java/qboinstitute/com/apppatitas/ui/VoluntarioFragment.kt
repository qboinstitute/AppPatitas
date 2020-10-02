package qboinstitute.com.apppatitas.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_voluntario.*
import org.json.JSONObject
import qboinstitute.com.apppatitas.R
import qboinstitute.com.apppatitas.db.entity.PersonaEntity
import qboinstitute.com.apppatitas.viewmodel.PersonaViewModel


class VoluntarioFragment : Fragment() {

    private lateinit var queue: RequestQueue
    private lateinit var personaViewModel: PersonaViewModel
    private lateinit var personaEntity: PersonaEntity


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        queue = Volley.newRequestQueue(context)
        personaViewModel = ViewModelProvider(this).get(PersonaViewModel::class.java)
        personaViewModel.obtener()
            .observe(viewLifecycleOwner, Observer { persona ->
                persona?.let {
                    if(persona.esvoluntario == "1"){
                        actualizarFormulario()
                    }else{
                        personaEntity = persona
                    }
                }
            })
        val vista =inflater.inflate(R.layout.fragment_voluntario, container, false)
        val btnregvoluntario : Button = vista.findViewById(R.id.btnregvoluntario)
        btnregvoluntario.setOnClickListener {
            if(chkacepta.isChecked){
                btnregvoluntario.isEnabled = false
                registrarVoluntarioWS(it)
            }else{
                mostrarMensaje(it, getString(R.string.valerrorterminos))
            }
        }
        return vista
    }

    private fun registrarVoluntarioWS(vista: View) {
        val urlwsvoluntario = "http://www.kreapps.biz/patitas/personavoluntaria.php"
        val parametros = JSONObject()
        parametros.put("idpersona", personaEntity.id)
        val request = JsonObjectRequest(Request.Method.POST,
        urlwsvoluntario, parametros, {response ->
                if(response.getBoolean("rpta")){
                    val nuevaPersonaEntity = PersonaEntity(
                        personaEntity.id,
                        personaEntity.nombres,
                        personaEntity.apellidos,
                        personaEntity.email,
                        personaEntity.celular,
                        personaEntity.usuario,
                        personaEntity.password,
                        "1"
                    )
                    personaViewModel.actualizar(nuevaPersonaEntity)
                    actualizarFormulario()
                }
                mostrarMensaje(vista, response.getString("mensaje"))
                btnregvoluntario.isEnabled = true
            },{
                Log.e("REGVOLUNTARIO", it.message)
                btnregvoluntario.isEnabled = true
            })
        queue.add(request)
    }

    private fun actualizarFormulario(){
        tvtextoterminos.visibility = View.GONE
        btnregvoluntario.visibility = View.GONE
        chkacepta.visibility = View.GONE
        tvtituvoluntario.text = getString(R.string.valesvoluntario)
    }


    fun mostrarMensaje(vista: View, mensaje: String){
        Snackbar.make(vista, mensaje, Snackbar.LENGTH_LONG).show()
    }
}