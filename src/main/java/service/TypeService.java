
package service;

import entity.Type;
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

        if(TypeDb.get(TypeId) == null)
            throw new IllegalStateException("User gelöscht");
        return type;
    }


}
