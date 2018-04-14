package com.funnyface.make.bmpmodel;

import java.io.Serializable;


public class bmpStickerPropertyModel implements Serializable {
    private static final long serialVbmpionUID = 3800737478616389410L;

    private long bmpstickerId;
    private String bmptext;
    private float bmpxLocation;
    private float bmpyLocation;
    private float bmpdegree;
    private float bmpgpescaling;
    private int bmporder;
    private int erhorizonMirror;
    private String bmpstickerURL;

    public int geterhorizonMirror() {
        return erhorizonMirror;
    }

    public void seterhorizonMirror(int erhorizonMirror) {
        this.erhorizonMirror = erhorizonMirror;
    }

    public String getbmpstickerURL() {
        return bmpstickerURL;
    }

    public void setbmpstickerURL(String bmpstickerURL) {
        this.bmpstickerURL = bmpstickerURL;
    }

    public long getStickerId() {
        return bmpstickerId;
    }

    public void setStickerId(long bmpstickerId) {
        this.bmpstickerId = bmpstickerId;
    }

    public String getText() {
        return bmptext;
    }

    public void setText(String bmptext) {
        this.bmptext = bmptext;
    }

    public float getbmpxLocation() {
        return bmpxLocation;
    }

    public void setbmpxLocation(float bmpxLocation) {
        this.bmpxLocation = bmpxLocation;
    }

    public float getbmpyLocation() {
        return bmpyLocation;
    }

    public void setbmpyLocation(float bmpyLocation) {
        this.bmpyLocation = bmpyLocation;
    }

    public float getbmpdegree() {
        return bmpdegree;
    }

    public void setbmpdegree(float bmpdegree) {
        this.bmpdegree = bmpdegree;
    }

    public float getbmpgpescaling() {
        return bmpgpescaling;
    }

    public void setbmpgpescaling(float bmpgpescaling) {
        this.bmpgpescaling = bmpgpescaling;
    }

    public int getbmporder() {
        return bmporder;
    }

    public void setbmporder(int bmporder) {
        this.bmporder = bmporder;
    }
}
