package com.example.roomdatabase_06azhaara

import android.content.Intent
import android.content.LocusId
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Update
import com.example.roomdatabase_06azhaara.room.Constant
import com.example.roomdatabase_06azhaara.room.Movie
import com.example.roomdatabase_06azhaara.room.MovieDb
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val db by lazy { MovieDb (this) }
    lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupListener()

        setupRecyclerView()

    }

    fun loadMovie(){
        CoroutineScope(Dispatchers.IO).launch {
            val movie = db.movieDao().getMovie()
            Log.d("MainActivity", "dbresponse: $movie")
            withContext(Dispatchers.Main){
                movieAdapter.setData(movie)
            }
        }
    }

    fun intentEdit(barangId: Int, intentType: Int){
        startActivity(
            Intent(applicationContext, AddActivity::class.java)
                .putExtra("intent_id", barangId)
                .putExtra("intent_id", intentType)
        )
    }

    private fun setupRecyclerView(){
        movieAdapter = MovieAdapter(arrayListOf(), object : MovieAdapter.OnAdapaterListener{
            override fun onClick(movie: Movie) {
                intentEdit(movie.id,Constant.TYPE_READ)
            }

            override fun onUpdate(movie: Movie) {
                intentEdit(movie.id,Constant.TYPE_UPDATE)
            }

            override fun onDelete(movie: Movie) {
                CoroutineScope(Dispatchers.IO).launch {
                    db.movieDao().deleteMovie(movie)
                    loadMovie()
                }
            }
        })
        rv_movie.apply {
         layoutManager = LinearLayoutManager(applicationContext)
         adapter = movieAdapter
        }
    }

    fun setupListener(){
        add_movie.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
       loadMovie()
    }
}