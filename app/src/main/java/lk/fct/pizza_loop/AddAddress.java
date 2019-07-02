package lk.fct.pizza_loop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddAddress extends AppCompatActivity {

    EditText name, mobile, street, unit, province, city, postalcode;

    String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        price = getIntent().getStringExtra("PRICE");


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
                    Intent intent = new Intent(AddAddress.this, VisaCard.class);
                    intent.putExtra("NAME", name.getText().toString());
                    intent.putExtra("MOBILE", mobile.getText().toString());
                    intent.putExtra("STREET", street.getText().toString());
                    intent.putExtra("UNIT", unit.getText().toString());
                    intent.putExtra("PROVINCE", province.getText().toString());
                    intent.putExtra("CITY", city.getText().toString());
                    intent.putExtra("POSTALCODE", postalcode.getText().toString());
                    intent.putExtra("PRICE", price);
                    startActivity(intent);

                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(AddAddress.this, "You Can not go back!", Toast.LENGTH_LONG).show();
    }
}