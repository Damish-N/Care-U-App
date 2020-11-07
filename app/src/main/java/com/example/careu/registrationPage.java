package com.example.careu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class registrationPage extends AppCompatActivity {

    EditText _fname,_lname,_email,_pnum,_username,_pwd,_nic,_address,_r1,_r1_num,_r2,_r2_num,_r3,_r3_num;
    //TextView _fnametv,_lnametv,_emailtv,_pnumtv,_usernametv,_pwdtv,_nictv,_addresstv,_r1_numtv,_r2_numtv,_r3_numtv;
    AwesomeValidation awesomeValidation;
    Button selectPics1,selectPics2,upload1,upload2,btnReg;
    Bitmap bitmap1,bitmap2;
    Spinner _gender;
    DatePicker _birthDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);


        _fname = findViewById(R.id.fname);
        _lname = findViewById(R.id.lname);
        _email = findViewById(R.id.email);
        _pnum = findViewById(R.id.pnum);
        _username = findViewById(R.id.usrname);
         _pwd = findViewById(R.id.pwd);
        _nic = findViewById(R.id.nic);
        _address = findViewById(R.id.address);
        _r1 = findViewById(R.id.r1);
        _r1_num = findViewById(R.id.r1_num);
        _r2 = findViewById(R.id.r2);
        _r2_num = findViewById(R.id.r2_num);
        _r3 = findViewById(R.id.r3);
        _r3_num = findViewById(R.id.r3_num);

        _gender = findViewById(R.id.gender);
        _birthDay=findViewById(R.id.birthDay);


//        _fnametv = findViewById(R.id.fnametv);
//        _lnametv = findViewById(R.id.lnametv);
//        _emailtv = findViewById(R.id.emailtv);
//        _pnumtv = findViewById(R.id.pnumtv);
//        _usernametv = findViewById(R.id.usernametv);
//        _pwdtv = findViewById(R.id.pwdtv);
//        _nictv = findViewById(R.id.nictv);
//        _addresstv = findViewById(R.id.addresstv);
//        _r1_numtv =findViewById(R.id.r1_numtv);
//        _r2_numtv =findViewById(R.id.r2_numtv);
//        _r3_numtv =findViewById(R.id.r3_numtv);

//      _tv1tx  = findViewById(R.id.tv1tx);


      //  selectPics.setOnClickListener(new View.OnClickListener() {
        selectPics1 = findViewById(R.id.btnSelectPic1);
        selectPics2 = findViewById(R.id.btnSelectPic2);
        btnReg = findViewById(R.id.btnReg);
       // nicNum = findViewById(R.id.nic);
        final String uploadUrl = "http://10.0.2.2/careu-php/uploadID.php";

