package _5AT.TP.RMI.ProbeSchularbeit;

import java.awt.print.Book;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;

public class BibliothekImpl implements BibliothekInterface{
    private ArrayList<Buch> books = new ArrayList<>();
    @Override
    public Buch[] getAllBooks() throws RemoteException {
        System.out.println(Arrays.toString(books.toArray()));
        return books.toArray(new Buch[0]);
    }

    @Override
    public Buch getBookFromISBN(long isbn) throws RemoteException {
        for (Buch b : books) {
            if (b.getISBN() == isbn) {
                return b;
            }
        }
        return null;
    }

    @Override
    public void addBook(String title, String author, long isbn, String verlag) throws RemoteException {
        if (title != null && author != null && verlag != null) {
            for (Buch b : books) {
                if (b.getISBN() == isbn) {
                    return;
                }
            }
            books.add(new Buch(title, author, isbn, verlag));
        }
    }
    @Override
    public void clearBooks() {
        books.clear();
    }
}
