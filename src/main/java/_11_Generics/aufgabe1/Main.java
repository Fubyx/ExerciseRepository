package _11_Generics.aufgabe1;

class Main {
    public static void main(String[] args) {
        Shelf<Book> bookShelf = new Shelf<>(); // nimmt nur Objekte der Klasse Book
        Shelf<Disc> discShelf = new Shelf<>(); // akzeptiert Objekte von Disc aller Klassen die von Disk vererben
        Shelf<WineBottle> wineBottleShelf = new Shelf<>();// nimmt nur Objekte der Klasse WineBottle
        WineBottle wineBottle = new WineBottle("Rotwein", 0.8);
        Book book = new Book("Harry Potter", "Es war einmal ...");
        Disc disc = new Disc(new boolean[]{true, true, false, true});
        CD cd = new CD(new boolean[]{true}, 12);
        DVD dvd = new DVD(new boolean[]{false}, "You're a wizard Harry!");

        //bookShelf.put(wineBottle);
        //discShelf.put(wineBottle);
        wineBottleShelf.put(wineBottle);

        bookShelf.put(book);
        //discShelf.put(book);
        //wineBottleShelf.put(book);

        //bookShelf.put(disc);
        discShelf.put(disc);
        //wineBottleShelf.put(disc);

        //bookShelf.put(cd);
        discShelf.put(cd);
        //wineBottleShelf.put(cd);

        //bookShelf.put(dvd);
        discShelf.put(dvd);
        //wineBottleShelf.put(dvd);
    }
}