//        btnReg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(getApplicationContext(),loginPage.class);
//                startActivity(i);
//            }
//        });

        selectPics1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(registrationPage.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(registrationPage.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });

        selectPics2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(registrationPage.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(registrationPage.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                    return;
                }
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
                intent.setType("image/*");
                startActivityForResult(intent,2);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode== RESULT_OK && data != null){
            Uri path = data.getData();
            try {
                bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(requestCode==2 && resultCode== RESULT_OK && data != null){
            Uri path = data.getData();
            try {
                bitmap2 = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String imageToString(Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgbytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgbytes,Base64.DEFAULT);
    }

    public void register(View view) {
        String uploadUrl = "http://10.0.2.2/careu-php/uploadID.php";
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        String s;
        String fname = _fname.getText().toString();
        awesomeValidation.addValidation(this,R.id.fname, RegexTemplate.NOT_EMPTY,R.string.Invalid_First_name);

        String lname = _lname.getText().toString();
      awesomeValidation.addValidation(this,R.id.lname, RegexTemplate.NOT_EMPTY,R.string.Invalid_Last_name);

        String email = _email.getText().toString();
       awesomeValidation.addValidation(this,R.id.email, Patterns.EMAIL_ADDRESS,R.string.Invalid_email);

        String phone = _pnum.getText().toString();
       // number(phone,R.id.pnum);
       awesomeValidation.addValidation(this,R.id.pnum,"[0]{1}[7]{1}[1||2||5||6||7||8]{1}[0-9]{7}$",R.string.invalid_number1);

        final String username = _username.getText().toString();
       awesomeValidation.addValidation(this,R.id.usrname, RegexTemplate.NOT_EMPTY,R.string.Invalid_user_name);

        String pwd = _pwd.getText().toString();
        awesomeValidation.addValidation(this,R.id.pwd,".{8,}",R.string.invalid_password);

        String NIC = _nic.getText().toString();

        if (NIC.length()==10){

            awesomeValidation.addValidation(this,R.id.nic,"[0-9]{9}[V|v]{1}$",R.string.Invalid_NIC);
        }else if (NIC.length()==12) {
//            Toast.makeText(this, "length is"+NIC.length(), Toast.LENGTH_LONG).show();
            awesomeValidation.addValidation(this, R.id.nic, "[0-9]{12}$", R.string.Invalid_NIC);
        }else {
//            Toast.makeText(this, "length is"+NIC.length(), Toast.LENGTH_LONG).show();
            awesomeValidation.addValidation(this,R.id.nic,"",R.string.Invalid_NIC);
        }

        String address = _address.getText().toString();
        awesomeValidation.addValidation(this,R.id.address, RegexTemplate.NOT_EMPTY,R.string.Invalid_address);

        String gender = _gender.getSelectedItem().toString();
//        Toast.makeText(this, gender, Toast.LENGTH_LONG).show();

        int day = _birthDay.getDayOfMonth();
        int month = _birthDay.getMonth()+1;
        int year =  _birthDay.getYear();
        String dateOfBirth = year + "/" + month + "/"+day  ;
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(year, month, day);

//        String bday =calendar.getTime().toString();
//        Toast.makeText(this, k, Toast.LENGTH_LONG).show();


        String r1 = _r1.getText().toString();
        String r1_num = _r1_num.getText().toString();
       if (!r1.isEmpty()){
//           number(_r1_num.getText().toString(),R.id.r1_num);
           awesomeValidation.addValidation(this,R.id.r1_num,"[0]{1}[7]{1}[1||2||5||6||7||8]{1}[0-9]{7}$",R.string.invalid_number1);
        }

        String r2 = _r2.getText().toString();
        String r2_num = _r2_num.getText().toString();
        if (!r2.isEmpty()){
//            number(_r2_num.getText().toString(),R.id.r2_num);
            awesomeValidation.addValidation(this,R.id.r2_num,"[0]{1}[7]{1}[1||2||5||6||7||8]{1}[0-9]{7}$",R.string.invalid_number1);
       }

        String r3 = _r3.getText().toString();
        String r3_num = _r3_num.getText().toString();
        if (!r3.isEmpty()){
//            number(_r3_num.getText().toString(),R.id.r3_num);
            awesomeValidation.addValidation(this,R.id.r3_num,"[0]{1}[7]{1}[1||2||5||6||7||8]{1}[0-9]{7}$",R.string.invalid_number1);
        }
        if (awesomeValidation.validate()) {
         //   Intent i = new Intent(this, loginPage.class);
           // startActivity(i);
            String type = "register";

            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            String state =backgroundWorker.execute(type,fname,lname,email,phone,username,pwd,NIC,address,gender,dateOfBirth,r1,r1_num,r2,r2_num,r3,r3_num).get();
            if (state.equals("Registration successful")){
                Intent i = new Intent(this, loginPage.class);
                this.startActivity(i);
            }

        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, uploadUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String Response = jsonObject.getString("response");
                    Toast.makeText(registrationPage.this,Response,Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String> params = new HashMap<>();
                params.put("userName",username);
                params.put("name",_nic.getText().toString().trim()+"_1");
                params.put("image",imageToString(bitmap1));

                return params;
            }
        };
        MySingleton.getInstance(registrationPage.this).addToRequestQue(stringRequest);

        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, uploadUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String Response = jsonObject.getString("response");
                    Toast.makeText(registrationPage.this,Response,Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String,String> params = new HashMap<>();
                params.put("userName",username);
                params.put("name",_nic.getText().toString().trim()+"_2");
                params.put("image",imageToString(bitmap2));

                return params;
            }
        };
        MySingleton.getInstance(registrationPage.this).addToRequestQue(stringRequest2);
    }


   public void number (String s,int k) {
        if (s.length()==10){
            awesomeValidation.addValidation(this,k,"[0]{1}[7]{1}[0||1||2||5||6||7||8]{1}[0-9]{7}$",R.string.invalid_number1);
        }else if (s.length()==9){
            if (s.startsWith("0")){
                awesomeValidation.addValidation(this,k,"[0]{1}[7]{1}[0||1||2||5||6||7||8]{1}[0-9]{7}$",R.string.invalid_number);
            }else {
                awesomeValidation.addValidation(this, k, "[7]{1}[0||1||2||5||6||7||8]{1}[0-9]{7}$", R.string.invalid_number2);
            }
        }else{
            awesomeValidation.addValidation(this,k,"[0]{1}[7]{1}[0||1||2||5||6||7||8]{1}[0-9]{7}$",R.string.invalid_number);
        }
   }
}
