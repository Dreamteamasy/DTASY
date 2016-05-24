package at.campus02.asy.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Admin on 23.05.2016.
 */
public class MaxFragenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maxfragen);
    }

    public void maxfragenspeichern(View view){
        //Button Action
        String infoText = "Ihre Einstellungen wurden gespeichert";
        Toast infoToast = Toast.makeText(getApplicationContext(), infoText, Toast.LENGTH_SHORT);
        infoToast.show();

        Intent intentMaxFragenSave = new Intent(this, EinstellungenActivity.class);
        startActivity(intentMaxFragenSave);
    }
}
