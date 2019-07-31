package com.example.tomco.appdevassignment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchResultActivity extends Activity {

    private
    String search = "";
    ArrayList<AppointmentStore> currentWeekAppointment = new ArrayList<AppointmentStore>();
    int appIdentify = 0;
    LinearLayout appSection;
    LinearLayout newSection;
    String accountName = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startLayout1();
    };

    private void startLayout1() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search_result);

        Bundle b = new Bundle();
        b = getIntent().getExtras();
        search = b.getString("SearchResult");

        ImageView backView = (ImageView) findViewById(R.id.back);
        backView.setImageResource(R.drawable.backbutton);

        appSection = (LinearLayout) findViewById(R.id.resultLayout);
        accountName = getIntent().getStringExtra("userName");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                organise();
            }
        }, 1000);
    };

    private void organise() {

        if (!search.equals("no appointments")) {
            String[] items = search.split("<searchAppointment>");

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


                createAppSection(data);
            }
        } else {
            String data = "no appointments";
            createAppSection(data);
        }
    }

    private void createAppSection(String data){
        LinearLayout.LayoutParams lParamsLayout = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout newLayout = new LinearLayout(this);

        newLayout.setOrientation(LinearLayout.HORIZONTAL);
        newLayout.setLayoutParams(lParamsLayout);
        newLayout.setBackgroundResource(R.drawable.edit_text_background);
        newLayout.setId(appIdentify);


        newLayout.setOnClickListener(loadAppointment);
        appSection.addView(newLayout);


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


            if(!data.equals("no appointments")) {

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

    public void returnMain(View view){
        finish();
    };

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
