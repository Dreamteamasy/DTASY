package at.campus02.asy.helloworld;

import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;


public class EinstellungenActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
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

    public void einstzuruecksetzen(View view) {
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();

        String infoText = "Die Einstellungen wurden zurückgesetzt";
        Toast infoToast = Toast.makeText(getApplicationContext(), infoText, Toast.LENGTH_SHORT);
        infoToast.show();
    }
//um auf Einstellungen zuzugreifen

//    public  void umkreisanzeigen(View view){
//        sharedPreferences = this.getSharedPreferences(UmkreisActivity.MyPREFERENCES, Context.MODE_PRIVATE);

//        String infoText = "Umkreis: " + sharedPreferences.getString(UmkreisActivity.Umkreis, "Default-Wert") + " km";
//        Toast infoToast = Toast.makeText(getApplicationContext(), infoText, Toast.LENGTH_SHORT);
//        infoToast.show();
//    }

//    @Override
//    protected void onResume(){
//        super.onResume();
//        getEinstellungen();
//    }

//    private void getEinstellungen() {
//        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
//        String schwierigkeitsstufe = sharedPreferences.getString(SchwierigkeitsgradActivity.Schwierigkeit, "alle Stufen");
//        if (schwierigkeitsstufe.equals("alle Stufen")){}
//        else if(schwierigkeitsstufe.equals("leicht (Stufe 0)")){}
//        else if(schwierigkeitsstufe.equals("normal (Stufe 1)")){}
//        else if(schwierigkeitsstufe.equals("schwer (Stufe 2")){}
//        else{}
//    }

}