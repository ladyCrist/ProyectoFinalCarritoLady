package com.thegun.jmd.shoppingcart;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {
    public EditText txtUsu, txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUsu = findViewById(R.id.txtUsuario);
        txtPass = findViewById(R.id.txtPassword);
    }

    public Connection conexionBD(){
        Connection cnn = null;
        try {
            StrictMode.ThreadPolicy pol=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(pol);
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            cnn = DriverManager.getConnection("jdbc:jtds:sqlserver://192.168.1.7:1433/BDCarritoG1;"+
                    "instance=SQL2019G2;user=sa;password=12345");
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return cnn;
    }

    public void Consulta(View view){
        try {
            Statement st=conexionBD().createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM usuarios " +
                    "where logeo='"+txtUsu.getText().toString()+"' and clave='"+txtPass.getText().toString()+"'");
            if(rs.next()){
                Toast.makeText(getApplicationContext(),"Conexion establecida",Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    public void InitLogin(View view){
        String user = txtUsu.getText().toString();
        String password = txtPass.getText().toString();

        if(user.equals("admin1")){
            if(password.equals("admin123")) {
                Toast.makeText(getApplicationContext(),"Bienvenido!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, VistaAdmin.class));
            } else {
                Toast.makeText(getApplicationContext(),"La contraseña es incorrecta!",Toast.LENGTH_SHORT).show();
            }
        }

        if(user.equals("cliente1")){
            if(password.equals("12345")) {
                Toast.makeText(getApplicationContext(),"Bienvenido!",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
            } else {
                Toast.makeText(getApplicationContext(),"La contraseña es incorrecta!",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
