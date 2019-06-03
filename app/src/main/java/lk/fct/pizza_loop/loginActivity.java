package lk.fct.pizza_loop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class loginActivity extends AppCompatActivity {

    private EditText username1;
    private EditText password1;
    private Button login1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username1=(EditText) findViewById(R.id.username);
        password1=(EditText) findViewById(R.id.password);
        login1=(Button) findViewById(R.id.login);

        login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username1.getText().toString();
                String password=password1.getText().toString();

                if(user=="admin" && password=="123"){
                    //Intent intent=new Intent(loginActivity.this,MainActivity.class);
                    //startActivity(intent);
                    System.out.println("00000000000000000000000000000");
                }
                else{
                    System.out.println("111111111111111111111111111111111111111111111");
                }
            }
        });


    }
}
