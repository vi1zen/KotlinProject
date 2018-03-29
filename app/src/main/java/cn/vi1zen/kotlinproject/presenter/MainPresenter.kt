package cn.vi1zen.kotlinproject.presenter

import android.util.Log
import cn.vi1zen.kotlinproject.adapter.Adapter
import cn.vi1zen.kotlinproject.bean.DailesBean
import cn.vi1zen.kotlinproject.model.ICallback
import cn.vi1zen.kotlinproject.model.MainModel
import cn.vi1zen.kotlinproject.net.Net
import cn.vi1zen.kotlinproject.ui.MainActivity
import cn.vi1zen.kotlinproject.view.IMainView
import io.reactivex.functions.Consumer

/**
 * 文件名称：MainPresenter
 * 描   述：
 * 作   者：wz
 * 时   间：2018/03/28 10:18
 */


class MainPresenter(private val mainView: IMainView, private val mainModel: MainModel) : ICallback{

    fun getListDatas(month: Int, day: Int) {
        mainModel.getListDatas(month,day,this)
    }

    override fun onSuccess(dailesBean: DailesBean) {
        mainView.showList(dailesBean)
    }
}
