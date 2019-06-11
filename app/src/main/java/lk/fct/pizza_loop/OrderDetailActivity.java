package lk.fct.pizza_loop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderDetailActivity extends AppCompatActivity {

    private ImageView pimg;
    private TextView Pname,pdescription;
    private TextView smallprice,mediumprice,largeprice;
    private TextView pprice,totalprice;
    private CardView cardViewpan,cardViewstuffed,cardViewsausage,cardViewpersonal,cardViewmedium,cardViewlarge;
    private CardView cardViewbbq,cardViewcheese,cardViewcham,cardViewcsausage,cardViewonion,cardViewtomato;
    private CardView selectcrust,selectsize,addextra,addqty;
    private  TextView crustmsg,sizemsg,extramsg,qtymsg;
    CheckBox ec1,ec2,ec3,ec4,ec5,ec6;
    private RadioButton cr1,cr2,cr3,sr1,sr2,sr3;
    private  TextView minus,number,plus;

    private Button buynow;
    private LinearLayout addtocart;

    private ElegantNumberButton button;

    private double psize=0.0,pextra=0.0;

    String pizzasize="personal";

    double  pizzaprice;
    //double bbq;
    //double cheese;
    double ham;
    double sausage;
    double onion;
    double tomata;
    double allprice;
    String crust;
    String extra;

    double cartprice;


    int qty=1;

    int num=1;

    Boolean Checked1 = false;

    private Intent intent1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);




        intent1 = getIntent();
        final String imgurl = intent1.getStringExtra("IMG");
        final String name1 = intent1.getStringExtra("NAME");
        final String description1 = intent1.getStringExtra("DETAILS");
        final double price1 = (double) intent1.getDoubleExtra("PRICE", 0.00);
        final double smallprice1 = (double) intent1.getDoubleExtra("SMALLPRICE", 0.00);
        final double mediumprice1 = (double) intent1.getDoubleExtra("MEDIUMPRICE", 0.00);
        final double largeprice1 = (double) intent1.getDoubleExtra("LARGEPRICE", 0.00);

        pizzaprice = (double) Double.parseDouble(String.valueOf(price1));

        pimg = (ImageView) findViewById(R.id.image);
        Pname = (TextView) findViewById(R.id.name);
        pdescription = (TextView) findViewById(R.id.description);
        pprice = (TextView) findViewById(R.id.price);
        smallprice = (TextView) findViewById(R.id.smallprice);
        mediumprice = (TextView) findViewById(R.id.mediumprice);
        largeprice = (TextView) findViewById(R.id.largeprice);
        totalprice = (TextView) findViewById(R.id.totprice);

        Glide.with(OrderDetailActivity.this).load(imgurl).into(pimg);
        Pname.setText(name1);
        pdescription.setText(description1);
        pprice.setText("RS. " + price1);
        smallprice.setText("(RS." + smallprice1+")");
        mediumprice.setText("(RS." + mediumprice1+")");
        largeprice.setText("(RS." + largeprice1+")");

        cardViewpan = (CardView) findViewById(R.id.pan);
        cardViewstuffed = (CardView) findViewById(R.id.stuffed);
        cardViewsausage = (CardView) findViewById(R.id.sausage);
        cr1 = (RadioButton) findViewById(R.id.chkpan);
        cr2 = (RadioButton) findViewById(R.id.chkstuffed);
        cr3 = (RadioButton) findViewById(R.id.chksausage);

        selectcrust =(CardView) findViewById(R.id.selectcrust);
        selectsize =(CardView) findViewById(R.id.selectsize);
        addextra =(CardView) findViewById(R.id.addextra);
        addqty =(CardView) findViewById(R.id.addqty);

        crustmsg = (TextView) findViewById(R.id.crustmsg);
        sizemsg = (TextView) findViewById(R.id.sizemsg);
        extramsg = (TextView) findViewById(R.id.extramsg);
        qtymsg = (TextView) findViewById(R.id.qtymsg);

        cardViewpersonal = (CardView) findViewById(R.id.personal);
        cardViewmedium = (CardView) findViewById(R.id.medium);
        cardViewlarge = (CardView) findViewById(R.id.large);
        sr1 = (RadioButton) findViewById(R.id.chkpersonal);
        sr2 = (RadioButton) findViewById(R.id.chkmedium);
        sr3 = (RadioButton) findViewById(R.id.chklarge);

        cardViewonion= (CardView) findViewById(R.id.onion);
        cardViewtomato = (CardView) findViewById(R.id.tomato);
        //    cardViewcham = (CardView) findViewById(R.id.cham);
        //    cardViewcsausage = (CardView) findViewById(R.id.csausage);
        //    cardViewonion = (CardView) findViewById(R.id.onion);
        //    cardViewtomato = (CardView) findViewById(R.id.tomato);

        cardViewpan.setVisibility(View.GONE);
        cardViewstuffed.setVisibility(View.GONE);
        cardViewsausage.setVisibility(View.GONE);

        cardViewpersonal.setVisibility(View.GONE);
        cardViewmedium.setVisibility(View.GONE);
        cardViewlarge.setVisibility(View.GONE);

        cardViewonion.setVisibility(View.GONE);
        cardViewtomato.setVisibility(View.GONE);


        selectcrust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewpan.setVisibility(View.VISIBLE);
                cardViewstuffed.setVisibility(View.VISIBLE);
                cardViewsausage.setVisibility(View.VISIBLE);
                sizemsg.setText(" ");
                if(cr1.isChecked()==true || cr2.isChecked()==true ||cr3.isChecked()==true){
                    cardViewpersonal.setVisibility(View.GONE);
                    cardViewmedium.setVisibility(View.GONE);
                    cardViewlarge.setVisibility(View.GONE);

                }
            }
        });


        selectsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extramsg.setText(" ");

                if(cr1.isChecked()==true){
                    cardViewpersonal.setVisibility(View.VISIBLE);
                    cardViewmedium.setVisibility(View.VISIBLE);
                    cardViewlarge.setVisibility(View.VISIBLE);
                }
                if(cr2.isChecked()==true){
                    cardViewlarge.setVisibility(View.VISIBLE);

                }
                if(cr3.isChecked()==true){
                    cardViewlarge.setVisibility(View.VISIBLE);

                }
                if (cr1.isChecked()==false && cr2.isChecked()==false && cr3.isChecked()==false)
                    {
                    sizemsg.setText("Select crust first");
                }

            }
        });
        addextra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardViewonion.setVisibility(View.VISIBLE);
                cardViewtomato.setVisibility(View.VISIBLE);

                extramsg.setText(" ");

                if(cr2.isChecked()==true){
                    if (sr3.isChecked()==false)
                    {
                        extramsg.setText("Select size first");
                    }
                }
                if(cr3.isChecked()==true){
                    if (sr3.isChecked()==false)
                    {
                        extramsg.setText("Select size first");
                    }
                }
                if (cr1.isChecked()==false && cr2.isChecked()==false && cr3.isChecked()==false)
                {
                    extramsg.setText("Select crust first");
                }


            }
        });


        cardViewpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cr1.isChecked() == false) {
                    cr1.setChecked(true);
                    cr2.setChecked(false);
                    cr3.setChecked(false);

                //    sr1.setChecked(true);
                    ec1.setChecked(false);
                    ec2.setChecked(false);

                    totalprice.setText("RS. " + smallprice1*qty);
                //    cardViewpersonal.setVisibility(View.VISIBLE);
                //    cardViewmedium.setVisibility(View.VISIBLE);
                    cardViewpan.setVisibility(View.GONE);
                    cardViewstuffed.setVisibility(View.GONE);
                    cardViewsausage.setVisibility(View.GONE);
                    crustmsg.setText("(Pan selected)");
                    sizemsg.setText(" ");
                }
                if(cr1.isChecked()==true){
                    crust="Pan";
                    crustmsg.setText("(Pan selected)");
                    cardViewpan.setVisibility(View.GONE);
                    cardViewstuffed.setVisibility(View.GONE);
                    cardViewsausage.setVisibility(View.GONE);
                }

            }
        });

        cardViewstuffed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cr2.isChecked() == false) {
                    cr2.setChecked(true);
                    cr1.setChecked(false);
                    cr3.setChecked(false);
                    sr3.setChecked(false);

                    ec1.setChecked(false);
                    ec2.setChecked(false);
                    totalprice.setText("RS. " + largeprice1*qty);

                //    cardViewpersonal.setVisibility(View.GONE);
                //    cardViewmedium.setVisibility(View.GONE);
                    cardViewpan.setVisibility(View.GONE);
                    cardViewstuffed.setVisibility(View.GONE);
                    cardViewsausage.setVisibility(View.GONE);
                    crustmsg.setText("(Stuffed selected)");
                    sizemsg.setText(" ");

                }
                if(cr2.isChecked()==true){
                    crust="Stuffed";
                    crustmsg.setText("(Stuffed selected)");
                    cardViewpan.setVisibility(View.GONE);
                    cardViewstuffed.setVisibility(View.GONE);
                    cardViewsausage.setVisibility(View.GONE);
                }

            }
        });

        cardViewsausage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cr3.isChecked() == false) {
                    cr3.setChecked(true);
                    cr1.setChecked(false);
                    cr2.setChecked(false);
                    sr3.setChecked(false);
                    ec1.setChecked(false);

                    ec2.setChecked(false);
                    totalprice.setText("RS. " + largeprice1*qty);
                //    cardViewpersonal.setVisibility(View.GONE);
                //    cardViewmedium.setVisibility(View.GONE);
                    cardViewpan.setVisibility(View.GONE);
                    cardViewstuffed.setVisibility(View.GONE);
                    cardViewsausage.setVisibility(View.GONE);
                    crustmsg.setText("(Sausage selected)");
                    sizemsg.setText(" ");
                }
                if(cr3.isChecked()==true){
                    crust="Sausage";
                    crustmsg.setText("(Sausage selected)");
                    cardViewpan.setVisibility(View.GONE);
                    cardViewstuffed.setVisibility(View.GONE);
                    cardViewsausage.setVisibility(View.GONE);
                }
            }
        });



        //sr1.setChecked(true);

        cardViewpersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sr1.isChecked() == false) {
                    sr1.setChecked(true);
                    pizzasize="personal";
                    Checked1=true;
                   // button.setNumber("1");
                    sr2.setChecked(false);
                    sr3.setChecked(false);
                    ec1.setChecked(false);
                    ec2.setChecked(false);
         //           ec3.setChecked(false);
         //           ec4.setChecked(false);
         //           ec5.setChecked(false);
         //           ec6.setChecked(false);
                    cardViewpersonal.setVisibility(View.GONE);
                    cardViewmedium.setVisibility(View.GONE);
                    cardViewlarge.setVisibility(View.GONE);
                    sizemsg.setText("(Personal selected)");

                } else if (sr1.isChecked() == true) {
                    ec1.setChecked(false);
                    ec2.setChecked(false);
                    //sr1.setChecked(false);
                    sizemsg.setText("(Personal selected)");
                    cardViewpersonal.setVisibility(View.GONE);
                    cardViewmedium.setVisibility(View.GONE);
                    cardViewlarge.setVisibility(View.GONE);
                }

                if(sr1.isChecked()==true){
                    pizzaprice=smallprice1;
                    cartprice=smallprice1*qty;
                    //sizeprice=psize;
                    totalprice.setText("RS. " + pizzaprice*qty);

                }
                else if(sr1.isChecked()==true)
                {
                    //psize=pizzaprice+0;
                    //sizeprice=psize;
                    //totalprice.setText("RS: " + psize);


                }



            }

        });


        cardViewmedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sr2.isChecked() == false) {
                    sr2.setChecked(true);
                    pizzasize="medium";
                    //button.setNumber("1");
                    sr1.setChecked(false);
                    sr3.setChecked(false);
                    ec1.setChecked(false);
                    ec2.setChecked(false);
                    cardViewpersonal.setVisibility(View.GONE);
                    cardViewmedium.setVisibility(View.GONE);
                    cardViewlarge.setVisibility(View.GONE);
        //            ec3.setChecked(false);
        //            ec4.setChecked(false);
        //            ec5.setChecked(false);
        //            ec6.setChecked(false);

                    sizemsg.setText("(Medium selected)");
                } else if (sr2.isChecked() == true) {
                    ec1.setChecked(false);
                    ec2.setChecked(false);
                    sizemsg.setText("(Medium selected)");
                    cardViewpersonal.setVisibility(View.GONE);
                    cardViewmedium.setVisibility(View.GONE);
                    cardViewlarge.setVisibility(View.GONE);
                    //sr2.setChecked(false);
                }

                if(sr2.isChecked()==true){
                    pizzaprice=mediumprice1;
                    cartprice=mediumprice1*qty;
                    //sizeprice=psize;
                    totalprice.setText("RS. " + pizzaprice*qty);
                }
                else if(sr2.isChecked()==false)
                {

                    //psize=pizzaprice+0;
                    //totalprice.setText("RS: " + psize);
                    //sizeprice=psize;
                }
            }
        });

        cardViewlarge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sr3.isChecked() == false) {
                    sr3.setChecked(true);
                    pizzasize="large";
                    cardViewpersonal.setVisibility(View.GONE);
                    cardViewmedium.setVisibility(View.GONE);
                    cardViewlarge.setVisibility(View.GONE);
                   // button.setNumber("1");
                    sr2.setChecked(false);
                    sr1.setChecked(false);
                    ec1.setChecked(false);
                    ec2.setChecked(false);
        //            ec3.setChecked(false);
        //            ec4.setChecked(false);
        //            ec5.setChecked(false);
        //            ec6.setChecked(false);

                    sizemsg.setText("(Large selected)");
                } else if (sr3.isChecked() == true) {
                    ec1.setChecked(false);
                    ec2.setChecked(false);
                    cardViewpersonal.setVisibility(View.GONE);
                    cardViewmedium.setVisibility(View.GONE);
                    cardViewlarge.setVisibility(View.GONE);
                    //sr3.setChecked(false);
                    sizemsg.setText("(Large selected)");
                }

                if(sr3.isChecked()==true){
                    pizzaprice=largeprice1;
                    cartprice=largeprice1*qty;
                    //sizeprice=psize;
                    totalprice.setText("RS. " + pizzaprice*qty);
                }
                else if(sr3.isChecked()==false)
                {
                    //psize=pizzaprice+0;
                    //totalprice.setText("RS: " + psize);
                    //sizeprice=psize;
                }


            }
        });




        ec1 = (CheckBox) findViewById(R.id.chkonion);
        ec2 = (CheckBox) findViewById(R.id.chktomato);
    //    ec3 = (CheckBox) findViewById(R.id.chkchum);
    //    ec4 = (CheckBox) findViewById(R.id.chksausage1);
    //    ec5 = (CheckBox) findViewById(R.id.chkonion);
    //    ec6 = (CheckBox) findViewById(R.id.chktomato);

        cardViewonion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ec1.isChecked() == false) {

                    ec1.setChecked(true);
                } else if (ec1.isChecked() == true) {
                    ec1.setChecked(false);
                    //number.setText("1");
                }
                if (ec1.isChecked() == true) {
                    onion = 160;
                    extra="Onion";

                    if (pizzasize == "personal") {
                        allprice = (pizzaprice + onion) * qty;
                        cartprice=allprice;
                        totalprice.setText("RS. " + allprice);
                    } else if (pizzasize == "medium") {
                        allprice = (pizzaprice + onion) * qty;
                        cartprice=allprice;
                        totalprice.setText("RS. " + allprice);
                    } else if (pizzasize == "large") {
                        allprice = (pizzaprice + onion) * qty;
                        cartprice=allprice;
                        totalprice.setText("RS. " + allprice);
                    }

                }
                    if (ec1.isChecked() == true && ec2.isChecked() == true) {
                        tomata = 140;
                        extra="Onion , Tomato";

                        if (pizzasize == "personal") {
                            allprice = (pizzaprice + onion + tomata)*qty;
                            cartprice=allprice;
                            totalprice.setText("RS. " + allprice);
                        } else if (pizzasize == "medium") {
                            allprice = (pizzaprice + onion + tomata)*qty;
                            cartprice=allprice;
                            totalprice.setText("RS. " + allprice);
                        } else if (pizzasize == "large") {
                            allprice = (pizzaprice + onion + tomata)*qty;
                            cartprice=allprice;
                            totalprice.setText("RS. " + allprice);
                        }





                }
                if (ec1.isChecked() == false && ec2.isChecked() == true) {
                    onion = 0;
                    tomata = 140;
                    extra="Tomato";

                    if (pizzasize == "personal") {
                        allprice = (pizzaprice + onion + tomata) * qty;
                        cartprice=allprice;
                        totalprice.setText("RS. " + allprice);
                    } else if (pizzasize == "medium") {
                        allprice = (pizzaprice + tomata + onion) * qty;
                        cartprice=allprice;
                        totalprice.setText("RS. " + allprice);
                    } else if (pizzasize == "large") {
                        allprice = (pizzaprice + onion + tomata) * qty;
                        cartprice=allprice;
                        totalprice.setText("RS. " + allprice);
                    }
                }
                    if (ec1.isChecked() == false && ec2.isChecked() == false) {
                        onion = 0;
                        tomata = 0;
                        extra=" ";

                        if (pizzasize == "personal") {
                            allprice = (pizzaprice + onion + tomata)*qty;
                            cartprice=allprice;
                            totalprice.setText("RS. " + allprice);
                        } else if (pizzasize == "medium") {
                            allprice = (pizzaprice + onion + tomata)*qty;
                            cartprice=allprice;
                            totalprice.setText("RS. " + allprice);
                        } else if (pizzasize == "large") {
                            allprice = (pizzaprice + onion + tomata)*qty;
                            cartprice=allprice;
                            totalprice.setText("RS. " + allprice);
                        }

                    }


            }
        });


        cardViewtomato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ec2.isChecked() == false) {
                    ec2.setChecked(true);
                } else if (ec2.isChecked() == true) {
                    ec2.setChecked(false);
                    //number.setText("1");
                }
                if (ec2.isChecked() == true) {
                    tomata = 140;
                    extra="Tomato";

                    if (pizzasize == "personal") {
                        allprice = (pizzaprice + tomata) * qty;
                        cartprice=allprice;
                        totalprice.setText("RS. " + allprice);
                    } else if (pizzasize == "medium") {
                        allprice = (pizzaprice + tomata) * qty;
                        cartprice=allprice;
                        totalprice.setText("RS. " + allprice);
                    } else if (pizzasize == "large") {
                        allprice = (pizzaprice + tomata) * qty;
                        cartprice=allprice;
                        totalprice.setText("RS. " + allprice);
                    }
                }
                        if (ec1.isChecked() == true && ec2.isChecked() == true) {
                                onion = 160;
                                extra="Onion, Tomato";

                                if (pizzasize == "personal") {
                                    allprice = (pizzaprice + onion + tomata)* qty;
                                    cartprice=allprice;
                                    totalprice.setText("RS. " + allprice);
                                } else if (pizzasize == "medium") {
                                    allprice = (pizzaprice + tomata + onion)* qty;
                                    cartprice=allprice;
                                    totalprice.setText("RS. " + allprice);
                                } else if (pizzasize == "large") {
                                    allprice = (pizzaprice + tomata + onion)* qty;
                                    cartprice=allprice;
                                    totalprice.setText("RS. " + allprice);
                                }

                }
                if (ec2.isChecked() == false && ec1.isChecked() == true) {
                    tomata = 0;
                    onion = 160;
                    extra="Onion";

                    if (pizzasize == "personal") {
                        allprice = (pizzaprice + onion + tomata) * qty;
                        cartprice=allprice;
                        totalprice.setText("RS. " + allprice);
                    } else if (pizzasize == "medium") {
                        allprice = (pizzaprice + onion + tomata) * qty;
                        cartprice=allprice;
                        totalprice.setText("RS. " + allprice);
                    } else if (pizzasize == "large") {
                        allprice = (pizzaprice + onion + tomata) * qty;
                        cartprice=allprice;
                        totalprice.setText("RS. " + allprice);
                    }
                }
                    if (ec1.isChecked() == false && ec2.isChecked() == false) {
                        onion = 0;
                        tomata = 0;
                        extra=" ";

                        if (pizzasize == "personal") {
                            allprice = (pizzaprice + onion + tomata)* qty;
                            cartprice=allprice;
                            totalprice.setText("RS. " + allprice);
                        } else if (pizzasize == "medium") {
                            allprice = (pizzaprice + onion + tomata)* qty;
                            cartprice=allprice;
                            totalprice.setText("RS. " + allprice);
                        } else if (pizzasize == "large") {
                            allprice = (pizzaprice + onion + tomata)* qty;
                            cartprice=allprice;
                            totalprice.setText("RS. " + allprice);
                        }


                }


            }
        });
