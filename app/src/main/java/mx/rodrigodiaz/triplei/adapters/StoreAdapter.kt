package mx.rodrigodiaz.triplei.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import mx.rodrigodiaz.triplei.R
import mx.rodrigodiaz.triplei.entities.StoreData
import mx.rodrigodiaz.triplei.viewholders.StoreViewHolder


class StoreAdapter (val dataList: List<StoreData>) : RecyclerView.Adapter<StoreViewHolder>() {

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }
    lateinit var data: StoreData
    var listener: ((levelId:Int) -> Unit?)? =null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val inflatedView = parent.inflate(R.layout.item_store, false)
        return StoreViewHolder(inflatedView)
    }

    override fun onBindViewHolder(viewHolder: StoreViewHolder, position: Int) {
        var data=dataList[position]
        this. listener?.let { viewHolder.listener= it as ((sucursal:String) -> Unit) }

        viewHolder.bind(dataList[position])
    }

    fun setOnClickListener(listener: (levelId:Int) -> Unit) {
        this. listener=listener
    }
    override fun getItemCount() = dataList.size
}