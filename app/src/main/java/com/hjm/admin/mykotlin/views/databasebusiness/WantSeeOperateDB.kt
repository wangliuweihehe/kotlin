package com.hjm.admin.mykotlin.views.databasebusiness

import android.arch.persistence.room.*

/**
 * Created by admin on 2017/10/26.
 */
@Entity(tableName = "WantSee")
data class WantSeeEntity(@PrimaryKey(autoGenerate = true) var id: Long? = null, var movieId: Int = 0, var movieName: String = "", var movieCoverUrl: String = "") {
    constructor() : this(0)
}

@Dao
interface WantSeeDao {
    @Insert
    fun insertWantSee(entity: WantSeeEntity)

    @Query("SELECT * FROM WantSee")
    fun queryAllWantSeeData(): List<WantSeeEntity>

    @Query("SELECT * FROM WantSee where movieId= :arg0")
    fun queryOneWantSeeData(movieId: Int): WantSeeEntity

    @Delete
    fun deleteOneWantSee(entity: WantSeeEntity)
}