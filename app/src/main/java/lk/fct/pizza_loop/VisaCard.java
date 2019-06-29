package lk.fct.pizza_loop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.craftman.cardform.Card;
import com.craftman.cardform.CardForm;
import com.craftman.cardform.OnPayBtnClickListner;

public class VisaCard extends AppCompatActivity {

    String add_name;
    String add_street;
    String add_unit;
    String add_city;
    String add_province;
    String add_postalcode;
    String add_mobile,totprice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_visa_card);

        totprice=getIntent().getStringExtra("PRICE");

        final TextView price=(TextView)findViewById(R.id.payment_amount);
        TextView pricetext=(TextView)findViewById(R.id.payment_amount_holder);
        Button btnpay=(Button)findViewById(R.id.btn_pay);
        CardForm cardForm = (CardForm) findViewById(R.id.cardform);

        pricetext.setText("");
        price.setText("");
        btnpay.setText("SAVE");

        add_name = getIntent().getStringExtra("NAME");
        add_mobile = getIntent().getStringExtra("MOBILE");
        add_street = getIntent().getStringExtra("STREET");
        add_unit = getIntent().getStringExtra("UNIT");
        add_province = getIntent().getStringExtra("PROVINCE");
        add_city = getIntent().getStringExtra("CITY");
        add_postalcode = getIntent().getStringExtra("POSTALCODE");

//        btnpay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(VisaCard.this,OrderConfirm.class);
//                startActivity(intent);
//
//            }
//        });

        cardForm.setPayBtnClickListner(new OnPayBtnClickListner() {
            @Override
            public void onClick(Card card) {
                String cnumber=card.getNumber();
                String lastFourDigits = "";

                if (cnumber.length() > 4)
                {
                    lastFourDigits = cnumber.substring(cnumber.length() - 4);
                }
                else
                {
                    lastFourDigits = cnumber;
                }
                Intent intent=new Intent(VisaCard.this,OrderConfirm.class);
                intent.putExtra("CARD NUMBER",lastFourDigits);
                intent.putExtra("NAME1",add_name);
                intent.putExtra("MOBILE1",add_mobile);
                intent.putExtra("STREET1",add_street);
                intent.putExtra("UNIT1",add_unit);
                intent.putExtra("PROVINCE1",add_province);
                intent.putExtra("CITY1",add_city);
                intent.putExtra("POSTALCODE1",add_postalcode);
                intent.putExtra("PRICE",totprice);


                startActivity(intent);
//
            }
        });
    }
}
