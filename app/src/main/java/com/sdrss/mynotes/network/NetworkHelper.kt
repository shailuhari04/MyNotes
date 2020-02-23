package com.sdrss.mynotes.network

import android.util.Log
import java.net.HttpURLConnection
import java.net.URL

interface INetworkHelper {

    fun getConnection(
        endPoint: String,
        requestType: String,
        isDoOutput: Boolean = false
    ): HttpURLConnection?

    suspend fun getNetworkRequest(): String

}

object NetworkHelper : INetworkHelper {

    override fun getConnection(
        endPoint: String,
        requestType: String,
        isDoOutput: Boolean
    ): HttpURLConnection? {
        val baseUrl = "http://192.168.1.109:3001/"

        return try {
            (URL("$baseUrl$endPoint").openConnection() as HttpURLConnection).apply {
                connectTimeout = 1000000
                readTimeout = 1000000
                requestMethod = requestType
                doOutput = isDoOutput
            }
        } catch (e: Exception) {
            Log.d("Connection Error", e.toString())
            e.printStackTrace()
            null
        }
    }

    override suspend fun getNetworkRequest(): String {
        var stringResponse = ""
        val connection = getConnection(USER_ENDPOINT, GET) //get the instance of HttpUrlConnection
        try {
            val response =
                connection?.responseCode //get requestResponse Code and compare with RequestStatus HTTP_OK (200)
            if (response == HttpURLConnection.HTTP_OK) {
                stringResponse = connection.inputStream.bufferedReader().readText()
            }
        } catch (e: Exception) {
            Log.d("Response Error", e.toString())
            e.printStackTrace()
            return e.toString()
        } finally {
            connection?.disconnect() //close the connection
        }

        return stringResponse
    }

    //request types
    const val GET = "GET"
    const val POST = "POST"
    const val PUT = "PUT"
    const val DELETE = "DELETE"

    //endpoints
    const val USER_ENDPOINT = "users"
}