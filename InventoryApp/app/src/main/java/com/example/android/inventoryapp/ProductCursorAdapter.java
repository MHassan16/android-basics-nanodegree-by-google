package com.example.android.inventoryapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.data.ProductContract;

/**
 * Created by Mostafa on 2/24/2018.
 */

public class ProductCursorAdapter extends CursorAdapter {
    public ProductCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView priceTextView = (TextView) view.findViewById(R.id.price);
        final TextView quantityTextView = (TextView) view.findViewById(R.id.quantity);
        final int productId = cursor.getInt(cursor.getColumnIndexOrThrow(ProductContract.ProductEntry._ID));
        int nameColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_NAME);
        int priceColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_QUANTITY);


        String productName=cursor.getString(nameColumnIndex);
        String productPrice = cursor.getString(priceColumnIndex);
        final int productQuantity=cursor.getInt(quantityColumnIndex);

        Button sellButton = (Button) view.findViewById(R.id.sell_button);
        sellButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int qunatity_left = productQuantity;
                if(qunatity_left <=0){
                    Toast.makeText(context,"No more Products in Stock",Toast.LENGTH_SHORT).show();
                }
                else {
                    qunatity_left = productQuantity - 1;
                }

                ContentValues values = new ContentValues();
                values.put(ProductContract.ProductEntry.COLUMN_QUANTITY,qunatity_left);
                Uri ProductUri = ContentUris.withAppendedId(ProductContract.ProductEntry.CONTENT_URI,productId);
                context.getContentResolver().update(ProductUri,values,null,null);
                quantityTextView.setText(productQuantity+ " in the Stock.");

            }
        });

        nameTextView.setText("Product Name: "+ productName);
        priceTextView.setText("It Costs "+productPrice);
        quantityTextView.setText(productQuantity+" in the Stock.");

    }


}
