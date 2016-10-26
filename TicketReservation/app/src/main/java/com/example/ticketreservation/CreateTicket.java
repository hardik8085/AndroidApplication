/*
Group 23

Hardik Thakkar
Sindhuja vemulapalli
Ruoshan Qin
 */

package com.example.ticketreservation;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateTicket extends AppCompatActivity {
    final static int printCode=0x0007;
    final static String TicketObject ="TicketOject";



    Date dateFirst = new Date();
    int firstdate = 0;
    int compareDateR = 0;
    int compareDateF  = 0;
    int todate;
    Date date_Time = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final CharSequence[] items = {"Albany, NY", "Atlanta, GA", "Boston, MA", "Charlotte, NC", "Chicago, IL", "Greenville, SC",
                "Houston, TX", "Las Vegas, NV", "Los Angeles, CA", "Miami, FL", "Myrtle Beach, SC", "New York, NY", "Portland, OR",
                "Raleigh, NC", "San Jose, CA", "Washington, DC"};


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ticket);

        final EditText source = (EditText) findViewById(R.id.editText_source);
        final EditText nameText = (EditText) findViewById(R.id.editText_name);
        final EditText destination = (EditText) findViewById(R.id.editText_destination);
        final EditText departureDate = (EditText) findViewById(R.id.editText_departuredate);
        final EditText departureTime = (EditText) findViewById(R.id.editText_departuretime);
        final EditText returningDate = (EditText) findViewById(R.id.editText_returndate);
        final EditText returningTime = (EditText) findViewById(R.id.editText_returntime);
        final RadioButton oneway = (RadioButton) findViewById(R.id.radioButton_oneway);
        final RadioButton roundtrip = (RadioButton) findViewById(R.id.radioButton_roundtrip);
        final Button printSummaryButton = (Button) findViewById(R.id.button_printsummary);


        /**
         * hiding return time and date if one way is checked
         */

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

        /**
         * Setting maximum length to name
         */
        // nameText.setKeyListener(null);
        int maxLength = 20;
        InputFilter[] FilterArray = new InputFilter[1];
        FilterArray[0] = new InputFilter.LengthFilter(maxLength);
        nameText.setFilters(FilterArray);


        /**
         * Opening alert Dialog for Source
         */
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

        /** source.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override public void onFocusChange(View v, boolean hasFocus) {
        sourceAlertDialog.show();
        }
        });**/

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

        /**destination.setOnFocusChangeListener(new View.OnFocusChangeListener() {
        @Override public void onFocusChange(View v, boolean hasFocus) {
        destinationAlertDialog.show();
        }
        });**/
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
        departureDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

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
        returningDatePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);


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

        final Ticket ticket = new Ticket();
        final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
       /* printSummaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateTicket.this,"App is Running",Toast.LENGTH_LONG).show();
            }
        });*/
        printSummaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   if ((source.getText().toString() != "Albany, NY" && source.getText().toString() != "Atlanta, GA" && source.getText().toString() != "Boston, MA" && source.getText().toString() != "Charlotte, NC"
                        && source.getText().toString() != "Chicago, IL" && source.getText().toString() != "Greenville, SC" && source.getText().toString() != "Houston, TX" && source.getText().toString() != "Las Vegas, NV"
                        && source.getText().toString() != "Los Angeles, CA" && source.getText().toString() != "Miami, FL" && source.getText().toString() != "Myrtle Beach, SC" && source.getText().toString() != "New York, NY" &&
                        source.getText().toString() != "Portland, OR" && source.getText().toString() != "Raleigh, NC" && source.getText().toString() != "San Jose, CA" && source.getText().toString() != "Washington, DC")) {*/
                    ticket.setName(nameText.getText().toString());
                    ticket.setSource(source.getText().toString());
                    ticket.setDestination(destination.getText().toString());


                    if (source.getText().toString().equals(destination.getText().toString())) {
                        Toast.makeText(CreateTicket.this, "Source and Destination are Same", Toast.LENGTH_LONG).show();

                    }
                    if (oneway.isChecked()) {
                        ticket.setTrip("one-way");
                    } else if (roundtrip.isChecked()) {
                        ticket.setTrip("round-trip");
                    }
                    try {
                        ticket.setDepartureDate(departureDate.getText().toString());
                        ticket.setReturningDate(returningDate.getText().toString());
                    } catch (Exception e) {
                        Log.d("demo", "Some Error Occured");
                    }
                    //Toast.makeText(CreateTicket.this,"" + ticket.getName().toString(),Toast.LENGTH_SHORT).show();
                    ticket.setDepartureTime(departureTime.getText().toString());
                    ticket.setReturningTime(returningTime.getText().toString());
                    Log.d("demo", "setInside" + returningTime.getText().toString());
                    Intent itenaryIntent = new Intent(CreateTicket.this, PrintTicket.class);
//               Bundle data=new Bundle();
//               data.putParcelable("TicketObject",ticket);
                    //               itenaryIntent.put("TicketObject", ticket);
                    Log.d("demo", "increate" + ticket.getReturningTime());
                    itenaryIntent.putExtra("TicketObject", ticket);
                    Log.d("demo", "increateafter intent" + ticket.getReturningTime());

                    if (ticket == null) {
                        Log.d("demo1", "it's null!");
                    } else {
                        Log.d("demo1", "it's not");
                    }
                    //itenaryIntent.putExtra("bundle",data);
                   // startActivity(itenaryIntent);
                    //startActivityForResult(itenaryIntent,printCode);

                try {
                    SimpleDateFormat formate = new SimpleDateFormat("MM/dd/yyyy HH:mm");


                    Date datefrom = formate.parse(departureDate.getText().toString() + " " + departureTime.getText().toString());
                    Log.d("CreateDemo", "" + datefrom.toString());
                    Date currentDate = new Date();
                    compareDateR = datefrom.compareTo(currentDate);
                    Log.d("InCreateDemo", "" + currentDate);
                    Log.d("InCreateDemo", "" + datefrom);


                    if (roundtrip.isChecked()) {
                        dateFirst = formate.parse(returningDate.getText().toString());
                        Log.d("CreateDemo", "" + dateFirst.toString());
                        firstdate = datefrom.compareTo(dateFirst);


                        compareDateF = dateFirst.compareTo(currentDate);
                    }


                    Log.d("CreateDemo2", "" + compareDateR);
                    Log.d("CreateDemo2", "" + compareDateF);

                } catch (Exception e) {
                    e.printStackTrace();
                }


                Log.d("Departurtre TIme",""+departureTime.getText().toString());

                if(nameText.getText().toString().isEmpty())
                {
                    Toast.makeText(CreateTicket.this, "Name Can not be Empty", Toast.LENGTH_LONG).show();
                    //   Button b = (Button)findViewById(R.id.buttonPrintSummary);
                    //  b.setEnabled(false);

                }

                else if (destination.getText().toString().isEmpty())
                {
                    Toast.makeText(CreateTicket.this, "Destination Name Can not be Empty", Toast.LENGTH_LONG).show();
                }

                else if (source.getText().toString().isEmpty())
                {
                    Toast.makeText(CreateTicket.this, "Source Name Can not be Empty", Toast.LENGTH_LONG).show();
                }

                else if (departureDate.getText().toString().isEmpty())
                {
                    Toast.makeText(CreateTicket.this, "Departure Date Can not be Empty", Toast.LENGTH_LONG).show();
                }

                else if ( roundtrip.isChecked() && returningDate.getText().toString().isEmpty() )
                {
                    Toast.makeText(CreateTicket.this, "Return Date Can not be Empty", Toast.LENGTH_LONG).show();
                }
                else if (departureTime.getText().toString().isEmpty())
                {
                    Toast.makeText(CreateTicket.this, "Departure Time Can not be Empty", Toast.LENGTH_LONG).show();
                }
                else if (roundtrip.isChecked() && returningTime.getText().toString().isEmpty()  )
                {
                    Toast.makeText(CreateTicket.this, "Return Time Can not be Empty", Toast.LENGTH_LONG).show();
                }



                else if (roundtrip.isChecked() && (todate == 1))
                {
                    Toast.makeText(CreateTicket.this, "Return Date should not be less then departure date ", Toast.LENGTH_LONG).show();
                }

                else if (compareDateR == -1 || compareDateF == -1 )
                {
                    Toast.makeText(CreateTicket.this, "Please Do not select past date", Toast.LENGTH_LONG).show();
                }
else
                {
                    //Intent itenaryIntent = new Intent(CreateTicket.this, PrintTicket.class);
                    startActivity(itenaryIntent);
                }


            }






        });



    }


    /*public static boolean checkIfEndDateIsAfter(String startDate, String endDate) {
        try {
            String myFormatString = "MM-dd-yy"; // for example
            SimpleDateFormat dateFormat = new SimpleDateFormat (myFormatString);
            Date endedDate = dateFormat.parse(endDate);
            Date startedDate = dateFormat.parse(startDate);

            if (endedDate.after(startedDate)) {
                return true;
            } else if (endedDate.equals(startedDate)) {
                return true; //some instances it is okay for it to be the same
            } else
                return false;
        } catch (Exception e) {

            return false;
        }
    }*/
}
