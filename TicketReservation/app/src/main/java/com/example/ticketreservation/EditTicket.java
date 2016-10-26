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
import java.util.Date;

public class EditTicket extends AppCompatActivity{
    final static int printCode=0x0007;
    final  CharSequence[] items={"Albany, NY","Atlanta, GA","Boston, MA","Charlotte, NC","Chicago, IL","Greenville, SC",
            "Houston, TX","Las Vegas, NV","Los Angeles, CA","Miami, FL","Myrtle Beach, SC","New York, NY","Portland, OR",
            "Raleigh, NC","San Jose, CA","Washington, DC"};

    Date dateFirst = new Date();
    int firstdate = 0;
    int compareDateR = 0;
    int compareDateF  = 0;
    int todate;
    Date date_Time = new Date();

    private CharSequence[] nameList;

    ArrayList<Ticket> tickets;

    SimpleDateFormat format=null;

    Ticket ticket;
    EditText name, source, destination, departureDate, departureTime, returningDate, returningTime;
    RadioGroup radioGroup;
    RadioButton oneway,roundtrip;
    Button selectTicketBtn, saveBtn;
    int editTicketNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_ticket);


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
        saveBtn = (Button) findViewById(R.id.button_delete);

        name.setEnabled(false);
        source.setEnabled(false);
        destination.setEnabled(false);
        departureDate.setEnabled(false);
        departureTime.setEnabled(false);
        returningDate.setEnabled(false);
        returningTime.setEnabled(false);
        oneway.setEnabled(false);
        roundtrip.setEnabled(false);
        saveBtn.setEnabled(false);

        editTicketNumber=0;

      /*  //items.add(ticket.getName());
        if (getIntent().getExtras() != null)
            tickets = getIntent().getExtras().getParcelableArrayList("TICKETS");//look at me! Don't know whether here is correct.
*/
        tickets = new ArrayList<>();
        tickets.clear();
        tickets.addAll(MainActivity.tickets);

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
                        Log.d("demo",editTicketNumber+"");
                        ticket = tickets.get(which);
                       // ticket = findTicketByName(name);
                    }
                }).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == -1 )
                {
                    name.setEnabled(true);
                    source.setEnabled(true);
                    destination.setEnabled(true);
                    departureDate.setEnabled(true);
                    departureTime.setEnabled(true);
                    returningDate.setEnabled(true);
                    returningTime.setEnabled(true);
                    oneway.setEnabled(true);
                    roundtrip.setEnabled(true);
                    saveBtn.setEnabled(true);
                }
                display(ticket);











                Log.d("demo","Which value is "+editTicketNumber);
            }
        });
        final AlertDialog selectDialog = selectBuilder.create();
        selectTicketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDialog.show();
            }
        });


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

         ticket = new Ticket();

        format = new SimpleDateFormat("MM/dd/yyyy");



        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("demo", "inside the onclick event");
                update();

                tickets.get(editTicketNumber).setName(name.getText().toString());
                tickets.get(editTicketNumber).setSource(source.getText().toString());
                tickets.get(editTicketNumber).setDestination(destination.getText().toString());

                if (oneway.isChecked()) {
                    tickets.get(editTicketNumber).setTrip("one-way");

                } else if (roundtrip.isChecked()) {
                    tickets.get(editTicketNumber).setTrip("round-trip");
                }
                try {
                    tickets.get(editTicketNumber).setDepartureDate (departureDate.getText().toString());
                    tickets.get(editTicketNumber).setReturningDate (returningDate.getText().toString());
                } catch (Exception e) {
                    Log.d("demo", "Some Error Occured");
                }
                tickets.get(editTicketNumber).setDepartureTime(departureTime.getText().toString());
                tickets.get(editTicketNumber).setReturningTime(returningTime.getText().toString());

                Log.d("demo", "after the update");

                updateNameList();
               finish();
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


                Log.d("Departurtre TIme", "" + departureTime.getText().toString());

                if(name.getText().toString().isEmpty())
                {
                    Toast.makeText(EditTicket.this, "Name Can not be Empty", Toast.LENGTH_LONG).show();
                    //   Button b = (Button)findViewById(R.id.buttonPrintSummary);
                    //  b.setEnabled(false);

                }

                else if (destination.getText().toString().isEmpty())
                {
                    Toast.makeText(EditTicket.this, "Destination Name Can not be Empty", Toast.LENGTH_LONG).show();
                }

                else if (source.getText().toString().isEmpty())
                {
                    Toast.makeText(EditTicket.this, "Source Name Can not be Empty", Toast.LENGTH_LONG).show();
                }

                else if (departureDate.getText().toString().isEmpty())
                {
                    Toast.makeText(EditTicket.this, "Departure Date Can not be Empty", Toast.LENGTH_LONG).show();
                }

                else if ( roundtrip.isChecked() && returningDate.getText().toString().isEmpty() )
                {
                    Toast.makeText(EditTicket.this, "Return Date Can not be Empty", Toast.LENGTH_LONG).show();
                }
                else if (departureTime.getText().toString().isEmpty())
                {
                    Toast.makeText(EditTicket.this, "Departure Time Can not be Empty", Toast.LENGTH_LONG).show();
                }
                else if (roundtrip.isChecked() && returningTime.getText().toString().isEmpty()  )
                {
                    Toast.makeText(EditTicket.this, "Return Time Can not be Empty", Toast.LENGTH_LONG).show();
                }



                else if (roundtrip.isChecked() && (todate == 1))
                {
                    Toast.makeText(EditTicket.this, "Return Date should not be less then departure date ", Toast.LENGTH_LONG).show();
                }

                else if (compareDateR == -1 || compareDateF == -1 )
                {
                    Toast.makeText(EditTicket.this, "Please Do not select past date", Toast.LENGTH_LONG).show();
                }
                    else
                {
                    //Intent itenaryIntent = new Intent(CreateTicket.this, PrintTicket.class);
                    if(name.getText().toString() == "") {
                        update();

                        tickets.get(editTicketNumber).setName(name.getText().toString());
                        tickets.get(editTicketNumber).setSource(source.getText().toString());
                        tickets.get(editTicketNumber).setDestination(destination.getText().toString());

                        if (oneway.isChecked()) {
                            tickets.get(editTicketNumber).setTrip("one-way");
                        } else if (roundtrip.isChecked()) {
                            tickets.get(editTicketNumber).setTrip("round-trip");
                        }
                        try {
                            tickets.get(editTicketNumber).setDepartureDate(departureDate.getText().toString());
                            tickets.get(editTicketNumber).setReturningDate(returningDate.getText().toString());
                        } catch (Exception e) {
                            Log.d("demo", "Some Error Occured");
                        }
                        tickets.get(editTicketNumber).setDepartureTime(departureTime.getText().toString());
                        tickets.get(editTicketNumber).setReturningTime(returningTime.getText().toString());

                        Log.d("demo", "after the update");

                        updateNameList();
                        finish();
                    }
                }


            }







        });
    }

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

    private void display(Ticket ticket){





        name.setText(ticket.getName());
        source.setText(ticket.getSource());
        destination.setText(ticket.getDestination());
        departureDate.setText(ticket.getDepartureDate().toString());
        departureTime.setText(ticket.getDepartureTime());
        if(ticket.getTrip().equals("one-way"))
            oneway.setChecked(true);
        else
            roundtrip.setChecked(true);

        if(roundtrip.isChecked())
            returningDate.setText(ticket.getReturningDate().toString());
        else {
            returningDate.setVisibility(View.INVISIBLE);
            returningDate.setText("");
        }
        if(roundtrip.isChecked())
            returningTime.setText(ticket.getReturningTime());
        else {
            returningTime.setVisibility(View.INVISIBLE);
            returningTime.setText("");
        }
        if(ticket.getTrip().equals("one-way"))
            oneway.setChecked(true);
        else
            roundtrip.setChecked(true);

    }

      void updateNameList(){
        nameList=new CharSequence[tickets.size()];
        Log.d("demo1", "in updateNameList " + tickets.toString());
        int i=0;
        for(Ticket ticket:tickets){
            nameList[i++]=ticket.getName();
        }
    }

      Ticket findTicketByName(String name){
        for(Ticket ticket:MainActivity.tickets){
            if(name.equals(ticket.getName()))
                return ticket;
        }
        return null;
    }
      void update(){

        /*Log.d("demo","Inside the update");

            Ticket editTicket = new Ticket();
        if (oneway.isChecked()) {
            editTicket.setTrip("one-way");
        } else if (roundtrip.isChecked()) {
            editTicket.setTrip("round-trip");
        }
        try {
            editTicket.setDepartureDate(format.parse(departureDate.getText().toString()));
            editTicket.setReturningDate(format.parse(returningDate.getText().toString()));
        } catch (Exception e) {
            Log.d("demo", "Some Error Occured");
        }
        editTicket.setName(name.getText().toString());
        editTicket.setSource(source.getText().toString());
        editTicket.setDestination(destination.getText().toString());
        editTicket.setDepartureTime(departureTime.getText().toString());
        editTicket.setReturningTime(returningTime.getText().toString());
        MainActivity.tickets.remove(which);
        MainActivity.tickets.add(which,editTicket);*/


         for(Ticket ticket:MainActivity.tickets)
            if(ticket.getName().equalsIgnoreCase(this.name.toString())) {
                if (oneway.isChecked()) {
                    ticket.setTrip("one-way");
                } else if (roundtrip.isChecked()) {
                    ticket.setTrip("round-trip");
                }
                try {
                    ticket.setDepartureDate(departureDate.getText().toString());
                    ticket.setReturningDate (returningDate.getText().toString());
                } catch (Exception e) {
                    Log.d("demo", "Some Error Occured");
                }
                ticket.setName(name.getText().toString());
                ticket.setSource(source.getText().toString());
                ticket.setDestination(destination.getText().toString());
                ticket.setDepartureTime(departureTime.getText().toString());
                ticket.setReturningTime(returningTime.getText().toString());


                Log.d("demo1", "HEllo");
            }

        Log.d("demo1",MainActivity.tickets.toString());
    }


}
