package ft.exomind.testexomind.repositories.impl

import ft.exomind.testexomind.data.CityResponse
import ft.exomind.testexomind.data.model.City
import ft.exomind.testexomind.repositories.IRepositoryWeather
import ft.exomind.testexomind.utils.Utils
import ft.exomind.testexomind.utils.Utils.roundItToDislayTemperature
import ft.exomind.testexomind.webservices.impl.WebService
import kotlinx.coroutines.flow.MutableSharedFlow

class RepositoryWeather(private val mWebService: WebService) : IRepositoryWeather {

    var responseDataFromRepository = MutableSharedFlow<CityResponse<City>>()
    var cResp : CityResponse<City> =CityResponse()

    override suspend fun getdata(mCity : City)  {
        try{
            // transform data to a convenient form
            var data  = mWebService.getdata(mCity.longLat)
            if(data.isSuccessful){
                var resp = data.body()
                mCity.temperature = Utils.fahrenheitToCelsius(resp?.currently?.temperature?.toDouble()).roundItToDislayTemperature().toString()
                mCity.wind = resp?.currently?.cloudCover.toString()
                cResp.data = mCity
                cResp.code = data.code()
                responseDataFromRepository.emit(cResp)
            }else{
                cResp.data = null
                cResp.code = data.code()
                responseDataFromRepository.emit(cResp)
            }
        }catch (ex : Exception){
            cResp.data = null
            cResp.code = null
            responseDataFromRepository.emit(cResp)
        }


    }
}