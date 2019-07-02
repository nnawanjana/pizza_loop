package lk.fct.pizza_loop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class AddAddress1 extends AppCompatActivity {

    EditText name, mobile, street, unit, province, city, postalcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address1);

        name = (EditText) findViewById(R.id.name);
        mobile = (EditText) findViewById(R.id.mobile);
        street = (EditText) findViewById(R.id.street);
        unit = (EditText) findViewById(R.id.unit);
        province = (EditText) findViewById(R.id.province);
        city = (EditText) findViewById(R.id.city);
        postalcode = (EditText) findViewById(R.id.postalCode);
        Button btnnext = (Button) findViewById(R.id.next);


        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.length() == 0) {
                    name.setError("Enter Name");
                } else if (mobile.length() == 0) {
                    mobile.setError("Enter Mobile Number");
                } else if (street.length() == 0) {
                    street.setError("Enter Street");
                } else if (unit.length() == 0) {
                    unit.setError("Enter Unit");
                } else if (province.length() == 0) {
                    province.setError("Enter Province");
                } else if (city.length() == 0) {
                    city.setError("Enter City");
                } else if (postalcode.length() == 0) {
                    postalcode.setError("Enter Postal Code");
                } else if (!(mobile.length() == 10)) {
                    mobile.setError("Check the mobile number");
                } else {
                    Intent intent = new Intent(AddAddress1.this, OrderSuccess.class);
                    delcart();
                    startActivity(intent);
                }
            }
        });
    }

    private void delcart() {
        String URL = "http://" + IPAddress.IPAddress + ":8080/demo/deleteByUserID?userid="+loginActivity.id+"";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AddAddress1.this, error.getMessage(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(AddAddress1.this, "You Can not go back!", Toast.LENGTH_LONG).show();
    }

}