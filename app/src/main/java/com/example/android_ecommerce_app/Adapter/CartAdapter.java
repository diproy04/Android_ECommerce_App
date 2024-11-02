package com.example.android_ecommerce_app.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_ecommerce_app.R;
import com.example.android_ecommerce_app.databinding.ItemcartBinding;
import com.example.android_ecommerce_app.databinding.QuantityDialogBinding;
import com.example.android_ecommerce_app.model.Product;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    Context context;
    ArrayList<Product> products;

    public CartAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.itemcart,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        Product product=products.get(position);
        Glide.with(context)
                .load(product.getImage())
                .into(holder.binding.image);
        holder.binding.name.setText(product.getName());
        holder.binding.price.setText("৳" + product.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuantityDialogBinding quantityDialogBinding = QuantityDialogBinding.inflate(LayoutInflater.from(context));
                AlertDialog daDialog=new AlertDialog.Builder(context)
                        .setView(quantityDialogBinding.getRoot())
                        .create();

                daDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));


                daDialog.show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ItemcartBinding binding;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ItemcartBinding.bind(itemView);
        }
    }
}
