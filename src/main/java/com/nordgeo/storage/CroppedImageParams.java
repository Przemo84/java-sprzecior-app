package com.nordgeo.storage;

public class CroppedImageParams {

    private String x;
    private String y;
    private String width;
    private String height;
    private String oldImageName;

    public CroppedImageParams() { }

    public CroppedImageParams(String x, String y, String width, String height, String oldImageName) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.oldImageName = oldImageName;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getOldImageName() {
        return oldImageName;
    }

    public void setOldImageName(String oldImageName) {
        this.oldImageName = oldImageName;
    }
}
