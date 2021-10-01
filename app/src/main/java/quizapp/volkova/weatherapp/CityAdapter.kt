package quizapp.volkova.weatherapp

import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import java.net.URL
import java.util.*

class CityAdapter : RecyclerView.Adapter<CityAdapter.ViewHolder>() {
    val API: String = "407711ecf9fdb73061869dc807029279"
    val cityList : List<City> = arrayListOf(City("Zhytomyr", "0"),
        City("Poltava", "0"), City("Kremenchuk", "0"),
        City("Dnipro", "0"),City("Donetsk", "0"),
        City("Mariupol", "0"), City("Nikopol", "0"),
        City("Odesa", "0"), City("Cherkasy", "0"))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cityName.text = cityList[position].cityName
        holder.cityTemp.text = cityList[position].cityTemp
    }

    override fun getItemCount(): Int {
       return cityList.size
    }

    inner class ViewHolder(cityView: View) : RecyclerView.ViewHolder(cityView) {
        var cityName: TextView
        var cityTemp: TextView

        init {
            cityName = itemView.findViewById<TextView>(R.id.city_name)
            cityTemp = itemView.findViewById<TextView>(R.id.city_temp)
        }
    }

}
