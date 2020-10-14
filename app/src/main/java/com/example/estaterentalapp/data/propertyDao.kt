package com.example.estaterentalapp.data
import androidx.room.*

@Dao
interface propertyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(property: property)
    @Query("Select * from property where id = :id")
    suspend fun findEventsByDeptID(id: String): List<property>
    @Query("Select * from property where occupied = 1")
    suspend fun findAllBookmarkedEvents(): List<property>
    @Delete
    suspend fun delete(vararg property: property)
    @Update
    suspend fun update(property: property)
}