package at.campus02.asy.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.campus02.asy.helloworld.objects.ElearningService;
import at.campus02.asy.helloworld.objects.Question;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Response;


public class DSAlleFragenActivity extends AppCompatActivity
{
    private Retrofit retrofit;
    private ElearningService service;
    private TextView tvQuestion;
    private TextView tvAnswer;
    public String frage;
    public String antwort;
    public Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailscreen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.v("DSAlleFragenActivity", "Fehler aufgetreten");

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        tvQuestion = (TextView) findViewById(R.id.frage);
        tvAnswer = (TextView) findViewById(R.id.antwort);


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
                List<Question> list = response.body();

                Collections.shuffle(list);

                // ggfs. kürzen

                List<Question> result = new ArrayList<Question>();

                result = list.subList(0,10);

                result.iterator().next();

                for(Question q: result)
                {
                    frage= q.Fragetext;
                    antwort = q.Antwort;
                    if (tvQuestion != null) {
                        tvQuestion.setText(frage);
                        tvAnswer.setText(antwort);
                    }
                    else{
                        Log.d("DSAlleFragenActivity", "Fehler aufgetreten");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {

                Log.v("DSAlleFragenActivity", "Fehler aufgetreten", t);

            }
        });
    }

    public void frageEinblenden(View view) {

        //Log.d("DSAlleFragenActiviry","frage einblenden");
        tvAnswer.setVisibility(View.VISIBLE);

    }
}
