/*
*Assignment:HomeWork1
* File name:ComputerCalculator
* Full Name:-Hardik Thakkar
*           Ruoshan Qin
*           Sindhuja vemulapalli
 */



package com.example.computercalculator;

import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity     {

    DecimalFormat df;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SeekBar tipSeekBar = (SeekBar) findViewById(R.id.seekBar_tip);
        tipSeekBar.setProgress(3);
        tipSeekBar.setMax(5);

        df= new DecimalFormat("#.##");


        final TextView progressText = (TextView) findViewById(R.id.textView3_progress);
        progressText.setText("" + tipSeekBar.getProgress() * 5);
      // EditText budget = (EditText) findViewById(R.id.editText_budget);


      /*
        budget.addTextChangedListener((TextWatcher) budget);
        String s1= budget.getText().toString();
        if(s1.matches(""))*/



        //Variable value







        //Calculate _Button_Event
        final Button btn = (Button) findViewById(R.id.button_Calculate);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //variale assignment
                EditText editText = (EditText) findViewById(R.id.editText_budget);
                double storagevaluedouble = 250;
                double memoryvaluedouble = 2;
                int CheckBoxCount = 0;
                double seekBarValue;
                double Delivery = 1;
                double outputValue = 0.00;


                //userinput


                if (!editText.getText().toString().matches("")) {


                    //Toast.makeText(getApplicationContext(),"Enter Your Budget!!!",Toast.LENGTH_LONG).show();
                    // Log.d("Demo", "Enter some value");

                    //Memory finder


                     RadioGroup memory = (RadioGroup) findViewById(R.id.radioGroup);
                    String memoryvalue = ((RadioButton) findViewById(memory.getCheckedRadioButtonId())).getText().toString();
                      if (memoryvalue.matches("2GB")) {
                        memoryvaluedouble = 2;


                    }
                    if (memoryvalue.matches("4GB")) {
                        memoryvaluedouble = 4;

                    }
                    if (memoryvalue.matches("8GB")) {
                        memoryvaluedouble = 8;
                    }
                    if (memoryvalue.matches("16GB")) {
                        memoryvaluedouble = 16;
                    }

                    //Storage
                    RadioGroup storage = (RadioGroup) findViewById(R.id.radiogroup_storage);
                    String storagevalue = ((RadioButton) findViewById(memory.getCheckedRadioButtonId())).getText().toString();

                    if (storagevalue.matches("250GB")) {
                        storagevaluedouble = 250;
                    }
                    if (storagevalue.matches("500GB")) {
                        storagevaluedouble = 500;
                    }
                    if (storagevalue.matches("750GB")) {
                        storagevaluedouble = 750;
                    }
                    if (storagevalue.matches("1TB")) {
                        storagevaluedouble = 1000;
                    }
                    //Checkbox
                    CheckBox mouse = (CheckBox) findViewById(R.id.checkBox_mouse);
                    CheckBox FlashDrive = (CheckBox) findViewById(R.id.checkBox_flashDrive);
                    CheckBox CoolingPad = (CheckBox) findViewById(R.id.checkBox_CoolingPad);
                    CheckBox CarryingCase = (CheckBox) findViewById(R.id.checkBox_carryingCase);
                    //checkBox counter
                    if (mouse.isChecked()) {
                        CheckBoxCount++;
                    }
                    if (FlashDrive.isChecked()) {
                        CheckBoxCount++;
                    }
                    if (CoolingPad.isChecked()) {
                        CheckBoxCount++;
                    }
                    if (CarryingCase.isChecked()) {
                        CheckBoxCount++;
                    }

                    Log.d("Demo", CheckBoxCount + "");


                    //seekbar

                    SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar_tip);
                    seekBarValue = seekBar.getProgress() * 5;


                    //Switch

                    Switch switch_delivery = (Switch) findViewById(R.id.switch_delivery);
                    if (switch_delivery.isChecked()) {
                        Delivery = 1;
                       // Toast.makeText(getApplicationContext(), "Selected", Toast.LENGTH_LONG).show();
                    } else {
                        Delivery = 0;
                      //
                       //Toast.makeText(getApplicationContext(), "Not Selected", Toast.LENGTH_LONG).show();

                    }

                    //priceOutputand calculation

                    TextView textviewprice = (TextView) findViewById(R.id.textViewprice);


                    outputValue = ((((10 * memoryvaluedouble) + (.75 * storagevaluedouble) + (20 * CheckBoxCount)) +
                            (1 + (seekBarValue / 100))) + (5.95 * Delivery));
                    textviewprice.setText("$" + df.format(outputValue)+ "");


                    String edit = editText.getText().toString();
                    double editdouble =Double.parseDouble(edit);

                    if(outputValue <= editdouble)
                    {
                        Log.d("Demo","within limit");
                        TextView textViewStatusOp = (TextView) findViewById(R.id.textViewstatusop);
                        textViewStatusOp.setText("Within Budget");
                        textViewStatusOp.setBackgroundColor(Color.GREEN);
                    }
                    else
                    {
                        TextView textViewStatusOp = (TextView) findViewById(R.id.textViewstatusop);
                        textViewStatusOp.setText("Over Budget");
                        textViewStatusOp.setBackgroundColor(Color.RED);
                    }



                }
                else
                {
                    editText.setError("Enter the value in budger!!!");
                }

                //Toast.makeText(this,"Enter some data",Toast.LENGTH_LONG).show();
            }

        });






        //Reset Button

            Button resetbutton = (Button) findViewById(R.id.button_reset);
             resetbutton.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     EditText editText = (EditText) findViewById(R.id.editText_budget);
                     RadioGroup memory = (RadioGroup) findViewById(R.id.radioGroup);
                     RadioGroup storage = (RadioGroup) findViewById(R.id.radiogroup_storage);
                     CheckBox mouse = (CheckBox) findViewById(R.id.checkBox_mouse);
                     CheckBox FlashDrive = (CheckBox) findViewById(R.id.checkBox_flashDrive);
                     CheckBox CoolingPad = (CheckBox) findViewById(R.id.checkBox_CoolingPad);
                     CheckBox CarryingCase = (CheckBox) findViewById(R.id.checkBox_carryingCase);
                     SeekBar tipSeekBar = (SeekBar) findViewById(R.id.seekBar_tip);
                     tipSeekBar.setProgress(3);
                     editText.setText("");
                     RadioButton radioButtonmemory = (RadioButton) findViewById(R.id.radioButton_2gb);
                     radioButtonmemory.setChecked(true);
                     RadioButton radioButtonstorage = (RadioButton) findViewById(R.id.radioButton_250gb);
                     radioButtonstorage.setChecked(true);
                     mouse.setChecked(false);
                     FlashDrive.setChecked(false);
                     CoolingPad.setChecked(false);
                     CarryingCase.setChecked(false);
                     Switch switch_delivery = (Switch) findViewById(R.id.switch_delivery);
                     switch_delivery.setChecked(true);
                     TextView textviewprice= (TextView) findViewById(R.id.textViewprice);
                     textviewprice.setText("");
                     TextView textviewstatusop = (TextView) findViewById(R.id.textViewstatusop);
                     textviewstatusop.setText("");
                 }
             });






        tipSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressText.setText("" + tipSeekBar.getProgress() * 5);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Checkbox function





    }



   /* @Override
    public void onClick(View v) {


    }*/

}

