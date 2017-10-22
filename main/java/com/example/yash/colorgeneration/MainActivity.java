package com.example.yash.colorgeneration;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener{

    public static final String TAG = "asdasda";
    SeekBar seekBar1,seekBar2,seekBar3;
    EditText etTextR,etTextHEX,etTextG,etTextB;
    LinearLayout llMain;
    Button btnSetHex,btnSetRGB,btnRandom;
    Integer r,g,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);


        etTextR = (EditText) findViewById(R.id.etTextR);
        etTextG = (EditText) findViewById(R.id.etTextG);
        etTextB = (EditText) findViewById(R.id.etTextB);
        etTextHEX = (EditText) findViewById(R.id.etTextHEX);
        llMain = (LinearLayout) findViewById(R.id.llMain);







        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar3.setOnSeekBarChangeListener(this);

        btnSetHex = (Button) findViewById(R.id.btnSetHex);
        btnSetRGB = (Button) findViewById(R.id.btnSetRGB);
        btnRandom = (Button) findViewById(R.id.btnRandom);

        Random rand = new Random();
        r = rand.nextInt(256);
        g = rand.nextInt(256);
        b = rand.nextInt(256);

        etTextR.setText(r.toString());
        etTextG.setText(g.toString());
        etTextB.setText(b.toString());
        etTextHEX.setText("#" + hexcode(r) + hexcode(g) + hexcode(b));


        seekBar1.setProgress(r);
        seekBar2.setProgress(g);
        seekBar3.setProgress(b);

        llMain.setBackgroundColor(Color.rgb(r,g,b));

        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand = new Random();
                r = rand.nextInt(256);
                g = rand.nextInt(256);
                b = rand.nextInt(256);

                etTextR.setText(r.toString());
                etTextG.setText(g.toString());
                etTextB.setText(b.toString());
                etTextHEX.setText("#" + hexcode(r) + hexcode(g) + hexcode(b));


                seekBar1.setProgress(r);
                seekBar2.setProgress(g);
                seekBar3.setProgress(b);

                llMain.setBackgroundColor(Color.rgb(r,g,b));
            }
        });

        btnSetRGB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etR = etTextR.getText().toString();
                String etG = etTextG.getText().toString();
                String etB = etTextB.getText().toString();

                seekBar1.setProgress(Integer.parseInt(etR));
                seekBar2.setProgress(Integer.parseInt(etG));
                seekBar3.setProgress(Integer.parseInt(etB));

            }
        });


        btnSetHex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String hex = etTextHEX.getText().toString();
/*
                if(hex.length()!=7)
                    Toast.makeText(MainActivity.this, "Acceptable length 7", Toast.LENGTH_SHORT).show();
                else if(hex.charAt(0)!='#')
                    Toast.makeText(MainActivity.this, "Should start with #", Toast.LENGTH_SHORT).show();
*/
                if(Pattern.matches("^#([A-Fa-f0-9]{6})$", hex))
                {
                    char r1,r2,g1,g2,b1,b2;

                    r1=hex.charAt(1);
                    r2=hex.charAt(2);
                    g1=hex.charAt(3);
                    g2=hex.charAt(4);
                    b1=hex.charAt(5);
                    b2=hex.charAt(6);

                    int r = (rgbCode(r1)*16)+rgbCode(r2);
                    int g = (rgbCode(g1)*16)+rgbCode(g2);
                    int b = (rgbCode(b1)*16)+rgbCode(b2);

                    seekBar1.setProgress(r);
                    seekBar2.setProgress(g);
                    seekBar3.setProgress(b);


                }
                else
                {
                    Toast.makeText(MainActivity.this, "Invalid input", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int id = seekBar.getId();
        switch(id){
            case R.id.seekBar1: r=progress;
                etTextR.setText(r.toString());
                etTextG.setText(g.toString());
                etTextB.setText(b.toString());
                etTextHEX.setText("#" + hexcode(r) + hexcode(g) + hexcode(b));
                Log.d("TAG", "onCreate: " + hexcode(r) + hexcode(g) + hexcode(b));
                llMain.setBackgroundColor(Color.rgb(r,g,b));
                break;

            case R.id.seekBar2: g=progress;
                etTextR.setText(r.toString());
                etTextG.setText(g.toString());
                etTextB.setText(b.toString());
                etTextHEX.setText("#" + hexcode(r) + hexcode(g) + hexcode(b));
                Log.d("TAG", "onCreate: " + hexcode(r) + hexcode(g) + hexcode(b));
                llMain.setBackgroundColor(Color.rgb(r,g,b));
                break;

            case R.id.seekBar3: b=progress;


                etTextR.setText(r.toString());
                etTextG.setText(g.toString());
                etTextB.setText(b.toString());
                etTextHEX.setText("#" + hexcode(r) + hexcode(g) + hexcode(b));
                Log.d("TAG", "onCreate: " + hexcode(r) + hexcode(g) + hexcode(b));
                llMain.setBackgroundColor(Color.rgb(r,g,b));
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

        Log.d(TAG, "onStartTrackingTouch: ");
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        Log.d(TAG, "onStopTrackingTouch: ");
    }



    static String hexcode(int a)
    {
        String temp="";

        int x1 = a%16;
        int x2 = a/16;

        switch(x2)
        {
            case 0: temp += "0";break;
            case 1: temp += "1";break;
            case 2: temp += "2";break;
            case 3: temp += "3";break;
            case 4: temp += "4";break;
            case 5: temp += "5";break;
            case 6: temp += "6";break;
            case 7: temp += "7";break;
            case 8: temp += "8";break;
            case 9: temp += "9";break;
            case 10: temp += "a";break;
            case 11: temp += "b";break;
            case 12: temp += "c";break;
            case 13: temp += "d";break;
            case 14: temp += "e";break;
            case 15: temp += "f";break;
        }

        switch(x1)
        {
            case 0: temp += "0";break;
            case 1: temp += "1";break;
            case 2: temp += "2";break;
            case 3: temp += "3";break;
            case 4: temp  += "4";break;
            case 5: temp += "5";break;
            case 6: temp += "6";break;
            case 7: temp += "7";break;
            case 8: temp += "8";break;
            case 9: temp += "9";break;
            case 10: temp += "a";break;
            case 11: temp += "b";break;
            case 12: temp += "c";break;
            case 13: temp += "d";break;
            case 14: temp += "e";break;
            case 15: temp += "f";break;
        }

        return temp;
    }

    static Integer rgbCode(char a)
    {
        Integer ans=0;
        switch(a)
        {
            case '0' : ans = 0;break;
            case '1' : ans = 1;break;
            case '2' : ans = 2;break;
            case '3' : ans = 3;break;
            case '4' : ans = 4;break;
            case '5' : ans = 5;break;
            case '6' : ans = 6;break;
            case '7' : ans = 7;break;
            case '8' : ans = 8;break;
            case '9' : ans = 9;break;
            case 'a' : ans = 10;break;
            case 'b' : ans = 11;break;
            case 'c' : ans = 12;break;
            case 'd' : ans = 13;break;
            case 'e' : ans = 14;break;
            case 'f' : ans = 15;break;
        }
        return ans;
    }


}
