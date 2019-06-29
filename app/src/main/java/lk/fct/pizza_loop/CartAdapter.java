package lk.fct.pizza_loop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private ArrayList<Cart> cartList;

    double total=0;
    double price1;


    private OnItemClickListner mListner;

    public interface OnItemClickListner{
        void  onItemClick(int position);
        void  onCartItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListner listener){
        mListner = listener;
    }

    public CartAdapter(Context context,ArrayList<Cart> cartList){
        this.context=context;
        this.cartList=cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cart_list,parent,false);
        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, final int position) {

        final Cart cart=cartList.get(position);

        //int id=cart.getCartId();
        String imageurl=cart.getImageUrl();
        String name1=cart.getPizza_name();
        String pizza_crust=cart.getPizza_crust();
        String pizza_size=cart.getPizza_size();
        String extra=cart.getExtra();
        int qty=cart.getQty();
        double totalprice=cart.getTotalprice();

        Glide.with(context).load(imageurl).into(holder.imageView);
        holder.name.setText(name1);
        holder.crust.setText(pizza_crust+" "+pizza_size+" - "+qty);
        holder.extra.setText("Extra : "+extra);
        holder.price.setText("RS. "+totalprice);

        price1=cart.getTotalprice();
        total=total+price1;
        System.out.println("Aaaaaaaaaaaaaaaaaaaaaaaa"+total);




    }


    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView name;
        public TextView crust;
        public TextView extra;
        public TextView price;
        public ImageView delete;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.img);
            name=(TextView) itemView.findViewById(R.id.name);
            crust=(TextView) itemView.findViewById(R.id.crust_size);
            extra=(TextView) itemView.findViewById(R.id.extra);
            price=(TextView) itemView.findViewById(R.id.price);
            delete=(ImageView) itemView.findViewById(R.id.delete);


            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListner != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListner.onItemClick(position);

                        }
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListner != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListner.onCartItemClick(position);

                        }
                    }
                }
            });






        }
    }

}
