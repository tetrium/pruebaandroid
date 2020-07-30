package mx.rodrigodiaz.triplei

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_filters.*
import mx.rodrigodiaz.triplei.adapters.StoreAdapter
import mx.rodrigodiaz.triplei.entities.SearchFilter
import mx.rodrigodiaz.triplei.entities.StoreData
import mx.rodrigodiaz.triplei.helpers.WebServiceHelper
import mx.rodrigodiaz.triplei.models.StoreModel
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    var searchFilter=SearchFilter.Sucursal
    var storeList=mutableListOf<StoreData>()

    companion object{

     lateinit   var instance:MainActivity
    }

    fun launchStoreDetails( sucursal:String){

        val intent = Intent(this, StoreDetailsActivity::class.java).apply {
            putExtra("sucursal", sucursal)
        }
        startActivity(intent)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var model=  StoreModel(this)
        instance=this
        searchEditText.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(
                v: View?,
                keyCode: Int,
                event: KeyEvent
            ): Boolean {



                if (event.action == KeyEvent.ACTION_DOWN &&
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    var textSearchString=searchEditText.getText().toString()
                    if(textSearchString.equals("")){
                        recyclerView.adapter=StoreAdapter( storeList)

                    }else {
                        if (searchFilter == SearchFilter.Determinante) {
                            try {
                                var int = textSearchString.toInt()
                                var resultList = storeList.filter { s -> s.DeterminanteGSP == int }
                                recyclerView.adapter = StoreAdapter(resultList)
                            } catch (e: Exception) {

                            }
                        }
                        if (searchFilter == SearchFilter.Cadena) {
                            try {

                                var resultList = storeList.filter { s ->
                                    s.Cadena.toLowerCase().contains(textSearchString.toLowerCase())
                                }
                                recyclerView.adapter = StoreAdapter(resultList)
                            } catch (e: Exception) {

                            }
                        }
                        if (searchFilter == SearchFilter.Sucursal) {
                            try {

                                var resultList = storeList.filter { s ->
                                    s.Sucursal.toLowerCase()
                                        .contains(textSearchString.toLowerCase())
                                }
                                recyclerView.adapter = StoreAdapter(resultList)
                            } catch (e: Exception) {

                            }
                        }
                    }
                    // Perform action on key press
                   /* Toast.makeText(this@MainActivity, searchEditText.getText(), Toast.LENGTH_SHORT)
                        .show()¨*/
                    return true
                }
                return false
            }
        })


        WebServiceHelper.getConjuntotiendasUsuario {
           /* for(storeData in it.getConjuntotiendasUsuarioResult){
                Log.d("ROK","Activo "+storeData.Activo +" Cadena "+storeData.Cadena)
            }*/

            recyclerView.layoutManager= LinearLayoutManager(this)
            storeList=it.getConjuntotiendasUsuarioResult
            storeList.add(StoreData())



            recyclerView.adapter=StoreAdapter( storeList)
            model.saveStores(it.getConjuntotiendasUsuarioResult){
                Log.d("ROK","TIENDAS GUARDADAS EXITOSAMENTE")


            }
            progressBar.visibility=View.GONE


        }
        filterButton.setOnClickListener {

            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialog_filters)
            dialog.setTitle("Seleccione un filtro")
            dialog.setCancelable(true)
            dialog.op1.setOnClickListener() {
                searchFilter=SearchFilter.Determinante
                updateButtonName()
                dialog.dismiss()

            }
            dialog.op2.setOnClickListener() {
                searchFilter=SearchFilter.Cadena
                updateButtonName()
                dialog.dismiss()
            }
            dialog.op3.setOnClickListener() {
                searchFilter=SearchFilter.Sucursal
                updateButtonName()
                dialog.dismiss()

            }

            dialog.show()
        }
        updateButtonName()
    }

    fun updateButtonName(){
        filterButton.text="Filtro: "+searchFilter.filterName

    }
}
