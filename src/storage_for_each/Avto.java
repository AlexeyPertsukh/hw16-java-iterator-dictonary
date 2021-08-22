package storage_for_each;

public class Avto {
    private final String model;
    private final int year;

    public Avto(String model, int year) {
        this.model = model;
        this.year = year;
    }

    @Override
    public String toString() {
        return (model + ", " + year);
    }
}
