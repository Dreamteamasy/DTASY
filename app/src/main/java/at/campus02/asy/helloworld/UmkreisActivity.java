package at.campus02.asy.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Admin on 26.05.2016.
 */
public class UmkreisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umkreis);
    }
    public void umkreiszwischenspeichern(View view) {
        String infoText = "Eingabe wurde zwischengespeichert";
        Toast infoToast = Toast.makeText(getApplicationContext(), infoText, Toast.LENGTH_SHORT);
        infoToast.show();

        Intent intentumkreis = new Intent(this, EinstellungenActivity.class);
        startActivity(intentumkreis);
    }
}
