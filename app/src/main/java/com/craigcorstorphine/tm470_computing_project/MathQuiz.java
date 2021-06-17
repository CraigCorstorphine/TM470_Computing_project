package com.craigcorstorphine.tm470_computing_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import static java.lang.Integer.valueOf;

public class MathQuiz extends AppCompatActivity {

    private ProgressBar mProgressBar;
    private TextView mLoadingText;

    private final int mProgressStatus = 0;

    private final Handler mHandler = new Handler();

    int value3;
    int answer;
    EditText answer_field;
    ProgressBar progressBar;
    int score;
    String mainOperator;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_quiz);
        setNumber();
        progressBar = findViewById(R.id.answerProgress);
        progressBar.setMax(10);
        progressBar.setProgress(0);







        Button myButton = findViewById(R.id.answer_button);






        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answer_field.getText().toString().equals("")){

                    answer = valueOf(answer_field.getText().toString());
                    //value3 = value1 * value2;


                    if(value3 == answer)
                    {
                        setNumber();

                        showToast("Correct");

                        score ++;
                        progressBar.setProgress(score);
                        endGame();
                        answer_field.setText("");



                    }
                    else {
                        showToast("wrong");
                        setNumber();
                        answer_field.setText("");
                    }

                }

            }

        });

    }

    public void setNumber(){
        final ImageView leftNumber = findViewById(R.id.left_number);
        TextView operator = findViewById(R.id.tvOperator);
        final ImageView rightNumber = findViewById(R.id.right_number);
        final int[] numberArray = new int[]{
                R.drawable.numberzero,
                R.drawable.number1,
                R.drawable.number2,
                R.drawable.number3,
                R.drawable.number4,
                R.drawable.number5,
                R.drawable.number6,
                R.drawable.number7,
                R.drawable.number8,
                R.drawable.number9,
                R.drawable.number10

        };
        // Create a random number generator
        final Random randomNumberGenerator = new Random();

        int value1 = randomNumberGenerator.nextInt(11);

        leftNumber.setImageResource(numberArray[value1]);

        // Create a new random number
        int value2 = randomNumberGenerator.nextInt(11);

        // Set the right number image using an image from the numberArray.
        rightNumber.setImageResource(numberArray[value2]);
        answer_field = findViewById(R.id.answer_field);

        final int[] stringArray = new int[]{
                R.string.X,
                R.string.plus
        };
        int operatorValue = randomNumberGenerator.nextInt(2);
        operator.setText(stringArray[operatorValue]);





        if(operator.getText().equals("x")){
            value3 = value1 * value2;

        }
        else{
            value3 = value1 + value2;
        }

    }







    public void showToast(String text){
        Toast.makeText(MathQuiz.this, text, Toast.LENGTH_LONG).show();
    }
    private void endGame() {


        // Present an alert dialog if we reach the end.
        if(score == 10) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
            builder.setMessage("Well done! You got to 10! Would you like to try again?")
                    .setCancelable(false)
                    .setPositiveButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                        }
                    })
                    .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            setNumber();
                            progressBar.setProgress(0);
                            score = 0;
                        }
                    });
            AlertDialog alert = builder.create();
            alert.setOnShowListener(arg0 -> {
                alert.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.black));
                alert.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.black));
            });
            alert.show();


        }


    }
}

