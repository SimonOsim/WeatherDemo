package at.osim.weatherdemo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import at.osim.weather.model.Weather

import kotlinx.android.synthetic.main.activity_weather_list.*
import kotlinx.android.synthetic.main.weather_list_content.view.*
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

    override fun onCreate(savedInstanceState: Bundle?) {
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
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, emptyList(), twoPane)
    }

    class SimpleItemRecyclerViewAdapter(
        private val parentActivity: WeatherListActivity,
        private val values: List<Weather>,
        private val twoPane: Boolean
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val onClickListener: View.OnClickListener

        init {
            onClickListener = View.OnClickListener { v ->
                val item = v.tag as Weather
                if (twoPane) {
                    val fragment = WeatherDetailFragment().apply {
                        arguments = Bundle().apply {
                            putSerializable(WeatherDetailFragment.ARG_WEATHER_DATE, item.date)
                        }
                    }
                    parentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.weather_detail_container, fragment)
                        .commit()
                } else {
                    val intent = Intent(v.context, WeatherDetailActivity::class.java).apply {
                        putExtra(WeatherDetailFragment.ARG_WEATHER_DATE, item.date)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.weather_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.idView.text = "${item.temp}°C"
            holder.contentView.text = item.condition.name

            with(holder.itemView) {
                tag = item
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val idView: TextView = view.id_text
            val contentView: TextView = view.content
        }
    }
}
