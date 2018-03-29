package cn.vi1zen.kotlinproject.model

import cn.vi1zen.kotlinproject.bean.DailesBean

/**
 * 文件名称：ICallback
 * 描   述：
 * 作   者：wz
 * 时   间：2018/03/28 10:49
 */


interface ICallback {
    fun onSuccess(dailesBean: DailesBean)
}
