package lk.fct.pizza_loop;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class loginActivity extends AppCompatActivity {

    EditText username1;
    EditText password1;
    Button login1;

    String Username2;
    String Password2;

    static String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username1 = (EditText) findViewById(R.id.username);
        password1 = (EditText) findViewById(R.id.password);
        login1 = (Button) findViewById(R.id.login);
        TextView Signup = (TextView) findViewById(R.id.createaccount);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(loginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username1.length() == 0) {
                    username1.setError("Enter Username");
                } else if (password1.length() == 0) {
                    password1.setError("Enter Password");
                }

                login();

            }
        });

    }

    private void login() {


        String URL1 = "http://" + IPAddress.IPAddress + ":8080/demo/findByUserName?username=" + username1.getText().toString() + "";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONArray carts = new JSONArray(response);

                    for (int i = 0; i < carts.length(); i++) {

                        JSONObject Userobject = carts.getJSONObject(i);

                        id = Userobject.getString("loginId");
                        Username2 = Userobject.getString("userName");
                        Password2 = Userobject.getString("password");

                        try {

                            if (Password2.equals(password1.getText().toString())) {
                                Intent intent = new Intent(loginActivity.this, MainActivity.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(loginActivity.this, "Username or Password is Incorrect!", Toast.LENGTH_LONG).show();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(loginActivity.this, "Username or Password is Incorrect!", Toast.LENGTH_LONG).show();
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(loginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

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
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        loginActivity.super.onBackPressed();
                    }
                }).create().show();
    }
}
