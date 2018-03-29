package cn.vi1zen.kotlinproject.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.util.AttributeSet
import android.util.Log
import android.view.View

import cn.vi1zen.kotlinproject.widget.NestedWebView

/**
 * 文件名称：FABBehavior
 * 描   述：FABBehavior
 * 作   者：wz
 * 时   间：2018/03/29 10:55
 */

class FABBehavior : CoordinatorLayout.Behavior<FloatingActionButton> {

    constructor() {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: FloatingActionButton?, dependency: View?): Boolean {
        return dependency is NestedWebView
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: FloatingActionButton?, dependency: View?): Boolean {
        if (dependency is NestedWebView) {
            dependency.setOnWebViewScrollChangeListener(object : NestedWebView.OnWebViewScrollChangeListener {
                override fun onWebViewScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
                    if(dependency.canScrollVertically(-1)){
                        if (oldt >= t) {
                            child!!.show()
                        } else {
                            child!!.hide()
                        }
                    }else{
                        child!!.show()
                    }
                }
            })
        }
        return true
    }
}
