package com.example.testmeli.utils

fun formatPrice(price: String) : String {
    return "$$price"
}

fun formatAvailables(available:String) : String {
    return "Disponibles: $available"
}

fun formatCondition(condition:String) : String {
    return "Condicion: $condition"
}