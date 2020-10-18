import java.io.*;
import java.text.Collator;
import java.util.Locale;

public class Book_Service {
    /**
     * Det teknet man söker efter av en book titel
     * hittas tecknet skrivs den booken ut och var någonstan
     * man finner den i arrayn
     * om inga böcker hittas
     * skrivs att inget hittades
     *
     * @param search
     * @param array
     */
    static void SearchBookArra(String search, Books[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            String temp = array[i].getTitel();
            temp = Generel_Metoder.changeString(temp);
            if (temp.indexOf(search) != -1) {
                System.out.println("\033[0;30mNR: " + i + "\t" + array[i].toString());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("\033[0;30mFanns inga Böcker med den Titel");
        }
    }


    /**
     * Lässer text fill och gör om dem till Book Objekt sötter in dem i en Books Array
     *
     * @param textFil
     * @param books
     * @throws IOException
     */
    static void readTextFilToObj(String textFil, Books[] books) throws IOException {
        BufferedReader textIn = new BufferedReader(new FileReader(textFil));
        int j = 0;
        while (true) {
            String rad = textIn.readLine();
            if (rad == null) {
                break;
            }
            rad = rad.trim();
            int i = rad.length();
            int n = rad.lastIndexOf(",");

            String title = rad.substring(0, n);
            String athor = rad.substring(n + 1, (i));
            athor = athor.trim();
            title = title.trim();
            Books temp = new Books(athor, title);
            books[j] = temp;
            j++;
        }// while

        System.out.println("\033[0;30mLäst klart text filen, gjort om dem till objekt och  satt dem i Arrayn\n" +
                "Antal rader lästa: " + j);
    } // Slut


    /**
     * Skickar Konto Objekt från en Array till Binärfilen (Skriver över Binärfilen)
     *
     * @param books
     * @param fil
     * @throws IOException
     */
    static void arrayToBinaryFile(Books[] books, String fil) throws IOException {
        // Öpnar Stream till fill
        ObjectOutputStream tillBinfil = new ObjectOutputStream(new FileOutputStream(fil));

        for (int i = 0; i < books.length; i++) {
            tillBinfil.writeObject(books[i]);
        }
        tillBinfil.close(); // Stänger filen
    }


    /**
     * Läser Book Objekten i den binärfil och vissar vad objekten innehåller
     *
     * @param fil
     * @throws IOException
     */
    static void displayObjectOfBinaryFile(String fil) throws IOException {
        ObjectInputStream fromBinfil = new ObjectInputStream(new FileInputStream(fil));
        String borrowed = "";
        while (true) {
            Books temp;
            try {
                temp = (Books) fromBinfil.readObject();
            } catch (EOFException | ClassNotFoundException e) {
                // Viktigt! När filen är slut så hamnar vi här
                break;
            }

            System.out.printf("\033[1;30mTitel: %-50s" + "\tFörfattare: %-45s \n", temp.getTitel(), temp.getAthor());

        }
    }

    /**
     * Objekt från Binärfil sätts in i Arrayn  (Hämtar Binärfilen)
     *
     * @param books
     * @param fil
     * @throws IOException
     */
    static void bookBinaryFileInToArray(Books[] books, String fil) throws IOException {
        ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(fil));
        int i = 0;
        // 2. Läs ett objekt i taget
        while (true) {
            Books temp;
            try {
                temp = (Books) fileReader.readObject();
            } catch (EOFException | ClassNotFoundException e) {
                // Viktigt! När filen är slut så hamnar vi här
                break;
            }
            books[i] = temp;
            i++;
        }
    }

    /**
     * Sorterar Book Arrayn i bokstavs ordning Titel och Författare
     *
     * @param bookArray
     */
    static void sortBookArray(Books[] bookArray) {
        Locale.setDefault(new Locale("sv", "SE"));    // Sätter svenska språk används
        Collator c = Collator.getInstance();
        c.setStrength(Collator.PRIMARY);
        int slut = bookArray.length - 1;
        for (int i = 0; i < slut; i++) {
            int m = i; //
            for (int j = i; j <= slut; j++) {
                if (c.compare(bookArray[j].getTitel(), bookArray[m].getTitel()) < 0 ||
                        (c.compare(bookArray[i].getTitel(), bookArray[m].getTitel()) == 0 &&
                                c.compare(bookArray[i].getAthor(), bookArray[m].getAthor()) < 0)) {
                    m = j; // nytt minsta värde
                }
                Books temp = bookArray[i];
                bookArray[i] = bookArray[m];
                bookArray[m] = temp;
            }
        }
    }
} // Book_Ser slut
