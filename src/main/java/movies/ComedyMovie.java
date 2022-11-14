package movies;

import java.util.List;

public class ComedyMovie extends Movie{


    public ComedyMovie(String title, int jahr, double rating, List<String> cast, double basePrice) {
        super(title, jahr, rating, cast, basePrice);
    }

    @Override
    public double calculatePrice() {
        if(this.getCast().contains("Adam Sandler")){
            return this.getBasePrice()*0.5;
        }
        else{
            return this.getBasePrice();
        }
    }
}
