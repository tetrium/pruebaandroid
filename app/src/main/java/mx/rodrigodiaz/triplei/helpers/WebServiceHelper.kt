package mx.rodrigodiaz.triplei.helpers

import android.util.Log
import mx.rodrigodiaz.triplei.entities.ErrorData
import mx.rodrigodiaz.triplei.entities.ServiceRequestData
import mx.rodrigodiaz.triplei.entities.ServiceResponse
import mx.rodrigodiaz.triplei.webapi.ServiceBuilder
import mx.rodrigodiaz.triplei.webapi.ServicePilot
import retrofit2.Call
import retrofit2.Response
object  WebServiceHelper {



    fun getConjuntotiendasUsuario(listener: (ServiceResponse) -> Unit) {
        var data = ServiceRequestData()
        val webApi = ServiceBuilder.buildService(ServicePilot::class.java)
        val requestCall = webApi.getConjuntotiendasUsuario(data)
        requestCall.enqueue(object : retrofit2.Callback<ServiceResponse> {
            override fun onResponse(
                call: Call<ServiceResponse>,
                httpResponse: Response<ServiceResponse>
            ) {
                if (httpResponse.isSuccessful) {
                    var response = httpResponse.body() // Use it or ignore it
                    Log.d(
                        "ROK",
                        "res, success : " + response!!.errorData.errorCode + " " + response!!.errorData.errorDescription
                    )


                    listener(response!!)

                } else {
                    var response = ServiceResponse()
                    response.errorData = ErrorData(-1, "Error al deserializar")

                    Log.d("ROK", "web api  Error: " + response.errorData.errorDescription)

                    listener(response)
                }
            }

            override fun onFailure(call: Call<ServiceResponse>, t: Throwable) {
                //    t.printStackTrace()
                var response = ServiceResponse()
                response.errorData = ErrorData(-1, "")

                Log.d("ROK", "web api  Error: " + response.errorData.errorDescription)

                listener(response)
            }
        })
    }

}