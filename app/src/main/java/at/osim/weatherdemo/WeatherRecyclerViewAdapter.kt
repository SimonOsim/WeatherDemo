package at.osim.weatherdemo

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import at.osim.weather.ListEvent
import at.osim.weather.mvvm.WeatherViewData
import com.squareup.picasso.Picasso

class WeatherRecyclerViewAdapter(context: Context) :
    RecyclerView.Adapter<WeatherRecyclerViewAdapter.WeatherViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val items = arrayListOf<WeatherViewData>()

    fun updateData(event: ListEvent<WeatherViewData>) {
        when (event.type) {
            ListEvent.Type.CLEAR -> clearData()
            ListEvent.Type.INIT -> initData(event.items)
            ListEvent.Type.INSERT -> insertData(event.items, event.pos)
            ListEvent.Type.UPDATE -> updateData(event.items, event.pos)
            ListEvent.Type.REMOVE -> removeData(event.pos, event.items.size)
            ListEvent.Type.END -> { //do nothing
            }
        }
    }

    private fun clearData() {
        items.clear()
        notifyDataSetChanged()
    }

    private fun initData(items: List<WeatherViewData>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    private fun insertData(items: List<WeatherViewData>, pos: Int) {
        this.items.addAll(pos, items)
        notifyItemRangeInserted(pos, items.size)
    }

    private fun updateData(items: List<WeatherViewData>, pos: Int) {
        for (x in 0 until items.size) {
            this.items.removeAt(pos)
        }
        this.items.addAll(pos, items)
        notifyItemRangeChanged(pos, items.size)
    }

    private fun removeData(pos: Int, count: Int) {
        for (x in 0 until count) {
            this.items.removeAt(pos)
        }
        notifyItemRangeRemoved(pos, count)
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, itemType: Int): WeatherViewHolder {
        return WeatherViewHolder(inflater.inflate(R.layout.weather_item, viewGroup, false))

    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(vh: WeatherViewHolder, pos: Int) {
        val item = items[pos]

        vh.ivIcon.setImageResource(item.weatherIcon)
        vh.tvDay.text = item.date
        vh.tvTemp.text = "${item.minTemperatur}°C todo"
    }


    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivIcon: ImageView = itemView.findViewById(R.id.iv_icon)
        val tvDay: TextView = itemView.findViewById(R.id.tv_day)
        val tvTemp: TextView = itemView.findViewById(R.id.tv_temp)
    }

}