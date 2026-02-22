package com.example.formapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNomPrenom, etEmail, etTelephone, etAdresse;
    private Spinner spinnerVille;
    private Button btnEnvoyer;
    private String villeSelectionnee = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNomPrenom = findViewById(R.id.etNomPrenom);
        etEmail = findViewById(R.id.etEmail);
        etTelephone = findViewById(R.id.etTelephone);
        etAdresse = findViewById(R.id.etAdresse);
        spinnerVille = findViewById(R.id.spinnerVille);
        btnEnvoyer = findViewById(R.id.btnEnvoyer);

        // Initialiser le Spinner avec la liste des villes
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.villes_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVille.setAdapter(adapter);

        spinnerVille.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                villeSelectionnee = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                villeSelectionnee = "";
            }
        });

        btnEnvoyer.setOnClickListener(v -> {
            if (validerFormulaire()) {
                envoyerDonnees();
            }
        });
    }

    private boolean validerFormulaire() {
        boolean ok = true;

        if (etNomPrenom.getText().toString().trim().isEmpty()) {
            etNomPrenom.setError("Nom & Prénom requis");
            ok = false;
        }
        if (etEmail.getText().toString().trim().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString()).matches()) {
            etEmail.setError("Email valide requis");
            ok = false;
        }
        if (etTelephone.getText().toString().trim().isEmpty()) {
            etTelephone.setError("Téléphone requis");
            ok = false;
        }
        if (etAdresse.getText().toString().trim().isEmpty()) {
            etAdresse.setError("Adresse requise");
            ok = false;
        }
        if (villeSelectionnee.isEmpty()) {
            Toast.makeText(this, "Veuillez sélectionner une ville", Toast.LENGTH_SHORT).show();
            ok = false;
        }

        return ok;
    }

    private void envoyerDonnees() {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("NOM_PRENOM", etNomPrenom.getText().toString().trim());
        intent.putExtra("EMAIL", etEmail.getText().toString().trim());
        intent.putExtra("TELEPHONE", etTelephone.getText().toString().trim());
        intent.putExtra("ADRESSE", etAdresse.getText().toString().trim());
        intent.putExtra("VILLE", villeSelectionnee);
        startActivity(intent);
    }
}
