package lk.fct.pizza_loop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class PaymentPopup extends AppCompatActivity {

    RadioButton cash;
    RadioButton visa;
    String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_popup);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.6),(int)(height*.4));

        TextView cancel=(TextView)findViewById(R.id.close);
        TextView next=(TextView)findViewById(R.id.next);

        cash=(RadioButton)findViewById(R.id.cash);
        visa=(RadioButton)findViewById(R.id.visa);

        String a=getIntent().getStringExtra("ORDER");

        String b="cart";
        String c="orderdetail";
        if(a.equals(b)){
            price=getIntent().getStringExtra("PAY");
            System.out.println("ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"+price);
        }else if(a.equals(c)){
//            System.out.println("11111111111111111111111111111111111111111111111111");
            price=getIntent().getStringExtra("PRICE");

        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(visa.isChecked()==true){
                    visa.setChecked(false);
                }
            }
        });


        visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cash.isChecked()==true){
                    cash.setChecked(false);
                }
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cash.isChecked()==true){
                    Intent intent=new Intent(PaymentPopup.this,AddAddress1.class);
                    startActivity(intent);
                }else if(visa.isChecked()==true){
                    Intent intent=new Intent(PaymentPopup.this,AddAddress.class);
                    intent.putExtra("PRICE",price);

//                    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+price);
                    startActivity(intent);
                }
            }
        });
    }
}
