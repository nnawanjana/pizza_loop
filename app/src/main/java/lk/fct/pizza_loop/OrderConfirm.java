package lk.fct.pizza_loop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;

public class OrderConfirm extends AppCompatActivity {

    CardView address, payment;
    TextView name, address1, address2, postalCode, phonNumber, cardnumber;

    String add1_name, add1_street, add1_unit, add1_city, add1_province, add1_postalcode, add1_mobile;

    String cnumber1;
    TextView price;
    CardView visa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);

        TextView addaddress = (TextView) findViewById(R.id.addaddress);

        address = (CardView) findViewById(R.id.address);

        name = (TextView) findViewById(R.id.name);
        address1 = (TextView) findViewById(R.id.address1);
        address2 = (TextView) findViewById(R.id.address2);
        postalCode = (TextView) findViewById(R.id.postal_code);
        phonNumber = (TextView) findViewById(R.id.phonenumber);

        cardnumber=(TextView)findViewById(R.id.cardnumber);
        TextView addcard = (TextView) findViewById(R.id.addcard);
        payment=(CardView)findViewById(R.id.payment);

        price=(TextView) findViewById(R.id.price);
        Button pay=(Button)findViewById(R.id.pay);

        addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderConfirm.this, AddAddress.class);
                startActivity(intent);


            }
        });

        addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderConfirm.this, AddAddress.class);
                startActivity(intent);

            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderConfirm.this, OrderSuccess.class);
                delcart();
                startActivity(intent);
            }
        });

        add1_name = getIntent().getStringExtra("NAME1");
        add1_mobile = getIntent().getStringExtra("MOBILE1");
        add1_street = getIntent().getStringExtra("STREET1");
        add1_unit = getIntent().getStringExtra("UNIT1");
        add1_province = getIntent().getStringExtra("PROVINCE1");
        add1_city = getIntent().getStringExtra("CITY1");
        add1_postalcode = getIntent().getStringExtra("POSTALCODE1");

        String totprice = getIntent().getStringExtra("PRICE");
        price.setText(""+totprice);

        name.setText(add1_name);
        address1.setText((add1_street) + ", " + (add1_unit));
        address2.setText((add1_city) + ", " + (add1_province));
        postalCode.setText(add1_postalcode);
        phonNumber.setText(add1_mobile);


        cnumber1=getIntent().getStringExtra("CARD NUMBER");

        cardnumber.setText("**** **** **** " + cnumber1);

        }

    private void delcart()
    {
        String URL= "http://"+IPAddress.IPAddress+":8080/demo/deleteByStatus?status=1";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

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
