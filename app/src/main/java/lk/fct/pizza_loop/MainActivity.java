package lk.fct.pizza_loop;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.RelativeLayout;
import android.widget.TextView;
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


public class MainActivity extends AppCompatActivity implements ProductAdapter.OnItemClickListner {


    private static final String URL = "http://" + IPAddress.IPAddress + ":8080/demo/all/";

    private static final String URL1 = "http://" + IPAddress.IPAddress + ":8080/demo/findByUserID?userid="+loginActivity.id+"";

    JSONArray products;


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

        RelativeLayout cart = (RelativeLayout) findViewById(R.id.gotocart);


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (products.length() == 0) {
                    Intent intent = new Intent(MainActivity.this, EmptyCart.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, CartActivity.class);

                    startActivity(intent);
                }
            }
        });


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    products = new JSONArray(response);

                    TextView badge = (TextView) findViewById(R.id.cart_badge);

                    if (products.length() == 0) {
                        badge.setVisibility(View.GONE);
                    } else {
                        badge.setText("" + products.length());
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);


    }


    private void loaditem() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray products = new JSONArray(response);

                    for (int i = 0; i < products.length(); i++) {

                        JSONObject productobject = products.getJSONObject(i);

                        String imageurl = productobject.getString("imageUrl");
                        String name = productobject.getString("name");
                        String description = productobject.getString("description");
                        Double price = productobject.getDouble("price");
                        Double smallprice = productobject.getDouble("smallprice");
                        Double mediumprice = productobject.getDouble("mediumprice");
                        Double largeprice = productobject.getDouble("largeprice");


                        Pizza product = new Pizza(name, description, price, imageurl, smallprice, mediumprice, largeprice);
                        productslist.add(product);

                    }

                    Context context = recyclerView.getContext();

                    LayoutAnimationController animationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);
                    recyclerView.setLayoutAnimation(animationController);

                    adapter = new ProductAdapter(MainActivity.this, productslist);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(MainActivity.this);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Sign Out?")
                .setMessage("Are you sure you want to Sign Out?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        moveTaskToBack(true);
                        android.os.Process.killProcess(android.os.Process.myPid());
                        Intent main = new Intent(MainActivity.this, loginActivity.class);
                        startActivity(main);
                        System.exit(1);
                    }
                }).create().show();

    }

}
