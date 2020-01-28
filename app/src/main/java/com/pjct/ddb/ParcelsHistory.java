package com.pjct.ddb;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.pjct.ddb.Entities.Parcel;

import java.util.List;


public class ParcelsHistory extends AppCompatActivity{

    public RecyclerView parcelsRecycleView;
    private List<Parcel> parcels;


    public void Toasts(){
        Toast.makeText(getApplicationContext(),"rtg",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcels_history);



        parcelsRecycleView = findViewById(R.id.studentsRecycleView);
        parcelsRecycleView.setHasFixedSize(true);
        parcelsRecycleView.setLayoutManager(new LinearLayoutManager(this));
        Firebase_DBManager.notifyToParcelList(new Firebase_DBManager.NotifyDataChange<List<Parcel>>() {
            @Override
            public void OnDataChanged(List<Parcel> obj) {
                if (parcelsRecycleView.getAdapter() == null) {
                    parcels = obj;
                    parcelsRecycleView.setAdapter(new StudentsRecycleViewAdapter(ParcelsHistory.this, parcels));
                } else

                    parcelsRecycleView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Exception exception) {
                Toast.makeText(getBaseContext(), "error to get parcels list\n" + exception.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        Firebase_DBManager.stopNotifyToParcelList();
        super.onDestroy();
    }



}
