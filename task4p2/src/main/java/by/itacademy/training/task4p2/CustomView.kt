package by.itacademy.training.task4p2

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class CustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val util = ColorUtil()

    private var centerX: Int = 0
    private var centerY: Int = 0
    private val rectF: RectF = RectF()
    private val paint: Paint
    private var listener: ColorTouchListener? = null
    private var chunk0Color: Int = 0
    private var chunk1Color: Int = 0
    private var chunk2Color: Int = 0
    private var chunk3Color: Int = 0
    private var centerColor: Int = 0

    init {
        initAttributes(attrs)
        isClickable = true
        paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL_AND_STROKE
        }
        setStartColors()
    }

    fun setColorTouchListener(touchListener: ColorTouchListener) {
        listener = touchListener
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        centerX = MeasureSpec.getSize(widthMeasureSpec) / 2
        centerY = MeasureSpec.getSize(heightMeasureSpec) / 2

        rectF.set(
            centerX - OUTER_RADIUS,
            centerY - OUTER_RADIUS,
            centerX + OUTER_RADIUS,
            centerY + OUTER_RADIUS
        )
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            paint.apply {
                color = chunk0Color
                canvas.drawArc(rectF, 0f, 90f, true, paint)
                color = chunk1Color
                canvas.drawArc(rectF, 90f, 90f, true, paint)
                color = chunk2Color
                canvas.drawArc(rectF, 180f, 90f, true, paint)
                color = chunk3Color
                canvas.drawArc(rectF, 270f, 90f, true, paint)
                color = centerColor
                canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), INNER_RADIUS, paint)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            checkTouchLocation(event.x, event.y)
        }
        return super.onTouchEvent(event)
    }

    private fun initAttributes(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView)
        centerColor = typedArray.getColor(R.styleable.CustomView_cmcv_centerColor, 0)
        typedArray.recycle()
    }

    private fun setStartColors() {
        chunk0Color = util.getRandomColor()
        chunk1Color = util.getRandomColor()
        chunk2Color = util.getRandomColor()
        chunk3Color = util.getRandomColor()
    }

    private fun checkTouchLocation(x: Float, y: Float) {
        if (util.ifPointInCircle(x, y, centerX.toFloat(), centerY.toFloat(), INNER_RADIUS)) {
            setStartColors()
            listener?.onTouchColorMixer(x, y, null)
            invalidate()
        } else if (util.ifPointInCircle(x, y, centerX.toFloat(), centerY.toFloat(), OUTER_RADIUS)) {
            setUpChunkColors(x, y)
        }
    }

    private fun setUpChunkColors(x: Float, y: Float) {
        var color: Int = 0
        val isAboveCenter = y < centerY
        val isLeftToCenter = x < centerX

        if (isAboveCenter && isLeftToCenter) {
            color = chunk2Color
            chunk2Color = util.getRandomColor()
        } else if (isAboveCenter && !isLeftToCenter) {
            color = chunk3Color
            chunk3Color = util.getRandomColor()
        } else if (!isAboveCenter && isLeftToCenter) {
            color = chunk1Color
            chunk1Color = util.getRandomColor()
        } else if (!isAboveCenter && !isLeftToCenter) {
            color = chunk0Color
            chunk0Color = util.getRandomColor()
        }
        listener?.onTouchColorMixer(x, y, color)
        invalidate()
    }

    companion object {
        const val OUTER_RADIUS = 400.0F
        const val INNER_RADIUS = 100.0F
    }
}
