package factoryPattern;

import commons.CarList;
import net.bytebuddy.implementation.bytecode.Throw;

public class EndUser {
    public static CarFactory carFactory;

    public static void main(String[] args) {
        // End User muốn xem và lái xe Honda
        carFactory = getCar("Honda");
        carFactory.viewCar();
        carFactory.bookCar();
        carFactory.driveCar();
    }
    public static CarFactory getCar(String carName) {
        CarFactory carFactory = null;
        CarList carList = CarList.valueOf(carName.toUpperCase());
        switch (carList) {
            case HONDA:
                carFactory = new HondaHead();
                break;
            case HUYNDAI:
                carFactory = new HuyndaiHead();
                break;
            case FORD:
                carFactory = new FordHead();
                break;
            default:
                new RuntimeException("Car name is not valid.");
        }
        return carFactory;
    }
}
