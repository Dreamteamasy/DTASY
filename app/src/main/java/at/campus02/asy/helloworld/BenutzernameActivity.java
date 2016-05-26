package at.campus02.asy.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Admin on 24.05.2016.
 */
public class BenutzernameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benutzername);
    }

    public void benutzernamespeichern(View view) {
        String infoText = "Die Einstellungen wurden gespeichert";
        Toast infoToast = Toast.makeText(getApplicationContext(), infoText, Toast.LENGTH_SHORT);
        infoToast.show();

        Intent intentbenutzerspeichern = new Intent(this, EinstellungenActivity.class);
        startActivity(intentbenutzerspeichern);
    }
}
