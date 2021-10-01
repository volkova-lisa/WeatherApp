package quizapp.volkova.weatherapp

import android.animation.ObjectAnimator
import android.os.AsyncTask
import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONObject
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    val CITY: String = "Kyiv"
    val API: String = "407711ecf9fdb73061869dc807029279"

    val cityList : List<City> = arrayListOf(City("Zhytomyr", "0"),
        City("Poltava", "0"), City("Kremenchuk", "0"),
        City("Dnipro", "0"),City("Donetsk", "0"),
        City("Mariupol", "0"), City("Nikopol", "0"),
        City("Odesa", "0"), City("Cherkasy", "0"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.all_cities).setOnClickListener{
            val intent = Intent(applicationContext, CitiesListActivity::class.java)
            intent.putExtra("api", API)
            startActivity(intent)
        }

        findWeather(cityList[1].cityName).execute()
    }

     inner class findWeather(cityName1 : String) : AsyncTask<String, Void, String>() {
        val cityName: String = cityName1
        override fun onPreExecute() {
            super.onPreExecute()
            findViewById<ProgressBar>(R.id.loader).visibility = View.VISIBLE
            findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.GONE
            findViewById<TextView>(R.id.errorText).visibility = View.GONE
        }

        override fun doInBackground(vararg params: String?): String? {
            var response: String?
            try {
                response =
                    URL("https://api.openweathermap.org/data/2.5/weather?q=$cityName&units=metric&appid=$API").readText(
                        Charsets.UTF_8
                    )
            } catch (e: Exception) {
                response = null
            }
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            try {
                /* Extracting JSON returns from the API */
                val jsonObj = JSONObject(result)
                val main = jsonObj.getJSONObject("main")
                val sys = jsonObj.getJSONObject("sys")
                val wind = jsonObj.getJSONObject("wind")
                val weather = jsonObj.getJSONArray("weather").getJSONObject(0)
                val updatedAt: Long = jsonObj.getLong("dt")
                val updatedAtText =
                    "Updated at: " + SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(
                        Date(updatedAt * 1000)
                    )
                val temp = main.getString("temp") + "°C"
                val tempMin = "Min Temp:\n" + main.getString("temp_min") + "°C"
                val tempMax = "Max Temp:\n" + main.getString("temp_max") + "°C"
                val pressure = main.getString("pressure")
                val humidity = main.getString("humidity")
                val sunrise: Long = sys.getLong("sunrise")
                val sunset: Long = sys.getLong("sunset")
                val windSpeed = wind.getString("speed")
                val weatherDescription = weather.getString("description")
                val address = jsonObj.getString("name") + ", " + sys.getString("country")

                findViewById<TextView>(R.id.address).text = address
                findViewById<TextView>(R.id.updated_at).text = updatedAtText
                findViewById<TextView>(R.id.status).text = weatherDescription.capitalize()
                findViewById<TextView>(R.id.temp).text = temp
                findViewById<TextView>(R.id.temp_min).text = tempMin
                findViewById<TextView>(R.id.temp_max).text = tempMax
                findViewById<TextView>(R.id.sunrise).text =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunrise * 1000))
                findViewById<TextView>(R.id.sunset).text =
                    SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(sunset * 1000))
                findViewById<TextView>(R.id.wind).text = windSpeed
                findViewById<TextView>(R.id.pressure).text = pressure
                findViewById<TextView>(R.id.humidity).text = humidity

                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<RelativeLayout>(R.id.mainContainer).visibility = View.VISIBLE
            } catch (e: Exception) {
                findViewById<ProgressBar>(R.id.loader).visibility = View.GONE
                findViewById<TextView>(R.id.errorText).visibility = View.VISIBLE
            }

            val temperature = findViewById<TextView>(R.id.temp)
            val fadeInAnim: ObjectAnimator =
                ObjectAnimator.ofFloat(temperature, View.ALPHA, 0f, 1f)
            fadeInAnim.setDuration(4000)

            fun Boolean.toInt() = if (this) 1 else 0

            var visible : Boolean = true
            val sq1: LinearLayout = findViewById<LinearLayout>(R.id.sq1)
            val sq2: LinearLayout = findViewById<LinearLayout>(R.id.sq2)
            val sq3: LinearLayout = findViewById<LinearLayout>(R.id.sq3)
            sq3.setOnClickListener{
                TransitionManager.beginDelayedTransition(sq1)
                visible = !visible
                findViewById<Space>(R.id.space_for_anim).setVisibility(if (visible) View.VISIBLE else View.GONE)
            }
            sq1.setOnClickListener{rotate(sq1)}
            sq2.setOnClickListener{slide(sq2)}
            fadeInAnim.start()
        }
        private fun slide(param: LinearLayout) {
            val rotAnim : Animation =
                AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
                param.startAnimation(rotAnim)
        }
        private fun rotate(param: LinearLayout) {
            val rotAnim : Animation =
                AnimationUtils.loadAnimation(applicationContext, R.anim.rotate)
            param.startAnimation(rotAnim)
        }
    }
}
