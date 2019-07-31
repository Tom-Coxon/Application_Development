package com.example.tomco.appdevassignment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class SearchActivity extends Activity {

    private
    EditText titleText;
    EditText locationText;
    Spinner dayDropDown;
    Spinner monthDropDown;
    Spinner yearDropDown;
    Spinner startDropDown;
    Spinner endDropDown;
    CheckBox checkTitle;
    CheckBox checkLocation;
    CheckBox checkDay;
    CheckBox checkMonth;
    CheckBox checkYear;
    CheckBox checkStart;
    CheckBox checkEnd;
    Button searchButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startLayout1();
    };

    private void startLayout1() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search_event);
        generalSetup();
        dropDownSetup();
    };

    private void generalSetup(){
        titleText = (EditText) findViewById(R.id.titleBox);
        titleText.setEnabled(false);
        locationText = (EditText) findViewById(R.id.locationBox);
        locationText.setEnabled(false);
        checkTitle = (CheckBox) findViewById(R.id.checkBoxTitle);
        checkLocation = (CheckBox) findViewById(R.id.checkBoxLocation);
        checkDay = (CheckBox) findViewById(R.id.checkBoxDay);
        checkMonth = (CheckBox) findViewById(R.id.checkBoxMonth);
        checkYear = (CheckBox) findViewById(R.id.checkBoxYear);
        checkStart = (CheckBox) findViewById(R.id.checkBoxStart);
        checkEnd = (CheckBox) findViewById(R.id.checkBoxEnd);
        searchButton = (Button) findViewById(R.id.searchEventButton);
        searchButton.setEnabled(false);
        ImageView backView = (ImageView) findViewById(R.id.back);
        backView.setImageResource(R.drawable.backbutton);
    };

    private void dropDownSetup() {
        days();
        months();
        years();
        times();
    };

    private void days() {
        dayDropDown = findViewById(R.id.dropdownDay);
        dayDropDown.setEnabled(false);
        ArrayList<String> items = new ArrayList<String>();

        items.add("");

        for (int i = 1; i <= 31; i++) {
            items.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapterDay = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dayDropDown.setAdapter(adapterDay);
    };

    private void months() {
        monthDropDown = findViewById(R.id.dropdownMonth);
        monthDropDown.setEnabled(false);
        String[] items = new String[]{"", "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        ArrayAdapter<String> adapterMonths = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        monthDropDown.setAdapter(adapterMonths);
    };

    private void years() {
        yearDropDown = findViewById(R.id.dropdownYear);
        yearDropDown.setEnabled(false);
        ArrayList<String> items = new ArrayList<String>();
        String year = "";

        items.add("");

        for (int i = 0; i <= 10; i++) {
            int yearNum = Calendar.getInstance().get(Calendar.YEAR);
            year = Integer.toString(i + yearNum);
            items.add(year);
        }
        ArrayAdapter<String> adapterYear = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        yearDropDown.setAdapter(adapterYear);
    };

    private void times(){
        startDropDown = findViewById(R.id.dropdownStart);
        startDropDown.setEnabled(false);
        endDropDown = findViewById(R.id.dropdownEnd);
        endDropDown.setEnabled(false);
        ArrayList<String> items = new ArrayList<String>();

        String time = "";
        String hours = "";
        String mins = "";

        items.add("");

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

    public void searchAppointment(View view){
        CharSequence textTitle_value = titleText.getText();

        CharSequence textLoc_value = locationText.getText();

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



        if(titleText.isEnabled() && titleText.getText().toString().length() == 0){
            Context context = getApplicationContext();
            CharSequence text = "Invalid: Fields not filled in";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else if(locationText.isEnabled() && locationText.getText().toString().length() == 0){
            Context context = getApplicationContext();
            CharSequence text = "Invalid: Fields not filled in";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else{
            String type = "search";
            Intent intent = getIntent();
            Bundle extras = intent.getExtras();
            String name = extras.getString("userName");

            ConnectionActivity connection = new ConnectionActivity(this);
            connection.execute(type, textTitle_value.toString(), textLoc_value.toString(),
                    spinner_day_data, spinner_month_data, spinner_year_data, spinner_start_data, spinner_end_data, name);
        }
    };

    public void returnMain(View view){
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    };

    public void checkTitle(View view){
        if(checkTitle.isChecked()){
            titleText.setEnabled(true);
        }
        else{
            titleText.setEnabled(false);
            titleText.setText("");
        }
        checkSearchButton();
    };

    public void checkLocation(View view){
        if(checkLocation.isChecked()){
            locationText.setEnabled(true);
        }
        else{
            locationText.setEnabled(false);
            locationText.setText("");
        }
        checkSearchButton();
    };

    public void checkDay(View view){
        if(checkDay.isChecked()){
            dayDropDown.setEnabled(true);
        }
        else{
            dayDropDown.setEnabled(false);
            dayDropDown.setSelection(0);
        }
        checkSearchButton();
    };

    public void checkMonth(View view){
        if(checkMonth.isChecked()){
            monthDropDown.setEnabled(true);
        }
        else{
            monthDropDown.setEnabled(false);
            monthDropDown.setSelection(0);
        }
        checkSearchButton();
    };

    public void checkYear(View view){
        if(checkYear.isChecked()){
            yearDropDown.setEnabled(true);
        }
        else{
            yearDropDown.setEnabled(false);
            yearDropDown.setSelection(0);
        }
        checkSearchButton();
    };

    public void checkStart(View view){
        if(checkStart.isChecked()){
            startDropDown.setEnabled(true);
        }
        else{
            startDropDown.setEnabled(false);
            startDropDown.setSelection(0);
        }
        checkSearchButton();
    };

    public void checkEnd(View view){
        if(checkEnd.isChecked()){
            endDropDown.setEnabled(true);
        }
        else{
            endDropDown.setEnabled(false);
            endDropDown.setSelection(0);
        }
        checkSearchButton();
    };

    public void checkSearchButton(){
        if(checkTitle.isChecked() || checkLocation.isChecked() || checkDay.isChecked()||
                checkMonth.isChecked() || checkYear.isChecked() || checkStart.isChecked() ||
                checkEnd.isChecked()){
            searchButton.setEnabled(true);
        }
        else{
            searchButton.setEnabled(false);
        }
    }
}