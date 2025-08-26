package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setFavoriteFlight(favorite: Favorite)

    @Query(
        """
        SELECT * from favorite ORDER BY destination_code ASC
    """
    )
    fun getAllFavorites(): Flow<List<Favorite>>
}