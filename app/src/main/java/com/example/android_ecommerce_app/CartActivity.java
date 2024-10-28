package com.example.android_ecommerce_app;

import android.os.Bundle;
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

import java.util.ArrayList;

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

        products.add(new Product("Product1","---","1234",45,45,45,1));
        products.add(new Product("Product2","---","1234",45,45,45,1));
        products.add(new Product("Product3","---","1234",45,45,45,1));

        adapter=new CartAdapter(this,products);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        DividerItemDecoration itemDecoration=new DividerItemDecoration(this,layoutManager.getOrientation());
        binding.cartlist.setLayoutManager(layoutManager);
        binding.cartlist.addItemDecoration(itemDecoration);
        binding.cartlist.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}