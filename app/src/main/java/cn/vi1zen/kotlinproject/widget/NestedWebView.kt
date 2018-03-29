package cn.vi1zen.kotlinproject.widget

import android.content.Context
import android.support.v4.view.NestedScrollingChild
import android.support.v4.view.NestedScrollingChildHelper
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.webkit.WebView

/**
 * 文件名称：NestedWebView
 * 描   述：支持嵌套滑动的webView
 * 作   者：wz
 * 时   间：2018/03/28 11:28
 */


class NestedWebView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : WebView(context, attrs, defStyleAttr), NestedScrollingChild {

    private val mNestedScrollingChildHelper: NestedScrollingChildHelper

    internal var x: Float = 0.toFloat()
    internal var y: Float = 0.toFloat()
    internal var downY: Float = 0.toFloat()
    internal var lastX: Float = 0.toFloat()
    internal var lastY: Float = 0.toFloat()
    internal var consumed = IntArray(2)
    internal var offsetInWindow = IntArray(2)

    init {
        mNestedScrollingChildHelper = NestedScrollingChildHelper(this)
        isNestedScrollingEnabled = true
    }

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        mNestedScrollingChildHelper.isNestedScrollingEnabled = enabled
    }

    override fun isNestedScrollingEnabled(): Boolean {
        return mNestedScrollingChildHelper.isNestedScrollingEnabled
    }

    override fun startNestedScroll(axes: Int): Boolean {
        return mNestedScrollingChildHelper.startNestedScroll(axes)
    }

    override fun stopNestedScroll() {
        mNestedScrollingChildHelper.stopNestedScroll()
    }

    override fun hasNestedScrollingParent(): Boolean {
        return mNestedScrollingChildHelper.hasNestedScrollingParent()
    }

    override fun dispatchNestedScroll(dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, offsetInWindow: IntArray?): Boolean {
        return mNestedScrollingChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow)
    }

    override fun dispatchNestedPreScroll(dx: Int, dy: Int, consumed: IntArray?, offsetInWindow: IntArray?): Boolean {
        return mNestedScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
    }

    override fun dispatchNestedFling(velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        return mNestedScrollingChildHelper.dispatchNestedFling(velocityX, velocityY, consumed)
    }

    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
        return mNestedScrollingChildHelper.dispatchNestedPreFling(velocityX, velocityY)
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        onWebViewScrollChangeListener?.onWebViewScrollChanged(l, t, oldl, oldt)
    }

    /**
     * 暴露对外的滑动监听
     */
    interface OnWebViewScrollChangeListener{
        fun onWebViewScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int);
    }

    private var onWebViewScrollChangeListener: OnWebViewScrollChangeListener? = null

    fun setOnWebViewScrollChangeListener(onWebViewScrollChangeListener: OnWebViewScrollChangeListener){
        this.onWebViewScrollChangeListener = onWebViewScrollChangeListener
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val y = event.y
        val x = event.x
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                downY = y
                startNestedScroll(ViewCompat.SCROLL_AXIS_NONE or ViewCompat.SCROLL_AXIS_VERTICAL)
            }
            MotionEvent.ACTION_MOVE -> {
                var dx = lastX - x
                var dy = lastY - y
                if (dispatchNestedPreScroll(dx.toInt(), dy.toInt(), consumed, offsetInWindow)) {
                    dx -= consumed[0].toFloat()
                    dy -= consumed[1].toFloat()
                    Log.d(TAG, "onTouchEvent: dy = $dy")
                }
                scrollBy(dx.toInt(), dy.toInt())
            }
            MotionEvent.ACTION_UP -> stopNestedScroll()
        }
        lastX = x
        lastY = y
        return super.onTouchEvent(event)
    }

    companion object {

        private val TAG = "NestedWebView"
    }
}
