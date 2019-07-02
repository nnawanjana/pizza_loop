package lk.fct.pizza_loop;

import android.content.Intent;
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

public class SignUpActivity extends AppCompatActivity {

    EditText fname,lname,username,password,cpassword,pnumber;

    String loginpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fname=(EditText)findViewById(R.id.fname);
        lname=(EditText)findViewById(R.id.lname);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.spassword);
        cpassword=(EditText)findViewById(R.id.cpassword);
        pnumber=(EditText)findViewById(R.id.pno);

        loginpassword=password.getText().toString();


        TextView login =(TextView) findViewById(R.id.login);

        Button signup=(Button)findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUpActivity.this,loginActivity.class);
                startActivity(intent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(password.getText().toString().equals(cpassword.getText().toString()))){
                    Toast.makeText(SignUpActivity.this, "Password Does not match!", Toast.LENGTH_LONG).show();
                }
                else if (fname.getText().length() == 0) {
                    fname.setError("Enter First Name");
                }
                else if (lname.getText().length() == 0) {
                    lname.setError("Enter Last Name");
                }
                else if (username.getText().length() == 0) {
                    username.setError("Enter User name");
                }
                else if (password.getText().length() == 0) {
                    password.setError("Enter Password");
                }
                else if (cpassword.getText().length() == 0) {
                    cpassword.setError("Re enter password");
                }
                else if (pnumber.getText().length() == 0) {
                    pnumber.setError("Enter Phone Number");
                }
                else if (!(pnumber.getText().length() == 10)) {
                    pnumber.setError("Invalid Phone Number");
                }
                else{
                    adduser();
                    Intent intent=new Intent(SignUpActivity.this,loginActivity.class);
                    startActivity(intent);
                }

            }
        });

    }

    private void adduser() {
        String URL1 = "http://" + IPAddress.IPAddress + ":8080/demo/adduser?loginId=&firstName=" + fname.getText() + "&lastName=" + lname.getText() + "&userName=" + username.getText() + "&password=" + password.getText().toString() + "&phoneNumber=" + pnumber.getText() + "";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SignUpActivity.this,"User already exist!", Toast.LENGTH_LONG).show();
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);

    }
}
