package java_sorting_app.model;

import java_sorting_app.validator.DataValidator;


import java.util.Optional;

public class Bus implements SerializableToCSVString {
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

    public static Optional<Bus> fromCSVString(String stringObjectCSV) {

        String[] busData = stringObjectCSV.split(";");
        if (busData.length != 3) {
            System.err.println("Ошибка в данных файла: строка не соответствует формату.");
            return Optional.empty();
        }

        String number = busData[0].trim();
        String model = busData[1].trim();
        int mileage = Integer.parseInt(busData[2].trim());

        BusBuilder busBuilder = Bus.create();

        if (DataValidator.isValidBusNumber(number)) {
            busBuilder.withNumber(number);
        }
        else {
            System.err.println("Некорректный номер автобуса из файла: " + number);
        }

        if (DataValidator.isValidBusModel(model)) {
            busBuilder.withModel(model);
        }
        else {
            System.err.println("Некорректная модель автобуса из файла: " + model);
        }

        if (DataValidator.isValidMileage(String.valueOf(mileage))) {
            busBuilder.withMileage(mileage);
        }
        else {
            System.err.println("Некорректный пробег автобуса из файла: " + mileage);
        }

        Bus bus = busBuilder.build();

        return Optional.of(bus);
    }

    @Override
    public String toCSVString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(number)
                .append(";")
                .append(model)
                .append(";")
                .append(mileage);

        return stringBuilder.toString();
    }
}
