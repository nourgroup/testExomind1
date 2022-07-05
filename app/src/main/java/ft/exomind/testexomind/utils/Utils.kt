package ft.exomind.testexomind.utils

import ft.exomind.testexomind.R
import ft.exomind.testexomind.data.model.City
import kotlin.math.roundToInt
import kotlin.math.roundToLong

object Utils {

    var delayTime : Long= 10000
    var max : Long = 60000

    var listWeather = mutableListOf(
        City("Rennes","48.1113387,-1.6800198"),
        City("Paris","48.8588897,2.320041"),//
        City("Nantes","47.2186371,-1.5541362"), //
        City("Bordeaux","44.841225,-0.5800364"), //
        City("Lyon","45.7578137,4.8320114"),
        City("Rouen","49.4404591,1.0939658")
    )

    // display waiting message
    var list = listOf(
        R.string.waiting1,
        R.string.waiting2,
        R.string.waiting3
    )

    fun Double.roundItToDislayTemperature() = (this * 100.0).roundToInt() / 100.0;

    /**
     * @param fahrenheit
     * fahrenheit to celsius
     * y celsius = (x fahrenheit − 32) * (5/9)
     */

    fun fahrenheitToCelsius(fahrenheit : Double?) : Double{
        return (fahrenheit?.minus(32) )!!.times(0.5556)
    }
}