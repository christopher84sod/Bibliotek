import java.io.*;
import java.text.Collator;


public class Konto_SERVICE {

    /**
     * Skickar Konto Objekt från en Array till Binärfilen (Fyller Binärfilen)
     * @param konton
     * @param fil
     * @throws IOException
     */
    static void KontoArrayToBinaryFile(Konto[] konton, String fil ) throws IOException {
        // Öpnar Stream till fill
        ObjectOutputStream tillBinfil = new ObjectOutputStream( new FileOutputStream(fil));

        for (int i = 0; i < konton.length; i++) {
            tillBinfil.writeObject(konton[i]);
        }
        tillBinfil.close(); // Stänger filen
    }

    /**
     * Objekt från Binärfil sätts in i en Array  (Hämtar Binärfilen)
     * @param konton
     * @param fil
     * @throws IOException
     */
    static void BinaryFilePutKontoObjectsInToArray(Konto[] konton , String fil) throws IOException {
        ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(fil));
        int i = 0;
        while (true) {
            Konto temp;
            try {
                temp = (Konto) fileReader.readObject();
            } catch (EOFException | ClassNotFoundException e) {
                break;
            }
            konton[i] = temp;
            i++;
        }
    }

    /**
     * SKapar ett nytt konto kopplat till en ny användare
     * Retunerar en ny array med alla användare nya som gammla
     * @param kontoBInaryFile
     * @param namn
     * @param kontoTemp
     * @return
     * @throws IOException
     */
    public static Konto[] CreatNewAccont(String kontoBInaryFile, String namn,Konto[] kontoTemp) throws IOException {
        //Konto[] kontoTemp = ;
        String losenord;
        while(true) {
            System.out.println("\033[0;35mSkriv Lösenord OBS! små bokstäver och siffror");
            losenord = Generel_Metoder.Write_String(); // Får skriva en sträng
             if (losenord.equals("EOF")){System.exit(0); }
            if (Generel_Metoder.checkPassword(losenord)) {
                Person p = new Person(namn, losenord);
                Konto k = new Konto(p);
                Konto_SERVICE.BinaryFilePutKontoObjectsInToArray(kontoTemp, kontoBInaryFile);    // Fyller Konto temp
                kontoTemp[kontoTemp.length - 1] = k; // lägger den i Arrayn
                System.out.println("\033[1;32mKONTO skapat");
                return kontoTemp;
            } // lösen
        }
    }

    /**
     * Kollar om ett konto namn är redan taget och i så fall:
     * var konto är i arrayn
     * @param konton
     * @param namn
     * @return
     */
    public static int CheckIfKontoExist(Konto[] konton, String namn) {
        Collator c = Collator.getInstance();
        c.setStrength(Collator.PRIMARY);
        for (int i = 0; i < konton.length; i++) {
            if ((c.compare(namn, konton[i].toString()) == 0)) {
                System.out.print("\033[4;34mKonto Namn finns! ");
                return i;
            }
        } // for
        return -1;
    }

} // Class Slut
