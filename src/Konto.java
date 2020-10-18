import java.io.*;

public class Konto implements Serializable {

    private Person own;
    private Books borow; // Bara en bok!
    static final String KontoBInaryFile = "kontofil.data";

    /**
     * Konstruktor ab Konto
     * en ägare av Person objekt & en bok som lånats
     * @param own
     * @param borow
     */
    Konto(Person own, Books borow){
        this.own = own;
        this.borow = borow;
    }

    /**
     * Sätter ägare
     * @param own
     */
    Konto(Person own){
        this.own = own;
    }

    /**
     * Retunerar en Sträng av Person Objektet
     * @return
     */
    @Override
    public String toString(){
        return this.own.getName();
    }

    /**
     * En sträng av ägarens lösen
     * @return
     */
    public String checkPass(){
        return this.own.checkPersonPassword();
    }

    /**
     * En sträng av Objektet Book
     * finns ingen Objekt en sträng berättar det
     * @return
     */
    public String getBorrow(){
        try {
            return this.borow.getTitel() + "\t" + this.borow.getAthor();
        }catch (Exception e){return "\033[1;30mHar Ej lånat någon book";}
    }


    } // Slut
