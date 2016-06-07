package at.campus02.asy.helloworld;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import at.campus02.asy.helloworld.objects.ElearningService;
import at.campus02.asy.helloworld.objects.Question;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DSAlleFragenActivity extends AppCompatActivity
{
    private TextView tvQuestion;
    private TextView tvAnswer;
    private Button btnVisible;
    private String frage;
    private String antwort;
    private ProgressDialog progbar;
    private int round;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Fragen = "FragenKey";
    public static final String Runde = "RundeKey";
    SharedPreferences sharedPreferences;

    @SuppressLint("SetTextI18n")
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
        btnVisible = (Button) findViewById(R.id.antwortEinblenden);
        if (btnVisible != null) {
            btnVisible.setTag(1);
            btnVisible.setText("Antwort einblenden");
        }


        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://campus02learningapp.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ElearningService service = retrofit.create(ElearningService.class);

        service.list().enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                // TODO
                List<Question> list = response.body();
                Collections.shuffle(list);
                List<Question> result = new ArrayList<Question>();
                if(list.size() > 10){
                    result = list.subList(0,10);
                }
                else{
                    result = list;
                }

                for(Question q: result)
                {
                    frage= q.Fragetext;
                    antwort = q.Antwort;
                    if (tvQuestion != null) {
                        tvQuestion.setText(frage);
                        tvAnswer.setText(antwort);
                    }
                    else{
                        Log.v("DSAlleFragenActivity", "Fehler aufgetreten");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DSAlleFragenActivity.this);
                alertDialog.setMessage("Ein Fehler ist aufgetreten!");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        Intent intent = new Intent(DSAlleFragenActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.show();
            }
        });
    }

    public void antwortEinblenden(View view) {
        final int status =(Integer) view.getTag();
        if(status == 1) {
            btnVisible.setText("Antwort ausblenden");
            tvAnswer.setVisibility(View.VISIBLE);
            view.setTag(0); //pause
        } else {
            btnVisible.setText("Antwort einblenden");
            tvAnswer.setVisibility(View.INVISIBLE);
            view.setTag(1); //pause
        }
    }

    public void next(View view) {
        laden();

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String runden = sharedPreferences.getString(Fragen, "10");
        int rundenInt = Integer.parseInt(runden);
        int rundeNew = getIntent().getIntExtra(Runde, 1);

        if (rundeNew <= rundenInt) {
            round++;
            Bundle localBundle = new Bundle();
            localBundle.putInt(Runde, round);
            Intent alleFragen = new Intent(this, DSAlleFragenActivity.class);
            alleFragen.putExtras(localBundle);
            startActivity(alleFragen);
        } else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(DSAlleFragenActivity.this);
            alertDialog.setMessage("Das Spiel ist vorbei");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(DSAlleFragenActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            alertDialog.show();
        }
    }

    private void laden(){
        progbar = new ProgressDialog(this);
        progbar.setMessage("Die nächste Frage wird geladen");
        progbar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progbar.setIndeterminate(true);
        progbar.setProgress(0);
        progbar.show();

        final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while (jumpTime < totalProgressTime) {
                    try {
                        sleep(200);
                        jumpTime += 5;
                        progbar.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };
        t.start();
    }

    public void backToMain(View view) {
        Intent intentGame = new Intent(this, MainActivity.class);
        startActivity(intentGame);
    }
}
