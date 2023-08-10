package com.example.appamst_lab2_firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class PerfilUsuario extends AppCompatActivity {

    TextView txt_id, txt_name, txt_email,txt_biografia,txt_telefono;
    ImageView imv_photo;
    DatabaseReference db_reference;
    EditText edt_tweet, edt_fecha;
    Button btn_enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        Intent intent = getIntent();
        HashMap<String, String> info_user = (HashMap<String, String>) intent.getSerializableExtra("info_user");
        System.out.println("Informacion" + info_user);
        txt_id = findViewById(R.id.txt_userId);
        txt_name = findViewById(R.id.txt_nombre);
        txt_email = findViewById(R.id.txt_correo);
        imv_photo = findViewById(R.id.imv_foto);
        edt_tweet = findViewById(R.id.edt_tweet);
        edt_fecha = findViewById(R.id.edt_fecha);
        btn_enviar = findViewById(R.id.btn_enviar);
        txt_telefono = findViewById(R.id.txt_telefono);
        txt_biografia = findViewById(R.id.txt_biografia);

        txt_telefono.setText(info_user.get("user_phone"));
        txt_biografia.setText(info_user.get("user_bio"));


        txt_id.setText(info_user.get("user_id"));
        txt_name.setText(info_user.get("user_name"));
        txt_email.setText(info_user.get("user_email"));
        String photo = info_user.get("user_photo");
        Picasso.get().load(photo).into(imv_photo);

        iniciarBaseDeDatos();
        //leerTweets();
        btn_enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tweet = edt_tweet.getText().toString();
                String fecha = edt_fecha.getText().toString();
                //escribirTweets(info_user.get("user_name"), tweet, fecha);
                edt_tweet.setText("");
                edt_fecha.setText("");
            }
        });
    }

    public void iniciarBaseDeDatos() {
        db_reference = FirebaseDatabase.getInstance().getReference().child("Grupos");
    }

    /*public void leerTweets() {
        db_reference.child("Grupo1").child("tweets")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            System.out.println(snapshot);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        System.out.println(error.toException());
                    }
                });
    }

    public void escribirTweets(String autor, String tweet, String fecha) {
        Map<String, String> tweetData = new HashMap<>();
        tweetData.put("autor", autor);
        tweetData.put("fecha", fecha);

        DatabaseReference tweetRef = db_reference.child("Grupo1").child("tweets").child(tweet);
        tweetRef.setValue(tweetData);
        tweetRef.child("contenido").setValue(tweet);
    }*/


    public void cerrarSesion(View view) {
        FirebaseAuth.getInstance().signOut();
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("msg", "cerrarSesion");
        startActivity(intent);
    }

    public void irRegistros(View view){
        Intent intent = new Intent(this, registros.class);
        startActivity(intent);
    }
}
