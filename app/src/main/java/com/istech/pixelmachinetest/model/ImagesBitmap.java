package com.istech.pixelmachinetest.model;

import android.graphics.Bitmap;

public class ImagesBitmap {
    private Bitmap image;

    public ImagesBitmap() {
    }

    public ImagesBitmap(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
