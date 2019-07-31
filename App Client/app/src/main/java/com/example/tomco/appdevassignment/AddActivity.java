package com.example.tomco.appdevassignment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends Activity {

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
    TextView textTitle;

    TextView textLocal;

    TextView textInfo;

    Spinner dayDropDown;
    Spinner monthDropDown;
    Spinner yearDropDown;
    Spinner startDropDown;
    Spinner endDropDown;
    Spinner occurrenceDropDown;
    Button button;

    String mode = "";

    String response = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startLayout1();
    };

    private void startLayout1() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_event);
        dropDownSetup();

        ImageView backView = (ImageView) findViewById(R.id.back);
        backView.setImageResource(R.drawable.backbutton);

        textTitle = (TextView) findViewById(R.id.TitleName);

        textLocal = (TextView) findViewById(R.id.LocationName);

        textInfo = (TextView) findViewById(R.id.Information);

        mode = getIntent().getStringExtra("mode");

        if(mode.startsWith("1")) {
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            accountName = extras.getString("userName");
            title = extras.getString("title");
            location = extras.getString("location");
            details = extras.getString("details");
            day = extras.getString("day");
            month = extras.getString("month");
            year = extras.getString("year");
            start = extras.getString("start");
            end = extras.getString("end");
            occurrence = extras.getString("occurrence");

            textTitle.setText(title);
            textLocal.setText(location);
            textInfo.setText(details);
            dayDropDown.setSelection(getIndex(dayDropDown, day));
            monthDropDown.setSelection(getIndex(monthDropDown, month));
            yearDropDown.setSelection(getIndex(yearDropDown,year));
            startDropDown.setSelection(getIndex(startDropDown,start));
            endDropDown.setSelection(getIndex(endDropDown, end));
            occurrenceDropDown.setSelection(getIndex(occurrenceDropDown, occurrence));
            button = (Button) findViewById(R.id.addEventButton);
            button.setText("Update");
        }
    };

    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }

    private void dropDownSetup(){
        days();
        months();
        years();
        occurrences();
        times();
    };

    private void days(){
        dayDropDown = findViewById(R.id.dropdownDay);
        ArrayList<String> items = new ArrayList<String>();

        for(int i = 1; i <= 31; i++){
            items.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapterDay = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dayDropDown.setAdapter(adapterDay);
    };

    private void months(){
        monthDropDown = findViewById(R.id.dropdownMonth);
        String[] items = new String[]{"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        ArrayAdapter<String> adapterMonths = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        monthDropDown.setAdapter(adapterMonths);
    };

    private void years(){
        yearDropDown = findViewById(R.id.dropdownYear);
        ArrayList<String> items = new ArrayList<String>();
        String year = "";

        for(int i = 0; i <= 10; i++){
            int yearNum = Calendar.getInstance().get(Calendar.YEAR);
            year = Integer.toString(i + yearNum);
            items.add(year);
        }
        ArrayAdapter<String> adapterYear = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        yearDropDown.setAdapter(adapterYear);
    };

    private void occurrences(){
        occurrenceDropDown = findViewById(R.id.dropdownOccurrence);
        String[] items = new String[]{"Once", "Daily", "Weekly", "Fortnightly", "Monthly", "Yearly"};

        ArrayAdapter<String> adapterOccurrence = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        occurrenceDropDown.setAdapter(adapterOccurrence);
    };

    private void times(){
        startDropDown = findViewById(R.id.dropdownStart);
        endDropDown = findViewById(R.id.dropdownEnd);
        ArrayList<String> items = new ArrayList<String>();

        String time = "";
        String hours = "";
        String mins = "";

        for(int i = 0; i <= 23; i++){
            if(i < 10){
                hours = "0" + Integer.toString(i) + ":";
            }
            else{
                hours = Integer.toString(i) + ":";
            }
            for(int j = 0; j <= 45; j = j + 15){
                if(j == 0){
                    mins = "0" + Integer.toString(j);
                }
                else{
                    mins = Integer.toString(j);
                }
                time = hours + mins;
                items.add(time);
            }
        }
        ArrayAdapter<String> adapterStart = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        startDropDown.setAdapter(adapterStart);

        ArrayAdapter<String> adapterEnd = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        endDropDown.setAdapter(adapterEnd);
    };

    public void returnMain(View view){
        finish();
    };

    public void addAppointment(View view){
        CharSequence textTitle_value = textTitle.getText();

        CharSequence textLocal_value = textLocal.getText();

        CharSequence textInfo_value = textInfo.getText();

        Spinner spinner_day = (Spinner) findViewById(R.id.dropdownDay);
        String spinner_day_data = spinner_day.getSelectedItem().toString();

        Spinner spinner_month = (Spinner) findViewById(R.id.dropdownMonth);
        String spinner_month_data = spinner_month.getSelectedItem().toString();

        Spinner spinner_year = (Spinner) findViewById(R.id.dropdownYear);
        String spinner_year_data = spinner_year.getSelectedItem().toString();

        Spinner spinner_start = (Spinner) findViewById(R.id.dropdownStart);
        String spinner_start_data = spinner_start.getSelectedItem().toString();

        Spinner spinner_end = (Spinner) findViewById(R.id.dropdownEnd);
        String spinner_end_data = spinner_end.getSelectedItem().toString();

        Spinner spinner_occur = (Spinner) findViewById(R.id.dropdownOccurrence);
        String spinner_occur_data = spinner_occur.getSelectedItem().toString();

        if(textTitle_value.length() < 1 || textInfo_value.length() < 1 || textLocal_value.length() < 1){
            Context context = getApplicationContext();
            CharSequence text = "Error: Either the title or location\nor information has not been filled in";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else if(checkDate(spinner_day_data, spinner_month_data, spinner_year_data)){
            Context context = getApplicationContext();
            CharSequence text = "Error: Date has already passed";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else if(checkStartAndEndTimes(spinner_start_data, spinner_end_data)){
            Context context = getApplicationContext();
            CharSequence text = "Error: Issue with start and end times\nEither they are the same or in wrong order";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else if(mode.startsWith("1")) {
            String type = "update";
            ConnectionActivity connection = new ConnectionActivity(this);
            connection.execute(type, title, location, details, day, month, year, start, end, occurrence,
                    textTitle_value.toString(), textLocal_value.toString(), textInfo_value.toString(),
                    spinner_day_data, spinner_month_data, spinner_year_data, spinner_start_data, spinner_end_data, spinner_occur_data, accountName);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(response != "") {
                        CharSequence text = "Appointment Updated";
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
        else {
            String type = "add";
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            String name = extras.getString("userName");
            ConnectionActivity connection = new ConnectionActivity(this);
            connection.execute(type, textTitle_value.toString(), textLocal_value.toString(), textInfo_value.toString(),
                    spinner_day_data, spinner_month_data, spinner_year_data, spinner_start_data, spinner_end_data, spinner_occur_data, name);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(response != "") {
                        CharSequence text = "Appointment Added";
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
    };

    private boolean checkStartAndEndTimes(String start, String end){

        boolean check = false;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        try {
            Date startCal = df.parse(start);
            Date endCal = df.parse(end);

            if(startCal.after(endCal)){
                check = true;
            }
            else if(startCal.equals(endCal)){
                check = true;
            }
        }
        catch (ParseException e) {
        }

        if(check == true){
            return true;
        }
        else{
            return false;
        }
    };

    private boolean checkDate(String day, String month, String year){

        boolean check = false;
        String newMonth = month.substring(0,3);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        String dateInString = day + "-" + newMonth + "-" + year;

        try {
            Date date = formatter.parse(dateInString);
            Calendar cal = Calendar.getInstance();
            if(date.before(cal.getTime())){
                check = true;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(check == true){
            return true;
        }
        else{
            return false;
        }

    };

    public void setResult(String result){
        response = result;
    }
}