package by.itacademy.training.task4p2

import android.graphics.Color
import java.util.Random

class ColorUtil {

    fun getRandomColor(): Int {
        val random = Random()
        return Color.argb(
            255,
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        )
    }

    fun ifPointInCircle(
        xPoint: Float,
        yPoint: Float,
        xCenter: Float,
        yCenter: Float,
        radius: Float
    ) = radius > kotlin.math.sqrt(
        (xPoint - xCenter) * (xPoint - xCenter) + (yPoint - yCenter) * (yPoint - yCenter)
    )
}
