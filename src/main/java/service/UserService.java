package service;

import entity.Bestellung;
import entity.EinkaufsKorb;
import entity.User;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Path("users")
public class UserService {

    public static ConcurrentMap<Integer, User> userDb = new ConcurrentHashMap<>();

    @POST
    @Path("create")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User create(User user) {
        userDb.put(user.getUserId(), user);
        if (user != null) {
            System.out.println("User eingefügt");
            return user;
        } else {
            throw new IllegalStateException("User Fehler");
        }
    }

    @GET
    @Path("{UserId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User getUserById(@PathParam("UserId") int userId) {
        User user = userDb.get(userId);
        if (user != null) {
            return user;
        } else {
            throw new IllegalStateException("Es gibt kein user");
        }
    }

    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<User> getAllUser() {
        return userDb.values();
    }

    @DELETE
    @Path("{UserId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User delete(@PathParam("UserId") int userId) {
        User user = userDb.remove(userId);
        if (user != null) {
            System.out.println("User gelöscht");
            return user;
        } else {
            throw new IllegalStateException("User nicht gefunden");
        }
    }

    //http:
    //TODO addBestellungToUser  oder soll bestellung in Korb und dann to User
    @PUT
    @Path("addBestellungToUser/{UserId}/{BestellungId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User addBestellungToUser(@PathParam("UserId") int userId, @PathParam("BestellungId") int bestellungId) {

        User user = userDb.get(userId);
        Bestellung bestellung = BestellungService.BestellungDb.get(bestellungId);

        if (user == null || bestellung == null) {
            throw new IllegalStateException("User oder Bestellung nicht gefunden");
        }

        Collection<Bestellung> bestellungen = user.getBestellungen();
        if (bestellungen == null) {
            bestellungen = new ArrayList<>();
            user.setBestellungen(bestellungen);
        }

        bestellungen.add(bestellung);
        userDb.put(userId, user);

        System.out.println(bestellungen);

        return user;

    }

    //TODO addBestellungToUser  oder soll bestellung in Korb und dann to User
    @PUT
    @Path("addEinkaufsKorbToUser/{UserId}/{EinkaufsKorbId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User addBestellungToUser(@PathParam("UserId") int userId, @PathParam("EinkaufsKorbId") UUID einkaufsKorbId) {

        User user = userDb.get(userId);
        EinkaufsKorb einkaufsKorb = EinkaufsKorbService.EinkaufsKorbDb.get(einkaufsKorbId);

        if (user == null || einkaufsKorb == null) {
            throw new IllegalStateException("User oder EinkaufsKorb nicht gefunden");
        }


        Collection<EinkaufsKorb> einkaufsKorben = user.getEinkaufsKorb();
        if (einkaufsKorben == null) {
            einkaufsKorben = new ArrayList<>();
            user.setEinkaufsKorb(einkaufsKorben);
        }

        einkaufsKorben.add(einkaufsKorb);
        userDb.put(userId, user);

        System.out.println("EinkaufsKorb added to user: " + user);

        return user;
    }

    // gib mir alle einkaufskoerbe in UserId 1
    //http://localhost:8000/restapi/users/1/einkaufskoerbe
    @GET
    @Path("{UserId}/einkaufskoerbe")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<EinkaufsKorb> getEinkaufskoerbeByUserId(@PathParam("UserId") int userId) {
        User user = userDb.get(userId);
        if (user == null) {
            throw new IllegalStateException("No User found with this ID");
        }
        return user.getEinkaufsKorb();
    }

    // gib mir  UserId 1 in EinkaufsKorbId 1
    @GET
    @Path("{UserId}/einkaufskorb/{EinkaufsKorbId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public EinkaufsKorb getEinkaufsKorbByUserIdAndEinkaufsKorbId(@PathParam("UserId") int userId, @PathParam("EinkaufsKorbId") UUID einkaufsKorbId) {
        User user = userDb.get(userId);
        if (user == null) {
            throw new IllegalStateException("No User found with this ID");
        }

        for (EinkaufsKorb einkaufsKorb : user.getEinkaufsKorb()) {
            if (einkaufsKorb.getEinkaufsKorbId().equals(einkaufsKorbId)) {
                return einkaufsKorb;
            }
        }

        throw new IllegalStateException("No EinkaufsKorb found with this ID in the specified User");
    }
}
