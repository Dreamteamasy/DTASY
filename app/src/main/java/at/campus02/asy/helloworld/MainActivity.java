package at.campus02.asy.helloworld;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    SharedPreferences sharedPreferences;

    public boolean isNetworkAvailable(final Context context) {

        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Begrüßung mit Vornamen falls vorhanden
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String aktname = aktname = sharedPreferences.getString(Name, "");
        if(!aktname.equals("")) {
            String infoText = "Hallo " + aktname;
            Toast infoToast = Toast.makeText(getApplicationContext(), infoText, Toast.LENGTH_SHORT);
            infoToast.show();
        }

        //Nachricht wenn keine Internetverbindung vorhanden ist
        if (isNetworkAvailable(getBaseContext())) {
            // code here
        } else {
            Toast errorToast = Toast.makeText(getApplicationContext(),
                    "Kein Internet vorhanden", Toast.LENGTH_LONG);
            errorToast.show();
        }

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    // button next onClick Handler
    public void frageAnzeigen(View view) {
        //validate input
        Intent intentGame = new Intent(this, FrageAnzeigenActivity.class);
        startActivity(intentGame);
    }

    public void frageErstellen(View view) {
        //validate input
        Intent intentGame = new Intent(this, FrageErstellenActivity.class);
        startActivity(intentGame);
    }

    public void einstellungen(View view) {
        //validate input
        Intent intenteinstellungen = new Intent(this, EinstellungenActivity.class);
        startActivity(intenteinstellungen);
    }
}
