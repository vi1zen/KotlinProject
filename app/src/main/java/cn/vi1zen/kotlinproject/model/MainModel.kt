package cn.vi1zen.kotlinproject.model

import android.util.Log
import android.widget.Toast

import java.util.ArrayList

import cn.vi1zen.kotlinproject.bean.DailesBean
import cn.vi1zen.kotlinproject.bean.DailyBean
import cn.vi1zen.kotlinproject.net.Net
import cn.vi1zen.kotlinproject.presenter.MainPresenter
import cn.vi1zen.kotlinproject.ui.MainActivity
import io.reactivex.functions.Consumer

/**
 * 文件名称：MainModel
 * 描   述：
 * 作   者：wz
 * 时   间：2018/03/28 10:23
 */


class MainModel {

    fun getListDatas(month: Int, day: Int, callback: ICallback) {
        val consumer = Consumer<DailesBean> { dailesBean ->
            for (dailyBean in dailesBean.results!!) {
                Log.d("MainModel", if (dailyBean.who == null) "null" else dailyBean.who)
                Log.d("MainModel", if (dailyBean.images == null) "null" else dailyBean.images!![0])
            }
            callback.onSuccess(dailesBean)
        }
        Net.instance.getGankDailyInfo(consumer, month, day)
    }
}
