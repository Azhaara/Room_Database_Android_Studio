package com.example.roomdatabase_06azhaara.room

import android.icu.text.CaseMap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie (
    @PrimaryKey(autoGenerate = true )
    val id : Int,
    val title: String,
    val description: String,

        )
