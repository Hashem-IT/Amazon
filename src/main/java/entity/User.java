package entity;

import jakarta.xml.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
    @XmlAttribute
    private int UserId;
    private String Adresse;
    private Date Zeit;

    private Collection<Bestellung> bestellungen;
    private Collection<Type> types ;
    private EinkaufsKorb einkaufsKorb;

    public User() { }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public Date getZeit() {
        return Zeit;
    }

    public void setZeit(Date zeit) {
        Zeit = zeit;
    }

    public Collection<Bestellung> getBestellungen() {
        return bestellungen;
    }

    public void setBestellungen(Collection<Bestellung> bestellungen) {
        this.bestellungen = bestellungen;
    }

    public Collection<Type> getTypes() {
        return types;
    }

    public void setTypes(Collection<Type> types) {
        this.types = types;
    }

    public EinkaufsKorb getEinkaufsKorb() {
        return einkaufsKorb;
    }

    public void setEinkaufsKorb(EinkaufsKorb einkaufsKorb) {
        this.einkaufsKorb = einkaufsKorb;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", Adresse='" + Adresse + '\'' +
                ", Zeit=" + Zeit +
                ", bestellungen=" + bestellungen +
                ", types=" + types +
                '}';
    }
}
