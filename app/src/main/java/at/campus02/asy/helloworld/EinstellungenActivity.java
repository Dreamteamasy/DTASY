package at.campus02.asy.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class EinstellungenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einstellungen);
    }

    public void maxfragen(View view) {
        Intent intentFragen = new Intent(this, MaxFragenActivity.class);
        startActivity(intentFragen);
    }

    public void umkreis(View view) {
        Intent intentUmkreis = new Intent(this, DetailscreenActivity.class);
        startActivity(intentUmkreis);
    }

    public void benutzername(View view) {
        Intent intentUser = new Intent(this, BenutzernameActivity.class);
        startActivity(intentUser);
    }

    public void schwierigkeitsgrad(View view) {
        Intent intentSchwierigkeitsgrad = new Intent(this, DetailscreenActivity.class);
        startActivity(intentSchwierigkeitsgrad);
    }

    public void speichern(View view) {
        //Button Action
        String infoText = "Ihre Einstellungen wurden gespeichert";
        Toast infoToast = Toast.makeText(getApplicationContext(), infoText, Toast.LENGTH_SHORT);
        infoToast.show();

        Intent intentSpeichern = new Intent(this, MainActivity.class);
        startActivity(intentSpeichern);
    }
}