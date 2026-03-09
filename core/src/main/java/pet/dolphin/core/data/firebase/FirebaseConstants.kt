package pet.dolphin.core.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

object FirebaseConstants {

    val firebaseAuth get() = FirebaseAuth.getInstance()

    val currentUser get() = FirebaseAuth.getInstance().currentUser

    val firebaseDatabase: FirebaseDatabase
        get() = FirebaseDatabase.getInstance()

}