package qboinstitute.com.apppatitas.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import qboinstitute.com.apppatitas.R
import qboinstitute.com.apppatitas.model.Mascota

class MascotaAdapter(private var lstmascotas: List<Mascota>,
private val context: Context)
    : RecyclerView.Adapter<MascotaAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_mascota,
            parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemmascota = lstmascotas[position]
        holder.tvnommascota.text = itemmascota.nommascota
        holder.tvlugar.text = itemmascota.lugar
        holder.tvcontacto.text = itemmascota.contacto
        holder.tvfecha.text = itemmascota.fechaperdida
        Glide.with(context).load(itemmascota.urlimagen).into(holder.ivmascota)
    }

    override fun getItemCount(): Int {
        return lstmascotas.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvnommascota : TextView = itemView.findViewById(R.id.tvnommascota)
        val tvlugar : TextView = itemView.findViewById(R.id.tvlugar)
        val tvfecha : TextView = itemView.findViewById(R.id.tvfecha)
        val tvcontacto : TextView = itemView.findViewById(R.id.tvcontacto)
        val ivmascota : ImageView = itemView.findViewById(R.id.ivmascota)
    }

}