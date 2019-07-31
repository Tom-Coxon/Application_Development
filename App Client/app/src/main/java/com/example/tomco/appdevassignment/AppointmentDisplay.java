package com.example.tomco.appdevassignment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AppointmentDisplay extends Activity {

    private
    String accountName = "";
    String title = "";
    String location = "";
    String details = "";
    String day = "";
    String month = "";
    String year = "";
    String start = "";
    String end = "";
    String occurrence = "";

    String response = "";

    TextView titleT;
    TextView locationT;
    TextView detailsT;
    TextView dataT;
    TextView dateT;
    TextView timesT;
    TextView occurrenceT;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startLayout1();
    };

    private void startLayout1() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_app_display);

        ImageView backView = (ImageView) findViewById(R.id.back);
        backView.setImageResource(R.drawable.backbutton);

        accountName = getIntent().getStringExtra("userName");
        title = getIntent().getStringExtra("title");
        location = getIntent().getStringExtra("location");
        details = getIntent().getStringExtra("details");
        day = getIntent().getStringExtra("day");
        month = getIntent().getStringExtra("month");
        year = getIntent().getStringExtra("year");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        occurrence = getIntent().getStringExtra("occurrence");

        titleT = (TextView) findViewById(R.id.titleName);
        titleT.setText("Title: " + title);
        locationT = (TextView) findViewById(R.id.locationName);
        locationT.setText("Location: " + location);
        detailsT = (TextView) findViewById(R.id.detailsName);
        detailsT.setText("Details:");
        dataT = (TextView) findViewById(R.id.data);
        dataT.setText(details);
        dateT = (TextView) findViewById(R.id.dateName);
        dateT.setText("Date: " + day + "/" + month + "/" + year);
        timesT = (TextView) findViewById(R.id.timesName);
        timesT.setText("Times: " + start + "--" + end);
        occurrenceT = (TextView) findViewById(R.id.occurrenceName);
        occurrenceT.setText("Occurrence: " + occurrence);

    };

    public void returnMain(View view){
        finish();
    };

    public void editAppointment(View view){
        AlertDialog alertDialog = new AlertDialog.Builder(AppointmentDisplay.this).create();
        alertDialog.setTitle("Would you like to update this appointment?");
        alertDialog.setMessage("Title: " + title + "\n" +
                "Location: " + location + "\n" +
                "Start: " + start + " End: " + end);

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Update",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent();
                        intent.setClass(AppointmentDisplay.this, AddActivity.class);
                        intent.putExtra("userName", accountName);
                        intent.putExtra("title", title);
                        intent.putExtra("location", location);
                        intent.putExtra("details", details);
                        intent.putExtra("day", day);
                        intent.putExtra("month", month);
                        intent.putExtra("year", year);
                        intent.putExtra("start", start);
                        intent.putExtra("end", end);
                        intent.putExtra("occurrence", occurrence);
                        intent.putExtra("mode", "1");
                        startActivityForResult(intent, 1);
                    }
                });
        alertDialog.show();
    };

    public void deleteAppointment(View view){

        AlertDialog alertDialog = new AlertDialog.Builder(AppointmentDisplay.this).create();
        alertDialog.setTitle("Would you like to delete this appointment?");
        alertDialog.setMessage("Title: " + title + "\n" +
                "Location: " + location + "\n" +
                "Start: " + start + " End: " + end);

        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Delete",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String type = "delete";
                        ConnectionActivity connection = new ConnectionActivity(AppointmentDisplay.this);
                        connection.execute(type, title, location, details,
                                day, month, year, start, end, occurrence, accountName);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if(response != "") {
                                        CharSequence text = "Appointment Deleted";
                                        int duration = Toast.LENGTH_SHORT;

                                        Toast toast = Toast.makeText(getApplicationContext(), text, duration);
                                        toast.show();
                                        Intent returnIntent = new Intent();
                                        setResult(RESULT_OK, returnIntent);
                                        finish();
                                    }
                                }
                            }, 1000);
                    }
                });
        alertDialog.show();
    };

    public void setResponse(String result){
        response = result;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            if(resultCode == RESULT_OK){
                Intent returnIntent = new Intent();
                setResult(RESULT_OK,returnIntent);
                finish();
            }
        }
    }
}
