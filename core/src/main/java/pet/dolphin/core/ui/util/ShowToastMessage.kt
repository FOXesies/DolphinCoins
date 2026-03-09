package pet.dolphin.core.ui.util

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

fun showToastMessage(context: Context, message: String){
    //REWRITE (TEMPORARY REALIZATION)
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}