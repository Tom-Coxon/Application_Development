package com.example.tomco.appdevassignment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class ConnectionActivity extends AsyncTask<String,Void,String> {
    private
    Context context;
    String type = "";

    String user_name = "";

    ConnectionActivity (Context ctx) {
        context = ctx;
    }
    @Override
    protected String doInBackground(String... params) {
        type = params[0];
        String web_url = "http://computing.derby.ac.uk/~st100404970/App_Dev/index.php?";
        URL url;

        String user_password = "";
        String method = "";
        String call = "";
        String app_title = "";
        String app_location = "";
        String app_details = "";
        String app_day = "";
        String app_month = "";
        String app_year = "";
        String app_start = "";
        String app_end = "";
        String app_occurrence = "";
        String prev_title = "";
        String prev_location = "";
        String prev_details = "";
        String prev_day = "";
        String prev_month = "";
        String prev_year = "";
        String prev_start = "";
        String prev_end = "";
        String prev_occurrence = "";
        String app_user = "";

        try {
            url = new URL(web_url);
            if(type.equals("login")) {
                user_name = params[1];
                user_password = params[2];
                method = "POST";
                call = "login";
            }
            else if (type.equals("register")){
                user_name = params[1];
                user_password = params[2];
                method = "POST";
                call = "register";
            }
            else if(type.endsWith(":current")){
                app_day = params[1];
                app_month = params[2];
                app_year = params[3];
                app_user = params[4];
                method = "POST";
                call = params[5];
            }
            else if(type.equals("add")){
                app_title = params[1];
                app_location = params[2];
                app_details = params[3];
                app_day = params[4];
                app_month = params[5];
                app_year = params[6];
                app_start = params[7];
                app_end = params[8];
                app_occurrence = params[9];
                app_user = params[10];
                method = "POST";
                call = "add";
            }
            else if(type.equals("search")){
                app_title = params[1];
                app_location = params[2];
                app_day = params[3];
                app_month = params[4];
                app_year = params[5];
                app_start = params[6];
                app_end = params[7];
                app_user = params[8];
                user_name = app_user;
                method = "POST";
                call = "search";
            }
            else if(type.equals("delete")){
                app_title = params[1];
                app_location = params[2];
                app_details = params[3];
                app_day = params[4];
                app_month = params[5];
                app_year = params[6];
                app_start = params[7];
                app_end = params[8];
                app_occurrence = params[9];
                app_user = params[10];
                user_name = app_user;
                method = "POST";
                call = "delete";
            }
            else if(type.equals("update")){
                prev_title = params[1];
                prev_location = params[2];
                prev_details = params[3];
                prev_day = params[4];
                prev_month = params[5];
                prev_year = params[6];
                prev_start = params[7];
                prev_end = params[8];
                prev_occurrence = params[9];

                app_title = params[10];
                app_location = params[11];
                app_details = params[12];
                app_day = params[13];
                app_month = params[14];
                app_year = params[15];
                app_start = params[16];
                app_end = params[17];
                app_occurrence = params[18];
                app_user = params[19];
                user_name = app_user;
                method = "POST";
                call = "update";
            }

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod(method);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = "";
            if(type.equals("login") || type.equals("register")) {
                post_data = URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(user_name, "UTF-8") + "&"
                        + URLEncoder.encode("user_password", "UTF-8") + "=" + URLEncoder.encode(user_password, "UTF-8")+ "&"
                        + URLEncoder.encode("CALL", "UTF-8") + "=" + URLEncoder.encode(call, "UTF-8");
            }
            else if(type.endsWith(":current")){
                post_data = URLEncoder.encode("app_day", "UTF-8") + "=" + URLEncoder.encode(app_day, "UTF-8") + "&"
                        + URLEncoder.encode("app_month", "UTF-8") + "=" + URLEncoder.encode(app_month, "UTF-8") + "&"
                        + URLEncoder.encode("app_year", "UTF-8") + "=" + URLEncoder.encode(app_year, "UTF-8") + "&"
                        + URLEncoder.encode("app_user", "UTF-8") + "=" + URLEncoder.encode(app_user, "UTF-8") + "&"
                        + URLEncoder.encode("CALL", "UTF-8") + "=" + URLEncoder.encode(call, "UTF-8");
            }
            else if(type.equals("add") || type.equals("delete")){
                post_data = URLEncoder.encode("app_title", "UTF-8") + "=" + URLEncoder.encode(app_title, "UTF-8") + "&"
                        + URLEncoder.encode("app_location", "UTF-8") + "=" + URLEncoder.encode(app_location, "UTF-8") + "&"
                        + URLEncoder.encode("app_details", "UTF-8") + "=" + URLEncoder.encode(app_details, "UTF-8") + "&"
                        + URLEncoder.encode("app_day", "UTF-8") + "=" + URLEncoder.encode(app_day, "UTF-8") + "&"
                        + URLEncoder.encode("app_month", "UTF-8") + "=" + URLEncoder.encode(app_month, "UTF-8") + "&"
                        + URLEncoder.encode("app_year", "UTF-8") + "=" + URLEncoder.encode(app_year, "UTF-8") + "&"
                        + URLEncoder.encode("app_start", "UTF-8") + "=" + URLEncoder.encode(app_start, "UTF-8") + "&"
                        + URLEncoder.encode("app_end", "UTF-8") + "=" + URLEncoder.encode(app_end, "UTF-8") + "&"
                        + URLEncoder.encode("app_occurrence", "UTF-8") + "=" + URLEncoder.encode(app_occurrence, "UTF-8") + "&"
                        + URLEncoder.encode("app_user", "UTF-8") + "=" + URLEncoder.encode(app_user, "UTF-8") + "&"
                        + URLEncoder.encode("CALL", "UTF-8") + "=" + URLEncoder.encode(call, "UTF-8");
            }
            else if(type.equals("search")){
                post_data = URLEncoder.encode("app_title", "UTF-8") + "=" + URLEncoder.encode(app_title, "UTF-8") + "&"
                        + URLEncoder.encode("app_location", "UTF-8") + "=" + URLEncoder.encode(app_location, "UTF-8") + "&"
                        + URLEncoder.encode("app_day", "UTF-8") + "=" + URLEncoder.encode(app_day, "UTF-8") + "&"
                        + URLEncoder.encode("app_month", "UTF-8") + "=" + URLEncoder.encode(app_month, "UTF-8") + "&"
                        + URLEncoder.encode("app_year", "UTF-8") + "=" + URLEncoder.encode(app_year, "UTF-8") + "&"
                        + URLEncoder.encode("app_start", "UTF-8") + "=" + URLEncoder.encode(app_start, "UTF-8") + "&"
                        + URLEncoder.encode("app_end", "UTF-8") + "=" + URLEncoder.encode(app_end, "UTF-8") + "&"
                        + URLEncoder.encode("app_user", "UTF-8") + "=" + URLEncoder.encode(app_user, "UTF-8") + "&"
                        + URLEncoder.encode("CALL", "UTF-8") + "=" + URLEncoder.encode(call, "UTF-8");
            }
            else if(type.equals("update")){
                post_data = URLEncoder.encode("prev_title", "UTF-8") + "=" + URLEncoder.encode(prev_title, "UTF-8") + "&"
                        + URLEncoder.encode("prev_location", "UTF-8") + "=" + URLEncoder.encode(prev_location, "UTF-8") + "&"
                        + URLEncoder.encode("prev_details", "UTF-8") + "=" + URLEncoder.encode(prev_details, "UTF-8") + "&"
                        + URLEncoder.encode("prev_day", "UTF-8") + "=" + URLEncoder.encode(prev_day, "UTF-8") + "&"
                        + URLEncoder.encode("prev_month", "UTF-8") + "=" + URLEncoder.encode(prev_month, "UTF-8") + "&"
                        + URLEncoder.encode("prev_year", "UTF-8") + "=" + URLEncoder.encode(prev_year, "UTF-8") + "&"
                        + URLEncoder.encode("prev_start", "UTF-8") + "=" + URLEncoder.encode(prev_start, "UTF-8") + "&"
                        + URLEncoder.encode("prev_end", "UTF-8") + "=" + URLEncoder.encode(prev_end, "UTF-8") + "&"
                        + URLEncoder.encode("prev_occurrence", "UTF-8") + "=" + URLEncoder.encode(prev_occurrence, "UTF-8") + "&"
                        + URLEncoder.encode("app_title", "UTF-8") + "=" + URLEncoder.encode(app_title, "UTF-8") + "&"
                        + URLEncoder.encode("app_location", "UTF-8") + "=" + URLEncoder.encode(app_location, "UTF-8") + "&"
                        + URLEncoder.encode("app_details", "UTF-8") + "=" + URLEncoder.encode(app_details, "UTF-8") + "&"
                        + URLEncoder.encode("app_day", "UTF-8") + "=" + URLEncoder.encode(app_day, "UTF-8") + "&"
                        + URLEncoder.encode("app_month", "UTF-8") + "=" + URLEncoder.encode(app_month, "UTF-8") + "&"
                        + URLEncoder.encode("app_year", "UTF-8") + "=" + URLEncoder.encode(app_year, "UTF-8") + "&"
                        + URLEncoder.encode("app_start", "UTF-8") + "=" + URLEncoder.encode(app_start, "UTF-8") + "&"
                        + URLEncoder.encode("app_end", "UTF-8") + "=" + URLEncoder.encode(app_end, "UTF-8") + "&"
                        + URLEncoder.encode("app_occurrence", "UTF-8") + "=" + URLEncoder.encode(app_occurrence, "UTF-8") + "&"
                        + URLEncoder.encode("app_user", "UTF-8") + "=" + URLEncoder.encode(app_user, "UTF-8") + "&"
                        + URLEncoder.encode("CALL", "UTF-8") + "=" + URLEncoder.encode(call, "UTF-8");
            }

            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line="";
            while((line = bufferedReader.readLine())!= null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

        if(result.equals("register success") || result.equals("login success")){
            Intent intent = new Intent();
            intent.setClass(context, MainMenuActivity.class);
            intent.putExtra("userName", user_name);
            context.startActivity(intent);
        }
        else if(result.equals("appointment deleted")){

            ((AppointmentDisplay)context).setResponse(result);
        }

        else if(type.equals("Mon:current")){
            ((MainMenuActivity)context).setMon(result);
        }

        else if(type.equals("Tue:current")){
            ((MainMenuActivity)context).setTue(result);
        }

        else if(type.equals("Wed:current")){
            ((MainMenuActivity)context).setWed(result);
        }

        else if(type.equals("Thu:current")){
            ((MainMenuActivity)context).setThu(result);
        }

        else if(type.equals("Fri:current")){
            ((MainMenuActivity)context).setFri(result);
        }

        else if(type.equals("Sat:current")){
            ((MainMenuActivity)context).setSat(result);
        }

        else if(type.equals("Sun:current")){
            ((MainMenuActivity)context).setSun(result);
        }
        else if(type.equals("search")){
            Intent intent = new Intent();
            intent.setClass(context, SearchResultActivity.class);
            intent.putExtra("SearchResult", result);
            intent.putExtra("userName", user_name);
            context.startActivity(intent);
        }
        else if(result.equals("register failed")){
            CharSequence text = "Invalid: Account already taken";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else if(result.equals("login failed")){
            CharSequence text = "Invalid: Login details incorrect";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else if(result.equals("appointment success")){
            ((AddActivity)context).setResult(result);
        }
        else if(result.equals("appointment failed")){
            CharSequence text = "Appointment not added";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }

        else if(result.equals("update success")){
            ((AddActivity)context).setResult(result);

        }
        else if(result.equals("update failed")){
            CharSequence text = "Appointment not added";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}