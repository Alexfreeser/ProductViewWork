package com.example.productview.models;

import android.content.Context;

import com.example.productview.R;
import com.example.productview.pojo.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ModelProductList {

    private static final String KEY_RESPONSE = "response";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME= "name";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_DESCRIPTION = "description";

    public static ArrayList<Product> getProductsFromJSON(Context context) {
        ArrayList<Product> result = new ArrayList<>();
        try {
            String jsonText = readTextFromJSON(context);
            JSONObject jsonObject = new JSONObject(jsonText);
            JSONArray jsonArray = jsonObject.getJSONArray(KEY_RESPONSE);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject objectProduct = jsonArray.getJSONObject(i);
                int id = objectProduct.getInt(KEY_ID);
                String name = objectProduct.getString(KEY_NAME);
                String image = objectProduct.getString(KEY_IMAGE);
                String description = objectProduct.getString(KEY_DESCRIPTION);
                Product product = new Product(id, name, image, description);
                result.add(product);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String readTextFromJSON(Context context) {
        StringBuilder sb= null;
        try {
            InputStream is = context.getResources().openRawResource(R.raw.product);
            BufferedReader br= new BufferedReader(new InputStreamReader(is));
            sb = new StringBuilder();
            String s;
            while((  s = br.readLine())!=null) {
                sb.append(s);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
