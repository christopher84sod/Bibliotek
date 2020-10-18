import java.io.*;

public class Biban_Main {
    /**
     * Ett Bibliotek där användaren skapar ett konto loggarin och lånar söker efter böcker.
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        Books[] books = Generel_Metoder.tryCallBookBinory(Books.BooksBInaryFile, Books.TextOfBooks); // ser om det finns någon Book Binär fil

        Konto[] konton = Generel_Metoder.tryCallKontoBinaryFile(Konto.KontoBInaryFile);     // ser om det finns någon Konto Binär fil
                                                                    // Om inte då skappas dessa Binär filler

        while (true) {
            String val = Generel_Metoder.Question(Generel_Metoder.frag1);
            if (val.equals("EOF")){System.exit(0);}
            if (val.equals("1")) {
                konton = new Konto[Generel_Metoder.countObjectsBinFile(Konto.KontoBInaryFile)]; // Skapar konto Array
                Konto_SERVICE.BinaryFilePutKontoObjectsInToArray(konton,Konto.KontoBInaryFile);                 // Fyller Kono arrayn
                System.out.println("\033[0;30mDu valde: LogaIn");
                int index;
                while (true) {
                     System.out.println("\033[0;30mSkriv in ditt användarnamn");
                     String namn = Generel_Metoder.Write_String();
                    if (namn.equals("EOF")){System.exit(0);}
                     index = Konto_SERVICE.CheckIfKontoExist(konton, namn);

                     if (index != -1 ) {
                         System.out.println("\033[0;30mSkriv in ditt Lösenord");
                         String losenord = Generel_Metoder.Write_String();
                         if (losenord.equals("EOF")){System.exit(0);}
                         if (konton[index].checkPass().equals( losenord)){
                         System.out.println("\033[1;35mDu är nu Inloggad");
                         break;}
                        }
                      System.out.println("\033[0;31mFelaktigt användare eller Lösenord");
                     }
                 //    System.out.println("Felaktig användare!");
                while (true) {
                    System.out.println("\n\033[0;30mVälkommen: " + konton[index].toString() + "\n" +  konton[index].getBorrow() + "\n");
                    val = Generel_Metoder.Question(Generel_Metoder.frag2);
                    if (val.equals("EOF")){System.exit(0);}
                     if (val.equals("1")) {
                         System.out.print("\033[0;30mSkriv in den titel du söker efter\t");
                         String titel = Generel_Metoder.Write_String();
                         if (titel.equals("EOF")) {System.exit(0);}
                         Book_Service.SearchBookArra(titel,books);
                    } else if (val.equals("2")) {
                         int nr = -2;
                     while (true) {
                         System.out.println("\033[0;30mväljBok NR: ");
                         String stringnr = Generel_Metoder.Write_String();
                         if(stringnr.equals("EOF")){System.exit(0);}
                         try {
                             nr = Integer.parseInt(stringnr);
                         } catch (Exception e) {
                             System.out.println("\033[0;30mMåste vara en siffra");
                         }
                          if(  (-1 < nr) && (nr < books.length -1)  ){
                                Person nyP = new  Person(konton[index].toString(),konton[index].checkPass() );
                                Konto nyK = new Konto(nyP,books[nr] );
                                konton[index] = nyK;
                                Konto_SERVICE.KontoArrayToBinaryFile(konton,Konto.KontoBInaryFile);
                                System.out.println("\033[1;35mDu har nu Lånat book");
                                break;
                          }else {System.out.println("\033[0;30mSiffran måste vara mellan 0-" + (books.length -1));}
                     }
                    } else if (val.equals("3")) {
                         for (int i = 0; i < books.length; i++) {
                             System.out.printf( "Nr: %-10s \n",i + " "  + books[i].toString());
                         }

                    } else if (val.equals("4")) {
                        System.out.println("\033[0;30mDu har valdt: Avsluta Pogramet");
                        System.exit(0);
                    }
                } // Andra if Sattsen

            } else if (val.equals("2")) {
                System.out.println("\033[0;30mDu valde: Skapa ett Konto");
                String namn;
                while (true) {
                    System.out.println("\033[1;35mVälj Användar Namn  OBS! enbart små bokstäver " );
                    int ant = Generel_Metoder.countObjectsBinFile(Konto.KontoBInaryFile); // Antal lagrade konton
                    ant = ant + 1; // Resarverar en plats för det nya
                    Konto[] kontoTemp = new Konto[ant]; // Skapar ny array
                    namn = Generel_Metoder.Write_String(); // Får skriva en sträng
                    if (namn.equals("EOF")) {System.exit(0);}
                   if (Generel_Metoder.checkNameStrain(namn) && (Konto_SERVICE.CheckIfKontoExist(konton, namn)) == -1  ) {
                       // Konton array Sparas i binärfil

                       Konto_SERVICE.KontoArrayToBinaryFile( Konto_SERVICE.CreatNewAccont(Konto.KontoBInaryFile, namn,kontoTemp),Konto.KontoBInaryFile);
                       konton = kontoTemp;
                       break;
                    } // Skapar nytt konto
                        System.out.println("\033[0;30mGör igen:");
                        } // Kollar att det inte är admin
                } // while

            else if (val.equals("3")) {
                System.out.println("\033[0;30mDu valde: Se Vårt Sortement:");
                Book_Service.displayObjectOfBinaryFile(Books.BooksBInaryFile);
            } else if (val.equals("4")) {
                System.out.println("\033[0;30mDu valde: Avsluta Pogramet");
                System.exit(0);
            }
        } // While first loop

    } // Main

} // Slut
