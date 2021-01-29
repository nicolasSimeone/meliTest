package com.example.testmeli.utils

import android.annotation.TargetApi
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.NetworkCapabilities
import android.os.Build
import android.provider.ContactsContract.CommonDataKinds.Email.TYPE_MOBILE
import android.telephony.TelephonyManager

object ConnectivityHelper {

    @TargetApi(Build.VERSION_CODES.M)
    fun getConnectionType(context: Context): Int {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        connectivityManager?.run {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                return when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> NetworkCapabilities.TRANSPORT_WIFI
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> NetworkCapabilities.TRANSPORT_CELLULAR
                    else -> -1
                }
            }
        }
        return -1
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun getConnectionTypeSDK21(context: Context): Int {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        for (network in connectivityManager!!.allNetworks) {
            val networkInfo = connectivityManager.getNetworkInfo(network)
            var typeMobile = connectivityManager.getNetworkInfo(network)!!.type
            var subnetworkInfo = networkInfo!!.subtype
            if (typeMobile == TYPE_MOBILE && subnetworkInfo == TelephonyManager.NETWORK_TYPE_UNKNOWN) {
                typeMobile = TYPE_MOBILE
            }
            return when (networkInfo.type) {
                TYPE_WIFI -> TYPE_WIFI
                typeMobile -> typeMobile
                else -> -1
            }
        }
        return -1


    }
}