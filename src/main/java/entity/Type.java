package entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.Date;
import java.util.UUID;

// auch Produkten zu kaufen
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Type {
    @XmlAttribute
    private UUID TypeId;
    private String TypeBeschreibung;

    public Type() {
    }

    public UUID getTypeId() {
        return TypeId;
    }

    public void setTypeId(UUID typeId) {
        TypeId = typeId;
    }

    public String getTypeBeschreibung() {
        return TypeBeschreibung;
    }

    public void setTypeBeschreibung(String typeBeschreibung) {
        TypeBeschreibung = typeBeschreibung;
    }

    @Override
    public String toString() {
        return "Type{" +
                "TypeId=" + TypeId +
                ", TypeBeschreibung='" + TypeBeschreibung + '\'' +
                '}';
    }
}
