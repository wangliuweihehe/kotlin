package com.hjm.admin.mykotlin.views.databasebusiness

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by admin on 2017/10/26.
 */
@Database(entities = arrayOf(WantSeeEntity::class, HaveSeenEntity::class), version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun WantSeeDao(): WantSeeDao
    abstract fun HaveSeenDao(): HaveSeenDao
}

class MovieRoomOperate private constructor(ctx: Context) {
    val appDataBase = Room.databaseBuilder(ctx, AppDataBase::class.java, "timemovieinkt").build()!!

    companion object {
        lateinit var mContext: Context
        val objectInstance: MovieRoomOperate by lazy {
            MovieRoomOperate(mContext)
        }
        fun newInstance(ctx: Context):MovieRoomOperate{
            mContext=ctx.applicationContext
            return objectInstance
        }
    }
    //插入一条数据
    fun insertOneMovieWantSee(movieId:Int,movieName:String,movieCoverUrl:String)= appDataBase.WantSeeDao().insertWantSee(WantSeeEntity(movieId=movieId,movieName = movieName,movieCoverUrl = movieCoverUrl))
    //查询所有数据
    fun queryAllMovieWantSee()=appDataBase.WantSeeDao().queryAllWantSeeData()
    //删除一条数据
    fun deleteOneMovieWantSee(movieId: Int){
        val id=appDataBase.WantSeeDao().queryOneWantSeeData(movieId).id//取得主键才能删除
        appDataBase.WantSeeDao().deleteOneWantSee(WantSeeEntity(id=id))
    }
    fun insertOneHaveSeen(movieId: Int,movieName: String,movieCoverUrl: String)=
            appDataBase.HaveSeenDao().insertHaveSeen(HaveSeenEntity(movieId =movieId,movieName = movieName,movieCoverUrl = movieCoverUrl ))

    fun queryAllHaveSeen()=appDataBase.HaveSeenDao().queryAllHaveSeenData()


    fun deleteOneHaveSeen(movieId: Int){
        val primaryId=appDataBase.HaveSeenDao().queryOneHaveSeenData(movieId).id
        appDataBase.HaveSeenDao().ddeleteOneHaveSeen(HaveSeenEntity(primaryId))
    }
}

