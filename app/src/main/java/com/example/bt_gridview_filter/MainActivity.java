package com.example.bt_gridview_filter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ArrayList<Tivi> arrayList;
    GridView gridView;
//    ArrayAdapter arrayAdapter;
    TiviAdapter tiviAdapter;
    Button btn1,btn2,btn3;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);

        arrayList = new ArrayList<>();
        arrayList.add(new Tivi(1, "Sony01", 14560000, R.drawable.tivi_01));
        arrayList.add(new Tivi(2, "Sony02", 12990000, R.drawable.tivi_02));
        arrayList.add(new Tivi(3, "Sharp01", 11590000, R.drawable.tivi_03));
        arrayList.add(new Tivi(4, "Sharp02", 10290000, R.drawable.tivi_04));
        arrayList.add(new Tivi(5, "Sharp03", 18290000, R.drawable.tivi_05));
        arrayList.add(new Tivi(6, "Sharp04", 9999000, R.drawable.tivi_06));
        arrayList.add(new Tivi(7, "Panasonic01", 1399000, R.drawable.tivi_07));
        arrayList.add(new Tivi(8, "Panasonic02", 1799000, R.drawable.tivi_08));
        arrayList.add(new Tivi(9, "Panasonic03", 1459000, R.drawable.tivi_09));
        arrayList.add(new Tivi(10, "Panasonic04", 1789000, R.drawable.tivi_10));

//        arrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1
////                , arrayList);

        tiviAdapter = new TiviAdapter(MainActivity.this, R.layout.gridview_cell, arrayList);


        gridView.setAdapter(tiviAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Tivi tivi = arrayList.get(position);

                DetailDialog detailDialog = new DetailDialog(MainActivity.this, tivi);
                detailDialog.show();
            }
        });

        btn1 = findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.add(new Tivi(11, "Sony3", 12340000, R.drawable.tivi_03));
                tiviAdapter.notifyDataSetChanged();
            }
        });

        btn2 = findViewById(R.id.button3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.remove(arrayList.size()-1);
                tiviAdapter.notifyDataSetChanged();
            }
        });

        btn3 = findViewById(R.id.button4);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(arrayList);
                tiviAdapter.notifyDataSetChanged();

            }
        });

        edt = findViewById(R.id.editText);
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Toast.makeText(getApplicationContext(), "hang on", Toast.LENGTH_SHORT  );
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tiviAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(getApplicationContext(), "It's been filter", Toast.LENGTH_SHORT  );
            }
        });


    }
}
