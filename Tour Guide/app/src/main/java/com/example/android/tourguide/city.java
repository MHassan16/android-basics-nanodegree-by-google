package com.example.android.tourguide;

/**
 * Created by Mostafa on 10/29/2017.
 */

public class city {
    private String placeName;
    private String placeAddress;
    private String placeDescription;
    private int mImageResourceId=NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;


    public city(String name, String address, String description,int imageResource){
        placeName=name;
        placeAddress=address;
        placeDescription=description;
        mImageResourceId=imageResource;
    }

    public String getPlaceName(){
        return placeName;
    }
    public String getPlaceAddress(){
        return placeAddress;

    }
    public String getPlaceDescription(){
        return placeDescription;
    }
    public int getmImageResourceId(){
        return mImageResourceId;
    }
    public boolean hasImage(){
        return mImageResourceId !=NO_IMAGE_PROVIDED;
    }
}
