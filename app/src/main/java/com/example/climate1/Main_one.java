package com.example.climate1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main_one extends AppCompatActivity {
TextView Country_city,Date_time,Temp_min,Temp_max;
ImageView imageView;
RecyclerView recycle;
EditText searchView;
ImageButton search;
    WeatherarrAdapter weatherarrAdapter;
    String cityname;

    //defining adapter here so that it can be accessed inside any block
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_one);
        Country_city =findViewById(R.id.Cityname);
        Date_time=findViewById(R.id.Date);
        Temp_min=findViewById(R.id.Min);
        Temp_max=findViewById(R.id.Max);
        recycle=findViewById(R.id.recycle);
        imageView=findViewById(R.id.imm);
        searchView=findViewById(R.id.editText);
        search=findViewById(R.id.imageButton);
     //   recycle.setLayoutManager( new LinearLayoutManager(this)); this is used when we are usin recycler view  we can use it either xml or in java
        //right now we already creater in java
//creating and initializing an arraylist
        ArrayList<Arraydata> datamodalarray;
        datamodalarray=new ArrayList<>();

search.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        cityname=searchView.getText().toString();

        // storing API link into a string name url
        String url="http://api.weatherapi.com/v1/forecast.json?key=311b0430b7b74866af541519231511&q=India&days=1&aqi=yes&alerts=yes";
        //Api data fetching code started
        // for this first add volley librery
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    //entering into the location object
                    JSONObject jsonObject=response.getJSONObject("location");
                    //fetching string from the location objects
                   String country= jsonObject.getString("country");
                    String city=jsonObject.getString("region");
                    Country_city.setText(country+" "+city);
                    String time=jsonObject.getString("localtime");
                    //Date_time.append(time);
//entering into the forecast object then forecast day array inside it then  object 0 and then onject day
                JSONObject jsonObject1=response.getJSONObject("forecast");
                 JSONArray jsonarray= jsonObject1.getJSONArray("forecastday");
                   JSONObject jsonObject2=jsonarray.getJSONObject(Integer.parseInt("0"));
                   String dd=jsonObject2.getString("date");
                   Date_time.setText(dd);
                    JSONArray jsonArray1=jsonObject2.getJSONArray("hour");
                    // with the help of for loop accesing different elements
                   for(int i=0;i<24;i++){
                    JSONObject jsonObject4=jsonArray1.getJSONObject(i);
                    String tim=jsonObject4.getString("time");
                    String wind=jsonObject4.getString("wind_kph");
                JSONObject jsonObject5=jsonObject4.getJSONObject("condition");
                   String icon=jsonObject5.getString("icon");
                   //adding elements to the arraylist
                    datamodalarray.add(new Arraydata(tim,wind,icon));
                    // the notify method will set changes to the arraylist out of this block
                    weatherarrAdapter.notifyDataSetChanged();

                   }//for loop ended here

                    JSONObject jsonObject3=jsonObject2.getJSONObject("day");
                    // fetching the string inside the day object
                    int max_=jsonObject3.getInt("maxtemp_c");
                    int min_=jsonObject3.getInt("mintemp_c");
                    // as the above values have integer datatype we have to convert them into the string type
                    String max_tem=String.valueOf(max_);
                    String min_tem=String.valueOf(min_);
                    // appending them to the textview
                    Temp_max.setText("Max:"+max_tem+"°C");
                    Temp_min.setText("Min:"+min_tem+"°C");

                } catch (JSONException e) {
                    throw new RuntimeException(e);

                }
            }
        },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Main_one.this, "faillll"+error, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
        weatherarrAdapter=new WeatherarrAdapter(getApplicationContext(),datamodalarray);
        recycle.setAdapter(weatherarrAdapter);
    }
});
    }
//    public  String getcityname(){
//       String city= searchView.getText().toString();
//       return city;
//    }

}