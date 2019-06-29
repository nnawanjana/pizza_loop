package lk.fct.pizza_loop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;
import android.widget.ImageView;
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

public class CartActivity extends AppCompatActivity implements CartAdapter.OnItemClickListner{

    private static final String URL= "http://"+IPAddress.IPAddress+":8080/demo/cart";

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    List<Cart> cartlist;

    double allprice=0.0;

    String imageurl;
    String name;
    String description;
    Double price;
    Double smallprice;
    Double mediumprice;
    Double largeprice;
    String PName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartlist = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ImageView back=(ImageView) findViewById(R.id.backarrow);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(CartActivity.this, MainActivity.class);
                startActivity(main);
            }
        });

        loadcart();

        Button pay=(Button)findViewById(R.id.pay);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CartActivity.this,PaymentPopup.class);
                String Open="cart";
                intent.putExtra("ORDER",Open);
                intent.putExtra("PAY","Rs."+Double.toString(allprice));
                startActivity(intent);
            }
        });

    }

    private void loadcart() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                try {

                    JSONArray products  = new JSONArray(response);

                    for (int i =0; i<products.length(); i++){

                        JSONObject productobject  = products.getJSONObject(i);

                        int cartid = productobject.getInt("cartId");
                        String imageurl = productobject.getString("imageUrl");
                        String pizza_name = productobject.getString("pizzaname");
                        String pizza_crust = productobject.getString("pizzacrust");
                        String pizza_size = productobject.getString("pizzasize");
                        String extra = productobject.getString("extra");
                        int qty = productobject.getInt("qty");
                        double totalprice = productobject.getDouble("totalprice");


                        allprice=allprice+totalprice;

                        TextView totprice=(TextView)findViewById(R.id.totalprice);
                        totprice.setText("RS. "+allprice);


                        Cart product = new Cart(cartid,imageurl,pizza_name, pizza_crust, pizza_size, extra,qty, totalprice);
                        cartlist.add(product);



                    }

                    adapter = new CartAdapter(CartActivity.this, (ArrayList<Cart>) cartlist);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(CartActivity.this);


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


        Cart clickedItem = cartlist.get(position);

        int id=clickedItem.getCartId();

        Intent intent = new Intent(CartActivity.this, DeletePopup.class);
        intent.putExtra("CARTID",Integer.toString(id));
        startActivity(intent);


/*

        String URL1="http://"+IPAddress.IPAddress+":8080/demo/deleteByCartId?id="+id+"";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);
*/
    }
    int id;
    @Override
    public void onCartItemClick(int position) {
        Cart clickedItem = cartlist.get(position);
        id=clickedItem.getCartId();
        PName=clickedItem.getPizza_name();

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+PName);

        findCartItemByname();

//        Intent intent = new Intent(CartActivity.this, OrderDetailEditActivity.class);
//        intent.putExtra("CART_ID",Integer.toString(id));
//        intent.putExtra("IMG", imageurl);
//        intent.putExtra("NAME", name);
//        intent.putExtra("DETAILS", description);
//        intent.putExtra("PRICE", price);
//        intent.putExtra("SMALLPRICE", smallprice);
//        intent.putExtra("MEDIUMPRICE", mediumprice);
//        intent.putExtra("LARGEPRICE", largeprice);
//        System.out.println("222222222222222222222222222222222222222222222222222222222"+largeprice);
//        startActivity(intent);
    }

    private void findCartItemByname(){
        String URL1="http://"+IPAddress.IPAddress+":8080/demo/findByName?name="+PName+"";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray products  = new JSONArray(response);

                    for (int i =0; i<products.length(); i++){

                        JSONObject productobject  = products.getJSONObject(i);

                        //int id = productobject.getInt("pizzaId");
                        imageurl = productobject.getString("imageUrl");
                        name = productobject.getString("name");
                        description = productobject.getString("description");
                        price = productobject.getDouble("price");
                        smallprice = productobject.getDouble("smallprice");
                        mediumprice = productobject.getDouble("mediumprice");
                        largeprice = productobject.getDouble("largeprice");

                        System.out.println("222222222222222222222222222222222222222222222"+name);

                        Intent intent = new Intent(CartActivity.this, OrderDetailEditActivity.class);
                        intent.putExtra("CART_ID",Integer.toString(id));
                        intent.putExtra("IMG", imageurl);
                        intent.putExtra("NAME", name);
                        intent.putExtra("DETAILS", description);
                        intent.putExtra("PRICE", price);
                        intent.putExtra("SMALLPRICE", smallprice);
                        intent.putExtra("MEDIUMPRICE", mediumprice);
                        intent.putExtra("LARGEPRICE", largeprice);
                        startActivity(intent);




                    }

                } catch (JSONException e) {
                    e.printStackTrace();
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

}

