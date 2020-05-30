package com.chickenInavaders;

import java.awt.*;

public class ImageDraw {
    public String sourceImag;
    public Image image;
    public int loc_x;
    public int loc_y;
    public int width;
    public int height;

    public ImageDraw(String source ,Image image,int loc_x,int loc_y,int width,int height){
        this.sourceImag = source;
        this.image = image;
        this.width = width;
        this.height = height;
        this.loc_x = loc_x;
        this.loc_y = loc_y;
    }
    public ImageDraw(Image image,int loc_x,int loc_y,int width,int height) {
        this("",image,loc_x,loc_y,width,height);
    }
    public boolean isItInRage(int x,int y){
        return (loc_x < x && x < (loc_x+width)+10 && loc_y < y &&  y< (loc_y+height)+30);
    }
}
