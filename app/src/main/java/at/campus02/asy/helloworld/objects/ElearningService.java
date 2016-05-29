package at.campus02.asy.helloworld.objects;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by peskandar on 21.05.2016.
 */
public interface ElearningService {
    @GET("api/Fragen")
    Call<List<Question>> list();

    @GET("api/Fragen/{id}")
    Call<Question> get(@Path("id") String id);

    @GET("api/Kategorie")
    Call<String[]> categories();

    @GET("api/Kategorie/{name}")
    Call<List<Question>> byCategory(@Path("name") String name);

    @GET("api/Fragen/Koordinaten/")
    Call<List<Question>> byCoordinates(@Query("La") double la, @Query("Lo") double lo, @Query("Di") int di);

    @POST("api/Fragen")
    Call<String> createQuestion(@Body Question question);

    @PUT("api/Fragen/{id}")
    Call<Question> updateQuestion(String id, @Body Question question);

    @DELETE("api/Fragen/{id}")
    Call deleteQuestion(String id);
}


