package mx.rodrigodiaz.triplei.sqllite

import androidx.room.*
import mx.rodrigodiaz.triplei.entities.StoreData


@Dao
interface StoreDAO {
    @Insert
    fun insertStore(cardData: StoreData)

    @Query("SELECT * FROM store " )
    fun getStores(): List<StoreData>

    @Query("SELECT * FROM store WHERE Sucursal =:Sucursal")
    fun getStoreById(Sucursal: String): StoreData?


    @Delete
    fun deleteCard(store: StoreData)

    @Update
    fun updateCard(store: StoreData)
}