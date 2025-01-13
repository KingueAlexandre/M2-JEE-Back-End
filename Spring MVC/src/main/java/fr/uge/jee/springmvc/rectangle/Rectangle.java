package fr.uge.jee.springmvc.rectangle;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class Rectangle {

    @NotNull(message = "User's age cannot be null.")
    @Min(value = 18)
    private int width;

    @NotNull(message = "User's age cannot be null.")
    @Min(value = 18)
    private int height;

    public int area(){return width*height;}

    public void setWidth(int newWidth){
        width = newWidth;
    }
    public int getWidth(){
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int newHeight){
        height = newHeight;
    }
}


