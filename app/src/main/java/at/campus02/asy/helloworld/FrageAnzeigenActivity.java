package at.campus02.asy.helloworld;

import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class FrageAnzeigenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frage_anzeigen);
    }

    public void alleKategorien(View view) {
        Intent intentGame = new Intent(this, DetailscreenActivity.class);
        startActivity(intentGame);
    }

    public void kategorie(View view) {
        Intent intentGame = new Intent(this, DetailscreenActivity.class);
        startActivity(intentGame);
    }

    public void umgebung(View view) {
        Intent intentGame = new Intent(this, DetailscreenActivity.class);
        startActivity(intentGame);
    }
}
