package cn.vi1zen.kotlinproject.listener

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent

/**
 * 文件名称：MyOnItemTouchListener
 * 描   述：
 * 作   者：wz
 * 时   间：2018/03/16 14:12
 */


class MyOnItemTouchListener(private val context: Context, private val onItemClickListener: OnItemClickListener) : GestureDetector.SimpleOnGestureListener(), RecyclerView.OnItemTouchListener {
    private val gestureDetector: GestureDetector
    private var recyclerView: RecyclerView? = null

    init {
        gestureDetector = GestureDetector(context, this)
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        this.recyclerView = rv
        gestureDetector.onTouchEvent(e)
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }

    override fun onDown(e: MotionEvent): Boolean {
        return false
    }

    override fun onShowPress(e: MotionEvent) {

    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        val childView = recyclerView!!.findChildViewUnder(e.x, e.y)
        val position = recyclerView!!.getChildLayoutPosition(childView)
        Log.d("ITEM", "position = " + position)
        if (position != RecyclerView.NO_POSITION) {
            onItemClickListener.onItemClick(position)
        }
        return false
    }

    override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent) {
        val childView = recyclerView!!.findChildViewUnder(e.x, e.y)
        val position = recyclerView!!.getChildLayoutPosition(childView)
        Log.d("ITEM", "position = " + position)
        if (position != RecyclerView.NO_POSITION) {
            onItemClickListener.onItemLongClick(position)
        }
    }

    override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        return false
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onItemLongClick(position: Int)
    }
}
