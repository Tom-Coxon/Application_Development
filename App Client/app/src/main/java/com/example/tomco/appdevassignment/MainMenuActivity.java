package com.example.tomco.appdevassignment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainMenuActivity extends Activity {

    private
    Calendar c;
    String accountName = "";
    String monString = "";
    String tueString = "";
    String wedString = "";
    String thuString = "";
    String friString = "";
    String satString = "";
    String sunString = "";

    String monApp = "";
    String tueApp = "";
    String wedApp = "";
    String thuApp = "";
    String friApp = "";
    String satApp = "";
    String sunApp = "";

    ProgressBar spinner;

    ArrayList<AppointmentStore> currentWeekAppointment = new ArrayList<AppointmentStore>();

    int appIdentify = 1;
    LinearLayout monSection;
    LinearLayout tueSection;
    LinearLayout wedSection;
    LinearLayout thuSection;
    LinearLayout friSection;
    LinearLayout satSection;
    LinearLayout sunSection;
    LinearLayout newSection;
    int appToLoadByInt;
    ArrayList<Integer> id = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startLayout2();
    };

    private void startLayout2(){
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_loading_screen);

        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);

        accountName = getIntent().getStringExtra("userName");

        loadCurrentWeek();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                spinner.setVisibility(View.GONE);
                setContentView(R.layout.activity_main_menu);
                monSection = (LinearLayout) findViewById(R.id.newMon);
                tueSection = (LinearLayout) findViewById(R.id.newTue);
                wedSection = (LinearLayout) findViewById(R.id.newWed);
                thuSection = (LinearLayout) findViewById(R.id.newThu);
                friSection = (LinearLayout) findViewById(R.id.newFri);
                satSection = (LinearLayout) findViewById(R.id.newSat);
                sunSection = (LinearLayout) findViewById(R.id.newSun);
                store(monString, monApp);
                store(tueString, tueApp);
                store(wedString, wedApp);
                store(thuString, thuApp);
                store(friString, friApp);
                store(satString, satApp);
                store(sunString, sunApp);
                setting();
            }
        }, 2000);
    };

    private void loadCurrentWeek(){
        Date date = new Date();
        c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();

        c.add(Calendar.DATE, -i);

        listOfDays(c);
    };

    private void listOfDays(Calendar c){
        Date day = c.getTime();
        monString = "Monday  " + new SimpleDateFormat("dd  MMMM  yyyy").format(day);
        getDayDetails("Mon", monString);

        c.add(Calendar.DATE, 1);
        day = c.getTime();
        tueString = "Tuesday  " + new SimpleDateFormat("dd  MMMM  yyyy").format(day);

        getDayDetails("Tue", tueString);

        c.add(Calendar.DATE, 1);
        day = c.getTime();
        wedString = "Wednesday  " +new SimpleDateFormat("dd  MMMM  yyyy").format(day);

        getDayDetails("Wed", wedString);

        c.add(Calendar.DATE, 1);
        day = c.getTime();
        thuString = "Thursday  " + new SimpleDateFormat("dd  MMMM  yyyy").format(day);

        getDayDetails("Thu", thuString);

        c.add(Calendar.DATE, 1);
        day = c.getTime();
        friString = "Friday  " + new SimpleDateFormat("dd  MMMM  yyyy").format(day);

        getDayDetails("Fri", friString);

        c.add(Calendar.DATE, 1);
        day = c.getTime();
        satString = "Saturday  " + new SimpleDateFormat("dd  MMMM  yyyy").format(day);

        getDayDetails("Sat", satString);

        c.add(Calendar.DATE, 1);
        day = c.getTime();
        sunString = "Sunday  " + new SimpleDateFormat("dd  MMMM  yyyy").format(day);

        getDayDetails("Sun", sunString);
    };

    private void getDayDetails(String start, String day){
        String type = start + ":current";
        String call = "week_app";
        String[] parts = day.split("  ");
        if(parts[1].startsWith("0")){
            parts[1] = parts[1].substring(1,2);
        }

        ConnectionActivity connection = new ConnectionActivity(this);
        connection.execute(type, parts[1], parts[2], parts[3], accountName, call);
    };

    private void store(String day, String appointments){

        if(day.contains("Monday")) {
            TextView dayTitle = (TextView) findViewById(R.id.nameMon);
            dayTitle.setText(day);
        }
        else if(day.contains("Tuesday")) {
            TextView dayTitle = (TextView) findViewById(R.id.nameTue);
            dayTitle.setText(day);
        }
        else if(day.contains("Wednesday")) {
            TextView dayTitle = (TextView) findViewById(R.id.nameWed);
            dayTitle.setText(day);
        }
        else if(day.contains("Thursday")) {
            TextView dayTitle = (TextView) findViewById(R.id.nameThu);
            dayTitle.setText(day);
        }
        else if(day.contains("Friday")) {
            TextView dayTitle = (TextView) findViewById(R.id.nameFri);
            dayTitle.setText(day);
        }
        else if(day.contains("Saturday")) {
            TextView dayTitle = (TextView) findViewById(R.id.nameSat);
            dayTitle.setText(day);
        }
        else if(day.contains("Sunday")) {
            TextView dayTitle = (TextView) findViewById(R.id.nameSun);
            dayTitle.setText(day);
        }

        if(!appointments.equals("no appointments")) {
            String[] items = appointments.split("<searchAppointment>");

            for (String s : items) {
                String[] parts = s.split(" ==== ");

                AppointmentStore app = new AppointmentStore();

                app.setDay(parts[0]);
                app.setMonth(parts[1]);
                app.setYear(parts[2]);
                app.setName(parts[3]);
                app.setLocation(parts[4]);
                app.setStart(parts[5]);
                app.setEnd(parts[6]);
                app.setOccurrence(parts[7]);
                app.setDetails(parts[8]);

                currentWeekAppointment.add(app);

                String data = "Title: " + app.getName() + "\n" +
                        "Location: " + app.getLocation() + "\n" +
                        "Start: " + app.getStart() + " End: " + app.getEnd();


                createAppSection(data, day);
            }
        }
        else{
            String data = "no appointment";
            createAppSection(data, day);
        }
    }

    private void createAppSection(String data, String day){
        LinearLayout.LayoutParams lParamsLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout newLayout = new LinearLayout(this);

        newLayout.setOrientation(LinearLayout.HORIZONTAL);
        newLayout.setLayoutParams(lParamsLayout);
        newLayout.setBackgroundResource(R.drawable.edit_text_background);
        newLayout.setId(appIdentify);

        if(day.contains("Monday")) {
            if(!data.equals("no appointment")){
                id.add(appIdentify);
            }
            newLayout.setOnClickListener(loadAppointment);
            monSection.addView(newLayout);
        }
        else if(day.contains("Tuesday")) {
            if(!data.equals("no appointment")){
                id.add(appIdentify);
            }
            newLayout.setOnClickListener(loadAppointment);
            tueSection.addView(newLayout);
        }
        else if(day.contains("Wednesday")) {
            if(!data.equals("no appointment")){
                id.add(appIdentify);
            }
            newLayout.setOnClickListener(loadAppointment);
            wedSection.addView(newLayout);
        }
        else if(day.contains("Thursday")) {
            if(!data.equals("no appointment")){
                id.add(appIdentify);
            }
            newLayout.setOnClickListener(loadAppointment);
            thuSection.addView(newLayout);
        }
        else if(day.contains("Friday")) {
            if(!data.equals("no appointment")){
                id.add(appIdentify);
            }
            newLayout.setOnClickListener(loadAppointment);
            friSection.addView(newLayout);
        }
        else if(day.contains("Saturday")) {
            if(!data.equals("no appointment")){
                id.add(appIdentify);
            }
            newLayout.setOnClickListener(loadAppointment);
            satSection.addView(newLayout);
        }
        else if(day.contains("Sunday")) {
            if(!data.equals("no appointment")){
                id.add(appIdentify);
            }
            newLayout.setOnClickListener(loadAppointment);
            sunSection.addView(newLayout);
        }

        newSection = (LinearLayout) findViewById(appIdentify);

        appIdentify = appIdentify + 1;

        LinearLayout.LayoutParams lParamsText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lParamsText.setMargins(10,10,10,10);
        TextView newTxtView = new TextView(this);
        newTxtView.setLayoutParams(lParamsText);
        newTxtView.setEms(10);
        newTxtView.setTextColor(getResources().getColor(R.color.black));
        newTxtView.setEllipsize(TextUtils.TruncateAt.START);
        newTxtView.setPadding(0,0,0,0);
        newTxtView.setTextSize(15);
        newTxtView.setText(data);

        newSection.addView(newTxtView);

    }

    View.OnClickListener loadAppointment = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            LinearLayout linear = (LinearLayout) findViewById(view.getId());
            View v = linear.getChildAt(0);
            TextView tv = (TextView) v;
            String data = tv.getText().toString();


            if(!data.equals("no appointment")) {

                AppointmentStore app = null;
                for(AppointmentStore s : currentWeekAppointment){
                    if(data.contains("Title: " + s.getName() + "\n" +
                            "Location: " + s.getLocation() + "\n" +
                            "Start: " + s.getStart() + " End: " + s.getEnd())){
                        app = s;
                        break;
                    }
                }

                Intent i = new Intent(view.getContext(), AppointmentDisplay.class);

                i.putExtra("userName", accountName);
                i.putExtra("title", app.getName());
                i.putExtra("location", app.getLocation());
                i.putExtra("details", app.getDetails());
                i.putExtra("day", app.getDay());
                i.putExtra("month", app.getMonth());
                i.putExtra("year", app.getYear());
                i.putExtra("start", app.getStart());
                i.putExtra("end", app.getEnd());
                i.putExtra("occurrence", app.getOccurrence());

                startActivityForResult(i, 1);
            }
            else{
                Context context = getApplicationContext();
                CharSequence text = "There are no items in the selected area";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }
    };

    public void searchButton(View view){
        Intent intent = new Intent();
        intent.setClass(this, SearchActivity.class);
        intent.putExtra("userName", accountName);
        startActivityForResult(intent, 1);
    }

    public void addButton(View view){
        Intent intent = new Intent();
        intent.setClass(this, AddActivity.class);
        intent.putExtra("userName", accountName);
        intent.putExtra("mode", "0");
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            if(resultCode == RESULT_OK){

                setContentView(R.layout.activity_loading_screen);

                spinner=(ProgressBar)findViewById(R.id.progressBar);
                spinner.setVisibility(View.VISIBLE);

                loadCurrentWeek();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        spinner.setVisibility(View.GONE);
                        setContentView(R.layout.activity_main_menu);
                        monSection = (LinearLayout) findViewById(R.id.newMon);
                        tueSection = (LinearLayout) findViewById(R.id.newTue);
                        wedSection = (LinearLayout) findViewById(R.id.newWed);
                        thuSection = (LinearLayout) findViewById(R.id.newThu);
                        friSection = (LinearLayout) findViewById(R.id.newFri);
                        satSection = (LinearLayout) findViewById(R.id.newSat);
                        sunSection = (LinearLayout) findViewById(R.id.newSun);
                        store(monString, monApp);
                        store(tueString, tueApp);
                        store(wedString, wedApp);
                        store(thuString, thuApp);
                        store(friString, friApp);
                        store(satString, satApp);
                        store(sunString, sunApp);
                        setting();
                    }
                }, 2000);
            }
        }
    }

    private void setting(){

        ImageView searchView = (ImageView) findViewById(R.id.searchButton);
        searchView.setImageResource(R.drawable.searchbutton);

        ImageView addView = (ImageView) findViewById(R.id.addButton);
        addView.setImageResource(R.drawable.addbutton);

        ImageView logoutView = (ImageView) findViewById(R.id.logoutButton);
        logoutView.setImageResource(R.drawable.logoutbutton);

        ImageView nextView = (ImageView) findViewById(R.id.next);
        nextView.setImageResource(R.drawable.nextbutton);

        ImageView prevView = (ImageView) findViewById(R.id.previous);
        prevView.setImageResource(R.drawable.prevbutton);

        TextView range = (TextView) findViewById(R.id.weekRange);
        range.setText(monString + " - " + sunString);
    };

    public void setMon(String info){
        monApp = info;
    };

    public void setTue(String info){
        tueApp = info;
    };

    public void setWed(String info){
        wedApp = info;
    };

    public void setThu(String info){
        thuApp = info;
    };

    public void setFri(String info){
        friApp = info;
    };

    public void setSat(String info){
        satApp = info;
    };

    public void setSun(String info){
        sunApp = info;
    };

    public void nextWeek(View view){
        currentWeekAppointment.clear();

        c.add(Calendar.DATE, 1);

        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();

        c.add(Calendar.DATE, -i);

        listOfDays(c);

        setContentView(R.layout.activity_loading_screen);
        currentWeekAppointment.clear();
        appIdentify = 0;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                spinner.setVisibility(View.GONE);
                setContentView(R.layout.activity_main_menu);
                monSection = (LinearLayout) findViewById(R.id.newMon);
                tueSection = (LinearLayout) findViewById(R.id.newTue);
                wedSection = (LinearLayout) findViewById(R.id.newWed);
                thuSection = (LinearLayout) findViewById(R.id.newThu);
                friSection = (LinearLayout) findViewById(R.id.newFri);
                satSection = (LinearLayout) findViewById(R.id.newSat);
                sunSection = (LinearLayout) findViewById(R.id.newSun);
                store(monString, monApp);
                store(tueString, tueApp);
                store(wedString, wedApp);
                store(thuString, thuApp);
                store(friString, friApp);
                store(satString, satApp);
                store(sunString, sunApp);
                setting();
            }
        }, 2000);
    };

    public void prevWeek(View view){
        currentWeekAppointment.clear();

        c.add(Calendar.DATE, -8);

        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();

        c.add(Calendar.DATE, -i);

        listOfDays(c);

        setContentView(R.layout.activity_loading_screen);

        currentWeekAppointment.clear();
        appIdentify = 0;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                spinner.setVisibility(View.GONE);
                setContentView(R.layout.activity_main_menu);
                monSection = (LinearLayout) findViewById(R.id.newMon);
                tueSection = (LinearLayout) findViewById(R.id.newTue);
                wedSection = (LinearLayout) findViewById(R.id.newWed);
                thuSection = (LinearLayout) findViewById(R.id.newThu);
                friSection = (LinearLayout) findViewById(R.id.newFri);
                satSection = (LinearLayout) findViewById(R.id.newSat);
                sunSection = (LinearLayout) findViewById(R.id.newSun);
                store(monString, monApp);
                store(tueString, tueApp);
                store(wedString, wedApp);
                store(thuString, thuApp);
                store(friString, friApp);
                store(satString, satApp);
                store(sunString, sunApp);
                setting();
            }
        }, 2000);
    };

    public void logoutButton(View view){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
