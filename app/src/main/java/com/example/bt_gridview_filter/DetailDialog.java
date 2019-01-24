package com.example.bt_gridview_filter;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class DetailDialog extends Dialog {

    ImageView imageView;
    Button btn;
    TextView txt1, txt2;

    public DetailDialog(Context context, Tivi tivi) {
        super(context);
        setContentView(R.layout.cell_detail);

        imageView = findViewById(R.id.imageView2);
        imageView.setImageResource(tivi.getImage());

        txt1 = findViewById(R.id.textView4);
        txt1.setText(tivi.getName());

        txt2  = findViewById(R.id.textView5);
        DecimalFormat df = new DecimalFormat();
        txt2.setText(df.format(tivi.getPrice()));

        final String tiviname = tivi.getName();

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getContext() , "Oh yeah this "+ tiviname +"  is expensive", Toast.LENGTH_SHORT  ).show();
                dismiss();


            }
        });

    }


}
