package id.ac.polinema.uts;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.HashMap;

public class CekKota extends AppCompatActivity {
    SessionManagement s;
    String[] daftar;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    Button btnLogout;
    public static CekKota ck;
    HashMap<String,String> loginUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_kota);

        Button btn = (Button) findViewById(R.id.btnKembali);
        //btnLogout  = findViewById(R.id.btnLogout);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(CekKota.this,
                        InsertKota.class);
                startActivity(inte);

            }
        });

        ck = this;
        dbcenter = new DataHelper(this);
        RefreshList();

    }
    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM kota", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        ListView01 = (ListView) findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat Biodata"};
                AlertDialog.Builder builder = new AlertDialog.Builder(CekKota.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                switch (item) {
                                    case 0:
                                        Intent i = new
                                                Intent(getApplicationContext(), Detail_kota.class);
                                        i.putExtra("nama", selection);
                                        startActivity(i);
                                        break;
                                }
                            }
                        });
                builder.create().show();
            }
                                                                                                                                                                                                                                });
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
    }
    }


