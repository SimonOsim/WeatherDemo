package at.osim.weatherdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import at.osim.weather.model.Location
import at.osim.weather.model.LocationDao
import at.osim.weather.model.WeatherModel
import at.osim.weather.model.network.mock.MockWeatherApiFactory
import at.osim.weather.mvvm.WeatherListViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_weather_list.*
import kotlinx.android.synthetic.main.weather_list.*

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [WeatherDetailActivity] representing
 * items details. On tablets, the activity presents the list of items and
 * items details side-by-side using two vertical panes.
 */
class WeatherListActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    private lateinit var weatherAdapter: WeatherRecyclerViewAdapter
    private lateinit var model: WeatherListViewModel

    private lateinit var subscription:CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {

        model = WeatherListViewModel(
            WeatherModel(LocationDao(), MockWeatherApiFactory().getWeatherApi()).apply { setLocation(Location.LONDON) },
            AndroidResourceMapper(this, theme)
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        if (weather_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        setupRecyclerView(weather_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        weatherAdapter = WeatherRecyclerViewAdapter(this)
        recyclerView.adapter = weatherAdapter
    }

    override fun onStart() {
        super.onStart()
        subscription = CompositeDisposable()
        subscription.add(
        model.weather().subscribe {
            weatherAdapter.updateData(it)
        })
    }

    override fun onStop() {
        subscription.dispose()
        super.onStop()
    }
}
