package entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Collection;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Bestellung {
    @XmlAttribute
    private int BestellungId;
    private String BestellungBeschreibung;
    private double BestellungPreis;
    private Date Zeit;

    private Collection<Type> types ;
    private EinkaufsKorb einkaufsKorb ;

    public Bestellung() {   }

    public Bestellung(int bestellungId, String bestellungBeschreibung, double bestellungPreis, Date zeit) {
        BestellungId = bestellungId;
        BestellungBeschreibung = bestellungBeschreibung;
        BestellungPreis = bestellungPreis;
        Zeit = zeit;
    }

    public int getBestellungId() {
        return BestellungId;
    }

    public void setBestellungId(int bestellungId) {
        BestellungId = bestellungId;
    }

    public String getBestellungBeschreibung() {
        return BestellungBeschreibung;
    }

    public void setBestellungBeschreibung(String bestellungBeschreibung) {
        BestellungBeschreibung = bestellungBeschreibung;
    }

    public double getBestellungPreis() {
        return BestellungPreis;
    }

    public void setBestellungPreis(double bestellungPreis) {
        BestellungPreis = bestellungPreis;
    }

    public Date getZeit() {
        return Zeit;
    }

    public void setZeit(Date zeit) {
        Zeit = zeit;
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
        return "Bestellung{" +
                "BestellungId=" + BestellungId +
                ", BestellungBeschreibung='" + BestellungBeschreibung + '\'' +
                ", BestellungPreis=" + BestellungPreis +
                ", Zeit=" + Zeit +
                ", types=" + types +
                ", einkaufsKorb=" + einkaufsKorb +
                '}';
    }
}
