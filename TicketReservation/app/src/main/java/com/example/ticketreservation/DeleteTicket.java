package com.example.ticketreservation;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DeleteTicket extends AppCompatActivity {
    final static int printCode = 0x0007;
    final CharSequence[] items = {"Albany, NY", "Atlanta, GA", "Boston, MA", "Charlotte, NC", "Chicago, IL", "Greenville, SC",
            "Houston, TX", "Las Vegas, NV", "Los Angeles, CA", "Miami, FL", "Myrtle Beach, SC", "New York, NY", "Portland, OR",
            "Raleigh, NC", "San Jose, CA", "Washington, DC"};
    final static String Delete_Key = "delete";
    private CharSequence[] nameList;

    ArrayList<Ticket> tickets;

    SimpleDateFormat format = null;
    static int number;
    Ticket ticket;
    EditText name, source, destination, departureDate, departureTime, returningDate, returningTime;
    RadioGroup radioGroup;
    RadioButton oneway, roundtrip;
    Button selectTicketBtn, deleteBtn, cancelBtn;
    int editTicketNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_ticket);

        try {
            name = (EditText) findViewById(R.id.editText_name);
            source = (EditText) findViewById(R.id.editText_source);
            destination = (EditText) findViewById(R.id.editText_destination);
            departureDate = (EditText) findViewById(R.id.editText_departuredate);
            departureTime = (EditText) findViewById(R.id.editText_departuretime);
            returningDate = (EditText) findViewById(R.id.editText_returningdate);
            returningTime = (EditText) findViewById(R.id.editText_returningtime);
            radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
            oneway = (RadioButton) findViewById(R.id.onewayRadio);
            roundtrip = (RadioButton) findViewById(R.id.roundtripRadio);
            selectTicketBtn = (Button) findViewById(R.id.button_select);
            deleteBtn = (Button) findViewById(R.id.button_delete);
            cancelBtn = (Button) findViewById(R.id.button_cancel);

            editTicketNumber = 0;

      /*  //items.add(ticket.getName());
        if (getIntent().getExtras() != null)
            tickets = getIntent().getExtras().getParcelableArrayList("TICKETS");//look at me! Don't know whether here is correct.
*/
            number = 0;
            tickets = new ArrayList<>();
            tickets.clear();
            if (number == 0) {
                tickets.addAll(MainActivity.tickets);
            }

            if (tickets != null) {
                Log.d("demo1", "the size is " + tickets.size());
                updateNameList();
            } else {
                Log.d("demo1", "its null");
            }


            final AlertDialog.Builder selectBuilder = new AlertDialog.Builder(this);
            selectBuilder.setTitle("Pick a Ticket")
                    .setSingleChoiceItems(nameList, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //name.setText(nameList[which]);
                            String name = nameList[which] + "";
                            editTicketNumber = which;
                            Log.d("demo", editTicketNumber + "");
                            ticket = tickets.get(which);
                            // ticket = findTicketByName(name);
                        }
                    }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                        display(ticket);




                    Log.d("demo", "Which value is " + editTicketNumber);
                }
            });
            final AlertDialog selectDialog = selectBuilder.create();
            if(tickets.size()==0)
            {
                selectTicketBtn.setEnabled(false);
            }
            else {
                selectTicketBtn.setEnabled(true);


                selectTicketBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectDialog.show();
                    }
                });
            }

            if (oneway.isChecked()) {
                returningDate.setVisibility(View.INVISIBLE);
                returningTime.setVisibility(View.INVISIBLE);
            }

            oneway.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    returningDate.setVisibility(View.INVISIBLE);
                    returningTime.setVisibility(View.INVISIBLE);
                }
            });

            roundtrip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    returningDate.setVisibility(View.VISIBLE);
                    returningTime.setVisibility(View.VISIBLE);
                }
            });

            int maxLength = 20;
            InputFilter[] FilterArray = new InputFilter[1];
            FilterArray[0] = new InputFilter.LengthFilter(maxLength);
            name.setFilters(FilterArray);

            final AlertDialog.Builder sourceBuilder = new AlertDialog.Builder(this);
            sourceBuilder.setTitle("Source").setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    source.setText(items[which]);
                }
            }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            final AlertDialog sourceAlertDialog = sourceBuilder.create();

            source.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sourceAlertDialog.show();
                }
            });

            final AlertDialog.Builder destinationBuilder = new AlertDialog.Builder(this);
            destinationBuilder.setTitle("Destination").setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    destination.setText(items[which]);

                }
            }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            final AlertDialog destinationAlertDialog = destinationBuilder.create();

            destination.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    destinationAlertDialog.show();
                }
            });

            Calendar departureCalender = Calendar.getInstance();


            final DatePickerDialog departureDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    String year1 = String.valueOf(year);
                    String month1 = String.valueOf(monthOfYear + 1);
                    String day1 = String.valueOf(dayOfMonth);
                    departureDate.setText(month1 + "/" + day1 + "/" + year1);

                }
            }, departureCalender.get(Calendar.YEAR),
                    departureCalender.get(Calendar.MONTH),
                    departureCalender.get(Calendar.DAY_OF_MONTH));

            departureDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    departureDatePicker.show();
                }
            });

            int hour = departureCalender.get(Calendar.HOUR_OF_DAY);
            int minute = departureCalender.get(Calendar.MINUTE);
            final TimePickerDialog departureTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String departurehour = String.valueOf(hourOfDay);
                    String departureminute = String.valueOf(minute);
                    departureTime.setText(departurehour + ":" + departureminute);

                }
            }, hour, minute, false);
            departureTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    departureTimePicker.show();
                }

            });

            final DatePickerDialog returningDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    String year1 = String.valueOf(year);
                    String month1 = String.valueOf(monthOfYear + 1);
                    String day1 = String.valueOf(dayOfMonth);
                    returningDate.setText(month1 + "/" + day1 + "/" + year1);

                }
            }, departureCalender.get(Calendar.YEAR),
                    departureCalender.get(Calendar.MONTH),
                    departureCalender.get(Calendar.DAY_OF_MONTH));

            returningDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    returningDatePicker.show();
                }
            });


            final TimePickerDialog returningTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String departurehour = String.valueOf(hourOfDay);
                    String departureminute = String.valueOf(minute);
                    returningTime.setText(departurehour + ":" + departureminute);
                }
            }, hour, minute, false);
            returningTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    returningTimePicker.show();
                }

            });

            ticket = new Ticket();

            format = new SimpleDateFormat("MM/dd/yyyy");

            if(tickets.size()==0)
            {
                Toast.makeText(getApplicationContext(),"Null value",Toast.LENGTH_LONG).show();
                deleteBtn.setEnabled(false);
            }
            else {
                deleteBtn.setEnabled(true);

                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Log.d("demo", "inside the onclick event");
                        MainActivity.tickets.remove(editTicketNumber);
                        Log.d("demo", "after delete" + tickets.size());

                        number = 1;
                        updateNameList();
                        finish();
                    }
                });
            }
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DeleteTicket.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }catch (NullPointerException e)
        {
            Toast.makeText(getApplicationContext(),"Null Value",Toast.LENGTH_LONG).show();
        }}

