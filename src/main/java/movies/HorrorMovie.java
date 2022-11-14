package movies;

import java.util.List;

public class HorrorMovie extends Movie{


    private int scariness;

    public HorrorMovie(String title, int jahr, double rating, List<String> cast, double basePrice, int scariness) {
        super(title, jahr, rating, cast, basePrice);
        this.scariness = scariness;
    }

    public int getScariness() {
        return scariness;
    }

    public void setScariness(int scariness) {
        this.scariness = scariness;
    }

    @Override
    public double calculatePrice() {
        return this.getBasePrice()*0.9;
    }
}
