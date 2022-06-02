package es.dam.examenjunio1ord.models.items;

import java.util.Random;

public abstract class Item {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Item getRandomItem() {
        Random random = new Random();
        int randomNum = random.nextInt(3);

        switch (randomNum) {
            case 0:
                return new Armas().setNivelFuerza(random.nextInt(50) + 50);
            case 1:
                return new Medicina().setNivelVida(random.nextInt(50) + 50);
            case 2:
                return new Trampa();
            default:
                throw new RuntimeException("Invalid number");
        }
    }
}
