package com.example.android_ecommerce_app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android_ecommerce_app.ProductDetailActivity;
import com.example.android_ecommerce_app.R;
import com.example.android_ecommerce_app.databinding.ItemProdutBinding;
import com.example.android_ecommerce_app.model.Product;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    Context context;
    ArrayList<Product> productArrayList;

    public ProductAdapter(Context context, ArrayList<Product> productArrayList) {
        this.context = context;
        this.productArrayList = productArrayList;
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_produt,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        Product product=productArrayList.get(position);
        Glide.with(context)
                .load(product.getImage())
                .into(holder.binding.image);
        holder.binding.label.setText(product.getName());
        holder.binding.price.setText("Taka" + product.getPrice());

        holder.itemView.setOnClickListener(c->{
            Intent intent=new Intent(context, ProductDetailActivity.class);
            intent.putExtra("label",product.getName());
            intent.putExtra("image",product.getImage());
            intent.putExtra("id",product.getId());
            intent.putExtra("price",product.getPrice());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ItemProdutBinding binding;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemProdutBinding.bind(itemView);
        }
    }
}
