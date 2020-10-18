import java.io.Serializable;

public class Person implements Serializable {

    private String name;        // Namn
    private String password;     // Lösenord

    /**
     * Konstruktor av Person
     *
     * @param name
     * @param password
     */
    public Person(String name, String password) {
        if (Generel_Metoder.checkNameStrain(name) && Generel_Metoder.checkPassword(password)) {
            this.name = name;
            this.password = password;
        } else {
            throw new IllegalArgumentException("\033[0;31mDet är fel på Namn eller lösenord!");
        }
    }

    /**
     * Visar en string av name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Visar en string av Password
     *
     * @return
     */
    public String checkPersonPassword() {
        return password;
    }
}




