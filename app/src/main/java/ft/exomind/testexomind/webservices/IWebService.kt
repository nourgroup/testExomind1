package ft.exomind.testexomind.webservices

import ft.exomind.testexomind.data.model.APIData.Weather
import retrofit2.Response

interface IWebService {
    suspend fun getdata(latlong : String): Response<Weather>
}