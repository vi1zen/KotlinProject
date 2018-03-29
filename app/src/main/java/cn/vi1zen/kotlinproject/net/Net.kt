package cn.vi1zen.kotlinproject.net

import com.google.gson.Gson
import com.google.gson.GsonBuilder

import org.reactivestreams.Subscriber

import cn.vi1zen.kotlinproject.api.GankApi
import cn.vi1zen.kotlinproject.bean.DailesBean
import cn.vi1zen.kotlinproject.bean.DailyBean
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 文件名称：Net
 * 描   述：
 * 作   者：wz
 * 时   间：2018/03/15 13:48
 */


class Net private constructor() {
    private val gankApi: GankApi

    init {
        val client = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://gank.io/api/")//必须以斜杆结尾，否则报错
                .build()
        gankApi = retrofit.create(GankApi::class.java)
    }


    fun getGankDailyInfo(consumer: Consumer<DailesBean>, month: Int, day: Int) {
        val observable = gankApi.getGankDailyInfo(month, day)
        toSubscribe(observable, consumer)
    }

    private fun toSubscribe(observable: Observable<DailesBean>, consumer: Consumer<DailesBean>) {
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribe(consumer)
    }

    companion object {

        val instance = Net()
    }

}
