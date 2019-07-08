package com.example.productview.screens.products;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.productview.screens.company.CompanyActivity;
import com.example.productview.pojo.Product;
import com.example.productview.screens.detail_product.ProductDetailActivity;
import com.example.productview.R;
import com.example.productview.adapters.ProductAdapter;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity implements ProductsListView {

    private RecyclerView recyclerViewProducts;
    private ProductAdapter productAdapter;
    private ProductListPresenter presenter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.itemCompany) {
            Intent intent = new Intent(this, CompanyActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new ProductListPresenter(this);
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        presenter.loadProducts(this);
    }

    @Override
    public void showProducts(ArrayList<Product> products) {
        productAdapter = new ProductAdapter(products);
        recyclerViewProducts.setAdapter(productAdapter);
        productAdapter.setOnProductClickListener(new ProductAdapter.OnProductClickListener() {
            @Override
            public void onProductClick(int position) {
                Product product = productAdapter.getProducts().get(position);
                Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                intent.putExtra("id", product.getId());
                intent.putExtra("name", product.getName());
                intent.putExtra("image", product.getImage());
                intent.putExtra("description", product.getDescription());
                startActivity(intent);
            }
        });
    }
}
