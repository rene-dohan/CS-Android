package renetik.android.json.data.properties

import renetik.android.json.data.CSJsonMap
import renetik.android.json.data.extensions.getInt
import renetik.android.json.data.extensions.put

class CSJsonInt(
    val data: CSJsonMap,
    private val key: String,
    private val defaultValue: Int = 0
) {
    var integer: Int?
        get() = data.getInt(key)
        set(value) {data.put(key, value)}

    var value
        get() = integer ?: defaultValue
        set(value) {
            integer = value
        }


    override fun toString() = "$value"
}