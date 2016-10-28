/**
 * Group 23 Assignment 04
 * Hardik Thakkar
 * Sindhuja Vemulapalli
 * Roushan Qin
 *
 */

package com.example.inclass04;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
    ProgressDialog progressDialog;
    TextView passwordView;
    String selectedItem;
    CheckBox numberCheckbox;
    CheckBox lowercaseCheckbox;
    CheckBox uppperCaseCheckbox;
    CheckBox specialCharactersCheckbox;
    Handler handler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordView=(TextView)findViewById(R.id.textView_password);
        Button asyncTaskButton=(Button)findViewById(R.id.button_generatepasswordAsync);
        Button threadTask=(Button)findViewById(R.id.button_generatePasswordThread);
        numberCheckbox=(CheckBox)findViewById(R.id.checkBox_numbers);
        lowercaseCheckbox=(CheckBox)findViewById(R.id.checkBox_lowercaseetters);
        uppperCaseCheckbox=(CheckBox)findViewById(R.id.checkBox_uppercaseletters);
        specialCharactersCheckbox=(CheckBox)findViewById(R.id.checkBox_specialcharacters);

        final Spinner spinner= (Spinner) findViewById(R.id.spinner_password);
        ArrayAdapter<String> adapter;
        List<String> list;

        list = new ArrayList<String>();
        list.add("Select Password Length");
        list.add("8-12");
        list.add("13-17");
        list.add("18-22");

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        selectedItem=spinner.getSelectedItem().toString();

       /** spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedItem = spinner.getItemAtPosition(position).toString();
            }
        });**/
        asyncTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!numberCheckbox.isChecked()&&!lowercaseCheckbox.isChecked()&&
                        !uppperCaseCheckbox.isChecked() &&!specialCharactersCheckbox.isChecked() || spinner.getSelectedItemPosition()==0)
                {
                    passwordView.setText("");
                    Toast.makeText(MainActivity.this,"Please Select atleast One Checkbox and Password length",Toast.LENGTH_LONG).show();
                }
                else {
                    Boolean[] checkbox = new Boolean[4];

                    checkbox[0] = numberCheckbox.isChecked();
                    checkbox[2] = lowercaseCheckbox.isChecked();
                    checkbox[1] = uppperCaseCheckbox.isChecked();
                    checkbox[3] = specialCharactersCheckbox.isChecked();
                    new DoWork().execute(checkbox);
                }

            }
        });

        threadTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!numberCheckbox.isChecked()&&!lowercaseCheckbox.isChecked()&&
                        !uppperCaseCheckbox.isChecked() &&!specialCharactersCheckbox.isChecked() || spinner.getSelectedItemPosition()==0)
                {
                    passwordView.setText("");
                    Toast.makeText(MainActivity.this,"Please Select atleast One checkbox and password length",Toast.LENGTH_LONG).show();
                }
                else {
                    progressDialog = new ProgressDialog(MainActivity.this);
                    progressDialog.setTitle("Generating Passwords");
                    progressDialog.setCancelable(false);
                    progressDialog.show();
                    new Thread(new HandlerClass()).start();
                }

            }
        });
       handler=new Handler(new Handler.Callback() {
           @Override
           public boolean handleMessage(Message msg) {

               switch(msg.what)
               {
                   case HandlerClass.ACTIVITY_START:
                      // progressDialog.show();
                       break;
                   case HandlerClass.ACTIVITY_STEP:

                       passwordView.setText(msg.obj.toString());
                       break;


                   case  HandlerClass.ACTIVITY_STOP:
                       progressDialog.dismiss();

                       break;
               }
               return false;
           }
       });

    }






    class DoWork extends AsyncTask<Boolean,Integer,String>
    {
        ArrayList<Double> numbers=new ArrayList<Double>();
        double sum;

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);


        }

        @Override
        protected void onPostExecute(String result) {

            progressDialog.dismiss();
            passwordView.setText(result);


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Generating Passwords");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Boolean... params) {
            int length=0;
         if(selectedItem.equals("8-12")) {
             length = 0;
         }
            else if(selectedItem.equals("13-17"))
         {
             length=1;
         }
            else if(selectedItem.equals("18-22"))
         {
             length=2;
         }
         String password=Util_New.getPassword(length,params[0],params[1],params[2],params[3]);


            return  password;
        }
    }

    class HandlerClass implements Runnable
    {
        static final  int ACTIVITY_START= 0x00;
        static final  int ACTIVITY_STEP =0x01;
        static final  int ACTIVITY_STOP = 0x02;


        @Override
        public void run()
        {

            Message msg = new Message();
            msg.what = ACTIVITY_START;
            int length=0;
            if(selectedItem.equals("8-12")) {
                length = 0;
            }
            else if(selectedItem.equals("13-17"))
            {
                length=1;
            }
            else if(selectedItem.equals("18-22"))
            {
                length=2;
            }

            msg = new Message();
            msg.what = ACTIVITY_STEP;
            String passwordhandler =Util_New.getPassword(length,numberCheckbox.isChecked(),uppperCaseCheckbox.isChecked(),lowercaseCheckbox.isChecked(),specialCharactersCheckbox.isChecked());

            msg.obj = passwordhandler;
            handler.sendMessage(msg);

            msg = new Message();
            msg.what = ACTIVITY_STOP;
            handler.sendMessage(msg);




        }
    }

}
