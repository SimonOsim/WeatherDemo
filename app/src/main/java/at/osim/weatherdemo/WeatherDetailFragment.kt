package at.osim.weatherdemo

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_weather_detail.*
import kotlinx.android.synthetic.main.weather_detail.view.*

/**
 * A fragment representing a single Weather detail screen.
 * This fragment is either contained in a [WeatherListActivity]
 * in two-pane mode (on tablets) or a [WeatherDetailActivity]
 * on handsets.
 */
class WeatherDetailFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_WEATHER_DATE)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                //items = DummyContent.ITEM_MAP[it.getString(ARG_WEATHER_DATE)]
                //activity?.toolbar_layout?.title = items?.content
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.weather_detail, container, false)


        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the items ID that this fragment
         * represents.
         */
        const val ARG_WEATHER_DATE = "weather_date"
    }
}
