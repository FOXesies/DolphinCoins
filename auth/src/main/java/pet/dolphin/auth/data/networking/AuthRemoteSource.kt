package pet.dolphin.auth.data.networking

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await
import pet.dolphin.auth.data.networking.dto.UserDto
import pet.dolphin.core.data.firebase.CustomFirebaseException
import pet.dolphin.core.data.firebase.FirebaseConstants
import pet.dolphin.core.data.firebase.FirebaseDatabaseSchemas
import pet.dolphin.core.data.networking.safeFirebaseCall
import pet.dolphin.core.domain.util.AuthError
import pet.dolphin.core.domain.util.ErrorDomain
import pet.dolphin.core.domain.util.Result
import pet.dolphin.core.domain.util.onSuccess

class AuthRemoteSource(
) {
    suspend fun signIn(email: String, password: String): Result<Unit, ErrorDomain> {
        return safeFirebaseCall {
            // Take user from auth firebase
            val user = FirebaseConstants.firebaseAuth.signInWithEmailAndPassword(
                email,
                password
            ).await().user

            // if Success -> write in Database RT
            if(user != null) completeLoginUser(user.uid)

            Unit
        }
    }

    suspend fun signUp(login: String, email: String, password: String): Result<Unit, ErrorDomain> {
        return safeFirebaseCall {
            // Check unique login
            checkUniqueLogin(newLogin = login)

            // Add to auth firebase
            val user = FirebaseConstants.firebaseAuth.createUserWithEmailAndPassword(
                email,
                password
            ).await().user

            // if Success -> write in Database RT
            if(user != null) {
                completeRegisterUser(
                    UserDto(
                        uid = user.uid,
                        email = email,
                        login = login
                    )
                )
            }

            Unit
        }
    }

    // Adding user to Firebase RT
    private suspend fun completeRegisterUser(user: UserDto): Result<Void, ErrorDomain> {
        return safeFirebaseCall {
            FirebaseDatabaseSchemas.users
                .child(user.uid)
                .setValue(user)
                .await()
        }
    }

    // Update last time login user in Firebase RT
    private suspend fun completeLoginUser(uid: String): Result<Any, ErrorDomain> {
        return safeFirebaseCall {
            FirebaseDatabaseSchemas.users
                .child(uid)
                .setValue(Timestamp.now().toString())
                .await()
        }
    }

    private suspend fun checkUniqueLogin(newLogin: String) {
        // Get order which have login == newLogin
        val hasNewLogin = FirebaseDatabaseSchemas.users
            .orderByChild("login")
            .equalTo(newLogin)
            .get()
            .await()

        if (hasNewLogin.exists()) {
            /**
             * If login exist trow custom exception for [safeFirebaseCall].
             */
            throw CustomFirebaseException.LoginNoUniqueException()
        }
    }
}