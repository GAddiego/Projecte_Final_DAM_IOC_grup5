
package servidor;

/**
 *
 * @author Aleix
 */
public class Usuari {
    String user;
    String pass;
    String tipus;
    String codi;

    public Usuari(String user, String pass, String tipus) {
        this.user = user;
        this.pass = pass;
        this.tipus = tipus;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }
    
    
    
}
