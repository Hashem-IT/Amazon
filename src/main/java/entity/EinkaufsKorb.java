package entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Collection;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EinkaufsKorb {

    @XmlAttribute
    private UUID EinkaufsKorbId;
    private String EinkaufsKorbBeschreibung;
    private Collection<Bestellung> bestellungen;

    private User user ;
    private Type type ;

    public UUID getEinkaufsKorbId() {
        return EinkaufsKorbId;
    }
    public EinkaufsKorb() {}
    public void setEinkaufsKorbId(UUID einkaufsKorbId) {
        EinkaufsKorbId = einkaufsKorbId;
    }

    public String getEinkaufsKorbBeschreibung() {
        return EinkaufsKorbBeschreibung;
    }

    public void setEinkaufsKorbBeschreibung(String einkaufsKorbBeschreibung) {
        EinkaufsKorbBeschreibung = einkaufsKorbBeschreibung;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    public Collection<Bestellung> getBestellungen() {
        return bestellungen;
    }

    public void setBestellungen(Collection<Bestellung> bestellungen) {
        this.bestellungen = bestellungen;
    }
    @Override
    public String toString() {
        return "EinkaufsKorb{" +
                "EinkaufsKorbId=" + EinkaufsKorbId +
                ", EinkaufsKorbBeschreibung='" + EinkaufsKorbBeschreibung + '\'' +
                ", user=" + user +
                ", type=" + type +
                '}';
    }
}
