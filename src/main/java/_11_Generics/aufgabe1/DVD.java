package _11_Generics.aufgabe1;

 class DVD extends Disc{
    String movieName;

    public DVD(boolean[] savedData, String movieName) {
        super(savedData);
        this.movieName = movieName;
    }
}
