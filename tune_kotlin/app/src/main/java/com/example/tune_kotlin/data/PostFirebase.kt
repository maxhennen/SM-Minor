package com.example.tune_kotlin.data

import com.example.tune_kotlin.models.Post
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import org.greenrobot.eventbus.EventBus


class PostFirebase(genres: ArrayList<String>?): Firebase() {

    private val genres: ArrayList<String>? = genres

    fun getPosts() {
        genres?.map { genre ->
            reference.child(genre).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.value != null) {
                        snapshot.children.map { postData ->
                            val post = postData.getValue(Post::class.java)!!
                            EventBus.getDefault().post(post)
                        }
                    }
                }
            })
        }
    }

    fun updatePost(post: Post): Boolean{
        return reference.child(post.filename.toString()).setValue(post).isSuccessful
    }
}