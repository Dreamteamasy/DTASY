package at.campus02.asy.helloworld;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private String recipient = "info@campus02.at";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get resources and init UI
        //etName = (EditText) findViewById(R.id.et_name);
        //etAge = (EditText) findViewById(R.id.et_age);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send Email to recipient address
                sendEmail();
                //Sna ckbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            // forward to InfoActivity with intent
            Intent forwardToInfo = new Intent(this,InfoActivity.class);
            startActivity(forwardToInfo);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    // button next onClick Handler
    public void frageAnzeigen(View view) {
        //validate input
        Intent intentGame = new Intent(this, FrageAnzeigenActivity.class);
        startActivity(intentGame);
    }

    public void frageErstellen(View view) {
        //validate input
        Intent intentGame = new Intent(this, FrageErstellenActivity.class);
        startActivity(intentGame);
    }

    public void einstellungen(View view) {
        //validate input
        Intent intentGame = new Intent(this, SettingsActivity.class);
        startActivity(intentGame);
    }

    /*// check if the name and age etitText fields are not empty
    private boolean inputIsValid() {
        if ((etName.getText().toString().length() == 0) || (etAge.getText().toString().length() == 0)) {
            Log.d("MainActivity", "ValidationError Name: " + etName.getText().toString() +
                    " Age: " + etAge.getText().toString());
            // create errorToast Message and show it
            Toast errorToast = Toast.makeText(getApplicationContext(),
                    "Geben Sie bitte Ihren Vornamen und Ihr Alter ein!", Toast.LENGTH_LONG);
            errorToast.show();
            return false;
        } else {
            Log.d("MainActivity", "Validation OK");
            return true;
        }
    }*/




    // send E-mail to the given recipient(s) with dummy text
    /*protected void sendEmail() {

        String[] recipients = {recipient};
        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));

        // prompts email clients only
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, recipients);
        email.putExtra(Intent.EXTRA_SUBJECT, "Anfrage von der Campus02 App");
        email.putExtra(Intent.EXTRA_TEXT, "Anfragetext ...");
        try {
            // the user can choose the email client
            startActivity(Intent.createChooser(email, "Wählen Sie einen E-Mailclient aus."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "Kein E-Mailclient installiert!",
                    Toast.LENGTH_LONG).show();
        }
    }*/



    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://at.campus02.asy.helloworld/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://at.campus02.asy.helloworld/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
