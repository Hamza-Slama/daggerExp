package com.elyeproj.DaggerExp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.elyeproj.DaggerExp.MyApplication.Companion.magicBox
import com.elyeproj.simplestappwithdagger2.R
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_list_movie.*
import retrofit2.http.GET
import javax.inject.Inject

class ListMovie : AppCompatActivity() {
    @Inject
    lateinit var apiMovies: ApiInterface
    private lateinit var movieAdapter: MoviesAdapter

    private var disposables: CompositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movie)
        magicBox.poke(this)
        movieAdapter = MoviesAdapter()
        movies_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        movies_list.adapter = movieAdapter

        disposables.add(apiMovies.getMovies()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    movieAdapter.setMovies(it.data)
                    Log.d("TAG", it.data[0].genre.toString())
                    it.data.forEach { println(it) }
                },
                        {
                            Toast.makeText(applicationContext, it.message, Toast.LENGTH_SHORT).show()
                        }))
    }


    inner class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

        private val movies: MutableList<Movie> = mutableListOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
            return MovieViewHolder(layoutInflater.inflate(R.layout.item_movie_layout, parent, false))
        }

        override fun getItemCount(): Int {
            return movies.size
        }

        override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
            holder.bindModel(movies[position])
        }

        fun setMovies(data: List<Movie>) {
            movies.addAll(data)
            notifyDataSetChanged()
        }

        inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val movieTitleTxt: TextView = itemView.findViewById(R.id.movieTitle)
            val movieGenreTxt: TextView = itemView.findViewById(R.id.movieGenre)
            val movieYearTxt: TextView = itemView.findViewById(R.id.movieYear)
            val movieAvatarImage: ImageView = itemView.findViewById(R.id.movieAvatar)

            fun bindModel(movie: Movie) {
                movieTitleTxt.text = movie.title
                movieGenreTxt.text = movie.genre
                movieYearTxt.text = movie.year
                Picasso.get().load(movie.poster).into(movieAvatarImage)
            }
        }
    }

    class Movie {

        var id: Int = 0
        var title: String? = null
        var year: String? = null
        var genre: String? = null
        var poster: String? = null
    }


    class MovieResponse {

        lateinit var data: List<Movie>
    }

}