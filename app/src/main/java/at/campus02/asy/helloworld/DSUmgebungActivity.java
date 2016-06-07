package at.campus02.asy.helloworld;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import at.campus02.asy.helloworld.R;

public class DSUmgebungActivity extends AppCompatActivity {
    private ProgressDialog progbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsumgebung);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void laden(){
        progbar = new ProgressDialog(this);
        progbar.setMessage("Die nächste Frage wird geladen");
        progbar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progbar.setIndeterminate(true);
        progbar.setProgress(0);
        progbar.show();

        final int totalProgressTime = 100;
        final Thread t = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;

                while (jumpTime < totalProgressTime) {
                    try {
                        sleep(200);
                        jumpTime += 5;
                        progbar.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        };
        t.start();
    }
}
