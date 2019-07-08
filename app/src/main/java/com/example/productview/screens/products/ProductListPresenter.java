package com.example.productview.screens.products;

import android.content.Context;

import com.example.productview.models.ModelProductList;
import com.example.productview.pojo.Product;

import java.util.ArrayList;


class ProductListPresenter {

    private ProductsListView view;

    ProductListPresenter(ProductsListView view) {
        this.view = view;
    }

    void loadProducts(Context context) {
        ArrayList<Product> products = ModelProductList.getProductsFromJSON(context);
        view.showProducts(products);
    }
}
