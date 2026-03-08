package pet.dolphin.auth.data.networking

import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import pet.dolphin.auth.data.networking.dto.UserDto
import pet.dolphin.core.data.firebase.FirebaseConstants
import pet.dolphin.core.data.firebase.FirebaseConstants.firebaseDatabase
import pet.dolphin.core.data.firebase.FirestoreSchemas
import pet.dolphin.core.data.networking.safeFirebaseCall
import pet.dolphin.core.data.networking.safeNetworkCall
import pet.dolphin.core.domain.util.ErrorDomain
import pet.dolphin.core.domain.util.Result
import pet.dolphin.core.domain.util.onSuccess

class AuthRemoteSource(
) {
    suspend fun signIn(email: String, password: String): Result<FirebaseUser?, ErrorDomain> {
        return safeFirebaseCall {
            FirebaseConstants.firebaseAuth.signInWithEmailAndPassword(
                email,
                password
            ).await().user
        }
        .onSuccess {
            if(it != null) completeLoginUser(it.uid)
        }
    }

    suspend fun signUp(login: String, email: String, password: String): Result<FirebaseUser?, ErrorDomain> {
        return safeFirebaseCall {
            FirebaseConstants.firebaseAuth.createUserWithEmailAndPassword(
                email,
                password
            ).await().user
        }
        .onSuccess {
            if(it != null) completeRegisterUser(
                UserDto(
                    uid = it.uid,
                    email = email,
                    login = login
                )
            )
        }
    }

    private suspend fun completeRegisterUser(user: UserDto): Result<Any, ErrorDomain> {
        return safeFirebaseCall {
            FirestoreSchemas.users
                .child(user.uid)
                .setValue(user)
                .await()
        }
    }

    private suspend fun completeLoginUser(uid: String): Result<Any, ErrorDomain> {
        return safeFirebaseCall {
            FirestoreSchemas.users
                .child(uid)
                .setValue(Timestamp.now().toString())
                .await()
        }
    }
}