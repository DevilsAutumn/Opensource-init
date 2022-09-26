package heritsm_heritagebyamit.example.heritsm_heritageofindia.heritage;

import android.util.Pair;

import java.util.ArrayList;

public class productModel {
    public String description, image , location, period,place_name;


    public productModel(String Description, String Image, String Location,String Period,String Place_Name) {
        this.description = Description;
        this.image = Image;
        this.location = Location;
        this.period=Period;
        this.place_name = Place_Name;

    }

    public productModel() {
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }
}
