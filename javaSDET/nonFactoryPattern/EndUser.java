package nonFactoryPattern;

public class EndUser {
    public static void main(String[] args) {
        // Sáng ngày 20/4
        // Tới hãng Honda để xem và đặt xe lái thử
        // Đi đến địa chỉ Hãng:
        HondaHead honda = new HondaHead();
        honda.viewCar();
        honda.bookCar();
        honda.driveCar();

        // Chiều ngày 20/4
        // Tới hãng Ford để xem và đặt xe lái thử
        // Đi đến địa chỉ Hãng:
        FordHead ford = new FordHead();
        ford.viewCar();
        ford.bookCar();
        ford.driveCar();

        // Sáng ngày 21/4
        // Tới hãng Huyndai để xem và đặt xe lái thử
        // Đi đến địa chỉ Hãng:
        HuyndaiHead huyndai = new HuyndaiHead();
        huyndai.viewCar();
        huyndai.bookCar();
        huyndai.driveCar();
    }
}
