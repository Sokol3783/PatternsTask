package patterns.models.movie;

import patterns.services.Service;

public abstract class MovieType implements Service<MovieType> {

    public abstract double getAmount(int daysRented);

    public abstract int getFrequentRenterPoints(int dayRented);


    public abstract String name();

    public abstract int id();

    @Override
    public boolean addModel() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void listModel() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean save(MovieType model) {
        return false;
    }

    @Override
    public MovieType read(String line) {
        String[] arr = line.split(";");
        return new MovieType() {

            @Override
            public double getAmount(int daysRented) {
                return Double.parseDouble(arr[2]);
            }

            @Override
            public String name() {
                return arr[1];
            }

            @Override
            public int id() {
                return Integer.parseInt(arr[0]);
            }

            @Override
            public int getFrequentRenterPoints(int days){
                int bonus = Integer.parseInt(arr[4]);
                int day = Integer.parseInt(arr[3]);
                int extra = Integer.parseInt(arr[5]);
                if (day >= days) {
                    return bonus + extra;
                }
                return bonus;
            }

        };
    }
}
