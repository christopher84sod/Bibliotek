import java.awt.print.Book;
import java.io.*;
import java.util.Scanner;

/**
 * Metoder som hjälper Biblioteket att köra men inte direkt kopplat till de olika Class Objekt hantering
 */
public class Generel_Metoder {

  static final String frag1 = "\033[0;30m1. LogaIn:\n2. Skapa ett Konto:\n" + "3. Se lista på alla böcker:" +
            "\n4. Avsluta programet.\nVad vill du göra:\t";
   static final String frag2 =   "\033[0;30mI. Söka efterbok titel: \n" + "II. Låna/Sök Bok:\n" + "III. Se hela sortementet\n"  +
            "IV.  Avsluta Spara alla konto Objekt och Bok Objekt till Binär fil\t";

    /**
     * Rensar Strängen från tommrum, komman och punkt
     *
     * @param in
     * @return
     */
    static String changeString(String in) {
        in = in.toLowerCase();
        in = in.replace(" ", "");
        in = in.replace(",", "");
        in = in.replace(".", "");
        return in;
    }


    /**
     * Kollar om strängen enbart inehåller a-ö
     *
     * @param in
     * @return
     */
    static boolean checkNameStrain(String in) {
        boolean beslut = false;

        for (int i = 1; i <= in.length(); i++) {
            String letter = in.substring(i - 1, i);
            if (letter.equals("a") || letter.equals("b") || letter.equals("c") || letter.equals("d")
                    || letter.equals("e") || letter.equals("f") || letter.equals("g") || letter.equals("h")
                    || letter.equals("i") || letter.equals("j") || letter.equals("k") || letter.equals("l")
                    || letter.equals("m") || letter.equals("n") || letter.equals("o") || letter.equals("p")
                    || letter.equals("q") || letter.equals("r") || letter.equals("s") || letter.equals("t")
                    || letter.equals("u") || letter.equals("v") || letter.equals("w") || letter.equals("x")
                    || letter.equals("y") || letter.equals("z") || letter.equals("å") || letter.equals("ä")
                    || letter.equals("ö")) {
                beslut = true;
            } else {
                beslut = false;
                System.out.println("\033[0;31mFelaktigt Tecken");
                break;
            }
        }
        return beslut;
    }

    /**
     * Kollar om strängen enbart inehåller 0-9  + - * /
     * frammför allt för lösenord
     *
     * @param in
     * @return
     */
    static boolean checkPassword(String in) {
        boolean beslut = false;

        for (int i = 1; i <= in.length(); i++) {
            String letter = in.substring(i - 1, i);
            if (letter.equals("a") || letter.equals("b") || letter.equals("c") || letter.equals("d")
                    || letter.equals("e") || letter.equals("f") || letter.equals("g") || letter.equals("h")
                    || letter.equals("i") || letter.equals("j") || letter.equals("k") || letter.equals("l")
                    || letter.equals("m") || letter.equals("n") || letter.equals("o") || letter.equals("p")
                    || letter.equals("q") || letter.equals("r") || letter.equals("s") || letter.equals("t")
                    || letter.equals("u") || letter.equals("v") || letter.equals("w") || letter.equals("x")
                    || letter.equals("y") || letter.equals("z") || letter.equals("å") || letter.equals("ä") ||
                    letter.equals("ö") ||
                    letter.equals("0") || letter.equals("1") || letter.equals("2") || letter.equals("3") ||
                    letter.equals("4") || letter.equals("5") || letter.equals("6") || letter.equals("7") ||
                    letter.equals("8") || letter.equals("9")) {
                beslut = true;
            } else {
                System.out.println("\033[0;31mFelaktigt Tecken");
                beslut = false;
                break;
            }
        }
        return beslut;
    } // slut

    /**
     * Räknar antal Objekt det finns i binärfilen
     *
     * @param fil
     * @return
     * @throws IOException
     */
    static int countObjectsBinFile(String fil) throws IOException {
        ObjectInputStream fromBinfil = new ObjectInputStream(new FileInputStream(fil));
        int i = 0;
        while (true) {
            // Konto temp;
            try {
                // temp = (Konto)
                fromBinfil.readObject();
            } catch (EOFException | ClassNotFoundException e) {
                // Viktigt! När filen är slut så hamnar vi här
                break;
            }
            i++;
        }
        return i;
    }

