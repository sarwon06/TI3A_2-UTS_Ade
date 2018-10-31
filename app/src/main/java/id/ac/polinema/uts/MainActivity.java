package id.ac.polinema.uts;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText edtUsername, edtPassword;
    Button buttonLogin;
    Context mContext;
    SessionManagement s;
    HashMap<String,String> loginUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername     = findViewById(R.id.editUsername);
        edtPassword  = findViewById(R.id.editPassword);
        buttonLogin  = findViewById(R.id.buttonLogin);
        s = new SessionManagement(this);

        if(s.isLoggedIn()){
            goToActivity();
        }
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                if(username.matches("")||username.trim().isEmpty()||password.matches("")||password.trim().isEmpty()){
                    Toast.makeText(MainActivity.this,"username dan Password Tidak Boleh Kosong / spasi",Toast.LENGTH_LONG).show();
                    return;
                }
                else {
                    s.createLoginSession(edtUsername.getText().toString(), edtPassword.getText().toString());
                    goToActivity();
                }
            }
        });
    }

    private void goToActivity() {
        Intent mIntent = new Intent(getApplicationContext(),SharedP.class);
        startActivity(mIntent);
    }
}
