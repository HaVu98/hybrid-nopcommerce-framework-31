package javaOOP;

public class Animals {
    private String animalName = "Shark";

    protected int animalAge = 5;

    protected String getAnimalName() {  // Getter
        return animalName;
    }

    protected void setAnimalName(String animalName) { // Setter
        this.animalName = animalName;
    }
}
