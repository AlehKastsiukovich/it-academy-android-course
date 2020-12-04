package by.itacademy.training.task4p2

import android.graphics.Color
import java.util.Random

class Util {

    fun getRandomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

    fun checkIfPointInCircle(
        xPoint: Float,
        yPoint: Float,
        xCenter: Float,
        yCenter: Float,
        radius: Float
    ) = radius > kotlin.math.sqrt((xPoint - xCenter) * (xPoint - xCenter) + (yPoint - yCenter) * (yPoint - yCenter))
}
