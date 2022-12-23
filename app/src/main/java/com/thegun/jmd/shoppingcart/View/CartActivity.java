package com.thegun.jmd.shoppingcart.View;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.thegun.jmd.shoppingcart.Adapter.CartAdapter;
import com.thegun.jmd.shoppingcart.MainActivity;
import com.thegun.jmd.shoppingcart.Model.ProductImage;
import com.thegun.jmd.shoppingcart.R;

//Notifications

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import static com.thegun.jmd.shoppingcart.Adapter.ProductAdapter.cartModels;

public class CartActivity extends AppCompatActivity {

    //Notifications
    private final static String CHANEL_ID = "NOTIFICATION";
    private final static int NOTIFICACION_ID = 0;

    //Principal
    public static TextView grandTotal;
    public static int grandTotalplus;
    // create a temp list and add cartitem list
    public static ArrayList<ProductImage> temparraylist;
    RecyclerView cartRecyclerView;
    CartAdapter cartAdapter;
    LinearLayout proceedToBook;
    Context context;
    private Toolbar mToolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        context = this;
        temparraylist = new ArrayList<>();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        proceedToBook = findViewById(R.id.proceed_to_book);
        grandTotal = findViewById(R.id.grand_total_cart);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Cart");


        mToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back_arrow));
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // these lines of code for show the same  cart for future refrence
                grandTotalplus = 0;
                for (int i = 0; i < temparraylist.size(); i++) {

                }
                cartModels.addAll(temparraylist);
                MainActivity.cart_count = (temparraylist.size());
//                addItemInCart.clear();
                finish();
            }
        });
        MainActivity.cart_count = 0;

        //addInCart();

        Log.d("sizecart_1", String.valueOf(temparraylist.size()));
        Log.d("sizecart_2", String.valueOf(cartModels.size()));

        // from these lines of code we remove the duplicacy of cart and set last added quantity in cart
        // for replace same item
        for (int i = 0; i < cartModels.size(); i++) {
            for (int j = i + 1; j < cartModels.size(); j++) {
                if (cartModels.get(i).getProductImage().equals(cartModels.get(j).getProductImage())) {
                    cartModels.get(i).setProductQuantity(cartModels.get(j).getProductQuantity());
                    cartModels.get(i).setTotalCash(cartModels.get(j).getTotalCash());
                    cartModels.remove(j);
                    j--;
                    Log.d("remove", String.valueOf(cartModels.size()));

                }
            }

        }
        temparraylist.addAll(cartModels);
        cartModels.clear();
        Log.d("sizecart_11", String.valueOf(temparraylist.size()));
        Log.d("sizecart_22", String.valueOf(cartModels.size()));
        // this code is for get total cash
        for (int i = 0; i < temparraylist.size(); i++) {
            grandTotalplus = grandTotalplus + temparraylist.get(i).getTotalCash();
        }
        grandTotal.setText("Importe total: " + String.valueOf(grandTotalplus));
        cartRecyclerView = findViewById(R.id.recycler_view_cart);
        cartAdapter = new CartAdapter(temparraylist, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        cartRecyclerView.setLayoutManager(mLayoutManager);
        cartRecyclerView.setAdapter(cartAdapter);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        grandTotalplus = 0;
        for (int i = 0; i < temparraylist.size(); i++) {
            MainActivity.cart_count = (temparraylist.size());
        }
        cartModels.addAll(temparraylist);
        //cartModels.clear();
    }

    public void Notification(View view){
        CrearNotificacion(view);
        Toast.makeText(CartActivity.this, "Procesando", Toast.LENGTH_SHORT).show();
    }

    //Notificaciones
    public void CrearNotificacion(View view){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),CHANEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_shopping_cart_24);
        builder.setContentTitle("Notificación: Lady Express");
        builder.setContentText("¡Pago Éxitoso!\n Gracias por su compra. ");
        builder.setColor(Color.GREEN);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setLights(Color.MAGENTA, 1000,1000);
        builder.setVibrate(new long[]{1000,1000,1000,1000});
        builder.setDefaults(Notification.DEFAULT_SOUND);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
        notificationManagerCompat.notify(NOTIFICACION_ID, builder.build());
        maxNotificacion();

    }

    public void maxNotificacion(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name="Notificacion";
            NotificationChannel notificationChannel=new NotificationChannel(CHANEL_ID,name, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }
}