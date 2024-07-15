package com.example.amphibians.Network

import com.example.amphibians.AmphibiansModel
import retrofit2.http.GET

//https://android-kotlin-fun-mars-server.appspot.com/amphibians
interface AmphibiansApiService{
@GET("amphibians")
suspend fun getAmphibians():List<AmphibiansModel>
}