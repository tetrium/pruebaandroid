package mx.rodrigodiaz.triplei.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "store")
 class StoreData(
){

    @ColumnInfo(name = "Activo")
    var Activo: Int=0
    @ColumnInfo(name = "Cadena")
    var Cadena:String="Dummy"
    @ColumnInfo(name = "DeterminanteGSP")
    var DeterminanteGSP: Int=0
    @ColumnInfo(name = "Latitud")
    var Latitud: Float=0.0F
    @ColumnInfo(name = "Longitud")
    var Longitud: Float=0.0F
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "Sucursal")
    var Sucursal :String="Dummy"
}
