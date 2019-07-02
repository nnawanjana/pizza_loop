package lk.fct.pizza_loop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class EmptyCart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_cart);

        ImageView back = (ImageView) findViewById(R.id.backarrow);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent main = new Intent(EmptyCart.this, MainActivity.class);
                startActivity(main);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(EmptyCart.this, "Please click the Back Arrow", Toast.LENGTH_LONG).show();
    }
}
