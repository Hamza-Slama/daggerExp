package com.elyeproj.DaggerExp.Movie

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.elyeproj.DaggerExp.MyApplication.Companion.magicBox
import com.elyeproj.DaggerExp.module.Movie
import com.elyeproj.DaggerExp.module.MovieResponse
import com.elyeproj.simplestappwithdagger2.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_list_movie.*
import javax.inject.Inject

class ListMovie : AppCompatActivity(), ListView {

    private lateinit var movieAdapter: MoviesAdapter
    @Inject
    lateinit var presenter: ListPresenter

    override fun showList(list: MovieResponse) {
        movieAdapter = MoviesAdapter()
        movies_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        movieAdapter.setMovies(list.data)
        Log.d("size", list.data.size.toString())
        movies_list.adapter = movieAdapter

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movie)
        magicBox.poke(this)
        presenter.attachView(this)
        presenter.getListPres()

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


}