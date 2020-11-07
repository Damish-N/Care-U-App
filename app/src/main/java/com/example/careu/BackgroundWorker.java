package com.example.careu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.UrlQuerySanitizer;
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

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    AlertDialog alertDialog;
    Context context;
    BackgroundWorker(Context ctx){
        context= ctx;
    }
    @Override
    protected String doInBackground(String... params){
        String type = params[0];
        String login_url = "http://10.0.2.2/careu-php/loginApp.php";
        String register_url = "http://10.0.2.2/careu-php/registerApp.php";
        if(type.equals("login")){
            String username = params[1];
            String password = params[2];
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("userName","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+
                        "&"+URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String line="";
                String result="";
                while((line = bufferedReader.readLine()) != null){
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
        }
        else if(type.equals("register")){
            String firstName= params[1];
            String lastName= params[2];
            String email = params[3];
            String phoneNumber = params[4];
            String userName= params[5];
            String password= params[6];
            String nic= params[7];
            String address = params[8];
            String gender = params[9];
            String dateOfBirth = params[10];
            String r1 = params[11];
            String r1_num= params[12];
            String r2 = params[13];
            String r2_num= params[14];
            String r3 = params[15];
            String r3_num= params[16];
            /*String gender= params[8];

            //String dateOfBirth= params[10];
            String r1 = params[11];
            String r1_num= params[12];
            String r2 = params[13];
            String r2_num= params[14];
            String r3 = params[15];
            String r3_num= params[16];*/
            try {
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data  = URLEncoder.encode("userName","UTF-8")+"="+URLEncoder.encode(userName,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                        URLEncoder.encode("firstName","UTF-8")+"="+URLEncoder.encode(firstName,"UTF-8")+"&"+
                        URLEncoder.encode("lastName","UTF-8")+"="+URLEncoder.encode(lastName,"UTF-8")+"&"+
                        URLEncoder.encode("nicNumber","UTF-8")+"="+URLEncoder.encode(nic,"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                        URLEncoder.encode("phoneNumber","UTF-8")+"="+URLEncoder.encode(phoneNumber,"UTF-8")+"&"+
                        URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"+
                        URLEncoder.encode("gender","UTF-8")+"="+URLEncoder.encode(gender,"UTF-8")+"&"+
                        URLEncoder.encode("dateOfBirth","UTF-8")+"="+URLEncoder.encode(dateOfBirth,"UTF-8")+"&"+
                        URLEncoder.encode("relative1","UTF-8")+"="+URLEncoder.encode(r1,"UTF-8")+"&"+
                        URLEncoder.encode("relative1Number","UTF-8")+"="+URLEncoder.encode(r1_num,"UTF-8")+"&"+
                        URLEncoder.encode("relative2","UTF-8")+"="+URLEncoder.encode(r2,"UTF-8")+"&"+
                        URLEncoder.encode("relative2Number","UTF-8")+"="+URLEncoder.encode(r2_num,"UTF-8")+"&"+
                        URLEncoder.encode("relative3","UTF-8")+"="+URLEncoder.encode(r3,"UTF-8")+"&"+
                        URLEncoder.encode("relative3Number","UTF-8")+"="+URLEncoder.encode(r3_num,"UTF-8");

                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String line="";
                String result="";
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Registration successful")){
            alertDialog.setMessage(result);
            alertDialog.show();
//            Intent i = new Intent(context, loginPage.class);
//            context.startActivity(i);
        }else{
            if(result.equals("Already Used User_name please use another one")){
                alertDialog.setMessage(result);
                alertDialog.show();
            }else if (result.equals("Already Created Account using this ID")){
                alertDialog.setMessage(result);
                alertDialog.show();

            }
//            alertDialog.setMessage(result);
//            alertDialog.show();



        }

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
