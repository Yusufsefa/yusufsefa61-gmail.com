package com.yyusufsefa.myapplication.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yyusufsefa.myapplication.model.Articles

@Dao
interface ProjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(list: List<Articles>?)

    @Query("SELECT * FROM articles_table")
    fun getAllArticles(): LiveData<List<Articles>?>

}