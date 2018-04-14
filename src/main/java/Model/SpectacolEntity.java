package Model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "spectacol", schema = "opera")
@NamedQuery(name="SpectacolEntity.afiseazaSpectacole",query = "FROM SpectacolEntity")
public class SpectacolEntity {
    private int id;
    private String gen;
    private String titlu;
    private String regia;
    private String distributie;
    private Integer nrTotalBilete;
    private Integer nrBileteVandute;
    private Date data;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "gen")
    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    @Basic
    @Column(name = "titlu")
    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    @Basic
    @Column(name = "regia")
    public String getRegia() {
        return regia;
    }

    public void setRegia(String regia) {
        this.regia = regia;
    }

    @Basic
    @Column(name = "distributie")
    public String getDistributie() {
        return distributie;
    }

    public void setDistributie(String distributie) {
        this.distributie = distributie;
    }

    @Basic
    @Column(name = "nr_total_bilete")
    public Integer getNrTotalBilete() {
        return nrTotalBilete;
    }

    public void setNrTotalBilete(Integer nrTotalBilete) {
        this.nrTotalBilete = nrTotalBilete;
    }

    @Basic
    @Column(name = "nr_bilete_vandute")
    public Integer getNrBileteVandute() {
        return nrBileteVandute;
    }

    public void setNrBileteVandute(Integer nrBileteVandute) {
        this.nrBileteVandute = nrBileteVandute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpectacolEntity that = (SpectacolEntity) o;

        if (id != that.id) return false;
        if (gen != null ? !gen.equals(that.gen) : that.gen != null) return false;
        if (titlu != null ? !titlu.equals(that.titlu) : that.titlu != null) return false;
        if (regia != null ? !regia.equals(that.regia) : that.regia != null) return false;
        if (distributie != null ? !distributie.equals(that.distributie) : that.distributie != null) return false;
        if (nrTotalBilete != null ? !nrTotalBilete.equals(that.nrTotalBilete) : that.nrTotalBilete != null)
            return false;
        if (nrBileteVandute != null ? !nrBileteVandute.equals(that.nrBileteVandute) : that.nrBileteVandute != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (gen != null ? gen.hashCode() : 0);
        result = 31 * result + (titlu != null ? titlu.hashCode() : 0);
        result = 31 * result + (regia != null ? regia.hashCode() : 0);
        result = 31 * result + (distributie != null ? distributie.hashCode() : 0);
        result = 31 * result + (nrTotalBilete != null ? nrTotalBilete.hashCode() : 0);
        result = 31 * result + (nrBileteVandute != null ? nrBileteVandute.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "Data")
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
