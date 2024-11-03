package com.example.android_ecommerce_app;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.android_ecommerce_app.Adapter.ProductAdapter;
import com.example.android_ecommerce_app.databinding.ActivityCatagoryBinding;
import com.example.android_ecommerce_app.model.Product;

import java.util.ArrayList;

public class CatagoryActivity extends AppCompatActivity {
    ActivityCatagoryBinding binding;

    ProductAdapter productAdapter;
    ArrayList<Product> productArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCatagoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        productArrayList=new ArrayList<>();
        productArrayList.add(new Product("Loafer Styles' Rubber Sole Winter and Summer Wind Proof Shoe For Men","https://img.drz.lazcdn.com/static/bd/p/6814671e70cd16bae08f0021449db7c3.jpg_720x720q80.jpg","bjdgsajdgjsajkdfb", 350,12,1,1));
        productArrayList.add(new Product("Type C USB C HUB High Speed 4 Port Multi Splitter Adapter OTG For Lenovo HUAWEI","https://ng.jumia.is/unsafe/fit-in/500x500/filters:fill(white)/product/45/9333001/2.jpg?1360","bjdgsajdgjsajkdfb",162,12,1,1));
        productArrayList.add(new Product("HAIER 1.5 Ton UltimateCool Inverter AC HSU-18UltimateCool:(INV)(UV)(Wifi)(QF)","https://lahorestores.com/wp-content/uploads/2022/05/Haier-HSU-18HFPCA-Inverter-Air-Conditioner-Black.jpg","bjdgsajdgjsajkdfb",80990,12,1,1));
        productArrayList.add(new Product("ACER ASPIRE A315 (NX.ADUEM.00D), INTEL CORE I5-1135G7 8GB RAM 1TB, 2GB MX350 Graphics","https://m.media-amazon.com/images/S/aplus-media/sota/0bbc5aab-d6ac-4c0f-89d0-5b371f47c3ed.__CR0,0,970,600_PT0_SX970_V1___.jpg","bjdgsajdgjsajkdfb",64999,12,1,1));
        productArrayList.add(new Product("Hex dumbbell 2.5 kg 1pcs","https://images.othoba.com/images/thumbs/0587396_25kg-hex-dumbbells-1-pair.jpeg","bjdgsajdgjsajkdfb", 578,12,1,1));
        productArrayList.add(new Product("Harry Potter 1-3 (3 Books Set) Paperback","https://img.drz.lazcdn.com/static/bd/p/13a5f2d7b71e08475f734096feb0889e.jpg_720x720q80.jpg","bjdgsajdgjsajkdfb", 224,12,1,1));
        productArrayList.add(new Product("JUICY PRO PLUS Disposable Vape 8500 Puffs or Lost Vape Orion Bar 7500 Puffs Rechargeable Disposable Device (Any 1 piece)","https://vape4change.ca/cdn/shop/files/IMG_0074_2a4a946e-aef5-41bc-9274-45f9419823cf.jpg?v=1690763354","bjdgsajdgjsajkdfb", 1388,12,1,1));
        productArrayList.add(new Product("Watches for Men Diamond Business Dress Analog Quartz Stainless Steel Date Luxury Casual Fashion Wrist Watch Waterproof Luminous","https://img.drz.lazcdn.com/static/bd/p/a291b82a39da949fbf4bdac8240b6131.jpg_960x960q80.jpg_.webp","bjdgsajdgjsajkdfb", 400,12,1,1));
        productAdapter=new ProductAdapter(this,productArrayList);

        int catId = getIntent().getIntExtra("catId",0);
        String catagoryName = getIntent().getStringExtra("catagoryName");

        getSupportActionBar().setTitle(catagoryName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        getRecentProducts();

        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        binding.productlist.setLayoutManager(layoutManager);
        binding.productlist.setAdapter(productAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }
}