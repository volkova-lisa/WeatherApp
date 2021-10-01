package quizapp.volkova.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class CitiesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities_list)
        var recyclerView : RecyclerView = findViewById(R.id.cities_list)
        var cityAdapter = CityAdapter()
        recyclerView.adapter = cityAdapter
        
    }
}