package at.osim.weather

class ListEvent<T>(val type: Type, val pos: Int, val item: T)  {


    /**
     * [CLEAR] deletes all items in the list
     * [INIT] creates a new list. The [pos] shall be ignored!
     * [END] the last entry of the list was reached
     */
    enum class Type {
        CLEAR,
        INIT,
        INSERT,
        UPDATE,
        REMOVE,
        END
    }
}