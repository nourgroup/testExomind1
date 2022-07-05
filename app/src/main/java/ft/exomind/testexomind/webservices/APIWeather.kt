package ft.exomind.testexomind.webservices

import ft.exomind.testexomind.data.model.APIData.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface APIWeather {

    @GET("{coordinate}")
    suspend fun getMeteo(@Path("coordinate") coordinate : String) : Response<Weather>

}