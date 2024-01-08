package _5AT.TP.RMI.ProbeSchularbeit;

import java.awt.print.Book;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BibliothekInterface extends Remote {
    public Buch[] getAllBooks() throws RemoteException;
    public Buch getBookFromISBN(long isbn) throws RemoteException;
    public void addBook(String title, String author, long isbn, String verlag) throws RemoteException;
    public void clearBooks() throws RemoteException;
}
