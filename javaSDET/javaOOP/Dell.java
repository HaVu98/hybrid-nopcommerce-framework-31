package javaOOP;

public class Dell implements Computer {

    @Override
    public void cpu() {
        System.out.println("CPU Core i9 - 11000");
    }

    @Override
    public void ram() {

    }

    @Override
    public void ssd() {

    }

    @Override
    public void fan() {

    }

    public void gpu() {
        // Onboard: tích hợp trong main
        // Rời: gắn ngoài
    }
}
