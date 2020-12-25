# WebService
Webservice is a library project which contains CURD operation implementations and utilities based on Android's Volley library.

## Using Gradle
You can just add the dependency like below

dependencies {
   
    implementation 'com.android.volley:volley:1.1.1'

}

### Requests for commons tasks
* Insert data
* Fetch data and show in listview
* Update
* Delete


### Api used

 Insert=https://appd2u.000webhostapp.com/insert.php
 Fetch=https://appd2u.000webhostapp.com/fetch.php
 Update=https://appd2u.000webhostapp.com/update.php
 Delete=https://appd2u.000webhostapp.com/delete.php


#### How to use

On application startup you need to set the api

    String geturl="https://appd2u.000webhostapp.com/fetch.php";
    String seturl="https://appd2u.000webhostapp.com/insert.php";
    String updateurl="https://appd2u.000webhostapp.com/update.php";
    String deleteurl="https://appd2u.000webhostapp.com/delete.php";
    
##### Insert Opertion


                final ProgressDialog pd=new ProgressDialog(MainActivity.this);//create object of progress Dialog and pass this calss
                pd.setMessage("Please Wait"); //set message
                pd.show();//show it
               //craete object of StringRequest and pass 4 parameter
               //1=define method which u used post/get
               //2=uri
               //3=response listener
               //4=error listener

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
            
            
            
##### Fetch Opertion


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
            
            
            
 ##### Update Opertion
 
 
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


##### Delete Opertion

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
