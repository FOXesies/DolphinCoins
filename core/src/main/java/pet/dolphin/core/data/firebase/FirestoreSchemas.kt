package pet.dolphin.core.data.firebase

import pet.dolphin.core.data.firebase.FirebaseConstants.firebaseDatabase

object FirestoreSchemas {

    private const val USERS = "users"

    val users get() = firebaseDatabase
        .reference
        .child(USERS)

}