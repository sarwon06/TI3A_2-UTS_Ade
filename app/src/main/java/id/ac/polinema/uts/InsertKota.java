package id.ac.polinema.uts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertKota extends AppCompatActivity {
    Cursor cursor;
    DataHelper dbHelper;
    Button Tambah,Cek;
    EditText id,nama;

    private NestedScrollView nestedScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_kota);
        dbHelper = new DataHelper(this);
        id = findViewById(R.id.inputID);
        nama = findViewById(R.id.inputNama);
        Tambah = findViewById(R.id.btnTambah);
        Cek = findViewById(R.id.btnCek);

        Tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO kota (  id, nama) VALUES ('" +
                        id.getText().toString() +"','"+
                        nama.getText().toString()+"') ");

                //Intent i = new Intent(getApplicationContext(), CekKota.class);
                //startActivity(i);
                Toast.makeText(getApplicationContext(), "Tambah Kota Success", Toast.LENGTH_SHORT).show();
                //finish();
            }
        });
        Cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCek();
            }
        });
    }
    private void emptyEditText(){
        nama.setText(null);
    }
    private void goToCek(){
        Intent mIntent = new Intent(getApplicationContext(),CekKota.class);
        startActivity(mIntent);
    }
}

