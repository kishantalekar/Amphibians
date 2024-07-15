package com.example.amphibians

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

enum class AmphibianType {
    Toad,
    Frog,
    Salamander
}

@Serializable
data class AmphibiansModel(
    val name: String, val type: AmphibianType, val description: String,
    @SerialName("img_src")
    val imgSrc: String
)
