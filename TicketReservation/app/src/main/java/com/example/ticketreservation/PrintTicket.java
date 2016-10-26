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
import android.widget.TextView;

public class PrintTicket extends AppCompatActivity {
    Ticket ticket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print_ticket);
        TextView name=(TextView)findViewById(R.id.textView_itenaryName);
        TextView source=(TextView)findViewById(R.id.textView_ItenarySource);
        TextView destination=(TextView)findViewById(R.id.textView_itenaryDestination);
        TextView departure=(TextView)findViewById(R.id.textView_itenaryDepature);
        TextView returnDate=(TextView)findViewById(R.id.textView_itenaryReturn);

        final Button finish=(Button)findViewById(R.id.button_Itenaryfinish);

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra(MainActivity.TICKET_KEY,ticket);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        ticket=(Ticket) getIntent().getExtras().getParcelable("TicketObject");
            Log.d("demo1","ticket in print: "+ticket.toString());
            name.setText(ticket.getName());
            source.setText(ticket.getSource());
            destination.setText(ticket.getDestination());
            departure.setText(ticket.getDepartureDate().toString() + "," + ticket.getDepartureTime());
            Log.d("demo", "returning time" + ticket.getReturningTime());
            Log.d("demo","returning date"+ ticket.getReturningDate());
        //




            if(ticket.getTrip().equals("round-trip"))
            {

                Log.d("demo1", "round");
                returnDate.setText(ticket.getReturningDate().toString() + "," + ticket.getReturningTime());

            } else
            {
                Log.d("demo1", "round");
                returnDate.setVisibility(View.INVISIBLE);
            }

            MainActivity.tickets.add(ticket);
            Log.d("demo1",MainActivity.tickets.toString());
        }

}
