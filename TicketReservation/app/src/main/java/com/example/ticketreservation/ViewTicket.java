/*
Group 23

Hardik Thakkar
Sindhuja vemulapalli
Ruoshan Qin
 */



package com.example.ticketreservation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ViewTicket extends AppCompatActivity {
    ArrayList<Ticket> tickets;

    SimpleDateFormat format=null;
    final static String noOfCount ="number";



    Ticket ticket;
    TextView name, source, destination, departureDate, departureTime, returningDate, returningTime;
    RadioGroup radioGroup;
    RadioButton oneway,roundtrip;
    Button selectTicketBtn, finishBtn;
    static int numberOfTickets;
    static int numberOfCount;
    ImageButton firstButton,previousButton,nextButton,lastButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ticket);


        name = (TextView)findViewById(R.id.editText_name);
        source = (TextView)findViewById(R.id.editText_source);
        destination = (TextView)findViewById(R.id.editText_destination);
        departureDate = (TextView)findViewById(R.id.editText_departuredate);
        departureTime = (TextView)findViewById(R.id.editText_departuretime);
        returningDate = (TextView)findViewById(R.id.editText_returndate);
        returningTime = (TextView)findViewById(R.id.editText_returntime);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        oneway=(RadioButton)findViewById(R.id.radioButton_oneway);
        roundtrip=(RadioButton)findViewById(R.id.radioButton_roundtrip);
        selectTicketBtn = (Button)findViewById(R.id.button_select);
        finishBtn = (Button)findViewById(R.id.button_Finish);
        firstButton= (ImageButton) findViewById(R.id.imageButtonFirst);
        previousButton= (ImageButton) findViewById(R.id.imageButtonPrevious);
        lastButton= (ImageButton) findViewById(R.id.imageButtonLast);
        nextButton= (ImageButton) findViewById(R.id.imageButtonNext);

        tickets = new ArrayList<Ticket>();
        tickets.addAll(MainActivity.tickets);
        numberOfTickets = tickets.size()-1;
       // Toast.makeText(getApplicationContext(), numberOfTickets + "", Toast.LENGTH_LONG).show();
        //tickets= getIntent().getExtras().getParcelableArrayList(MainActivity.view_key);
        numberOfCount = 0;

        Log.d("demo", MainActivity.tickets.toString());
        Log.d("demo", tickets.get(0).getTrip());

        if(numberOfTickets==-1)
        {
            Toast.makeText(getApplicationContext(),"No Ticekts To show",Toast.LENGTH_LONG).show();
            firstButton.setEnabled(false);
            previousButton.setEnabled(false);
            lastButton.setEnabled(false);
            nextButton.setEnabled(false);
            finishBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(ViewTicket.this, MainActivity.class);
                    startActivity(intent);


                }
            });
        }
        else
        {

            firstButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name.setText(tickets.get(0).getName());
                    source.setText(tickets.get(0).getSource());
                    destination.setText(tickets.get(0).getSource());

                    if(tickets.get(0).getTrip().equals("one-way"))
                    {
                        oneway.setChecked(true);
                        departureDate.setText(tickets.get(0).getDepartureDate().toString());
                        departureTime.setText(tickets.get(0).getDepartureTime());
                    }
                    else
                    {
                        roundtrip.setChecked(true);
                        departureDate.setText(tickets.get(0).getDepartureDate().toString());
                        departureTime.setText(tickets.get(0).getDepartureTime());
                        returningDate.setVisibility(View.VISIBLE);
                        returningTime.setVisibility(View.VISIBLE);
                        returningDate.setText(tickets.get(0).getReturningDate().toString());
                        returningDate.setText(tickets.get(0).getReturningTime().toString());

                    }
                }
            });

            previousButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(numberOfCount == 0)
                    {
                        Toast.makeText(getApplicationContext(),"You reach the first ticket",Toast.LENGTH_LONG).show();
                        name.setText(tickets.get(0).getName());
                        source.setText(tickets.get(0).getSource());
                        destination.setText(tickets.get(0).getSource());

                        if(tickets.get(0).getTrip().equals("one-way"))
                        {
                            oneway.setChecked(true);
                            departureDate.setText(tickets.get(0).getDepartureDate().toString());
                            departureTime.setText(tickets.get(0).getDepartureTime());
                        }
                        else
                        {
                            roundtrip.setChecked(true);
                            departureDate.setText(tickets.get(0).getDepartureDate().toString());
                            departureTime.setText(tickets.get(0).getDepartureTime());
                            returningDate.setVisibility(View.VISIBLE);
                            returningTime.setVisibility(View.VISIBLE);
                            returningDate.setText(tickets.get(0).getReturningDate().toString());
                            returningDate.setText(tickets.get(0).getReturningTime().toString());

                        }
                    }

                    else
                    {
                        numberOfCount--;
                        name.setText(tickets.get(numberOfCount).getName());
                        source.setText(tickets.get(numberOfCount).getSource());
                        destination.setText(tickets.get(numberOfCount).getSource());

                        if(tickets.get(numberOfCount).getTrip().equals("one-way"))
                        {
                            oneway.setChecked(true);
                            departureDate.setText(tickets.get(numberOfCount).getDepartureDate().toString());
                            departureTime.setText(tickets.get(numberOfCount).getDepartureTime());
                        }
                        else
                        {
                            roundtrip.setChecked(true);
                            departureDate.setText(tickets.get(numberOfCount).getDepartureDate().toString());
                            departureTime.setText(tickets.get(numberOfCount).getDepartureTime());
                            returningDate.setVisibility(View.VISIBLE);
                            returningTime.setVisibility(View.VISIBLE);
                            returningDate.setText(tickets.get(numberOfCount).getReturningDate().toString());
                            returningTime.setText(tickets.get(numberOfCount).getReturningTime().toString());

                        }

                    }
                }
            });



            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(numberOfCount == (numberOfTickets))
                    {
                        Toast.makeText(getApplicationContext(),"You reach the last ticket",Toast.LENGTH_LONG).show();
                        numberOfCount = numberOfTickets;

                        name.setText(tickets.get(numberOfCount).getName());
                        source.setText(tickets.get(numberOfCount).getSource());
                        destination.setText(tickets.get(numberOfCount).getSource());

                        if(tickets.get(numberOfCount).getTrip().equals("one-way"))
                        {
                            oneway.setChecked(true);
                            departureDate.setText(tickets.get(numberOfCount).getDepartureDate().toString());
                            departureTime.setText(tickets.get(numberOfCount).getDepartureTime());
                        }
                        else
                        {
                            roundtrip.setChecked(true);
                            departureDate.setText(tickets.get(numberOfCount).getDepartureDate().toString());
                            departureTime.setText(tickets.get(numberOfCount).getDepartureTime());
                            returningDate.setVisibility(View.VISIBLE);
                            returningTime.setVisibility(View.VISIBLE);
                            returningDate.setText(tickets.get(numberOfCount).getReturningDate().toString());
                            returningTime.setText(tickets.get(numberOfCount).getReturningTime().toString());

                        }

                    }
                    else
                    {
                        numberOfCount++;
                        name.setText(tickets.get(numberOfCount).getName());
                        source.setText(tickets.get(numberOfCount).getSource());
                        destination.setText(tickets.get(numberOfCount).getSource());
                        if(tickets.get(numberOfCount).getTrip().equals("one-way"))
                        {
                            oneway.setChecked(true);
                            departureDate.setText(tickets.get(numberOfCount).getDepartureDate().toString());
                            departureTime.setText(tickets.get(numberOfCount).getDepartureTime());
                        }
                        else
                        {
                            roundtrip.setChecked(true);
                            departureDate.setText(tickets.get(numberOfCount).getDepartureDate().toString());
                            departureTime.setText(tickets.get(numberOfCount).getDepartureTime());
                            returningDate.setVisibility(View.VISIBLE);
                            returningTime.setVisibility(View.VISIBLE);
                            returningDate.setText(tickets.get(numberOfCount).getReturningDate().toString());
                            returningTime.setText(tickets.get(numberOfCount).getReturningTime().toString());

                        }
                    }
                }
            });

            lastButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    numberOfCount = numberOfTickets;
                    name.setText(tickets.get(numberOfCount).getName());
                    source.setText(tickets.get(numberOfCount).getSource());
                    destination.setText(tickets.get(numberOfCount).getSource());

                    if(tickets.get(numberOfCount).getTrip().equals("one-way"))
                    {
                        oneway.setChecked(true);
                        departureDate.setText(tickets.get(numberOfCount).getDepartureDate().toString());
                        departureTime.setText(tickets.get(numberOfCount).getDepartureTime());
                    }
                    else
                    {
                        roundtrip.setChecked(true);
                        departureDate.setText(tickets.get(numberOfCount).getDepartureDate().toString());
                        departureTime.setText(tickets.get(numberOfCount).getDepartureTime());
                        returningDate.setVisibility(View.VISIBLE);
                        returningTime.setVisibility(View.VISIBLE);
                        returningDate.setText(tickets.get(numberOfCount).getReturningDate().toString());
                        returningTime.setText(tickets.get(numberOfCount).getReturningTime().toString());

                    }
                }
            });

            finishBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(ViewTicket.this,MainActivity.class);
                    startActivity(intent);


                }
            });
        }

    }
}
