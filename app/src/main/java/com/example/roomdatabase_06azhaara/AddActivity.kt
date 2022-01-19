package com.example.roomdatabase_06azhaara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roomdatabase_06azhaara.room.Movie
import com.example.roomdatabase_06azhaara.room.MovieDb
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddActivity : AppCompatActivity() {
    val db by lazy { MovieDb(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        setuplistener()
    }

    fun setuplistener() {
        btn_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                db.movieDao().addMovie(
                    Movie(0, et_title.text.toString(),
                        et_description.text.toString())
                )

                finish()
            }
        }
    }
}