package ft.exomind.testexomind.repositories

import ft.exomind.testexomind.data.model.City

interface IRepositoryWeather {
    suspend fun getdata(mCity : City)
}