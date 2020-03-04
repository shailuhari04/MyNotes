package com.sdrss.mynotes.network

import android.util.Log
import java.io.DataOutputStream
import java.net.HttpURLConnection
import java.net.URL


interface INetworkHelper {

    fun getConnection(
        endPoint: String,
        requestType: String,
        isDoOutput: Boolean = false,
        requestBodyParams: String = ""
    ): HttpURLConnection?

    suspend fun getUserListNetworkRequest(): String

    suspend fun postUserNetworkRequest(): String

    suspend fun putUserNetworkRequest(): String

    suspend fun deleteUserNetworkRequest(): String

    suspend fun postFileNetworkRequest(): String

}

object NetworkHelper : INetworkHelper {

    override fun getConnection(
        endPoint: String,
        requestType: String,
        isDoOutput: Boolean,
        requestBodyParams: String
    ): HttpURLConnection? {
        val baseUrl = "https://notesnodeapp.herokuapp.com/"

        return try {
            (URL("$baseUrl$endPoint").openConnection() as HttpURLConnection).apply {
                connectTimeout = 100000
                readTimeout = 100000
                requestMethod = requestType
                doOutput = isDoOutput
                if (requestType == POST) {
                    setRequestProperty(Content_Type_KEY, Content_Type_JSON)
                    connect()
                    val wr = DataOutputStream(outputStream)
                    wr.writeBytes(requestBodyParams)
                    wr.flush()
                    wr.close()
                }
            }
        } catch (e: Exception) {
            Log.d("Connection Error", e.toString())
            e.printStackTrace()
            null
        }
    }

    override suspend fun getUserListNetworkRequest(): String {
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

    override suspend fun postUserNetworkRequest(): String {
        var stringResponse = ""
        val connection = getConnection(USER_ENDPOINT, POST) //get the instance of HttpUrlConnection
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

    override suspend fun putUserNetworkRequest(): String {
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

    override suspend fun deleteUserNetworkRequest(): String {
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

    override suspend fun postFileNetworkRequest(): String {
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


    //request content type
    const val Content_Type_KEY = "Content-Type"
    const val Content_Type_JSON = "application/json"

    //endpoints
    const val USER_ENDPOINT = "users"
}