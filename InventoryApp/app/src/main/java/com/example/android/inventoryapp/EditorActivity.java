package com.example.android.inventoryapp;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.inventoryapp.data.ProductContract;

import static com.example.android.inventoryapp.R.string.delete;

/**
 * Created by Mostafa on 2/24/2018.
 */

public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    int quantity = 0;
    private static final int EXISTING_PRODUCT_LOADER = 0;
    private Uri mCurrentProductUri;
    private boolean mProductHasChanged = false;

    private EditText mNameEditText;
    private EditText mPriceEditText;
    EditText mQuantityTextView;
    Button increaseQuantity;
    Button decreaseQuantity;
    private ImageView mImage;
    String image_1 = null;

    private static int Get_Image = 1;

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            mProductHasChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Intent intent = getIntent();
        mCurrentProductUri = intent.getData();
        if (mCurrentProductUri == null) {
            setTitle(getString(R.string.editor_activity_title_new_product));
            invalidateOptionsMenu();
        } else {
            setTitle(getString(R.string.editor_activity_title_edit_product));
            getLoaderManager().initLoader(EXISTING_PRODUCT_LOADER, null, this);
        }

        Button addImage = (Button) findViewById(R.id.image_button);

        mNameEditText = (EditText) findViewById(R.id.edit_product_name);
        mPriceEditText = (EditText) findViewById(R.id.edit_product_price);
        mQuantityTextView = (EditText) findViewById(R.id.edit_product_quantity);
        increaseQuantity =(Button) findViewById(R.id.plus_button);
        decreaseQuantity = (Button) findViewById(R.id.minus_button);
        mImage = (ImageView) findViewById(R.id.image_view);

        mNameEditText.setOnTouchListener(mTouchListener);
        mPriceEditText.setOnTouchListener(mTouchListener);
        mQuantityTextView.setOnTouchListener(mTouchListener);
        increaseQuantity.setOnTouchListener(mTouchListener);
        decreaseQuantity.setOnTouchListener(mTouchListener);


        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(Intent.createChooser(intent, "Select Image From"), Get_Image);
                }

            }
        });

        increaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemButton(view);

            }
        });
        decreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rejectItemButton(view);

            }
        });

    }
    public void addItemButton(View view) {
        quantity++;
        displayQuantity();
    }

    public void rejectItemButton(View view) {
        if (quantity == 0) {
            Toast.makeText(this, "Can't decrease quantity", Toast.LENGTH_SHORT).show();
        } else {
            quantity--;
            displayQuantity();
        }
    }

    public void displayQuantity() {
        mQuantityTextView.setText(String.valueOf(quantity));
    }


    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.edit_product_quantity);
        quantityTextView.setText("" + number);
    }

    public void increment(View view) {

        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity <= 0)
            Toast.makeText(EditorActivity.this, "Buy some items first", Toast.LENGTH_SHORT).show();
        else {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // If this is a new pet, hide the "Delete" menu item.
        if (mCurrentProductUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }


    private void showDeleteConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(delete, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteProduct();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteProduct() {
        if (mCurrentProductUri != null) {
            int rowsDeleted = getContentResolver().delete(mCurrentProductUri, null, null);
            if (rowsDeleted == 0) {
                Toast.makeText(this, "Error with deleting product", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Product Deleted", Toast.LENGTH_SHORT).show();
            }
            finish();
        }
    }

    public void onBackPressed() {
        if (!mProductHasChanged) {
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                };

        showUnsavedChangesDialog(discardButtonClickListener);

    }

    private void showUnsavedChangesDialog(DialogInterface.OnClickListener discardButtonClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void saveProduct() {
        String name = mNameEditText.getText().toString();
        String price = mPriceEditText.getText().toString();
        String quantity = mQuantityTextView.getText().toString();
        int Quantity_1 = 0;

        if (mCurrentProductUri == null && TextUtils.isEmpty(name) || TextUtils.isEmpty(price) || TextUtils.isEmpty(quantity) ||
                TextUtils.isEmpty(image_1)) {
            Toast.makeText(EditorActivity.this, "Continue all the Fields", Toast.LENGTH_SHORT).show();

            return;
        }

        ContentValues values = new ContentValues();

        if (TextUtils.isEmpty(name)) {
            Toast.makeText(this, getString(R.string.validation_msg_product_name), Toast.LENGTH_SHORT).show();
        } else {
            values.put(ProductContract.ProductEntry.COLUMN_NAME, name);

        }
        if (TextUtils.isEmpty(quantity)) {
            Toast.makeText(this, getString(R.string.validation_msg_product_quantity), Toast.LENGTH_SHORT).show();
        } else {
            Quantity_1 = Integer.parseInt(quantity);
            values.put(ProductContract.ProductEntry.COLUMN_QUANTITY, Quantity_1);
        }
        if (TextUtils.isEmpty(price)) {
            Toast.makeText(this, getString(R.string.validation_msg_product_price), Toast.LENGTH_SHORT).show();
        } else {
            values.put(ProductContract.ProductEntry.COLUMN_PRICE, price);
        }

        values.put(ProductContract.ProductEntry.COLUMN_IMAGE, image_1);


        if (mCurrentProductUri == null) {
            Uri newUri = getContentResolver().insert(ProductContract.ProductEntry.CONTENT_URI, values);
            if (newUri == null) {
                Toast.makeText(this, getString(R.string.editor_insert_product_failed), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_insert_product_successful), Toast.LENGTH_SHORT).show();
            }
        } else {
            int rowsAffected = getContentResolver().update(mCurrentProductUri, values, null, null);

            if (rowsAffected == 0) {
                Toast.makeText(this, getString(R.string.editor_update_product_failed), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.editor_update_product_successful), Toast.LENGTH_SHORT).show();
            }
        }
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String name = mNameEditText.getText().toString();
        String price = mPriceEditText.getText().toString();
        String quantity = mQuantityTextView.getText().toString();

        switch (item.getItemId()) {
            case R.id.action_save:
                saveProduct();
                return true;

            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;

            case R.id.action_order:
                orderProduct();
                return true;

            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                if (!mProductHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }

                DialogInterface.OnClickListener discardButtonClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    }

                };
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                ProductContract.ProductEntry._ID,
                ProductContract.ProductEntry.COLUMN_NAME,
                ProductContract.ProductEntry.COLUMN_PRICE,
                ProductContract.ProductEntry.COLUMN_QUANTITY,
                ProductContract.ProductEntry.COLUMN_IMAGE
        };
        return new CursorLoader(this, mCurrentProductUri, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if (data.moveToFirst()) {
            int nameColumnIndex = data.getColumnIndex(ProductContract.ProductEntry.COLUMN_NAME);
            int priceColumnIndex = data.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRICE);
            int quantityColumnIndex = data.getColumnIndex(ProductContract.ProductEntry.COLUMN_QUANTITY);
            int imageColumnIndex = data.getColumnIndex(ProductContract.ProductEntry.COLUMN_IMAGE);
            String name = data.getString(nameColumnIndex);
            String price = data.getString(priceColumnIndex);
            int quantity = data.getInt(quantityColumnIndex);
            String image = data.getString(imageColumnIndex);

            mNameEditText.setText(name);
            mPriceEditText.setText(price);
            mQuantityTextView.setText(Integer.toString(quantity));
            mImage.setImageURI(Uri.parse(image));
            image_1 = image;

        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mNameEditText.setText("");
        mPriceEditText.setText("");
        mQuantityTextView.setText("");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Get_Image && resultCode == RESULT_OK) {
            Uri imageUri = data.getData();
            image_1 = imageUri.toString();
            mImage.setImageURI(imageUri);
        }
    }

    private void orderProduct() {

        String orderName = mNameEditText.getText().toString();
        String orderQuantity = mQuantityTextView.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:mostafahassan@hotmail.com"));
        intent.putExtra(Intent.EXTRA_TEXT, "Order Name: " + orderName + "\nnumber: " + orderQuantity);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}
