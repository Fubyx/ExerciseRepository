package _11_Generics.aufgabe1;

 class WineBottle {
    String name;
    double filledFactor;

    public WineBottle(String name, double filledFactor) {
        if (filledFactor < 0 || filledFactor > 1) {
            System.out.println("Filledfactor cannot be smaller than 0 or greater than 1!");
            throw new RuntimeException();
        }
        this.name = name;
        this.filledFactor = filledFactor;
    }
}
