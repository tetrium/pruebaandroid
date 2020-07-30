package mx.rodrigodiaz.triplei.models

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.rodrigodiaz.triplei.entities.StoreData
import mx.rodrigodiaz.triplei.sqllite.SqlLiteHelper
import java.util.*


class StoreModel(var context: Context) {




    fun saveStores( storeList:List<StoreData>,listener: () -> Unit){
        GlobalScope.launch(Dispatchers.IO) {

            var dao=SqlLiteHelper.getDatabase(
                context
            ).storeDao()

            for( store in storeList) {

                var oldStore = dao.getStoreById(store.Sucursal) as StoreData?
                if(oldStore==null){
                    dao.insertStore(store)
                }


            }

            listener()
            withContext(Dispatchers.Main) {

            }
        }
    }






}