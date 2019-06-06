package lk.fct.pizza_loop;


import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Formatter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements ProductAdapter.OnItemClickListner{


    private static final String URL= "http://"+IPAddress.IPAddress+":8080/demo/all";



    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    List<Pizza> productslist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productslist = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loaditem();


    }



    private void loaditem(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray products  = new JSONArray(response);

                    for (int i =0; i<products.length(); i++){

                        JSONObject productobject  = products.getJSONObject(i);

                        //int id = productobject.getInt("pizzaId");
                        String imageurl = productobject.getString("imageUrl");
                        String name = productobject.getString("name");
                        String description = productobject.getString("description");
                        Double price = productobject.getDouble("price");
                        Double smallprice = productobject.getDouble("smallprice");
                        Double mediumprice = productobject.getDouble("mediumprice");
                        Double largeprice = productobject.getDouble("largeprice");



                        Pizza product = new Pizza(name, description, price, imageurl,smallprice,mediumprice,largeprice);
                        productslist.add(product);

                    }

                    adapter = new ProductAdapter(MainActivity.this, productslist);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(MainActivity.this);

                    //   progressDialog.dismiss();

                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(HomeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(HomeActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailintent = new Intent(this, OrderDetailActivity.class);
        Pizza clickedItem = productslist.get(position);

        detailintent.putExtra("IMG", clickedItem.getImageURL());
        detailintent.putExtra("NAME", clickedItem.getName());
        detailintent.putExtra("DETAILS", clickedItem.getDescription());
        detailintent.putExtra("PRICE", clickedItem.getPrice());
        detailintent.putExtra("SMALLPRICE", clickedItem.getSmallprice());
        detailintent.putExtra("MEDIUMPRICE", clickedItem.getMediumprice());
        detailintent.putExtra("LARGEPRICE", clickedItem.getLargeprice());


        startActivity(detailintent);

    }
}