/*
        cardViewcham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ec3.isChecked() == false) {
                    ec3.setChecked(true);
                } else if (ec3.isChecked() == true) {
                    ec3.setChecked(false);
                }

            }
        });

        cardViewcsausage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ec4.isChecked() == false) {
                    ec4.setChecked(true);
                } else if (ec4.isChecked() == true) {
                    ec4.setChecked(false);
                }

            }
        });

        cardViewonion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ec5.isChecked() == false) {
                    ec5.setChecked(true);
                } else if (ec5.isChecked() == true) {
                    ec5.setChecked(false);
                }

            }
        });

        cardViewtomato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ec6.isChecked() == false) {
                    ec6.setChecked(true);
                } else if (ec6.isChecked() == true) {
                    ec6.setChecked(false);
                }

            }
        });

*/
       // double totPrice=psize+pizzaprice;
        totalprice.setText("RS. " + pizzaprice);
        //System.out.println("aaaaaaaaaaaaaaaaaaaa"+sizeprice);

/*
        button = (ElegantNumberButton) findViewById(R.id.qty);
        button.setOnClickListener(new ElegantNumberButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                String num = button.getNumber();
                qty=Double.parseDouble(num);
                System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+num);
                if(ec1.isChecked()==false && ec2.isChecked()==false) {

                    totalprice.setText("RS: " + pizzaprice*qty);

                }else if(ec1.isChecked()==true || ec2.isChecked()==true) {


                    double tprice = allprice * qty;
                    totalprice.setText("RS: " + tprice);
                }
            }
        });
*/
        minus = (TextView) findViewById(R.id.minus);
        number = (TextView) findViewById(R.id.number);
        plus = (TextView) findViewById(R.id.plus);

        number.setText(""+num);



        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num<10){
                    num++;
                    number.setText(""+num);
                    qty=num;
                    cartprice=cartprice*qty;
                    if(ec1.isChecked()==false && ec2.isChecked()==false) {

                        totalprice.setText("RS. " + pizzaprice*qty);

                    }
                    if(ec1.isChecked()==true) {
                        double tprice = allprice * qty;
                        totalprice.setText("RS. " + tprice);
                        if( ec2.isChecked()==true){
                            double tprice1 = allprice * qty;
                            totalprice.setText("RS. " + tprice1);
                        }
                    }if(ec2.isChecked()==true) {
                        double tprice = allprice * qty;
                        totalprice.setText("RS. " + tprice);
                        if( ec1.isChecked()==true){
                            double tprice1 = allprice * qty;
                            totalprice.setText("RS. " + tprice1);
                        }
                    }
                }
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num>1){
                    num--;
                    number.setText(""+num);
                    qty=num;
                    cartprice=cartprice*qty;
                    if(ec1.isChecked()==false && ec2.isChecked()==false) {

                        totalprice.setText("RS. " + pizzaprice*qty);

                    }if(ec1.isChecked()==true) {
                        double tprice = allprice * qty;
                        totalprice.setText("RS. " + tprice);
                        if( ec2.isChecked()==true){
                            double tprice1 = allprice * qty;
                            totalprice.setText("RS. " + tprice1);
                        }
                    }if( ec2.isChecked()==true){
                        double tprice1 = allprice * qty;
                        totalprice.setText("RS. " + tprice1);
                        if(ec1.isChecked()==true) {
                            double tprice = allprice * qty;
                            totalprice.setText("RS. " + tprice);
                        }
                    }
                }
            }
        });


        addtocart=(LinearLayout) findViewById(R.id.addtocart);
        buynow=(Button)findViewById(R.id.buynow);


        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(OrderDetailActivity.this,ConfirmPopup.class));

                Intent intent=new Intent(OrderDetailActivity.this,ConfirmPopup.class);

                intent.putExtra("IMAGE",intent1.getStringExtra("IMG"));
                intent.putExtra("NAME",Pname.getText().toString());
                intent.putExtra("QTY",Integer.toString(qty));
                intent.putExtra("CRUST",crust);
                intent.putExtra("EXTRA",extra);
                intent.putExtra("SIZE",pizzasize);
                intent.putExtra("PRICE",Double.toString(cartprice));

                startActivity(intent);

            }
        });




    }


    }
