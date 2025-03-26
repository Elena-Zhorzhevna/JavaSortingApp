package java_sorting_app.model;

import java_sorting_app.validator.DataValidator;


import java.util.Comparator;
import java.util.Optional;

public class Bus implements Comparable<Bus>, SerializableToCSVString {
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
    public int compareTo(Bus o) {
        return Comparator.comparing(Bus::getNumber)
                .thenComparing(Bus::getModel)
                .thenComparing(Bus::getMileage)
                .compare(this, o);
    }

    public static class BusBuilder {
        private String number = "";
        private String model = "";
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

        Optional<String> numberOptional = DataValidator.validateAndReturnBusNumber(number);
        numberOptional.ifPresentOrElse(busBuilder::withNumber,
                () -> System.err.println("Некорректный номер автобуса из файла."));

        Optional<String> modelOptional = DataValidator.validateAndReturnBusModel(model);
        modelOptional.ifPresentOrElse(busBuilder::withModel,
                () -> System.err.println("Некорректная модель автобуса из файла."));

        Optional<Integer> mileageOptional = DataValidator.validateAndReturnMileage(String.valueOf(mileage));
        mileageOptional.ifPresentOrElse(busBuilder::withMileage,
                () -> System.err.println("Некорректное значение пробега автобуса из файла.")
        );

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