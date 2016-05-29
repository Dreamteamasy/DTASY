package at.campus02.asy.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Admin on 26.05.2016.
 */
public class UmkreisActivity extends AppCompatActivity {

    EditText edumkreis;
    Button btnumkreis;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Umkreis = "umkreisKey";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umkreis);

        edumkreis = (EditText) findViewById(R.id.edit_umkreisantwort);
        btnumkreis = (Button) findViewById(R.id.btn_umkreisspeichern);
        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String aktumkreis = sharedPreferences.getString(Umkreis, "20");
        edumkreis.setText(aktumkreis);

        btnumkreis.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String u = edumkreis.getText().toString();

                if(u.equals("")){
                    String keinTxt = "Bitte einen Umkreis eingeben";
                    Toast keinTxtToast = Toast.makeText(getApplicationContext(), keinTxt, Toast.LENGTH_SHORT);
                    keinTxtToast.show();
                } else {

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(Umkreis, u);
                    editor.commit();

                    String infoText = "Ein Umkreis von " + u + " km wurde gespeichert";
                    Toast infoToast = Toast.makeText(getApplicationContext(), infoText, Toast.LENGTH_SHORT);
                    infoToast.show();

                    Intent intentumkreis = new Intent(getBaseContext(), EinstellungenActivity.class);
                    startActivity(intentumkreis);
                }
            }
        });
    }
}
