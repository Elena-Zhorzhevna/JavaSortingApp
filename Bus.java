package java_sorting_app.model;

import java.io.Serializable;

public class Bus implements Serializable, Comparable<Bus> {
    private String number;
    private String model;
    private int mileage;

    private Bus(String number, String model, int mileage) {
        this.number = number;
        this.model = model;
        this.mileage = mileage;
    }

    public static BusBuilder create() {
        return new BusBuilder();
    }

    public String getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "number='" + number + '\'' +
                ", model='" + model + '\'' +
                ", mileage=" + mileage +
                '}';
    }
    @Override
    public int compareTo(Bus other){
        int numberCompare =number.compareTo(other.number);
        if (numberCompare != 0) {
            return numberCompare;
        } else {
            return model.compareTo(other.model);
        }
    }

    public static class BusBuilder {
        private String number;
        private String model;
        private int mileage;

        public BusBuilder withNumber(String number) {
            this.number = number;
            return this;
        }

        public BusBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public BusBuilder withMileage(int mileage) {
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            return new Bus(number, model, mileage);
        }
    }
}
