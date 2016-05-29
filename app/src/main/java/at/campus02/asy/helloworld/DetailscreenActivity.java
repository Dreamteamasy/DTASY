package at.campus02.asy.helloworld;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import at.campus02.asy.helloworld.objects.ElearningService;
import at.campus02.asy.helloworld.objects.Question;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DetailscreenActivity extends AppCompatActivity
{
    private Retrofit retrofit;
    private ElearningService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailscreen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public DetailscreenActivity(Retrofit retrofit) {
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

            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {

            }
        });
    }

    /*Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://campus02learningapp.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ElearningService service = retrofit.create(ElearningService.class);


    // Example Service Call
    service.list().enqueue(new Callback<List<Question>>() {
    @Override
    public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {

    }

    @Override
    public void onFailure(Call<List<Question>> call, Throwable t) {

    }
    });*/





}
