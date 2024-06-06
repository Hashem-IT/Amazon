
package service;

import entity.Bestellung;
import entity.Type;
import entity.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Path("types")
public class TypeService {
    public static ConcurrentMap<UUID, Type> TypeDb = new ConcurrentHashMap<>();

    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Type Creat(Type type){

         UUID typeId = type.getTypeId();

        TypeDb.put(typeId,type);
        if (type != null)
        {
            System.out.println("Type eingefügt");
            return type;

        }else { throw new IllegalStateException("Type Fehler");}
    }



    @GET
    @Path("{TypeId}")
    public Type getTypeById(@PathParam("TypeId") UUID TypeId) {

        Type type = TypeDb.get(TypeId);

        if(type != null )
            return type;
        else
            throw new IllegalStateException("Es gibt kein messageid mit diese messageid");
    }
    @GET
    @Path("all")
    public Collection<Type> getAllType()  {
        return TypeDb.values();
    }
    @DELETE
    @Path("{TypeId}")
    public Type delete(@PathParam("TypeId") UUID TypeId) {

        Type type = TypeDb.remove(TypeId);

        if(type == null)
            throw new IllegalStateException("User gelöscht");
        return type;
    }

    // gib mir type nummer 1 in bestellung 1
    // http://localhost:8000/restapi/types/21000000-0000-0000-c000-000000000046/Bestellung/1
    @GET
    @Path("{TypeId}/Bestellung/{BestellungId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Type getTypefromBestellungId(@PathParam("TypeId") UUID typeId, @PathParam("BestellungId") int bestellungId) {
        Bestellung bestellung = BestellungService.BestellungDb.get(bestellungId);

        if (bestellung == null) {
            throw new IllegalStateException("No Bestellung found with this ID");
        }

        for (Type type : bestellung.getTypes()) {
            if (type.getTypeId().equals(typeId)) {
                return type;
            }
        }

        throw new IllegalStateException("No TypeId found with this ID in the specified Bestellung");

        }
    // gib mir alle type in bestellung 1
    // http://localhost:8000/restapi/types/1/types
    @GET
    @Path("{BestellungId}/types")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Type> getTypesByBestellungId(@PathParam("BestellungId") int bestellungId) {
        Bestellung bestellung = BestellungService.BestellungDb.get(bestellungId);
        if (bestellung == null) {
            throw new IllegalStateException("No Bestellung found with this ID");
        }

        return bestellung.getTypes();
    }
    }
