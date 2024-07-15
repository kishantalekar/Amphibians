package com.example.amphibians.data

import com.example.amphibians.AmphibiansModel
import com.example.amphibians.Network.AmphibiansApiService

interface AmphibianRepository {

    suspend fun getAmphibians(): List<AmphibiansModel>
}

class NetworkAmphibianRepository(private val amphibiansApiService: AmphibiansApiService) :
    AmphibianRepository {
    override suspend fun getAmphibians(): List<AmphibiansModel> {
        return amphibiansApiService.getAmphibians()
    }

}