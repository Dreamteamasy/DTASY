package at.campus02.asy.helloworld;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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

public class DSKategorieActivity extends AppCompatActivity {

    private TextView tvQuestion;
    private TextView tvAnswer;
    private Button btnVisible;
    private String frage;
    private String antwort;
    private Spinner kKategDropdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dskategorie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        tvQuestion = (TextView) findViewById(R.id.kFrage);
        tvAnswer = (TextView) findViewById(R.id.kAntwort);
        btnVisible = (Button) findViewById(R.id.kAntwortEinblenden);
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

        service.categories().enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                // TODO
                String[] kategorien = response.body();
                kKategDropdown = (Spinner)findViewById(R.id.kKategDropdown);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(DSKategorieActivity.this, android.R.layout.simple_spinner_dropdown_item, kategorien);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                kKategDropdown.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DSKategorieActivity.this);
                alertDialog.setMessage("Ein Fehler ist aufgetreten!");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        Intent intent = new Intent(DSKategorieActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.show();
            }
        });

        service.list().enqueue(new Callback<List<Question>>() {
            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                // TODO
                final List<Question> list = response.body();
                Collections.shuffle(list);
                if(kKategDropdown.getSelectedItem() == null) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(DSKategorieActivity.this);
                    alertDialog.setMessage("Es sind keine Kategorien vorhanden!");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            Intent intent = new Intent(DSKategorieActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    alertDialog.show();
                }

                kKategDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        String choosenKategs = kKategDropdown.getSelectedItem().toString();

                        for(Question q: list)
                        {
                            List<Question> result = new ArrayList<Question>();

                            if(q.Kategorie.equals(choosenKategs))
                            {
                                if(list.size() > 10){
                                    result = list.subList(0,10);
                                }
                                else{
                                    result = list;
                                }

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
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DSKategorieActivity.this);
                alertDialog.setMessage("Ein Fehler ist aufgetreten!");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        Intent intent = new Intent(DSKategorieActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.show();
            }
        });

    }

    public void kAntwortEinblenden(View view) {
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

    public void kNext(View view) {
        Intent intentGame = new Intent(this, DSKategorieActivity.class);
        startActivity(intentGame);
    }

    public void kBackToMain(View view) {
        Intent intentGame = new Intent(this, MainActivity.class);
        startActivity(intentGame);
    }
}
