package com.example.moviecatalogue.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.data.call.DataSource
import com.example.moviecatalogue.data.call.LocalDataSource
import com.example.moviecatalogue.data.call.RemoteDataSource
import com.example.moviecatalogue.data.local.entity.Movie
import com.example.moviecatalogue.data.local.entity.TvShow
import com.example.moviecatalogue.data.remote.responsestatus.ApiResponse
import com.example.moviecatalogue.utils.NetworkBoundResource
import com.example.moviecatalogue.vo.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class Repository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
    ) : DataSource {

    companion object {

        const val LOAD_SIZE = 4
        const val PAGE_SIZE = 4

        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource
        ): Repository =
            instance ?: synchronized(this) {
                Repository(remoteDataSource,localDataSource).apply {
                    instance = this
                }
            }


    }

    override fun getAllMovies(): LiveData<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<Movie>>() {
            override fun loadFromDB(): LiveData<List<Movie>> =
                localDataSource.getAllMovies()

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<Movie>>> =
                remoteDataSource.getMovie()


            override fun saveCallResult(data: List<Movie>) {
                val movieList = ArrayList<Movie>()
                for (response in data) {
                    val movies = Movie(
                        response.id,
                        response.backdrop_img,
                        response.title,
                        response.img_path,
                        response.language,
                        response.overview,
                        response.release_date,
                        response.vote_average,
                        false
                    )
                    movieList.add(movies)
                }
                localDataSource.insertMovie(movieList)
            }

        }.asLiveData()

    override fun getAllTvShows(): LiveData<Resource<List<TvShow>>> =
        object : NetworkBoundResource<List<TvShow>,List<TvShow>>(){
            override fun loadFromDB(): LiveData<List<TvShow>> =
                localDataSource.getAllTvShows()

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShow>>> =
                remoteDataSource.getTvShow()

            override fun saveCallResult(data: List<TvShow>) {
                val tvShowList = ArrayList<TvShow>()
                for (response in data) {
                    val tvShow = TvShow(
                        response.id,
                        response.backdrop_img,
                        response.title,
                        response.imgPath,
                        response.language,
                        response.overview,
                        response.release_date,
                        response.vote_average,
                        false
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShow(tvShowList)
            }

        }.asLiveData()

    override fun getMovieById(movieId: Int): LiveData<Resource<Movie>> =
       object : NetworkBoundResource<Movie,Movie> (){

           override fun loadFromDB(): LiveData<Movie> =
               localDataSource.getMovieById(movieId)

           override fun shouldFetch(data: Movie?): Boolean =
               data == null

           override fun createCall(): LiveData<ApiResponse<Movie>> =
               remoteDataSource.getMovieById(movieId)

           override fun saveCallResult(data: Movie) {

           }


       }.asLiveData()

    override fun getTvById(tvId: Int): LiveData<Resource<TvShow>> =
        object : NetworkBoundResource<TvShow,TvShow>(){

            override fun loadFromDB(): LiveData<TvShow> =
                localDataSource.getTvShowById(tvId)


            override fun shouldFetch(data: TvShow?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<TvShow>> =
                remoteDataSource.getTvById(tvId)

            override fun saveCallResult(data: TvShow) {
                
            }

        }.asLiveData()


    override fun getFavoriteMovie(): LiveData<PagedList<Movie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(LOAD_SIZE)
            .setPageSize(PAGE_SIZE)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShow>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(LOAD_SIZE)
            .setPageSize(PAGE_SIZE)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }
    override fun setFavoriteMovie(movie: Movie,state : Boolean) {
        CoroutineScope(IO).launch {
            localDataSource.setFavoriteMovie(movie,state)
        }
    }

    override fun setFavoriteTvShow(tvShow: TvShow,state: Boolean) {
        CoroutineScope(IO).launch {
            localDataSource.setFavoriteTvShow(tvShow,state)
        }
    }
}



