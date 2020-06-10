package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
    private int productId;
    private String productName;
    private float price;
    private MultipartFile image;

    public ProductForm() {
    }

    public ProductForm(int productId, String productName, float price, MultipartFile image) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.image = image;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


}
