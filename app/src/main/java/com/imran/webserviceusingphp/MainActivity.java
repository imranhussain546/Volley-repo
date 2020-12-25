package com.imran.webserviceusingphp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String geturl="https://appd2u.000webhostapp.com/fetch.php";
    String seturl="https://appd2u.000webhostapp.com/insert.php";
    String updateurl="https://appd2u.000webhostapp.com/update.php";
    String deleteurl="https://appd2u.000webhostapp.com/delete.php";

    EditText ename,esalary,ephone;
    Button eregister,eshow,eupdate,edelete;
    ListView lv;
    String data;
    RequestQueue requestQueue; //it is used 4 sent data to server it is under volley
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ename=findViewById(R.id.name);
        esalary=findViewById(R.id.salary);
        ephone=findViewById(R.id.phone);
        lv=findViewById(R.id.list);

        eregister=findViewById(R.id.register);
        eshow=findViewById(R.id.show);
        eupdate=findViewById(R.id.update);
        edelete=findViewById(R.id.delete);
        requestQueue= Volley.newRequestQueue(this); //it create new request queue

        eregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                final ProgressDialog pd=new ProgressDialog(MainActivity.this);//create object of progress Dialog and pass this calss
                pd.setMessage("Please Wait"); //set message
                pd.show();//show it
               //craete object of StringRequest and pass 4 parameter
                //1=define method which u used post/get
                //2=uri

                StringRequest myrequest=new StringRequest(Request.Method.POST, seturl, new Response.Listener<String>() //3=respone listener it told what server respone
                {
                    @Override
                    public void onResponse(String response)
                    {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() //4=error listener if any error come in server it told
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                    //init block and overide params method through this data goes to server
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError
                    {
                        Map<String,String> mymap=new HashMap<String, String>(); //object hashmap

                        mymap.put("name",ename.getText().toString()); //sent data
                        mymap.put("salary",esalary.getText().toString());
                        mymap.put("phone",ephone.getText().toString());

                        return mymap; //return my map
                    }
                };
                requestQueue.add(myrequest); //send request to server
            }
        });

        eshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                final ProgressDialog pd=new ProgressDialog(MainActivity.this);//create object of progress Dialog and pass this calss
                pd.setMessage("Please Wait"); //set message
                pd.show();//show it

                StringRequest myrequest=new StringRequest(Request.Method.POST, geturl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        pd.dismiss();
                        ArrayList arrayList=new ArrayList();
                        try {
                            JSONObject jsonObject=new JSONObject(response); //create json object and pass response it get json object from database
                           JSONArray jsonArray= jsonObject.getJSONArray("result"); //through json object called json array and pass array name which is declare in php code
                            for (int i=0;i<jsonArray.length();i++)
                            {
                                JSONObject jobj=jsonArray.getJSONObject(i);//through json array called json objet pass index(i) it get all json index value
                                int id=jobj.getInt("id"); //it get id & store in int
                                String name=jobj.getString("name"); //it get name
                                int salary=jobj.getInt("salary"); //it get salary
                                String phone=jobj.getString("phone");//it get phone
                                arrayList.add(id+"\n"+name+"\n"+salary+"\n"+phone+"\n");

                                //Toast.makeText(MainActivity.this, ""+id+name+salary+phone, Toast.LENGTH_SHORT).show();

                            }

                        }catch(Exception e)
                        {}

                        ArrayAdapter<ArrayList> adapter=new ArrayAdapter<ArrayList>(MainActivity.this,android.R.layout.simple_list_item_1,arrayList);
                        lv.setAdapter(adapter);
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
               requestQueue.add(myrequest);
            }

        });

        eupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                final ProgressDialog pd=new ProgressDialog(MainActivity.this);//create object of progress Dialog and pass this calss
                pd.setMessage("Please Wait"); //set message
                pd.show();//show it
                //craete object of StringRequest and pass 4 parameter
                //1=define method which u used post/get
                //2=uri

                StringRequest myrequest=new StringRequest(Request.Method.POST, updateurl, new Response.Listener<String>() //3=respone listener it told what server respone
                {
                    @Override
                    public void onResponse(String response)
                    {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() //4=error listener if any error come in server it told
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                        //init block and overide params method through this data goes to server
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError
                    {
                        Map<String,String> mymap=new HashMap<String, String>(); //object hashmap

                        mymap.put("salary",esalary.getText().toString());
                        mymap.put("phone",ephone.getText().toString());

                        return mymap; //return my map
                    }
                };
                requestQueue.add(myrequest); //send request to server

            }
        });

        edelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                final ProgressDialog pd=new ProgressDialog(MainActivity.this);//create object of progress Dialog and pass this calss
                pd.setMessage("Please Wait"); //set message
                pd.show();//show it

                StringRequest myrequest=new StringRequest(Request.Method.POST, deleteurl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError
                    {
                        Map<String,String> mymap=new HashMap<String, String>(); //object hashmap

                        mymap.put("phone",ephone.getText().toString());

                        return mymap; //return my map
                    }
                };
                requestQueue.add(myrequest);
            }
        });

    }

}