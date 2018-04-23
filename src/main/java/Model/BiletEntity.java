package Model;

import javax.persistence.*;

@Entity
@Table(name = "bilet", schema = "opera")
@NamedQueries({
        @NamedQuery(name = "BiletEntity.showAll", query = " FROM BiletEntity as bil where bil.idSpectacol=:id"),
        @NamedQuery(name = "BiletEntity.check", query = " FROM BiletEntity as bil where bil.rand=:rand and bil.numar=:numar and bil.idSpectacol= :idSpectacol")
})

public class BiletEntity {
    private int id;
    private Integer rand;
    private Integer numar;
    private Integer idSpectacol;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "rand")
    public Integer getRand() {
        return rand;
    }

    public void setRand(Integer rand) {
        this.rand = rand;
    }

    @Basic
    @Column(name = "numar")
    public Integer getNumar() {
        return numar;
    }

    public void setNumar(Integer numar) {
        this.numar = numar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BiletEntity that = (BiletEntity) o;

        if (id != that.id) return false;
        if (rand != null ? !rand.equals(that.rand) : that.rand != null) return false;
        if (numar != null ? !numar.equals(that.numar) : that.numar != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (rand != null ? rand.hashCode() : 0);
        result = 31 * result + (numar != null ? numar.hashCode() : 0);
        return result;
    }


    @Basic
    @Column(name = "id_spectacol")
    public Integer getIdSpectacol() {
        return idSpectacol;
    }

    public void setIdSpectacol(Integer idSpectacol) {
        this.idSpectacol = idSpectacol;
    }
}
