package movies;

import java.util.List;

public class SciFiMovie extends Movie{

    public SciFiMovie(String title, int jahr, double rating, List<String> cast, double basePrice) {
        super(title, jahr, rating, cast, basePrice); // super - Referenz zu dem Konstruktor aus der Basisklasse
        // Ruft den Konstruktor aus Movie auf mit den angegebenen Parametern
    }

    @Override
    public double calculatePrice() {
        return this.getBasePrice();
    }
}
