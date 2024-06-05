
package service;

import entity.Bestellung;
import entity.EinkaufsKorb;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Path("ShoppingBaskets")
public class EinkaufsKorbService {
    public static ConcurrentMap<UUID, EinkaufsKorb> EinkaufsKorbDb = new ConcurrentHashMap<>();

    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public EinkaufsKorb Creat(EinkaufsKorb einkaufsKorb){

        UUID einkaufsKorbId = einkaufsKorb.getEinkaufsKorbId();
        System.out.println("EinkaufsKorb ID: " + einkaufsKorb.getEinkaufsKorbId());
        System.out.println("Type ID: " + einkaufsKorbId);
        EinkaufsKorbDb.put(einkaufsKorbId, einkaufsKorb);
        if (einkaufsKorb != null) {
            System.out.println("EinkaufsKorb eingefügt: " + einkaufsKorb);
            System.out.println("Anzahl der EinkaufsKörbe: " + EinkaufsKorbDb.size());
            return einkaufsKorb;
        } else {
            throw new IllegalStateException("EinkaufsKorb Fehler");
        }
    }
    @GET
    @Path("{EinkaufsKorbId}")
    public EinkaufsKorb getGroupById(@PathParam("EinkaufsKorbId") UUID  EinkaufsKorbId) {

        EinkaufsKorb einkaufsKorb = EinkaufsKorbDb.get(EinkaufsKorbId);

        if(einkaufsKorb != null)
            return einkaufsKorb;
        else
            throw new IllegalStateException("Es gibt kein einkaufsKorb");
    }

 @GET
    @Path("all")
    public Collection<EinkaufsKorb> getAllEinkaufsKorb() {
     System.out.println("Anzahl der EinkaufsKörbe: " + EinkaufsKorbDb.size());
     return EinkaufsKorbDb.values();

    }

    @DELETE
    @Path("{EinkaufsKorbId}")
    public EinkaufsKorb delete(@PathParam("EinkaufsKorbId") UUID EinkaufsKorbId) {

        EinkaufsKorb einkaufsKorb = EinkaufsKorbDb.remove(EinkaufsKorbId);
        if(einkaufsKorb != null )
            throw new IllegalStateException("einkaufsKorb gelöscht");
        return einkaufsKorb;
    }

    @PUT
    @Path("addBestellungToEinkaufsKorb/{EinkaufsKorbId}/{BestellungId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public EinkaufsKorb addBestellungToEinkaufsKorb(@PathParam("EinkaufsKorbId") UUID einkaufsKorbId, @PathParam("BestellungId") int bestellungId) {
        EinkaufsKorb einkaufsKorb = EinkaufsKorbDb.get(einkaufsKorbId);
        Bestellung bestellung = BestellungService.BestellungDb.get(bestellungId);

        if (einkaufsKorb == null || bestellung == null) {
            throw new IllegalStateException("EinkaufsKorb oder Bestellung nicht gefunden");
        }

        Collection<Bestellung> bestellungen = einkaufsKorb.getBestellungen();
        if (bestellungen == null) {
            bestellungen = new ArrayList<>();
            einkaufsKorb.setBestellungen(bestellungen);
        }

        bestellungen.add(bestellung);
        EinkaufsKorbDb.put(einkaufsKorbId, einkaufsKorb);

        System.out.println("Bestellung added to EinkaufsKorb: " + einkaufsKorb);

        return einkaufsKorb;
    }


}

