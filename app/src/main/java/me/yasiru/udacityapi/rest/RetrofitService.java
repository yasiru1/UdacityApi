package me.yasiru.udacityapi.rest;




import me.yasiru.udacityapi.POJO.UdacityResponse;
import retrofit2.Call;
import retrofit2.http.GET;


public interface RetrofitService {
     //service
    @GET("courses")
    Call<UdacityResponse> getCourses();
}

