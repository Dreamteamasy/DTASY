package at.campus02.asy.helloworld.objects;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by peskandar on 29.05.2016.
 */
public class Quiz {

    private Retrofit retrofit;
    private ElearningService service;
    public List list;

    public Quiz() {

        //Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://campus02learningapp.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(ElearningService.class);

        this.service.list().enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                // TODO
                list = response.body();
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {

                Log.v("DetailscreenActivity", "Fehler aufgetreten", t);

            }
        });

    }
}
