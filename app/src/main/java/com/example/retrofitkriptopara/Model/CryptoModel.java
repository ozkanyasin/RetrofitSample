package com.example.retrofitkriptopara.Model;

import com.google.gson.annotations.SerializedName;

public class CryptoModel {
    
    
    @SerializedName("currency") // json içerisinde bize gelen değerin ismi neyse serializedname de aynısı olmak zorunda
    private String currency;
    
    @SerializedName("price")
    private String price;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
