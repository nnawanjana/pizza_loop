package lk.fct.pizza_loop;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class DeletePopup extends AppCompatActivity {

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_popup);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.7),(int)(height*.25));

        TextView cancel=(TextView)findViewById(R.id.cancel);
        TextView remove=(TextView)findViewById(R.id.remove);

        id=getIntent().getExtras().getString("CARTID");

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa"+id);
                removeitem();
                if(id==null){
                    Intent main = new Intent(DeletePopup.this, EmptyCart.class);
                    startActivity(main);
                }else {
                    Intent intent = new Intent(DeletePopup.this, CartActivity.class);
                    startActivity(intent);
                }

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


    }

    private void removeitem(){

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

    }


}
