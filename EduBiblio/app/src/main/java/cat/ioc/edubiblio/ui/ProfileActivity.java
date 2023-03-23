package cat.ioc.edubiblio.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import cat.ioc.edubiblio.R;
import cat.ioc.edubiblio.data.Usuari;
import cat.ioc.edubiblio.ui.login.LoginActivity;

public class ProfileActivity extends AppCompatActivity {

    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        String rol = intent.getStringExtra("rol");
        String user = intent.getStringExtra("user");

        TextView txtUser = findViewById(R.id.tvNomUsuari);
        txtUser.setText(user);

        TextView txtRol = findViewById(R.id.tvRole);
        txtRol.setText(rol);

//        TextView txtNom = findViewById(R.id.tvNomPersonal);
//        txtNom.setText(nom);

//        TextView txtCognoms = findViewById(R.id.tvCognomsUsuari);
//        txtCognoms.setText(Usuari.getPrimerCognom() + Usuari.getSegonCognom());

//        TextView txtNaixement = findViewById(R.id.tvDataNaixement);
//        txtNaixement.setText(Usuari.getDataNaixement().toString());

//        TextView txtEmail = findViewById(R.id.tvCorreu);
//        txtEmail.setText(Usuari.getEmail());

        // Mostrar o amagar el botó "Administrar usuaris" segons el rol de l'usuari
        Button btnGestioUsuaris = findViewById(R.id.btnGestioUsuaris);
        if (rol.equals("administrador")) {
            btnGestioUsuaris.setVisibility(View.VISIBLE);
        } else {
            btnGestioUsuaris.setVisibility(View.GONE);
        }

        Button btnGestioReserves = findViewById(R.id.btnGestioReserves);
        if (rol.equals("bibliotecari") || rol.equals("bibliotecaria")) {
            btnGestioReserves.setVisibility(View.VISIBLE);
        } else {
            btnGestioReserves.setVisibility(View.GONE);
        }

        Button btnGestioLlibres = findViewById(R.id.btnGestioLlibres);
        if (rol.equals("bibliotecari") || rol.equals("bibliotecaria")) {
            btnGestioLlibres.setVisibility(View.VISIBLE);
        } else {
            btnGestioLlibres.setVisibility(View.GONE);
        }

        Button buttonLogout = findViewById(R.id.btnLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ProfileActivity.this)
                        .setTitle("Tancar sessió")
                        .setMessage("Estàs segur que vols tancar sessió?")
                        .setPositiveButton("Tancar Sessió", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("Tornar", null)
                        .show();
            }
        });
    }
}
