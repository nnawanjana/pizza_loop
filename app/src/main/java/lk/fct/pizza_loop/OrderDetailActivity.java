package lk.fct.pizza_loop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class OrderDetailActivity extends AppCompatActivity {

    private ImageView pimg;
    private  TextView Pname;
    private TextView pdescription;
    private CardView cardViewpan,cardViewstuffed,cardViewsausage,cardViewpersonal,cardViewmedium,cardViewlarge;
    private RadioButton cr1,cr2,cr3,sr1,sr2,sr3;



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

        cardViewpan=(CardView)findViewById(R.id.pan);
        cardViewstuffed=(CardView)findViewById(R.id.stuffed);
        cardViewsausage=(CardView)findViewById(R.id.sausage);
        cr1=(RadioButton) findViewById(R.id.chkpan);
        cr2=(RadioButton)findViewById(R.id.chkstuffed);
        cr3=(RadioButton)findViewById(R.id.chksausage);


        cardViewpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(cr1.isChecked()==false) {
                        cr1.setChecked(true);
                        cr2.setChecked(false);
                        cr3.setChecked(false);
                    }
                    else if(cr1.isChecked()==true) {
                        cr1.setChecked(false);
                    }

                }
            });

        cardViewstuffed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cr2.isChecked()==false) {
                    cr2.setChecked(true);
                    cr1.setChecked(false);
                    cr3.setChecked(false);
                }
                else if(cr2.isChecked()==true) {
                    cr2.setChecked(false);
                }

            }
        });

        cardViewsausage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(cr3.isChecked()==false) {
                    cr3.setChecked(true);
                    cr1.setChecked(false);
                    cr2.setChecked(false);
                }
                else if(cr3.isChecked()==true) {
                    cr3.setChecked(false);
                }

            }
        });


        cardViewpersonal=(CardView)findViewById(R.id.personal);
        cardViewmedium=(CardView)findViewById(R.id.medium);
        cardViewlarge=(CardView)findViewById(R.id.large);
        sr1=(RadioButton) findViewById(R.id.chkpersonal);
        sr2=(RadioButton)findViewById(R.id.chkmedium);
        sr3=(RadioButton)findViewById(R.id.chklarge);

        cardViewpersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sr1.isChecked()==false) {
                    sr1.setChecked(true);
                    sr2.setChecked(false);
                    sr3.setChecked(false);
                }
                else if(sr1.isChecked()==true) {
                    sr1.setChecked(false);
                }

            }
        });

        cardViewmedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sr2.isChecked()==false) {
                    sr2.setChecked(true);
                    sr1.setChecked(false);
                    sr3.setChecked(false);
                }
                else if(sr2.isChecked()==true) {
                    sr2.setChecked(false);
                }

            }
        });

        cardViewlarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sr3.isChecked()==false) {
                    sr3.setChecked(true);
                    sr2.setChecked(false);
                    sr1.setChecked(false);
                }
                else if(sr3.isChecked()==true) {
                    sr3.setChecked(false);
                }

            }
        });

    }
}
