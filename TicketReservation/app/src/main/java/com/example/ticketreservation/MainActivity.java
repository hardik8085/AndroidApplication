/*
Group 23

Hardik Thakkar
Sindhuja vemulapalli
Ruoshan Qin
 */

package com.example.ticketreservation;

import android.app.Notification;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final int reqCreateCode = 0x0001;
    static final int reqEditCode = 0x0003;
    static final String TICKET_KEY = "ticket";
    static ArrayList<Ticket> tickets=new ArrayList<>();;
    Ticket ticket;

    TextView customerCareText;
    Button createTicketBtn;
    Button editTicketBtn;
    Button viewTicketBtn;
    Button deleteTicketBtn;
    Button finishTicketBtn;
    ImageView call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        customerCareText = (TextView)findViewById(R.id.textView_customerCare);
        createTicketBtn=(Button)findViewById(R.id.button_createticket);
        editTicketBtn=(Button)findViewById(R.id.button_editticket);
        viewTicketBtn = (Button) findViewById(R.id.button_viewticket);
        deleteTicketBtn= (Button) findViewById(R.id.button_deleteticket);
        finishTicketBtn= (Button) findViewById(R.id.button_Itenaryfinish);
        call = (ImageView) findViewById(R.id.imageView);
        customerCareText.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tell:9999999999"));
                //startActivity(intent);  //Need help! Can't figure it out.
            }
        });


        createTicketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CreateTicket.class);
                startActivity(intent);
                //startActivityForResult(intent, reqCreateCode);
            }
        });

        editTicketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,EditTicket.class);
                startActivity(intent);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(TICKET_KEY,ticket);
//                intent.putExtras(bundle);

                intent.putParcelableArrayListExtra("TICKETS",tickets);
                startActivityForResult(intent, reqEditCode);

            }
        });

        viewTicketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewTicket.class);
                startActivity(intent);
            }
        });

        deleteTicketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DeleteTicket.class);


                startActivity(intent);
            }
        });

        finishTicketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        customerCareText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9999999999"));
               startActivity(intent);
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "9999999999"));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==reqCreateCode){
            if(resultCode==RESULT_OK){
                tickets=(ArrayList)getIntent().getExtras().getParcelableArrayList("TICKETS");
                //tickets.add((Ticket)data.getExtras().getSerializable(TICKET_KEY));
            }
            else if(resultCode==RESULT_CANCELED){
                Log.d("demo1","something is wrong");
            }
        }
    }


}
