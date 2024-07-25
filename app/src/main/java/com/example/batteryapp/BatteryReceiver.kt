package com.example.batteryapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class BatteryReceiver(val batteryCheck: (Boolean) -> Unit): BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent!!.action){
            Intent.ACTION_BATTERY_OKAY -> batteryCheck(true)
            else -> batteryCheck(false)
        }
    }
}