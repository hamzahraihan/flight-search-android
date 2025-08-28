package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.flightsearch.model.Airport
import com.example.flightsearch.model.Favorite
import com.example.flightsearch.model.FavoriteWithAirports
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Transaction
    @Query(
        """
        SELECT * FROM favorite
    """
    )
    fun getAllFavoriteWithAirports(): Flow<List<FavoriteWithAirports>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setFavoriteFlight(favorite: Favorite)


    // delete favorite flight by departure code and destination code using query instead of delete annotation
    @Query(
        """
        DELETE FROM favorite WHERE departure_code = :departureCode AND destination_code = :destinationCode
    """
    )
    suspend fun deleteFavorite(departureCode: String, destinationCode: String)

    @Query(
        """
        SELECT * from favorite ORDER BY destination_code ASC
    """
    )
    fun getAllFavorites(): Flow<List<Favorite>>

    @Query(
        """
        SELECT COUNT(*) FROM favorite WHERE departure_code = :departureCode AND destination_code = :destinationCode
    """
    )
    suspend fun exists(departureCode: String, destinationCode: String): Int
}