package in.blogspot.tecnopandit.briantrain;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {
TextView quetext,timer,score;
Button opt1,opt2,opt3,opt4,startbut;
int countofright=0,a,b;
boolean timesup=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quetext=(TextView)findViewById(R.id.questiontext);
        opt1=(Button)findViewById(R.id.opt1);
        opt2=(Button)findViewById(R.id.opt2);
        opt3=(Button)findViewById(R.id.opt3);
        opt4=(Button)findViewById(R.id.opt4);
        timer=(TextView)findViewById(R.id.timertext);
        startbut=(Button)findViewById(R.id.startbut);
        score=(TextView)findViewById(R.id.scoretext);
    }
    public void generatequeNoptions(View view)
    {
        if(timesup=true) {


            a = ThreadLocalRandom.current().nextInt(21);
            b = ThreadLocalRandom.current().nextInt(21);
            int randomloc = ThreadLocalRandom.current().nextInt(4);
            quetext.setText(Integer.toString(a) + " + " + Integer.toString(b));
            ArrayList<Integer> listofoptions = new ArrayList<Integer>();
            for (int i = 0; i < 4; i++) {
                if (randomloc == i) {
                    listofoptions.add(a + b);
                } else {
                    int wrongno = ThreadLocalRandom.current().nextInt(41);

                    while ((a + b) == wrongno) {
                        wrongno = ThreadLocalRandom.current().nextInt(41);
                    }
                    listofoptions.add(wrongno);
                }
            }
            opt1.setText(Integer.toString(listofoptions.get(0)));
            opt2.setText(Integer.toString(listofoptions.get(1)));
            opt3.setText(Integer.toString(listofoptions.get(2)));
            opt4.setText(Integer.toString(listofoptions.get(3)));
        }
        else {
            Toast.makeText(this,"Time's Up",Toast.LENGTH_SHORT).show();
        }

    }
    public void start(View view)
    {
        countofright=0;
        score.setText(Integer.toString(countofright));
        startbut.setEnabled(false);
        opt1.setEnabled(true);
        opt2.setEnabled(true);
        opt3.setEnabled(true);
        opt4.setEnabled(true);
        generatequeNoptions(view);
        new CountDownTimer(40000, 1000) { //40000 milli seconds is total time, 1000 milli seconds is time interval

            public void onTick(long millisUntilFinished) {
                timer.setText(Long.toString(millisUntilFinished/1000));
            }
            public void onFinish() {
                startbut.setEnabled(true);
                timesup=false;
                opt1.setEnabled(false);
                opt2.setEnabled(false);
                opt3.setEnabled(false);
                opt4.setEnabled(false);

            }
        }.start();
    }
    public void ansselected(View view)
    {

        Button but=(Button)view;
        String ans= but.getText().toString();
        Toast.makeText(this,ans,Toast.LENGTH_SHORT).show();
        if (Integer.parseInt(ans)==(a+b))
        {
            countofright++;
            Toast.makeText(this,"Correct Answer",Toast.LENGTH_SHORT).show();
            score.setText(Integer.toString(countofright));
        }
        else
        {
            Toast.makeText(this,"Wrong Answer",Toast.LENGTH_SHORT).show();
        }
        generatequeNoptions(view);
    }

}
