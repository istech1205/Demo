package com.istech.pixelmachinetest.model;

import java.io.Serializable;

public class DealsOfTheModel implements Serializable {
    private String product_name;
    private String sale_price;
    private String mrp;
    private String product_image;

    public DealsOfTheModel() {
    }

    public DealsOfTheModel(String product_name, String sale_price, String mrp, String product_image) {
        this.product_name = product_name;
        this.sale_price = sale_price;
        this.mrp = mrp;
        this.product_image = product_image;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }
}
