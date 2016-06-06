package at.campus02.asy.helloworld;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import at.campus02.asy.helloworld.objects.ElearningService;
import at.campus02.asy.helloworld.objects.GPSTracker;
import at.campus02.asy.helloworld.objects.Question;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FrageErstellenActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private ElearningService service;
    private EditText etFrage;
    private EditText etAntwort;
    private EditText etKategorie;
    private EditText etBild;
    private EditText etSchwierigkeitsgrad;
    private String grade;
    private RadioGroup radioGroup;
    private Question question = new Question();
    private Spinner kategDropdown;
    GPSTracker gps;
    private int id = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frage_erstellen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etFrage = (EditText) findViewById(R.id.frageText);
        etAntwort = (EditText) findViewById(R.id.antwortText);
        //etKategorie = (EditText) findViewById(R.id.kategorieText);
        etBild = (EditText) findViewById(R.id.bildText);
        etSchwierigkeitsgrad = (EditText) findViewById(R.id.schwierigkeitsgradText);
        Button btnSave = (Button) findViewById(R.id.btnFrageErstellen);


        //Retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("http://campus02learningapp.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //get Kategorien from Service
        this.service = retrofit.create(ElearningService.class);
        this.service.categories().enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, Response<String[]> response) {
                // TODO
                String[] kategorienTmp = response.body();
                ArrayList<String> kategorien = new ArrayList<String>();
                for(String aktWert : kategorienTmp){
                    if(aktWert != null){
                        kategorien.add(aktWert);
                    }
                }
                kategDropdown = (Spinner)findViewById(R.id.kategDropdown);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(FrageErstellenActivity.this, android.R.layout.simple_spinner_dropdown_item, kategorien);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                kategDropdown.setAdapter(adapter);
        }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(FrageErstellenActivity.this);
                alertDialog.setMessage("Ein Fehler ist aufgetreten!");
                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        Intent intent = new Intent(FrageErstellenActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                alertDialog.show();
            }
        });

        // check if GPS enabled
        GPSTracker gpsTracker = new GPSTracker(this);

        if (gpsTracker.getIsGPSTrackingEnabled())
        {
            String stringLatitude = String.valueOf(gpsTracker.latitude);
            TextView tvBreitengrad = (TextView) findViewById(R.id.bGradView);
            tvBreitengrad.setText(stringLatitude);

            String stringLongitude = String.valueOf(gpsTracker.longitude);
            TextView tvLängengrad = (TextView) findViewById(R.id.lGradView);
            tvLängengrad.setText(stringLongitude);

            grade = stringLatitude + ";" + stringLongitude;
        }
        else {
            gpsTracker.showSettingsAlert();
        }
     }

    public void frageSpeichern(View view) {
        /*radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId)
            {
                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
                Integer id = checkedRadioButton.getId();
                String text = id.toString();
                question.Schwierigkeitsgrad = text;
            }
        });*/
        service = retrofit.create(ElearningService.class);

        question.FrageID = "Nummer: " + String.valueOf(id);
        question.Fragetext = etFrage.getText().toString();
        question.Antwort = etAntwort.getText().toString();
        question.Schwierigkeitsgrad = etSchwierigkeitsgrad.getText().toString();
        question.Kategorie = kategDropdown.getSelectedItem().toString();
        question.LaengenUndBreitengrad = grade;
        question.Bild = etBild.getText().toString();

        if(etFrage.toString().length() != 0 && etAntwort.toString().length() != 0 && etSchwierigkeitsgrad.toString().length() != 0
                && grade.length() != 0 && /*etBild.toString().length() != 0 ||*/ question != null) {
            Log.v("FrageErstellenActivity", "Hello");

            service.createQuestion(question).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(FrageErstellenActivity.this);
                    alertDialog.setMessage("Ein Fehler ist aufgetreten!");
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            Intent intent = new Intent(FrageErstellenActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });
                    alertDialog.show();
                }
            });

            Intent intentGame = new Intent(this, MainActivity.class);
            startActivity(intentGame);
        }

        else {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(FrageErstellenActivity.this);
            alertDialog.setMessage("Bitte alle Felder ausfüllen!");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialog.show();
        }



    }
}
