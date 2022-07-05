package ft.exomind.testexomind.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ft.exomind.testexomind.data.CityResponse
import ft.exomind.testexomind.data.model.City
import ft.exomind.testexomind.repositories.impl.RepositoryWeather
import ft.exomind.testexomind.utils.Utils
import ft.exomind.testexomind.webservices.impl.WebService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelProgressBar() : ViewModel(){



    var nbr : Long = 0

    var progress = MutableLiveData<Int>();

    var displayWeather = MutableLiveData<CityResponse<City>>()

    var displayMessage = MutableLiveData<Int>()

    var constructTable = MutableLiveData<String>()

    // decide repo and service to invoke
    val mRepositoryWeather = RepositoryWeather(WebService())

    /**
     *
     */
    fun loading(){
        viewModelScope.launch(Dispatchers.IO) {
            while(nbr <= Utils.max){
                progress.postValue(nbr.toInt())
                delay(Utils.delayTime)
                nbr+= Utils.delayTime
            }
            // end of call API
            constructTable.postValue("")
        }
    }

    /**
     *
     */
    fun getdata(mCity : City){
        viewModelScope.launch(Dispatchers.IO) {
            mRepositoryWeather.getdata(mCity)
            mRepositoryWeather.responseDataFromRepository.collect{
                displayWeather.postValue(it)
            }
        }
    }

    /**
     *
     */
    fun displayMessage(){
        var inc = 0
        viewModelScope.launch(Dispatchers.IO) {
            while(true){
                displayMessage.postValue(inc)
                delay(2000)
                inc++
            }
        }
    }

    /**
     *
     */
    fun reset(){
        nbr = 0
    }

}