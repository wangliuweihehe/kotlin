package com.hjm.admin.mykotlin.views.databasebusiness

import android.arch.persistence.room.*

/**
 * Created by admin on 2017/10/26.
 */
@Entity(tableName = "HaveSeen")
data class HaveSeenEntity(@PrimaryKey(autoGenerate = true) var id: Long? = null, var movieId: Int = 0, var movieName: String = "", var movieCoverUrl: String = "") {
    constructor() : this(0)
}

@Dao
interface HaveSeenDao {
    @Insert
    fun insertHaveSeen(entity: HaveSeenEntity)

    @Query("SELECT * FROM HaveSeen")
    fun queryAllHaveSeenData(): List<HaveSeenEntity>

    @Query("SELECT * FROM HaveSeen where movieId =:arg0")
    fun queryOneHaveSeenData(movieId: Int): HaveSeenEntity

    @Delete
    fun ddeleteOneHaveSeen(entity: HaveSeenEntity)
}