package com.shupeluter.message;

public enum PaddingType {
    DEFAULT("RSA/ECB/PKCS1Padding");

    final String string;
    private PaddingType(String name) {
        string=name;
    }

    @Override
    public String toString(){
        return string;        
    }

}
