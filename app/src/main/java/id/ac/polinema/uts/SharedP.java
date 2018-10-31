package id.ac.polinema.uts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class SharedP extends AppCompatActivity {
    SessionManagement s;
    TextView tvUsername;
    Button buttonKota;
    HashMap<String,String> loginUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_p);

        tvUsername = findViewById(R.id.tvUsername);
        buttonKota = findViewById(R.id.buttonKota);

        s = new SessionManagement(this);
        loginUser = s.getUserInformation();

        Toast.makeText(this, s.isLoggedIn()+"",Toast.LENGTH_LONG).show();

        tvUsername.setText(loginUser.get(SessionManagement.KEY_EMAIL));
        buttonKota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //s.logoutUser();
                goToInsert();
            }
        });
    }
    private void goToInsert() {
        Intent mIntent = new Intent(getApplicationContext(),InsertKota.class);
        startActivity(mIntent);
    }
}
