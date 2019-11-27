package com.example.tune_kotlin.data

import com.example.tune_kotlin.models.Post
import com.google.firebase.storage.FirebaseStorage
import org.greenrobot.eventbus.EventBus

class StorageFirebase {
    fun getPostFile(post: Post){
        val reference = FirebaseStorage.getInstance().reference
        val child: String = post.filename!!
        val MAX_BYTES: Long = 1000000

        reference.child(child).getBytes(MAX_BYTES).addOnSuccessListener { task ->
            val byteArray: ByteArray = task
            EventBus.getDefault().post(byteArray)
        }
            .addOnFailureListener{error ->
                println(error.message)
            }
    }
}