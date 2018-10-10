package com.hjm.admin.mykotlin.views.networkbusiness

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by admin on 2017/11/3.
 */
data class NewsArray(val newsList: List<NewsItem>)

data class NewsItem(val id: Int?, val image: String?, val publishTime: Long?, val title: String?, val title2: String?, val images: List<ImageItem>)
data class ImageItem(val url1: String?)

//trailers
data class TrailerItem(val movieName: String?, val coverImg: String?, val hightUrl: String?, val videoTitle: String?, val summary: String?)

data class TrailersDataArray(val trailers: List<TrailerItem>)

//news - banner
data class AdvertiseItem(val img: String?, val url: String?)

data class AdvertiseData(val topPosters: MutableList<AdvertiseItem>)

//review
data class ReviewItemRelated(val id: Int?, val image: String?, val title: String?, val year: Int?)

data class ReviewItem(val id: Int?, val nickname: String?, val rating: Double?, val summary: String?, val title: String?, val userImage: String?, val relatedObj: ReviewItemRelated?)


interface IFindFunnyService {
    @GET("News/NewsList.api?")
    fun requestFunnyNewsList(@Query("pageIndex") pageIndex: Int): Observable<NewsArray>


    @GET("PageSubArea/TrailerList.api")
    fun requestFunnyTrailerList(): Observable<TrailersDataArray>

    @GET("MobileMovie/Review.api?")
    fun requestFunnyReview(@Query("needTop") needTop: Boolean = false): Observable<MutableList<ReviewItem>>

    @GET("PageSubArea/GetFirstPageAdvAndNews.api")
    fun requestFunnyAdvertise(): Observable<AdvertiseData>
}