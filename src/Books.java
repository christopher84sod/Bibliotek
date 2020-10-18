import java.io.*;

public class Books implements Serializable {
    private String athor;       // Författare
    private String title;       // Titel

    static final String BooksBInaryFile = "bookfil.data";
    static final String TextOfBooks = "Books.txt";

    /**
     * Konstruktor av Books
     *
     * @param athor
     * @param title
     */
    Books(String athor, String title) {
        if ((athor != null) && (title != null)) {
            this.athor = athor;
            this.title = title;
        } else {
            throw new NullPointerException("\033[0;31mFelaktig Tecken i Titel eller Förfatare!");
        }
    }

    /**
     * Vissar Författare & Titel
     *
     * @return athor & title
     */
    @Override
    public String toString() {
        return String.format("\033[1;30mFörfatare: %-45s\tTitel: %-50s", athor, title);
    }

    /**
     * Ger String av titel
     *
     * @return title
     */
    public String getTitel() {
        return title;
    }

    /**
     * Ger String av Författare
     *
     * @return athor
     */
    public String getAthor() {
        return athor;
    }
} // Books slut