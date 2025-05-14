package com.example.myapplication

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

class HourGridLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val linePaint = Paint().apply {
        color = Color.LTGRAY
        strokeWidth = 2f
    }

    private val textPaint = Paint().apply {
        color = Color.DKGRAY
        textSize = 28f
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)

        val totalHours = 24
        val widthPerHour = width.toFloat() / totalHours

        for (i in 0..totalHours) {
            val x = i * widthPerHour

            // Dibuja la línea vertical
            canvas.drawLine(x, 0f, x, height.toFloat(), linePaint)

            // Etiqueta de la hora (opcional)
            if (i < totalHours) {
                val label = String.format("%02d:00", i)
                canvas.drawText(label, x + widthPerHour / 2, 20f, textPaint)
            }
        }
    }
}
