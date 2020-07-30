package mx.rodrigodiaz.triplei.webapi



import mx.rodrigodiaz.triplei.entities.ServiceRequestData
import mx.rodrigodiaz.triplei.entities.ServiceResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
interface ServicePilot {


    @POST("Tracker.Procesos.svc/getConjuntotiendasUsuario")
    fun getConjuntotiendasUsuario(@Body replyRequest: ServiceRequestData): Call<ServiceResponse>


}