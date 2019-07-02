package lk.fct.pizza_loop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;


public class ConfirmPopup extends AppCompatActivity {

    String pizzaname;
    String imageurl;
    String size;
    String pextra;
    String pcrust;
    String pprice;
    String qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_popup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .6), (int) (height * .5));

        ImageView image = (ImageView) findViewById(R.id.confimimage);
        TextView name = (TextView) findViewById(R.id.confirmname);
        TextView extra = (TextView) findViewById(R.id.confirmextra);
        TextView crust = (TextView) findViewById(R.id.confirmcrust);
        TextView price = (TextView) findViewById(R.id.confirmprice);

        Intent intent = getIntent();

        imageurl = intent.getStringExtra("IMAGE");
        pizzaname = getIntent().getExtras().getString("NAME");
        size = getIntent().getExtras().getString("SIZE");
        pextra = getIntent().getExtras().getString("EXTRA");
        pcrust = getIntent().getExtras().getString("CRUST");
        pprice = getIntent().getExtras().getString("PRICE");
        qty = getIntent().getExtras().getString("QTY");



        Glide.with(ConfirmPopup.this).load(imageurl).into(image);
        name.setText((pizzaname) + " " + (size));
        extra.setText(pextra);
        crust.setText("Crust : " + pcrust);
        price.setText("RS. " + pprice);

        Button cart = (Button) findViewById(R.id.cart);
        Button home = (Button) findViewById(R.id.gohome);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addcart();
                Intent intent = new Intent(ConfirmPopup.this, CartActivity.class);
                startActivity(intent);

            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addcart();
                Intent intent = new Intent(ConfirmPopup.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }

    private void addcart() {
        String URL1 = "http://" + IPAddress.IPAddress + ":8080/demo/addtocart?cartId=&imageUrl=" + imageurl + "&pizzaname=" + pizzaname + "&pizzacrust=" + pcrust + "&pizzasize=" + size + "&extra=" + pextra + "&qty=" + qty + "&totalprice=" + pprice + "&userID="+loginActivity.id+"&status=1";

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

    }
}
