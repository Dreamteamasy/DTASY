package at.campus02.asy.helloworld;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Admin on 24.05.2016.
 */
public class BenutzernameActivity extends AppCompatActivity {

    EditText edname;
    Button btnname;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Name = "nameKey";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benutzername);

        edname = (EditText) findViewById(R.id.edit_benutzername);
        btnname = (Button) findViewById(R.id.btn_benutzernamespeichern);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        String aktname = sharedPreferences.getString(Name, "");
        edname.setText(aktname);

        btnname.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String n = edname.getText().toString();

                if(n.equals("")) {
                    String keinTxt = "Bitte einen Namen eingeben";
                    Toast keinTxtToast = Toast.makeText(getApplicationContext(), keinTxt, Toast.LENGTH_SHORT);
                    keinTxtToast.show();
                } else{

                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString(Name, n);
                    editor.commit();

                    String infoText = "Der Name '" + n + "' wurde gespeichert";
                    Toast infoToast = Toast.makeText(getApplicationContext(), infoText, Toast.LENGTH_SHORT);
                    infoToast.show();

                    Intent intentbenutzerspeichern = new Intent(getBaseContext(), EinstellungenActivity.class);
                    startActivity(intentbenutzerspeichern);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
