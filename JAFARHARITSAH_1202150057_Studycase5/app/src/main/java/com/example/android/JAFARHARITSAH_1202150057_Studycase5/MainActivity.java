package com.example.android.JAFARHARITSAH_1202150057_Studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Helper myDb;
    RecyclerView recyclerView;
    Cursor cursor;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //deklarasi view yang digunakan
        recyclerView = (RecyclerView)findViewById(R.id.rv);
        myDb = new Helper(this);
        cursor  = myDb.getAllData();

        //menginisialisasi shared preference
        SharedPreferences sharedP = this.getApplicationContext().getSharedPreferences("Preferences", 0);
        int color = sharedP.getInt("Colourground", R.color.white);

        adapter = new MyAdapter(cursor,color,this);

        //2 baris dibawah digunakan untuk mengambil data yang telah diinputkan
        //lalu di masukkan menggunakan adapter
        adapter.swapCursor(myDb.getAllData());
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //arah dari swipe yang digunakan untuk menghapus data adalah arak kiri dan kanan
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                String id = (String)viewHolder.itemView.getTag(R.string.key);
                //memanggil method deleteDataSwiping untuk menghapus data yang ingin dihapus
                myDb.deleteDataSwipping(id);
                //mengambil kembali data yang masih ada/tidak dihapus
                adapter.swapCursor(myDb.getAllData());

            }
        }).attachToRecyclerView(recyclerView);//menampilkasn pada recycler view
    }

    //listener untuk tombol addtodo
    public void addToDo(View view) {
        //mengirim intent ke class TambahTodo
        startActivity(new Intent(MainActivity.this,TambahTodo.class));
    }

    //ketika menu pada activity di buat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //membuat menu dengan layout menu_main
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //method yang dijalankan ketika item di pilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //mendapatkan id dari item yang dipilih
        int id = item.getItemId();
        //apabila item yang dipilih adalah setting
        if (id==R.id.action_settings){
            //membuat intent baru dari list to do ke setting
            startActivity(new Intent(MainActivity.this, Setting.class));
            //menutup aktivitas setelah intent dijalankan
            finish();
        }
        return true;
    }
}
