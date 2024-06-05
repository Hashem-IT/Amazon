
package service;

import entity.Bestellung;
import entity.Type;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Path("orders")
public class BestellungService {
    public static ConcurrentMap<Integer, Bestellung> BestellungDb = new ConcurrentHashMap<>();

    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Bestellung Creat(Bestellung bestellung){


        BestellungDb.put(bestellung.getBestellungId(),bestellung);
        if (bestellung != null){
            System.out.println("bestellung eingefügt");
            return bestellung;

        }else { throw new IllegalStateException("groups Fehler");}
    }

    @GET
    @Path("{BestellungId}")
    public Bestellung getBestellungById(@PathParam("BestellungId") int BestellungId) {

        Bestellung bestellung = BestellungDb.get(BestellungId);

        if(BestellungId == bestellung.getBestellungId())
            return bestellung;
        else
            throw new IllegalStateException("Es gibt kein groups");
    }

 @GET
    @Path("all")
    public Collection<Bestellung> getAllBestellung() {
        return BestellungDb.values();    }


    @DELETE
    @Path("{BestellungId}")
    public Bestellung delete(@PathParam("BestellungId") int BestellungId) {

        Bestellung bestellung = BestellungDb.remove(BestellungId);

        if(BestellungDb.get(BestellungId) == null)
            throw new IllegalStateException("User gelöscht");
        return bestellung;

    }

    @PUT
    @Path("addTypeToBestellung/{BestellungId}/{TypeId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Bestellung addTypeToBestellung(@PathParam("BestellungId") int bestellungId, @PathParam("TypeId") UUID typeId) {
        Bestellung bestellung = BestellungDb.get(bestellungId);
        Type type = TypeService.TypeDb.get(typeId);

        if (bestellung == null || type == null) {
            throw new IllegalStateException("Bestellung oder Type nicht gefunden");
        }

        Collection<Type> types = bestellung.getTypes();
        if (types == null) {
            types = new ArrayList<>();
            bestellung.setTypes(types);
        }

        types.add(type);
        BestellungDb.put(bestellungId, bestellung);

        System.out.println("Type added to Bestellung: " + bestellung);

        return bestellung;
    }
}

