package com.example.estaterentalapp.ui.home

import android.os.Build
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.estaterentalapp.R
import com.example.estaterentalapp.data.property
import com.squareup.picasso.Picasso


/**
 * [RecyclerView.Adapter] that can display a [DummyItem].
 * TODO: Replace the implementation with code for your data type.
 */
class propertyRecyclerViewAdapter(
    private val values: List<property>
) : RecyclerView.Adapter<propertyRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_property, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        if(item.image_URL != "")
            Picasso.get().load(item.image_URL).into(holder.propertyImageView)
        else
            holder.propertyImageView.setImageDrawable(holder.itemView.context.getDrawable(R.drawable.ic_baseline_cloud_queue_24))
        holder.propertyNameView.text = item.estate
        holder.propertyLocaleView.text = item.property_title
        holder.propertyRent.text = item.rent

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val propertyNameView: TextView = view.findViewById(R.id.propertyNameView)
        val propertyLocaleView: TextView = view.findViewById(R.id.propertyLocaleView)
        val propertyImageView: ImageView = view.findViewById(R.id.propertyImageView)
        val propertyRent: TextView = view.findViewById(R.id.propertyRent)
        override fun toString(): String {
            return super.toString() + " '" + propertyNameView.text + "'"
        }
    }
}