package com.thegun.jmd.shoppingcart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.thegun.jmd.shoppingcart.Adapter.ProductAdapter;
import com.thegun.jmd.shoppingcart.Model.ProductModel;
import com.thegun.jmd.shoppingcart.View.CartActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ProductAdapter.CallBackUs, ProductAdapter.HomeCallBack {

    public static ArrayList<ProductModel> arrayList = new ArrayList<>();
    public static int cart_count = 0;
    ProductAdapter productAdapter;
    RecyclerView productRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addProduct();
        productAdapter = new ProductAdapter(arrayList, this, this);
        productRecyclerView = findViewById(R.id.product_recycler_view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        productRecyclerView.setLayoutManager(gridLayoutManager);
        productRecyclerView.setAdapter(productAdapter);
    }

    private void addProduct() {
        ProductModel productModel = new ProductModel("Batidora S/.40", "40", "20", R.drawable.batidora);
        arrayList.add(productModel);
        ProductModel productModel1 = new ProductModel("Cafetera S/.85", "85", "10", R.drawable.cafetera);
        arrayList.add(productModel1);
        ProductModel productModel2 = new ProductModel("Cocina S/.300", "300", "10", R.drawable.cocina);
        arrayList.add(productModel2);

        ProductModel productModel3 = new ProductModel("Extractora S/.70", "70", "20", R.drawable.extractora);
        arrayList.add(productModel3);
        ProductModel productModel12 = new ProductModel("Freidora S/.250", "250", "10", R.drawable.freidora);
        arrayList.add(productModel12);
        ProductModel productModel23 = new ProductModel("Hervidora S/.60", "60", "10", R.drawable.hervidora);
        arrayList.add(productModel23);

        ProductModel productModel4 = new ProductModel("Licuadora S/.77", "77", "20", R.drawable.licuadora);
        arrayList.add(productModel4);
        ProductModel productModel14 = new ProductModel("Microondas S/.220", "220", "10", R.drawable.microondas);
        arrayList.add(productModel14);
        ProductModel productModel25 = new ProductModel("Olla Presión S/.99", "99", "10", R.drawable.ollapresion);
        arrayList.add(productModel25);

        ProductModel productModel5 = new ProductModel("Parrillera S/.100", "100", "20", R.drawable.parrillera);
        arrayList.add(productModel5);
        ProductModel productModel16 = new ProductModel("Plancha S/.50", "50", "10", R.drawable.plancha);
        arrayList.add(productModel16);
        //ProductModel productModel17 = new ProductModel("Refrigeradora", "200", "10", R.drawable.refrigeradora);
        //arrayList.add(productModel17);

        ProductModel productModel18 = new ProductModel("Sarten S/.69", "69", "10", R.drawable.sarten);
        arrayList.add(productModel18);
        ProductModel productModel19 = new ProductModel("Tostadora S/.120", "120", "10", R.drawable.tostadora);
        arrayList.add(productModel19);
        ProductModel productModel20 = new ProductModel("Waflera S/.88", "88", "10", R.drawable.waflera);
        arrayList.add(productModel20);
    }

    @Override
    public void addCartItemView() {
        //addItemToCartMethod();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.cart_action);
        menuItem.setIcon(Converter.convertLayoutToImage(MainActivity.this, cart_count, R.drawable.ic_shopping_cart_white_24dp));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case R.id.cart_action:
                if (cart_count < 1) {
                    Toast.makeText(this, "there is no item in cart", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(this, CartActivity.class));
                }
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    public void updateCartCount(Context context) {
        invalidateOptionsMenu();
    }

    @Override
    protected void onStart() {
        super.onStart();
        invalidateOptionsMenu();
    }
}
