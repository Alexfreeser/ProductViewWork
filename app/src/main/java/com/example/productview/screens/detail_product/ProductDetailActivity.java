package com.example.productview.screens.detail_product;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.productview.R;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailView {

    private TextView textViewId;
    private TextView textViewName;
    private TextView textViewDescription;
    private ImageView imageViewProduct;

    private int id;
    private String name;
    private String description;
    private String imageName;

    private ProductDetailPresenter productDetailPresenter;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        textViewId = findViewById(R.id.textViewId);
        textViewName = findViewById(R.id.textViewName);
        textViewDescription = findViewById(R.id.textViewDescription);
        imageViewProduct = findViewById(R.id.imageViewProduct);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("id") && intent.hasExtra("name") && intent.hasExtra("image") && intent.hasExtra("description")) {
            id = intent.getIntExtra("id", -1);
            name = intent.getStringExtra("name");
            description = intent.getStringExtra("description");
            imageName = intent.getStringExtra("image");
        }
        productDetailPresenter = new ProductDetailPresenter(this);
        productDetailPresenter.loadDetailProduct();
    }

    @Override
    public void showDetailProduct() {
        int imageResource = getResources().getIdentifier(imageName, "drawable", getPackageName());
        textViewId.setText(String.format("%s", id));
        textViewDescription.setText(description);
        textViewName.setText(name);
        Picasso.get().load(imageResource).placeholder(R.drawable.no_image).into(imageViewProduct);
    }
}
