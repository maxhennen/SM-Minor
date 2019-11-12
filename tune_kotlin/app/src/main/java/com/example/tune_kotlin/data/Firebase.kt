package com.example.tune_kotlin.data

import android.content.Context
import android.os.Environment
import com.example.tune_kotlin.models.Genre
import com.example.tune_kotlin.models.Post
import com.example.tune_kotlin.utils.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.File
import durdinapps.rxfirebase2.DataSnapshotMapper
import durdinapps.rxfirebase2.RxFirebaseDatabase


class Firebase(private var applicationContext: Context?) {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val currentUser: FirebaseUser? = auth.currentUser
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val reference: DatabaseReference = database.reference
    private var disposable: Disposable? = null


    fun getGenres(): ArrayList<String> {
        val genres = ArrayList<String>()
        RxFirebaseDatabase.observeSingleValueEvent(
            reference.child("preferences/" + currentUser?.uid),
            DataSnapshotMapper.listOf<Genre>(Genre::class.java)
        )
            .subscribe { blogPost ->
                blogPost.map { genre -> genres.add(genre.name) }
            }
        return genres
    }

    fun getPosts(genre: String): ArrayList<Post> {
        val posts: ArrayList<Post> = ArrayList()

        RxFirebaseDatabase.observeSingleValueEvent(
            reference.child(genre),
            DataSnapshotMapper.mapOf<Post>(Post::class.java)
        )
            .subscribe{data ->
                data.map { post ->
                    println(post.value)
                    posts.add(post.value)
                }
            }
        return posts
    }
//    fun getPosts(genres: List<String>): List<Post> {
//        disposable?.dispose()
//        disposable = fetchPostsFromFirebase(genres)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .map { posts -> this.posts.addAll(posts) }
//            .doAfterSuccess(return posts)
//            .subscribe()
//    }
//
//    private fun fetchPostsFromFirebase(genres: List<String>): Single<List<Post>> {
//        val posts: ArrayList<Post> = ArrayList()
//        genres.map { genre ->
//            reference.child(genre).addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                    dataSnapshot.children.map { data ->
//                        val location = data.child("location").value.toString()
//                        val filename = data.child("filename").value.toString()
//                        val genre = data.child("genre").value.toString()
//                        val date = data.child("date").value.toString()
//                        val email = data.child("email").value.toString()
//                        val post = Post(location, email, filename, Genre.valueOf(genre), date)
//                        post.file = getData(filename)
//                        posts.add(post)
//                    }
//                }
//
//                override fun onCancelled(p0: DatabaseError) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//            })
//        }
//        return Single.just(posts)
//    }
//
//    private fun getData(filename: String): File {
//        val storage = FirebaseStorage.getInstance()
//        val reference = storage.reference.child(filename)
//        val rootPath = File(Environment.getDataDirectory(), "file_name")
//        val localFile = File(rootPath, "$filename.mp3")
//
//
//        reference.getFile(localFile).addOnSuccessListener {
//            Toast.toast(applicationContext, "Item is created at $localFile")
//        }
//        return localFile
//    }
//
//
//    fun getGenres(): ArrayList<String> {
//        disposable?.dispose()
//        disposable = fetchGenresFromFirebase()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .map { genre -> genres.addAll(genre) }
//            .doAfterSuccess(return genres)
//            .subscribe()
//    }
//
//    private fun fetchGenresFromFirebase(): Single<ArrayList<String>> {
//        val genreReference = reference.child("preferences/" + currentUser?.uid)
//
//        return Single.create { emitter ->
//            genreReference.addListenerForSingleValueEvent(object : ValueEventListener {
//                override fun onDataChange(dataSnapshot: DataSnapshot) {
//                    val genres = dataSnapshot.value as ArrayList<String>
//                    emitter.onSuccess(genres)
//                }
//
//                override fun onCancelled(p0: DatabaseError) {
//                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                }
//            })
//        }
//    }
//
//    private fun onGenresFetched(genres: ArrayList<String>) {
//
//    }
//
//    private fun onGenresFetchedFailed(throwable: Throwable) {}
//    //todo show error message
}