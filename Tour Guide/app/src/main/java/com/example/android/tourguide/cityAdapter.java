package com.example.android.tourguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mostafa on 10/29/2017.
 */

public class cityAdapter extends ArrayAdapter<city> {
    public cityAdapter(Context context, ArrayList<city> attractions) {
        super(context, 0, attractions);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View ListItemView = convertView;
        if (ListItemView == null) {
            ListItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        }

        city currentAttraction = getItem(position);
        TextView nameTextView = (TextView) ListItemView.findViewById(R.id.name_text);
        nameTextView.setText(currentAttraction.getPlaceName());
        TextView addressTextView = (TextView) ListItemView.findViewById(R.id.address);
        addressTextView.setText(currentAttraction.getPlaceAddress());
        TextView descriptionTextView = (TextView) ListItemView.findViewById(R.id.description);
        descriptionTextView.setText(currentAttraction.getPlaceDescription());
        ImageView imageView = (ImageView) ListItemView.findViewById(R.id.image_view);
        if (currentAttraction.hasImage()) {
            imageView.setImageResource(currentAttraction.getmImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }


        return ListItemView;
    }


}
