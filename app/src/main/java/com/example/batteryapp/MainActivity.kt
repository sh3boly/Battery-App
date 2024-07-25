package com.example.batteryapp

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.batteryapp.ui.theme.BatteryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BatteryAppTheme {
                BatteryScreen()
            }
        }
    }
}
@Composable
fun BatteryScreen(modifier: Modifier = Modifier){
    val context = LocalContext.current
    val intentFilter = IntentFilter().apply {
        addAction(Intent.ACTION_BATTERY_OKAY)
        addAction(Intent.ACTION_BATTERY_LOW)
    }
    var batteryImage by rememberSaveable{
        mutableStateOf(R.drawable.battery_full)
    }
    var batteryImageDescription by rememberSaveable{
        mutableStateOf("Battery full image")
    }
    val batteryReceiver = BatteryReceiver(){
        if(it){
            batteryImage = R.drawable.battery_full
            batteryImageDescription = "Battery full image"
        }else{
            batteryImage = R.drawable.battery_low
            batteryImageDescription = "Battery low image"
        }
    }
    context.registerReceiver(batteryReceiver, intentFilter)

    Box (
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ){
        Image(painter = painterResource(id = batteryImage), contentDescription = batteryImageDescription)
    }

}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BatteryAppTheme {
        Greeting("Android")
    }
}