    /**
     * Räknar antal rader i text-Filen
     *
     * @param textFil
     * @return
     * @throws IOException
     */
    static int CountLinesInTextFil(String textFil) throws IOException {
        BufferedReader textIn = new BufferedReader(new FileReader(textFil));
        int j = 0;
        while (true) {
            String rad = textIn.readLine();
            if (rad == null) {
                break;
            }
            j++;
        } // while
        return j;
    } // Slut


    /**
     * Låter användaren vissa valfi text för att sedan matta in endast 1-4, vid EOF retuneras "EOF"
     *
     * @param text
     * @return
     */
    static String Question(String text) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("\033[0;30mVad vill du göra:\n" + text);

            try {
                String val = input.readLine();
                if (val.equals("1") || val.equals("2") || val.equals("3") || val.equals("4")) {
                    return val;
                } else
                    System.out.println("\033[0;31mDet måste vara ett tal mellan 1-4");

            } catch (Exception NullPointerException) {
                System.out.println("\033[0;30mDu Valde att Avbryta");
                return "EOF";
            }
        }
    }

    /**
     * Låter användaren Skriv en sträng retunerar "EOF" vid EOF
     *
     * @return
     */
    public static String Write_String() {
        try {
            Scanner scan = new Scanner(System.in);
            return scan.next();
        } catch (Exception e) {
            System.out.println("\033[0;31mDu Har valt att Avbryta");
            return "EOF";
        }
    }

    /**
     * Testar om det finns ett binär fil med konto
     * Om inte skappas en binär-fil med en admin konto
     * @param kontoBInaryFile
     * @throws IOException
     */
    public static Konto[] tryCallKontoBinaryFile(String kontoBInaryFile) throws IOException {
        Konto[] konton = new Konto[0];
        try {
            int antal = Generel_Metoder.countObjectsBinFile(kontoBInaryFile);
            System.out.println("\033[0;30mHittar Konto Binärfilen");
            konton = new Konto[antal];
            Konto_SERVICE.BinaryFilePutKontoObjectsInToArray(konton,Konto.KontoBInaryFile);

        } catch (Exception e) {
            System.out.println("\033[0;31mHittade inga användare, Konto skapade");
            konton = new Konto[1];
            Person admin = new Person("admin", "12345");
            Konto adminKonto = new Konto(admin);
            konton[0] = adminKonto;
            Konto_SERVICE.KontoArrayToBinaryFile(konton, kontoBInaryFile);
            return konton;
        }
        return konton;
    }

    /**
     * Test metod som försöker se om det finns någon binär fil
     * Om inte skapar en med hjälp ut av Text-filen
     * Om inte den finns kommer felmedelande up sedan avslutas pogrammet
     * @param booksBInaryFile
     * @param textOfBooks
     * @throws InterruptedException
     */
    public static Books[] tryCallBookBinory(String booksBInaryFile, String textOfBooks) throws InterruptedException {
        Books[] books = new Books[1];
        try {
            // se om BINÄRFILER är funna
            Generel_Metoder.countObjectsBinFile(booksBInaryFile);
            System.out.println("\033[0;30mHittar Bok Binärfilen");
            books = new Books[Generel_Metoder.CountLinesInTextFil(textOfBooks)]; // Skapar en array med böcker
            Book_Service.bookBinaryFileInToArray(books, Books.BooksBInaryFile);
            Book_Service.sortBookArray(books);                                   //
            Book_Service.arrayToBinaryFile(books, booksBInaryFile);
           // return books;

        } catch (Exception FileNotFoundException) {
            System.out.println("\033[0;31mHittar inte Binär filer");
            try {
                // Se om Text filen finns
                books = new Books[Generel_Metoder.CountLinesInTextFil(textOfBooks)]; // Skapar en array med böcker
                Book_Service.readTextFilToObj(textOfBooks, books);
                Book_Service.sortBookArray(books);                                   //
                Book_Service.arrayToBinaryFile(books, booksBInaryFile);
                System.out.println("\033[0;30mSkapat BinärFil med böcker av Text-fil");
                return books;
            } catch (Exception e) {
                System.out.println("\033[0;31mHittar ingen Text-fil");
                Thread.sleep(1500);
                System.exit(0);
            }
        }
        return books;
    } //  tryCallBookBinory Slut

} // Classen sluy
