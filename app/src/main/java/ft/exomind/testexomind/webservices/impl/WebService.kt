package ft.exomind.testexomind.webservices.impl

import ft.exomind.testexomind.BuildConfig
import ft.exomind.testexomind.data.model.APIData.Weather
import ft.exomind.testexomind.webservices.APIWeather
import ft.exomind.testexomind.webservices.IWebService
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// https://openweathermap.org/current

class WebService : IWebService {
    var service: APIWeather
    init{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.darksky.net/forecast/${BuildConfig.API_DARKSKY}/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(APIWeather::class.java)

    }

    override suspend fun getdata(latlong : String): Response<Weather> {
        return service.getMeteo(latlong)
    }
}