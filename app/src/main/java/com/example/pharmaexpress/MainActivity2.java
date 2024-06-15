package com.example.pharmaexpress;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {

    private ImageView imageView;
    private int[] images = { R.drawable.pharmacy, R.drawable.servicelivraison, R.drawable.shoppharma, R.drawable.medoc }; // Remplacez par vos images
    private int indexImage = 0;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            imageView.setImageResource(images[indexImage]);
            indexImage++;
            if (indexImage >= images.length) {
                indexImage = 0;
            }
            handler.postDelayed(this, 2000); // Changez l'image toutes les 2000 ms
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView = findViewById(R.id.imageView); //  l'ID de  ImageView
        handler.postDelayed(runnable, 0); // Délai initial avant de commencer le défilement

        // Évènement déclencé lors du clic sur le bouton sign in
        findViewById(R.id.btn_signin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer l'activité Login
                startActivity(new Intent(MainActivity2.this, Login.class));
            }
        });

        // Évènement déclencé lors du clic sur le bouton sign up
        findViewById(R.id.btn_signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lancer l'activité Registration
                startActivity(new Intent(MainActivity2.this, Registration.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Supprimer les callbacks pour éviter les fuites de mémoire
        handler.removeCallbacks(runnable);
    }
}