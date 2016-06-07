package at.campus02.asy.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FrageAnzeigenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frage_anzeigen);
    }

    public void alleKategorien(View view) {
        Intent alleKategorien = new Intent(this, DSAlleFragenActivity.class);
        startActivity(alleKategorien);
    }

    public void kategorie(View view) {
        Intent kategorie = new Intent(this, DSKategorieActivity.class);
        startActivity(kategorie);
    }

    public void umgebung(View view) {
        Intent umgebung = new Intent(this, DSUmgebungActivity.class);
        startActivity(umgebung);
    }
}
