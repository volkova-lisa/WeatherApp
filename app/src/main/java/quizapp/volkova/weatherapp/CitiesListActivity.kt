package quizapp.volkova.weatherapp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.net.URL

class CitiesListActivity : AppCompatActivity() {

    val API: String = "407711ecf9fdb73061869dc807029279"

    //probably have to extract cities to separate database ???
    var city1 = City("Kyiv", "0")
    var city2 = City("Zhytomyr", "0")
    var city3 = City("Poltava", "0")
    var city4 = City("Kremenchuk", "0")
    var city5 = City("Dnipro", "0")
    var city6 = City("Donetsk", "0")
    var city7 = City("Mariupol", "0")
    var city8 = City("Nikopol", "0")
    var city9 = City("Odesa", "0")
    var city10 = City("Cherkasy", "0")
    var city11 = City("Lviv", "0")
    var city12 = City("Dnipro", "0")
    var city13 = City("Nikopol", "0")
    var city14 = City("Odesa", "0")
    var city15 = City("Cherkasy", "0")
    var city16 = City("Lviv", "0")
    var city17 = City("Dnipro", "0")

    val citiesList: ArrayList<City> = arrayListOf(city1,city2,city3,city4,city5,city6,city7,city8,
        city9,city10,city11,city12,city13,city14,city15,city16,city17)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities_list)
        var recyclerView : RecyclerView = findViewById(R.id.cities_list)
        var cityAdapter = CityAdapter()
        recyclerView.adapter = cityAdapter

        //https://openweathermap.org/current#cities
        //trouble there is no relevamnt city codes http://web.archive.org/web/20180619015316/http://openweathermap.org/help/city_list.txt

        for (i in 0..citiesList.size) {
            findWeather(citiesList[i].cityName)
        }

    }

    inner class findWeather(cityName : String) : AsyncTask<String, Void, String>() {
        val cityName1: String = cityName

        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$cityName1&units=metric&appid=$API").readText(
                        Charsets.UTF_8
                    )
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            val jsonObj = JSONObject(result)
            val main = jsonObj.getJSONObject("main")
            val temp = main.getString("temp") + "°C"
        }
    }
}