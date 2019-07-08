package com.example.productview.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.productview.pojo.Product;
import com.example.productview.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ArrayList<Product> products;
    private OnProductClickListener onProductClickListener;

    public interface OnProductClickListener {
        void onProductClick(int position);
    }

    public ProductAdapter(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void setOnProductClickListener(OnProductClickListener onProductClickListener) {
        this.onProductClickListener = onProductClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        Product product = products.get(i);
        productViewHolder.textViewIdItem.setText(String.format("%s", product.getId()));
        productViewHolder.textViewNameItem.setText(product.getName());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewIdItem;
        private TextView textViewNameItem;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewIdItem = itemView.findViewById(R.id.textViewIdItem);
            textViewNameItem = itemView.findViewById(R.id.textViewNameItem);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onProductClickListener != null) {
                        onProductClickListener.onProductClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
