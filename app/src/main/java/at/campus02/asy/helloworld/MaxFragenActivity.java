package at.campus02.asy.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Admin on 23.05.2016.
 */
public class MaxFragenActivity extends AppCompatActivity {

    EditText edfragen;
    Button btnfragen;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Fragen = "FragenKey";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maxfragen);

        edfragen = (EditText) findViewById(R.id.edit_maxfragen);
        btnfragen = (Button) findViewById(R.id.btn_maxfragenspeichern);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        btnfragen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f = edfragen.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(Fragen, f);
                editor.commit();

                String infoText = f + " Runden wurden gespeichert";
                Toast infoToast = Toast.makeText(getApplicationContext(), infoText, Toast.LENGTH_SHORT);
                infoToast.show();

                Intent intentMaxFragenSave = new Intent(getBaseContext(), EinstellungenActivity.class);
                startActivity(intentMaxFragenSave);
            }
        });
    }
}
