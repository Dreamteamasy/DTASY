package at.campus02.asy.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class EinstellungenActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einstellungen);
    }

    public void benutzername(View view) {
        Intent intentUser = new Intent(this, BenutzernameActivity.class);
        startActivity(intentUser);
    }

    public void maxfragen(View view) {
        Intent intentFragen = new Intent(this, MaxFragenActivity.class);
        startActivity(intentFragen);
    }

    public void umkreis(View view) {
        Intent intentUmkreis = new Intent(this, UmkreisActivity.class);
        startActivity(intentUmkreis);
    }

    public void schwierigkeitsgrad(View view) {
        Intent intentSchwierigkeitsgrad = new Intent(this, SchwierigkeitsgradActivity.class);
        startActivity(intentSchwierigkeitsgrad);
    }

//um auf Einstellungen zuzugreifen
    public  void umkreisanzeigen(View view){
        sharedPreferences = this.getSharedPreferences(UmkreisActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        String infoText = "Umkreis: " + sharedPreferences.getString(UmkreisActivity.Umkreis, "Default-Wert") + " km";
        Toast infoToast = Toast.makeText(getApplicationContext(), infoText, Toast.LENGTH_SHORT);
        infoToast.show();

        Intent nameanzeigen = new Intent(this, MainActivity.class);
        startActivity(nameanzeigen);
    }
//    public void speichern(View view) {
//        //Button Action
//        String infoText = "Die Einstellungen wurden gespeichert";
//        Toast infoToast = Toast.makeText(getApplicationContext(), infoText, Toast.LENGTH_SHORT);
//        infoToast.show();

//        Intent intentSpeichern = new Intent(this, MainActivity.class);
//        startActivity(intentSpeichern);
//    }

}