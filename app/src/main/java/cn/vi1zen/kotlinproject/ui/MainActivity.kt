package cn.vi1zen.kotlinproject.ui

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import java.util.ArrayList
import java.util.Calendar

import cn.vi1zen.kotlinproject.R
import cn.vi1zen.kotlinproject.adapter.Adapter
import cn.vi1zen.kotlinproject.bean.DailesBean
import cn.vi1zen.kotlinproject.bean.DailyBean
import cn.vi1zen.kotlinproject.constant.Constant
import cn.vi1zen.kotlinproject.model.MainModel
import cn.vi1zen.kotlinproject.presenter.MainPresenter
import cn.vi1zen.kotlinproject.listener.MyOnItemTouchListener
import cn.vi1zen.kotlinproject.view.IMainView

/**
 * 文件名称：MainActivity
 * 描   述：
 * 作   者：wz
 * 时   间：2018/03/15 17:15
 */


class MainActivity : Activity(), IMainView {

    private val items = ArrayList<DailyBean>()
    private var adapter: Adapter? = null
    private var mainPresenter: MainPresenter? = null
    private var calendar: Calendar = Calendar.getInstance()
    val year = calendar!!.get(Calendar.YEAR)
    val month = calendar!!.get(Calendar.MONTH) + 1
    val day = calendar!!.get(Calendar.DAY_OF_MONTH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

        mainPresenter = MainPresenter(this, MainModel())
        mainPresenter!!.getListDatas(month,day)
//        mainPresenter!!.getListDatas(10, 1)
    }

    private fun initViews() {
        val toolbar = findViewById<Toolbar>(R.id.toolBar)
        toolbar.setTitleTextColor(Color.WHITE)
        toolbar.title = "Android干货每日推送（$year.$month.$day）"
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = Adapter(this, items)
        recyclerView.adapter = adapter
        recyclerView.addOnItemTouchListener(MyOnItemTouchListener(this, object : MyOnItemTouchListener.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, DailyDetailActivity::class.java)
                intent.putExtra(Constant.URL, items[position].url)
                intent.putExtra(Constant.TITLE, items[position].desc)
                startActivity(intent)
            }

            override fun onItemLongClick(position: Int) {

            }
        }))
    }

    override fun showList(dailesBean: DailesBean) {
        this.items.clear()
        items.addAll(dailesBean.results!!)
        adapter!!.notifyDataSetChanged()
    }

    companion object {
        private val TAG = "MainActivity"
    }
}
