package com.example.formapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView tvNom, tvEmail, tvTelephone, tvAdresse, tvVille;
    private Button btnRetour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvNom = findViewById(R.id.tvNom);
        tvEmail = findViewById(R.id.tvEmail);
        tvTelephone = findViewById(R.id.tvTelephone);
        tvAdresse = findViewById(R.id.tvAdresse);
        tvVille = findViewById(R.id.tvVille);
        btnRetour = findViewById(R.id.btnRetour);

        // Récupérer les données via l’Intent
        String nom = getIntent().getStringExtra("NOM_PRENOM");
        String email = getIntent().getStringExtra("EMAIL");
        String telephone = getIntent().getStringExtra("TELEPHONE");
        String adresse = getIntent().getStringExtra("ADRESSE");
        String ville = getIntent().getStringExtra("VILLE");

        // Afficher les données
        tvNom.setText("Nom : " + (nom != null ? nom : ""));
        tvEmail.setText("Email : " + (email != null ? email : ""));
        tvTelephone.setText("Phone : " + (telephone != null ? telephone : ""));
        tvAdresse.setText("Adresse : " + (adresse != null ? adresse : ""));
        tvVille.setText("Ville : " + (ville != null ? ville : ""));

        btnRetour.setOnClickListener(v -> {
            finish(); // ferme cette activité et revient à MainActivity
        });
    }
}
