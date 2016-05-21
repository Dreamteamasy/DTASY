package at.campus02.asy.helloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {

    TextView tv_final_info = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        tv_final_info = (TextView) findViewById(R.id.tv_final_info);

        // get name and age from Intent
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("FrageAnzeigenActivity.Name");
        String highScore = extras.getString("FrageAnzeigenActivity.HighScore");
        //Log
        Log.d("FinalActivity", "Name: " + name+" Highscore: " + highScore);

        tv_final_info.setText("Gratulation "+name+"!\n\n Sie haben "+highScore+" Punkte erreicht.");

    }

    // quit app
    public void quit(View view) {
        finish();
        moveTaskToBack(true);
    }


    @Override
    public void onBackPressed() {
        // assure that button back is not working
    }
}
