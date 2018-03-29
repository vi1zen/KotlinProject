package cn.vi1zen.kotlinproject.api

import cn.vi1zen.kotlinproject.bean.DailesBean
import cn.vi1zen.kotlinproject.bean.DailyBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * 文件名称：GankApi
 * 描   述：
 * 作   者：wz
 * 时   间：2018/03/15 13:41
 */


interface GankApi {


    @GET("data/Android/{month}/{day}")
    fun getGankDailyInfo(@Path("month") month: Int, @Path("day") day: Int): Observable<DailesBean>
}
