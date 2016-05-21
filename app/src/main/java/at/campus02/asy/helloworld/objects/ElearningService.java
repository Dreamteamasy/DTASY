package at.campus02.asy.helloworld.objects;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by peskandar on 21.05.2016.
 */
public interface ElearningService {

    @GET("api/Fragen")
    Call<List<Question>> list();
    @GET("api/Fragen/{id}")
    Call<Question> get(String id);
    @GET("api/Kategorie")
    Call<List<String>> categories();
    @GET("api/Kategorie/{name}")
    Call<List<Question>> categories(String name);

    @POST("api/Fragen")
    Call<Question> createQuestion(@Body Question question);
    @POST("api/Fragen/{id}")
    Call<Question> updateQuestion(String id, @Body Question question);
    @DELETE("api/Fragen/{id}")
    Call deleteQuestion(String id);
}

