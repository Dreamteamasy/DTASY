package at.campus02.asy.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by Admin on 25.05.2016.
 */
public class SchwierigkeitsgradActivity extends AppCompatActivity {

//    RadioButton rbstufe0;
//    RadioButton rbstufe1;
//    RadioButton rbstufe2;
//    RadioButton rbstufe3;
//    RadioButton rbstufealle;
    RadioButton rbstufen;
    RadioGroup grpschwierigkeit;
    Button btnschwierigkeit;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Schwierigkeit = "schwierigkeitKey";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schwierigkeitsgrad);
    }

    public void schwierigkeitsgradspeichern(View view) {
        String infoText = "Die Einstellung wurde gespeichert";
        Toast infoToast = Toast.makeText(getApplicationContext(), infoText, Toast.LENGTH_SHORT);
        infoToast.show();

        Intent intentschwierigkeitsgrad = new Intent(this, EinstellungenActivity.class);
        startActivity(intentschwierigkeitsgrad);
    }
}
