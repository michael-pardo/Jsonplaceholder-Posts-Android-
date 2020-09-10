package com.mistpaag.jsonplaceholder.post.di


import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mistpaag.jsonplaceholder.post.data.local.PostDB
import com.mistpaag.jsonplaceholder.post.data.remote.ApiService
import com.mistpaag.jsonplaceholder.post.data.repository.Repository
import com.mistpaag.jsonplaceholder.post.utils.Const
import com.mistpaag.jsonplaceholder.post.views.SharedViewModel
import com.mistpaag.jsonplaceholder.post.views.detail.DetailPostViewModel
import com.mistpaag.jsonplaceholder.post.views.main.favorites.FavoriteViewModel
import com.mistpaag.jsonplaceholder.post.views.main.all.PostListViewModel
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val appModule = module {
    single { SharedViewModel() }
}


val mainVMModule = module {
    viewModel {
        PostListViewModel(
            get()
        )
    }
    viewModel {
        FavoriteViewModel(
            get()
        )
    }
}

val detailVMModule = module {
    viewModel { DetailPostViewModel( get() ) }
}







val dataModule = module {
    single { Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(Const.URL_BASE)
        .client(createOkHttpClient())
        .build()
    }

    single { Room.databaseBuilder(get(), PostDB::class.java, Const.dbName).build() }
    single { get<PostDB>().postdao }

    single { get<Retrofit>().create(ApiService::class.java) }

    single { Repository(get(), get(), get() ) }
}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()
}

