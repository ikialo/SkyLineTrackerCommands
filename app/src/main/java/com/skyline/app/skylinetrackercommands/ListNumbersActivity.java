package com.skyline.app.skylinetrackercommands;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListNumbersActivity extends AppCompatActivity implements AdapterListCars.OnItemClickListener {



    private List<String> mList;
    private List<String> listDriver;
    RecyclerView recyclerView;
    AdapterListCars adapter;
    private Button button;
    EditText phoneNumber, carName;
     SQLiteDatabase mydatabase;
    Cursor resultSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_numbers);



        mList = new ArrayList<>();
        listDriver = new ArrayList<>();

        mydatabase = openOrCreateDatabase("car number",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS PhoneCarName(Username VARCHAR,Password VARCHAR);");

        phoneNumber = findViewById(R.id.saveCarPhone);
        carName = findViewById(R.id.plateNumber);

         resultSet = mydatabase.rawQuery("Select * from PhoneCarName",null);

        long sizeOfEntry = getProfilesCount(mydatabase, "PhoneCarName");






        resultSet.moveToFirst();

        for (int q = 0; q<sizeOfEntry; q++){

            resultSet.moveToPosition(q);

            String Carname = resultSet.getString(1);

            mList.add(Carname);


        }


        recyclerView = findViewById(R.id.recviewListCars);
        button = findViewById(R.id.save);
        adapter  = new AdapterListCars(ListNumbersActivity.this,mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListNumbersActivity.this));

        adapter.setOnItemClickListener(ListNumbersActivity.this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (phoneNumber.getText().toString().length() != 8){

                    Toast.makeText(ListNumbersActivity.this, "Number not correct length", Toast.LENGTH_SHORT).show();

                    return;
                }


                if (phoneNumber.getText().toString() != null && carName.getText().toString() != null) {
                    mydatabase.execSQL("INSERT INTO PhoneCarName VALUES('" + phoneNumber.getText().toString() + "','" + carName.getText().toString() + "');");

                    mList.add(carName.getText().toString());
                }



                adapter.notifyDataSetChanged();
            }
        });

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {


        resultSet.moveToPosition(position);

        Intent intent = new Intent(ListNumbersActivity.this, MainActivity.class);

        intent.putExtra("CarNumber",resultSet.getString(0));
        intent.putExtra("CarName",resultSet.getString(1));

        startActivity(intent);

    }

    public long getProfilesCount( SQLiteDatabase db, String TABLE_NAME ) {
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME);

        return count;
    }



}
