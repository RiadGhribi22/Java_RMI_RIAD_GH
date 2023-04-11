import java.io.Serializable;

public class Utilisateur implements Serializable {
    public String id;
    public String pass;

    public Utilisateur(String id, String pass) {
        this.id = id;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
