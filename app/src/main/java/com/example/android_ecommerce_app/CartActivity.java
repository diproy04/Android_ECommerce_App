package com.example.android_ecommerce_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.android_ecommerce_app.Adapter.CartAdapter;
import com.example.android_ecommerce_app.databinding.ActivityCartBinding;
import com.example.android_ecommerce_app.model.Product;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.model.Item;
import com.hishd.tinycart.util.TinyCartHelper;

import java.util.ArrayList;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
    ActivityCartBinding binding;
    CartAdapter adapter;
    ArrayList<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getColor(R.color.orange));


        products=new ArrayList<>();

        Cart cart = TinyCartHelper.getCart();

        for(Map.Entry<Item,Integer>item:cart.getAllItemsWithQty().entrySet()){
            Product product = (Product) item.getKey();
            int quantity =item.getValue();
            product.setQuantity(quantity);

            products.add(product);
        }

        products.add(new Product("Loafer Styles' Rubber Sole Winter and Summer Wind Proof Shoe For Men","https://img.drz.lazcdn.com/static/bd/p/6814671e70cd16bae08f0021449db7c3.jpg_720x720q80.jpg","1234",350,12,5,1));
        products.add(new Product("HAIER 1.5 Ton UltimateCool Inverter AC HSU-18UltimateCool:(INV)(UV)(Wifi)(QF)","https://lahorestores.com/wp-content/uploads/2022/05/Haier-HSU-18HFPCA-Inverter-Air-Conditioner-Black.jpg","1234",80990,45,4,2));
        products.add(new Product("Harry Potter 1-3 (3 Books Set) Paperback","https://img.drz.lazcdn.com/static/bd/p/13a5f2d7b71e08475f734096feb0889e.jpg_720x720q80.jpg","1234",224,14,10,3));

        adapter=new CartAdapter(this, products, new CartAdapter.CartListener() {
            @Override
            public void onQuantityChanged() {
                binding.subtotal.setText(String.format("৳ %.2f",cart.getTotalPrice()));
            }
        });

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        DividerItemDecoration itemDecoration=new DividerItemDecoration(this,layoutManager.getOrientation());
        binding.cartlist.setLayoutManager(layoutManager);
        binding.cartlist.addItemDecoration(itemDecoration);
        binding.cartlist.setAdapter(adapter);

        binding.subtotal.setText(String.format("৳ %.2f",cart.getTotalPrice()));

        binding.continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, CheckoutActivity.class));
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}