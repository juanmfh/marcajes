package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val workBar = findViewById<WorkDayBarView>(R.id.workBar)

        workBar.workShift = WorkDayBarView.TimeSlot(9f, 18f) // 9:00 a 17:00
        workBar.breaks = listOf(
            WorkDayBarView.TimeSlot(10f, 10.5f),    // 12:00 a 12:30
            WorkDayBarView.TimeSlot(14.5f, 15.5f)    // 15:00 a 15:15
        )
        workBar.events = listOf(9f, 10f, 10.5f, 14.5f, 15.5f, 18f) // Entradas/salidas

        workBar.invalidate()

    }
}