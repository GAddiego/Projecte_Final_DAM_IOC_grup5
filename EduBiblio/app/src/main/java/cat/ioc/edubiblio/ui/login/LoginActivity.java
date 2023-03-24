package cat.ioc.edubiblio.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import cat.ioc.edubiblio.R;
import cat.ioc.edubiblio.data.Usuari;
import cat.ioc.edubiblio.ui.ProfileActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etUser;
    EditText etPass;
    final private String codiInicial = "00000000";
    final private String ip = "ip"; //ip local de xarxa, no localhost
    final private int port = 12345;
    String codi;
    String rol;
    Usuari usuari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = findViewById(R.id.etUsername);
        etPass = findViewById(R.id.etPassword);

        Button buttonLogin = findViewById(R.id.btnLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idUsuari = etUser.getText().toString();
                String contrassenya = etPass.getText().toString();
                connectToServer(idUsuari, contrassenya);
            }
        });
    }

    public void connectToServer(String idUsuari, String contrassenya) {
        new Thread(() -> {
            try {
                Socket socket = new Socket(ip, port);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                out.writeUTF(codiInicial);
                out.writeUTF(idUsuari);
                out.writeUTF(contrassenya);

                boolean userValid = false;
                userValid = in.readBoolean();

                if (userValid) {
                    codi = in.readUTF();
                    rol = in.readUTF();
                    Usuari usuari = new Usuari(idUsuari, contrassenya, rol);
                    try {
                        runOnUiThread(() -> Toast.makeText(LoginActivity.this, getString(R.string.login_success), Toast.LENGTH_SHORT).show());
                        Intent intent = new Intent(this, ProfileActivity.class);
                        intent.putExtra("rol", rol);
                        intent.putExtra("user", idUsuari);
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    runOnUiThread(() -> Toast.makeText(LoginActivity.this, getString(R.string.login_failed), Toast.LENGTH_SHORT).show());
                }
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
