package com.example.bt_gridview_filter;

import android.content.Context;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class TiviAdapter extends ArrayAdapter<Tivi> {

    Context context;
    int layout;
    ArrayList<Tivi> arrayList;
    ArrayList<Tivi> arrayListCopy;

    public TiviAdapter(Context context, int resource, ArrayList<Tivi> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.arrayList = objects;
        this.arrayListCopy = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {

        Tivi tivi = arrayList.get(position);

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(layout, null);

        TextView txt1 = convertView.findViewById(R.id.textView);
        txt1.setText(tivi.getId()+"");

        TextView txt2 = convertView.findViewById(R.id.textView2);
        txt2.setText(tivi.getName());

        TextView txt3 = convertView.findViewById(R.id.textView3);
        DecimalFormat df = new DecimalFormat();
        txt3.setText(df.format(tivi.getPrice()));

        ImageView imageView = convertView.findViewById(R.id.imageView);
        imageView.setImageResource(tivi.getImage());

        return convertView;


    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new TiviFilter();
    }


    class TiviFilter extends Filter{
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            ArrayList<Tivi> tmp = new ArrayList<>();

            for(Tivi tivi: arrayListCopy){
                if(tivi.getName().toLowerCase().contains(constraint.toString().toLowerCase())){
                    tmp.add(tivi);
                }
            }

            filterResults.count = tmp.size();
            filterResults.values = tmp;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayList = (ArrayList<Tivi>) results.values;
            notifyDataSetChanged();
        }
    }
}


