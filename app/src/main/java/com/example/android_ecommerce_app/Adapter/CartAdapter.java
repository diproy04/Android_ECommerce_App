package com.example.android_ecommerce_app.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_ecommerce_app.R;
import com.example.android_ecommerce_app.databinding.ItemcartBinding;
import com.example.android_ecommerce_app.databinding.QuantityDialogBinding;
import com.example.android_ecommerce_app.model.Product;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.util.TinyCartHelper;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    Context context;
    ArrayList<Product> products;
    CartListener cartListener;
    Cart cart;


    public interface CartListener{
        public void onQuantityChanged();
    }

    public CartAdapter(Context context, ArrayList<Product> products, CartListener cartListener) {
        this.context = context;
        this.products = products;
        this.cartListener = cartListener;
        Cart cart = TinyCartHelper.getCart();
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
        holder.binding.quantity.setText(product.getQuantity() + "item(s)");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuantityDialogBinding quantityDialogBinding = QuantityDialogBinding.inflate(LayoutInflater.from(context));
                AlertDialog daDialog=new AlertDialog.Builder(context)
                        .setView(quantityDialogBinding.getRoot())
                        .create();

                daDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));

                quantityDialogBinding.productName.setText(product.getName());
                quantityDialogBinding.productStock.setText("Stock: " + product.getStock());
                quantityDialogBinding.quantity.setText(String.valueOf(product.getQuantity()));

                int stock = product.getStock();

                quantityDialogBinding.plusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int quantity = product.getQuantity();
                        quantity++;

                        if(quantity>product.getStock()){
                            Toast.makeText(context, "Max stock available: "+ product.getStock(), Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            product.setQuantity(quantity);
                            quantityDialogBinding.quantity.setText(String.valueOf(quantity));
                        }
                        notifyDataSetChanged();
                        cart.updateItem(product,product.getQuantity());
                        cartListener.onQuantityChanged();
                    }
                });

                quantityDialogBinding.minusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int quantity = product.getQuantity();
                        if(quantity > 1)
                        quantity--;
                        product.setQuantity(quantity);
                        quantityDialogBinding.quantity.setText(String.valueOf(quantity));

                        notifyDataSetChanged();
                        cart.updateItem(product,product.getQuantity());
                        cartListener.onQuantityChanged();
                    }
                });

                quantityDialogBinding.saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        daDialog.dismiss();
//                        notifyDataSetChanged();
//                        cart.updateItem(product,product.getQuantity());
//                        cartListener.onQuantityChanged();
                    }
                });

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
