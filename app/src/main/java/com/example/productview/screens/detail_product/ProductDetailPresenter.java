package com.example.productview.screens.detail_product;

class ProductDetailPresenter {

    private ProductDetailView view;

    ProductDetailPresenter(ProductDetailView view) {
        this.view = view;
    }

    void loadDetailProduct() {
        view.showDetailProduct();
    }
}
