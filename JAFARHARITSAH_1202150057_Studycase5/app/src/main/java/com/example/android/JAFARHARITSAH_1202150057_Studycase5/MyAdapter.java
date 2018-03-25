package com.example.android.JAFARHARITSAH_1202150057_Studycase5;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    //deklarasi variabel
    private Cursor mCursor;
    private Context mContext;
    int color;

    public MyAdapter(Cursor mCursor, int color, Context mContext) {
        //deklarasi konstruktor yang digunakan
        this.mCursor = mCursor;
        this.mContext = mContext;
        this.color = color;
    }

    //holder untuk recyclerview
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        //membuat view baru
        View view = inflater.inflate(R.layout.text_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }
        //menentukan nilai yang disimpan oleh viewholder
        String id =  mCursor.getString(mCursor.getColumnIndex(MyDatabase.DatabaseScheme.DATABASE_ID));
        String todo  = mCursor.getString(mCursor.getColumnIndex(MyDatabase.DatabaseScheme.NAMA_TODO));
        String desc = mCursor.getString(mCursor.getColumnIndex(MyDatabase.DatabaseScheme.DESKRIPSI));
        String prio = mCursor.getString(mCursor.getColumnIndex(MyDatabase.DatabaseScheme.PRIORITAS));
        holder.infoText1.setText(todo);
        holder.infoText2.setText(desc);
        holder.infoText3.setText(prio);
        holder.itemView.setTag(todo);
        holder.itemView.setTag(desc);
        holder.itemView.setTag(prio);
        holder.itemView.setTag(R.string.key,id);
        //menentukan warna berdasarkan variabel color
        holder.cv.setCardBackgroundColor(mContext.getResources().getColor(this.color));
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    //setiap kali ada perubahan terhadap database kita memerlukan pertukaran cursor
    public void swapCursor(Cursor newCursor) {
        //selalu menutup cursor sebelumnya terlebih dahulu
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            //memaksai recyclerview untuk refresh data
            this.notifyDataSetChanged();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        //deklarasi variabel
        TextView infoText1,infoText2,infoText3;
        CardView cv;

        public MyViewHolder(View itemView) {
            super(itemView);
            //inisialisasi variabel terhadap id yang sesuai
            infoText1 = (TextView)itemView.findViewById(R.id.recycling_text);
            infoText2 = (TextView)itemView.findViewById(R.id.recycling_text2);
            infoText3 = (TextView)itemView.findViewById(R.id.recycling_text3);
            cv = (CardView) itemView.findViewById(R.id.cardView);

        }
    }
}
