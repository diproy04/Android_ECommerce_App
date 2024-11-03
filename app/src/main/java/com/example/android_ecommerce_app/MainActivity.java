package com.example.android_ecommerce_app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.android_ecommerce_app.Adapter.CategoryAdapter;
import com.example.android_ecommerce_app.Adapter.ProductAdapter;
import com.example.android_ecommerce_app.databinding.ActivityMainBinding;
import com.example.android_ecommerce_app.model.Category;
import com.example.android_ecommerce_app.model.Product;
import com.example.android_ecommerce_app.untils.Constants;
import com.mancj.materialsearchbar.MaterialSearchBar;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categoryArrayList;

    ProductAdapter productAdapter;
    ArrayList<Product> productArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.searchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {

            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                Intent intent=new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("query",text.toString());
                startActivity(intent);
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });

        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getColor(R.color.orange));
        initCategoris();
        initProducts();
        initslider();

    }

    private void initslider() {
        binding.carousel.addData(new CarouselItem("https://img.freepik.com/free-vector/mega-sale-offers-banner-template_1017-31299.jpg","Some Caption Here"));
        binding.carousel.addData(new CarouselItem("https://thumbs.dreamstime.com/b/vector-illustration-super-sale-banner-template-design-big-sales-special-offer-end-season-party-background-cool-136119596.jpg","Some Caption Here"));
        binding.carousel.addData(new CarouselItem("https://png.pngtree.com/png-vector/20220505/ourmid/pngtree-special-offer-50-off-sale-and-discount-banner-png-image_4563071.png","Some Caption Here"));
    }

    void initCategoris(){
        categoryArrayList=new ArrayList<>();
        categoryArrayList.add(new Category("Men's & Boys' Fashion","https://images.pexels.com/photos/298863/pexels-photo-298863.jpeg?cs=srgb&dl=pexels-solliefoto-298863.jpg&fm=jpg","#18ab4e","Some Desrioption",1));
        categoryArrayList.add(new Category("Electronic Accessories","https://celltophone.com/wp-content/uploads/2024/03/Top-10-Latest-Electronic-Gadgets-One-Can-Buy-in-2024-1200x900.jpg","#18ab4e","Some Desrioption",1));
        categoryArrayList.add(new Category("TV & Home Appliances","https://img.freepik.com/premium-photo/home-appliances-gas-cooker-tv-cinema-refrigerator-microwave-laptop-washing-machine-3d-illustration_252025-685.jpg","#18ab4e","Some Desrioption",1));
        categoryArrayList.add(new Category("Electronics Device","https://img.freepik.com/premium-photo/collection-electronic-devices-including-laptop-phone-ipod_1065421-12202.jpg","#18ab4e","Some Desrioption",1));
        categoryArrayList.add(new Category("Sports & Outdoor","https://media.istockphoto.com/id/1136317339/photo/sports-equipment-on-floor.jpg?s=612x612&w=0&k=20&c=-aI8u_Se89IC-HJZYH724ei5z-bIcSvRW6qUwyMtRyE=","#18ab4e","Some Desrioption",1));
        categoryArrayList.add(new Category("Home & Lifestyle","https://www.remelifestyle.in/wp-content/uploads/2019/10/Essential-Elements-Of-Modern-Home-D%C3%A9cor-1.jpg","#18ab4e","Some Desrioption",1));
        categoryArrayList.add(new Category("Groceries","https://www.upoharbd.com/images/uploads/Grocery/gro_baz_03.jpg","#18ab4e","Some Desrioption",1));
        categoryArrayList.add(new Category("Watches","https://luxurywatchesusa.com/wp-content/uploads/2020/04/%D0%B0%D0%BF%D0%BF.jpg","#18ab4e","Some Desrioption",1));
        categoryAdapter=new CategoryAdapter(this,categoryArrayList);

        //getCategories();

        GridLayoutManager layoutManager=new GridLayoutManager(this,4);
        binding.cetagorieslist.setLayoutManager(layoutManager);
        binding.cetagorieslist.setAdapter(categoryAdapter);
    }

    void initProducts(){
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

//        getRecentProducts();

        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        binding.productlist.setLayoutManager(layoutManager);
        binding.productlist.setAdapter(productAdapter);
    }
}