package quizapp.volkova.weatherapp

import android.animation.ObjectAnimator
import android.content.Intent
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class CityAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(cityView: View) : RecyclerView.ViewHolder(cityView) {
        var cityName: TextView
        var citeTemp: TextView

        init {
            cityName = itemView.findViewById<TextView>(R.id.city_name)
            citeTemp = itemView.findViewById<TextView>(R.id.city_temp)
        }
    }


}
