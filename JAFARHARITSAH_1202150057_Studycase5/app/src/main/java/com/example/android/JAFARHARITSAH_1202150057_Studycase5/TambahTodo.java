package com.example.android.JAFARHARITSAH_1202150057_Studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TambahTodo extends AppCompatActivity {

    //deklarasi variabel
    Helper myDb;
    EditText nameET , descET , prioET;
    RecyclerView recyclerView;
    MyAdapter adapter;
    Cursor cursor;
    Toast myToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_todo);
        recyclerView = (RecyclerView)findViewById(R.id.rv);
        //pembuatan objek DatabseHelper baru
        myDb = new Helper(this);
        //memanggil method getAllData untuk mengambil data yang ada
        cursor  = myDb.getAllData();

        //inisialisasi variabel terhadap id view yang sudah ada
        nameET = (EditText)findViewById(R.id.namaTodo);
        descET = (EditText)findViewById(R.id.descTodo);
        prioET = (EditText)findViewById(R.id.prioTodo);

        //deklarasi sharedPreference
        SharedPreferences sharedP = this.getApplicationContext().
                getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        //pembuatan objek MyAdapter baru
        adapter = new MyAdapter(cursor, color, this);
    }

    //method listener untuk tombol AddTOdo
    public void addDataClicked(View view) {
        boolean isInserted;
        //deklarasi variabel penampung isi dari edit text name, desc, dan prio
        String todoname = nameET.getText().toString();
        String description = descET.getText().toString();
        String priority = prioET.getText().toString();
        if(myToast!=null){
            myToast.cancel();
        }
        //kondisi yang harus dipenuhi
        if(!todoname.isEmpty()&&!description.isEmpty()&&!priority.isEmpty()) {
           isInserted =  myDb.insertData(todoname,description,priority);
            if(!isInserted){
                //jika tidak berhasil diinput
                myToast.makeText(this,"Data tidak berhasil diinputkan",
                        Toast.LENGTH_SHORT).show();
            }else {
                //jika berhasil diinputkan
                myToast.makeText(this,"Data berhasil diinputkan",
                        Toast.LENGTH_SHORT).show();
                //memulai inten baru menuju MainActivity kembali
                startActivity(new Intent(TambahTodo.this, MainActivity.class));
            }
        }else {
            //jika terdapat edit textn yang tidak diisi
            myToast.makeText(this,"Lengkapi terlebih dahulu field yang ada!",
                    Toast.LENGTH_SHORT).show();
        }

    }

}
