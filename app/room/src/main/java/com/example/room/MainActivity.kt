package com.example.room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.example.room.basico.data.UserDatabase
import com.example.room.basico.data.UserRepository
import com.example.room.ui.theme.ContenidosExtraTheme
import com.example.room.basico.screen.MainScreen
import com.example.room.basico.screen.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //llamada a todo lo que necesita ejemplo basico
        val db= Room.databaseBuilder(this, UserDatabase::class.java,"user_db").build()//llamada a la BD
        val dao=db.dao//UserDAO para las operaciones BD
        val repository= UserRepository(dao)//para acceso a Repository y el mapeo de la BD al objeto del UI

        val viewModel =
            MainViewModel(repository)//se debería usar un Factory en caso de App real con diversos ViewModel
//        val viewModel by viewModels<MainViewModel>()
        setContent {
            ContenidosExtraTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(viewModel)
                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ContenidosExtraTheme {
//        Greeting("Android")
//    }
//}