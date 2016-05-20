package at.campus02.asy.helloworld;

import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private TextView tvGreeting;
    private TextView tvQuestion;
    private TextView tvHighscore;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btnNextLevel;
    private boolean answerWasCorrect = false;
    private int clickCount = 0;
    private int highScore = 0;
    private int level = 0;
    private String name = "";
    private String highScoreText = "Runde: "+(1+level)+"   -   Punkte: "+highScore;

    //  game question and answers as array
    private String[] questionText = new String[]{   "Wie viele Seiten hat eine Pyramide?",
                                                    "Wie viele Buchstaben hat das griechische Alphabet?",
                                                    "Wer hat Java erfunden?",
                                                    "Wann war der Android Marktstart?",
                                                    "Was ist ein APK?"};
    private String[] answer1 = new String[]{"4","24","Brendan\nEich","2009","Android Persistence"};
    private String[] answer2 = new String[]{"6","20","Bjarne\nStroustrup","2007","Android Package"};
    private String[] answer3 = new String[]{"8","22"," Tim\nBerners-Lee","2008","Application Programming Key"};
    private String[] answer4 = new String[]{"12","26","James\nGosling","2010","Alternative Package"};
    private String[] correctAnswer = new String[]{"8","24","James\nGosling","2008","Android Package"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // get name and age from Intent
        Bundle extras = getIntent().getExtras();
        name = extras.getString("MainActivity.Name");
        String age = extras.getString("MainActivity.Age");
        //Log
        Log.d("GameActivity", "Name: "+name);


        //get UI
        tvGreeting = (TextView) findViewById(R.id.tv_game_greeting);
        tvQuestion = (TextView) findViewById(R.id.tv_game_question);
        tvHighscore = (TextView) findViewById(R.id.tv_game_highscore);

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);

        tvGreeting.setText("Herzlich willkommen "+name+"!");
        tvHighscore.setText(highScoreText);
        btnNextLevel = (Button) findViewById(R.id.button_nextLevel);
        initLevel();

    }

    // button eventHandler - get button from view
    public void answerButton(View view) {
        checkCorrectAnswer((Button)view);
    }


    private boolean checkCorrectAnswer(Button btn)
    {
        Log.d("GameActivity", "Method:checkAnswer LEVEL:'"+level+" CLICKCOUNT:"+clickCount+"button text:"+ btn.getText()+"'");
        clickCount++;
        if(btn.getText().toString().equals(correctAnswer[level]) && answerWasCorrect==false)
        {
            // correct Answer
            Log.d("GameActivity", "Method:checkAnswer TRUE:'" + btn.getText() + "'");
            btn.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x0000FF000));
            Toast successToast = Toast.makeText(getApplicationContext(), "RICHTIG!", Toast.LENGTH_SHORT);
            successToast.show();
            answerWasCorrect = true;
            if(clickCount==1){
                highScore++;
                // update textView
                tvHighscore.setText("Runde: "+(1+level)+"   -   Punkte: "+highScore);

            }
            answerWasCorrect = true;

            // increase level counter
            level++;

            if(level < correctAnswer.length){
                // show next level button
                btnNextLevel.setVisibility(View.VISIBLE);
            }else{
                // quit game
                Toast quitGame = Toast.makeText(getApplicationContext(), "Das Spiel ist nun vorbei!" +
                        "\nSie haben "+highScore+" Punkte erreicht!", Toast.LENGTH_LONG);
                quitGame.show();
                // Intent to FinalActivity
                Intent intentGame = new Intent(this, FinalActivity.class);
                //save name and age in intent
                intentGame.putExtra("GameActivity.Name", name);
                intentGame.putExtra("GameActivity.HighScore", ""+highScore);
                startActivity(intentGame);
            }
            return true;

        }else{

            if(!answerWasCorrect) {
                // wrong Answer
                Log.d("GameActivity", "Method:checkAnswer FALSE  " + btn.getText());
                // deprecated in API level 23 (M) btn.setBackgroundColor(getResources().getColor(R.color.red));
                // color filter  to set the shade of a button
                // LightingColorFilter is defined by two parameters, one used to multiply the source color
                // (called colorMultiply) and one used to add to the source color (called colorAdd). The alpha channel is left untouched by this color filter
                btn.getBackground().setColorFilter(new LightingColorFilter(0x00000000, 0x00FF00000));
                Toast failureToast = Toast.makeText(getApplicationContext(), "LEIDER FALSCH!", Toast.LENGTH_SHORT);
                failureToast.show();
            }
            return false;
        }
    }

    // inits question and answers for corresponding level and reset counters
    private void initLevel(){
        answerWasCorrect = false;
        clickCount = 0;
        btnNextLevel.setVisibility(View.INVISIBLE);

        // reset buttons to default color
        btn1.getBackground().clearColorFilter();
        btn2.getBackground().clearColorFilter();
        btn3.getBackground().clearColorFilter();
        btn4.getBackground().clearColorFilter();

        //set new level question and answers
        tvQuestion.setText(questionText[level]);
        btn1.setText(answer1[level]);
        btn2.setText(answer2[level]);
        btn3.setText(answer3[level]);
        btn4.setText(answer4[level]);
        tvHighscore.setText("Runde: "+(1+level)+"   -   Punkte: "+highScore);


    }

    // button onClick handler for next level
    public void nextLevel(View view) {
        initLevel();
    }


    @Override
    public void onResume() {
        super.onResume();
        // reset level
        level = 0;
        initLevel();

    }
}
