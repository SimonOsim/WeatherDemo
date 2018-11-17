package at.osim.weather.model

data class Location(val id: Long, val name: String, val countryCode: String) {

    companion object {
        val LONDON = Location(2643741, "City of London", "UK")
    }
}