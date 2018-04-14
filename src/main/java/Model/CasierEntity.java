package Model;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "casier", schema = "opera")
//@NamedQuery(name="CasierEntity.showAll", query = "select CasierEntity.id,CasierEntity.nume,CasierEntity.username FROM CasierEntity")
@NamedQueries({
        @NamedQuery(name = "CasierEntity.showAll", query = " FROM CasierEntity as cas"),
        @NamedQuery(name = "CasierEntity.findByUsername", query = "FROM CasierEntity C where C.username = :username")
})
public class CasierEntity {
    private int id;
    private String username;
    private String nume;
    private byte[] parola;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "nume")
    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Basic
    @Column(name = "parola")
    public byte[] getParola() {
        return parola;
    }

    public void setParola(byte[] parola) {
        this.parola = parola;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CasierEntity that = (CasierEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (nume != null ? !nume.equals(that.nume) : that.nume != null) return false;
        if (!Arrays.equals(parola, that.parola)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (nume != null ? nume.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(parola);
        return result;
    }
}
