package _5AT.TP.RMI.ProbeSchularbeit;

import java.io.Serializable;

public class Buch implements Serializable {
    private String title;
    private String Autor;
    private long ISBN;
    private String verlag;

    public Buch(String title, String autor, long ISBN, String verlag) {
        this.title = title;
        Autor = autor;
        this.ISBN = ISBN;
        this.verlag = verlag;
    }

    public String getTitle() {
        return title;
    }

    public String getAutor() {
        return Autor;
    }

    public long getISBN() {
        return ISBN;
    }

    public String getVerlag() {
        return verlag;
    }
}
