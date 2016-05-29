package at.campus02.asy.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
    RadioGroup rgrpschwierigkeit;
    Button btnschwierigkeit;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Schwierigkeit = "schwierigkeitKey";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schwierigkeitsgrad);

        rgrpschwierigkeit = (RadioGroup) findViewById(R.id.radgrp_schwierigkeit);
        btnschwierigkeit = (Button) findViewById(R.id.btn_schwierigkeitspeichern);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        setRadioButtion();

        btnschwierigkeit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rgrpschwierigkeit.getCheckedRadioButtonId();
                rbstufen = (RadioButton) findViewById(selectedId);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                String schwierigkeitsstufe = rbstufen.getText().toString();
                editor.putString(Schwierigkeit, schwierigkeitsstufe);
                editor.commit();

                String infoText = "Die Schwierigkeitsstufe wurde gespeichert";
                Toast infoToast = Toast.makeText(getApplicationContext(), infoText, Toast.LENGTH_SHORT);
                infoToast.show();

                Intent intentschwierigkeitsgrad = new Intent(getBaseContext(), EinstellungenActivity.class);
                startActivity(intentschwierigkeitsgrad);
            }
        });
    }

    private void setRadioButtion() {
        String schwierigkeitsstufe = sharedPreferences.getString(SchwierigkeitsgradActivity.Schwierigkeit, "alle Stufen");
        if (schwierigkeitsstufe.equals("alle Stufen")){
            rgrpschwierigkeit.check(R.id.radstufealle);
        } else if(schwierigkeitsstufe.equals("leicht (Stufe 0)")){
            rgrpschwierigkeit.check(R.id.radstufe0);
        } else if(schwierigkeitsstufe.equals("normal (Stufe 1)")){
            rgrpschwierigkeit.check(R.id.radstufe1);
        } else if(schwierigkeitsstufe.equals("schwer (Stufe 2")){
            rgrpschwierigkeit.check(R.id.radstufe2);
        } else{
            rgrpschwierigkeit.check(R.id.radstufe3);
        }
    }
}
