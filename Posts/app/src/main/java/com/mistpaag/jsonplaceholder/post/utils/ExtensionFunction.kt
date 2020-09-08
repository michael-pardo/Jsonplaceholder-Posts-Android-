package com.mistpaag.jsonplaceholder.post.utils

import android.content.Context
import android.net.ConnectivityManager
import android.view.*
import android.widget.EditText


fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

fun Int.isSucessfull() = this == 200

fun EditText.getTextString() = this.text.toString()



fun Context.haveConnection():Boolean{
    var connectivityManager: ConnectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var networkInfo = connectivityManager.activeNetworkInfo
    return (networkInfo!=null && networkInfo.isAvailable && networkInfo.isConnected)
}


