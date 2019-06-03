package lk.fct.pizza_loop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class OrderDetailActivity extends AppCompatActivity {

    private ImageView pimg;
    private  TextView Pname;
    private TextView pdescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Intent intent = getIntent();
        final String imgurl = intent.getStringExtra("IMG");
        final String name1 = intent.getStringExtra("NAME");
        final String description1 = intent.getStringExtra("DETAILS");

        pimg=(ImageView)findViewById(R.id.image);
        Pname = (TextView) findViewById(R.id.name);
        pdescription = (TextView) findViewById(R.id.description);

        Glide.with(OrderDetailActivity.this).load(imgurl).into(pimg);
        Pname.setText(name1);
        pdescription.setText(description1);
    }
}
