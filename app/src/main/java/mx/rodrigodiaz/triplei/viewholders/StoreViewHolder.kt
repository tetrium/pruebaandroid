package mx.rodrigodiaz.triplei.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_store.view.*
import mx.rodrigodiaz.triplei.MainActivity
import mx.rodrigodiaz.triplei.entities.StoreData


class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    lateinit var  listener: (sucursal:String) -> Unit

    fun setOnClickListener(listener: (sucursal:String) -> Unit) {
        this.listener=listener
    }
    fun bind(storeData: StoreData) {

        itemView.storeName.text=storeData.Sucursal
        itemView.storeChainName.text=storeData.Cadena
        itemView.storeInitialName.text=storeData.Sucursal.subSequence(0,1).toString()

       itemView.storeHolder.setOnClickListener{
            //this.listener(storeData.Sucursal)
            MainActivity.instance.launchStoreDetails(storeData.Sucursal)

        }


    }
}