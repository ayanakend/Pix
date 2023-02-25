package com.example.pix

    data class PixModel(
        var hits: java.util.ArrayList<Hit>
    )
    data class Hit(
        var largeImageURL: String
    )
