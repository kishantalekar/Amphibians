package com.example.amphibians.ui.screens



import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.AmphibiansModel
import com.example.amphibians.data.AmphibianRepository
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface AmphibianUiState{
    data class Success(val photo:List<AmphibiansModel>):AmphibianUiState
    object Error:AmphibianUiState
    object Loading:AmphibianUiState


}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class AmphibiansViewModel(private val amphibianRepository: AmphibianRepository):ViewModel() {

    var amphibianUiState:AmphibianUiState by mutableStateOf(AmphibianUiState.Loading)
        private set

    init {
        getAmphibiansPhotos()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getAmphibiansPhotos(){
        viewModelScope.launch {
            try {
                Log.d("Debug","hello")
                val result = amphibianRepository.getAmphibians()

                Log.d("Debug","hello2")
           amphibianUiState=     AmphibianUiState.Success(photo = result)
            }catch (e:IOException){
              amphibianUiState=  AmphibianUiState.Error
            }catch (e:HttpException){
               amphibianUiState= AmphibianUiState.Error
            }
            catch (e:IllegalArgumentException){
                e.message?.let { Log.e("Error", it) }
                AmphibianUiState.Error
            }
        }
    }


    companion object {
        val Factory:ViewModelProvider.Factory= viewModelFactory {
            initializer {
                val application=(this[APPLICATION_KEY] as AmphibiansApplication)
                val amphibianRepository=application.container.amphibianRepository
                AmphibiansViewModel(amphibianRepository=amphibianRepository)
            }
        }
    }
}