package won.who.briodrives

interface ApiService {
    @GET("rides")
    suspend fun getRides(): List<Ride>
}