//    public void onClick(View v) {
//        if (name.getText().length() > 0)
//            if (source.getText().length() > 0)
//                if (destination.getText().length() > 0) {
//                    Intent data = new Intent();
//                    data.putExtra(MainActivity.TICKET_KEY, ticket);
//                    setResult(RESULT_OK, data);
//                    finish();
//                }
//                else Toast.makeText(this, "Please Select a Destination", Toast.LENGTH_SHORT).show();
//            else Toast.makeText(this, "Please Select a Source", Toast.LENGTH_SHORT).show();
//        else Toast.makeText(this, "Please Input a Name", Toast.LENGTH_SHORT).show();
//    }

    private void display(Ticket ticket) {
        name.setText(ticket.getName());
        source.setText(ticket.getSource());
        destination.setText(ticket.getDestination());
        departureDate.setText(ticket.getDepartureDate().toString());
        departureTime.setText(ticket.getDepartureTime());
        if (ticket.getReturningDate() != null)
            returningDate.setText(ticket.getReturningDate().toString());
        else
            returningDate.setVisibility(View.INVISIBLE);
        if (ticket.getReturningTime() != null)
            returningTime.setText(ticket.getReturningTime());
        else
            returningTime.setVisibility(View.INVISIBLE);

        if (ticket.getTrip().equals("one-way"))
            oneway.setChecked(true);
        else
            roundtrip.setChecked(true);

    }

    void updateNameList() {
        nameList = new CharSequence[tickets.size()];
        Log.d("demo1", "in updateNameList " + tickets.toString());
        int i = 0;
        for (Ticket ticket : tickets) {
            nameList[i++] = ticket.getName();
        }
    }

    Ticket findTicketByName(String name) {
        for (Ticket ticket : MainActivity.tickets) {
            if (name.equals(ticket.getName()))
                return ticket;
        }
        return null;
    }


}
