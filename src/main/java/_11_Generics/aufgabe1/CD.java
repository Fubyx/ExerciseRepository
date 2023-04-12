package _11_Generics.aufgabe1;

 class CD extends Disc{
    int conpactness;

    public CD(boolean[] savedData, int conpactness) {
        super(savedData);
        this.conpactness = conpactness;
    }
}
