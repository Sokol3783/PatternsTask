package patterns.models.movietypes;

public abstract class MovieType {

    public abstract double getAmount(int daysRented);

    public int getFrequentRenterPoints(int dayRented) {
        if (dayRented > 1) {
            return 1;
        }
        return 0;
    }

    public abstract String name();

}
