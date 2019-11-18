package com.example.tune_kotlin.data

import android.content.Context
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.tune_kotlin.models.Comment
import com.example.tune_kotlin.models.Genre
import com.example.tune_kotlin.models.Post
import com.google.firebase.messaging.FirebaseMessaging
import org.json.JSONException
import org.json.JSONObject

class FirebaseNotification: Firebase() {

    private val serverKey = "key=" + "AAAAwve0gps:APA91bHcx96Mz4TxNx5pPa4PToVLsEkq_kRlSEQWsSlk5f52A6ZSmLsw_ucqXmMH-DMAvSZH3A6IZooRRCDhPeb0qs6dF6BYIVDuhuNwq6G9_BpNZWMHB4UwO4h58CTB3y_2d9h-pQCM"
    private val FCM_API = "https://fcm.googleapis.com/fcm/send"

    fun init(genre: String) {
        FirebaseMessaging.getInstance().subscribeToTopic("/topics/$genre")
    }

    fun send(post: Post, comment: Comment, application: Context){
        val topic = post.genre?.name

        val notification = JSONObject()
        val notificationBody = JSONObject()
        val message = comment.user + " has commented on your post!"

        try{
            notificationBody.put("title", "tune message")
            notificationBody.put("message", message)
            notification.put("to", "/topics/$topic")
            notification.put("data", notificationBody)
        } catch (e: JSONException){
            e.printStackTrace()
        }
        sendNotification(notification, application)
    }

    fun sendNotification(notification: JSONObject, application: Context){
        val jsonObjectRequest = object: JsonObjectRequest(FCM_API, notification,
            Response.Listener<JSONObject> { response ->
                println(response)
            },Response.ErrorListener {
                println("Request Error")
            }){
            override fun getHeaders(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["Authorization"] = serverKey
                params["Content-Type"] = "application/json"
                return params
            }
        }
        Volley.newRequestQueue(application).add(jsonObjectRequest)
    }
}