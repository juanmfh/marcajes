package com.example.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class WorkDayBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // Representa un tramo de tiempo (por ejemplo, jornada laboral o descanso)
    data class TimeSlot(val startHour: Float, val endHour: Float)

    // Datos para pintar
    var workShift: TimeSlot? = null
    var breaks: List<TimeSlot> = emptyList()
    var events: List<Float> = emptyList() // Horas como 9.5f para 9:30 AM
    var sepHoras: Boolean = false

    // Pinturas
    private val paintBar = Paint().apply {
        color = Color.LTGRAY
        isAntiAlias = true
    }

    private val paintShift = Paint().apply {
        color = Color.parseColor("#4CAF50") // Verde
        isAntiAlias = true
    }

    private val paintBreak = Paint().apply {
        color = Color.parseColor("#FFEB3B") // Amarillo
        isAntiAlias = true
    }

    private val paintEvent = Paint().apply {
        color = Color.parseColor("#06402B") // Verde oscuro
        isAntiAlias = true
    }

    private val hourEvent = Paint().apply {
        color = Color.parseColor("#b4b2b2") // Verde oscuro
        isAntiAlias = true
    }

    private val paint = Paint().apply {
        color = Color.BLACK
        textSize = 25f
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val barTop = height * 0.3f
        val barHeight = height * 0.2f
        val barLeft = paddingLeft.toFloat()
        val barRight = width - paddingRight.toFloat()
        val barWidth = barRight - barLeft

        // Dibuja la barra base de 24 horas
        canvas.drawRect(barLeft, barTop, barRight, barTop + barHeight, paintBar)

        // Dibuja la jornada laboral (verde)
        workShift?.let {
            val left = barLeft + (it.startHour / 24f) * barWidth
            val right = barLeft + (it.endHour / 24f) * barWidth
            canvas.drawRect(left, barTop, right, barTop + barHeight, paintShift)
        }

        // Dibuja los descansos (amarillo)
        breaks.forEach {
            val left = barLeft + (it.startHour / 24f) * barWidth
            val right = barLeft + (it.endHour / 24f) * barWidth
            canvas.drawRect(left, barTop, right, barTop + barHeight, paintBreak)
        }

        // Dibuja los eventos (rectángulos verticales rojos)
        events.forEach { hour ->
            val x = barLeft + (hour / 24f) * barWidth
            val top = barTop - 20
            val bottom = barTop + barHeight + 20
            canvas.drawRect(x - 5, top, x + 5, bottom, paintEvent)
        }

        if (sepHoras)
            for (i in 0..24) {
                val x = barLeft + (i / 24f) * barWidth
                val top = barTop
                val bottom = barTop + barHeight
                canvas.drawRect(x - 5, top, x + 5, bottom, hourEvent)
                canvas.drawText(i.toString() + ":00", x + 5 , barTop + 25, paint  )
            }
    }
}
