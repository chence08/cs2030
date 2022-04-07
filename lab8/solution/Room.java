import java.util.Optional;
import java.util.function.UnaryOperator;

class Room {
    private final String name;
    private final ImList<Thing> things;
    private final Optional<Room> previousRoom;

    Room(String name) {
        this(name, ImList.<Thing>of(), Optional.empty());
    }

    // Template constructor
    Room(String name, ImList<Thing> things, Optional<Room> previousRoom) {
        this.name = name;
        this.things = things;
        this.previousRoom = previousRoom;
    }

    @Override
    public String toString() {
        String result = "@" + name;
        for (Thing thing: things) {
            result += "\n" + thing.toString();
        }
        return result;
    }

    Room add(Thing thing) {
        return new Room(name, things.add(thing), previousRoom);
    }

    Room tick() {
        ImList<Thing> newThings = ImList.of();
        for (Thing thing: things) {
            newThings = newThings.add(thing.update());
        }
        return new Room(name, newThings, previousRoom);
    }

    Room tick(UnaryOperator<Room> action) {
        return action.apply(this.tick());
    }

    String getName() {
        return name;
    }

    ImList<Thing> getThings() {
        return things;
    }

    Optional<Room> getPreviousRoom() {
        return previousRoom;
    }

    Pair<Integer, Optional<Sword>> getSword() {
        for (int i = 0; i < things.size(); i++) {
            Thing thing = things.get(i);
            if (thing.identify().equals("Sword")) {
                return Pair.of(i, Optional.of((Sword) thing));
            }
        }
        return Pair.of(-1, Optional.empty());
    }

    Pair<Integer, Optional<Troll>> getTroll() {
        for (int i = 0; i < things.size(); i++) {
            Thing thing = things.get(i);
            if (thing.identify().equals("Troll")) {
                return Pair.of(i, Optional.of((Troll) thing));
            }
        }
        return Pair.of(-1, Optional.empty());
    }

    // Level 5 Constructor: deja vu
    Room(String name, Room otherRoom) {
        this(name, otherRoom.things, Optional.of(otherRoom));
    }

    Room(Room newRoom, Room previousRoom) {
        this(newRoom.name, newRoom.things, Optional.of(previousRoom));
    }
    
    /** 
     * 1. You can bring along a taken sword, but not candles or trolls.
     * 2. When you bring your sword to another room, you must remove the sword before leaving.
     */
    Room go(UnaryOperator<Room> lambda) {
        Room newRoom = lambda.apply(this);
        
        // Check if sword is in your inventory! Remember to bring it along!
        Pair<Integer, Optional<Sword>> pair = getSword();
        Optional<Sword> sword = pair.second();
        int indexOfSword = pair.first();
        if (sword.isPresent() && sword.get().isTaken()) {
            ImList<Thing> removeSwordFromCurrentRoom = things.remove(indexOfSword).second();
            Room currentRoomWithoutSword = new Room(name, removeSwordFromCurrentRoom, previousRoom);
            ImList<Thing> bringSword = ImList.<Thing>of()
                                       .add(sword.get())
                                       .addAll(newRoom.things);
            return new Room(newRoom.getName(), bringSword, Optional.of(currentRoomWithoutSword));
        }
        return new Room(newRoom, this);
    }

    
    /** 
     * Note that when you return from room B to room A, things in room A are output first, 
     * followed by B (this is to be consistent with the output in the previous level dealing 
     * with go). That is to say, things in the room that is created earlier are 
     * always listed first.
     * Also note that once we enter into a new room, and return from it, time in the original 
     * room ticks only once.
     */
    Room back() {
        if (previousRoom.isEmpty()) {
            return this;
        }
        Room destination = previousRoom.get().tick();
        // Check if sword is in your inventory! Remember to bring it along!
        Optional<Sword> sword = getSword().second();
        if (sword.isPresent() && sword.get().isTaken()) {
            /* 
            For simplicity, once we come back from a room, the room that we came back from vanishes, 
            so we can't go into it again.
            So, we don't have to remove the sword when we bring it with us.
            */
            ImList<Thing> bringSword = ImList.<Thing>of()
                                      .addAll(destination.things)
                                      .add(sword.get()); // behind!
            return new Room(destination.getName(), bringSword, destination.getPreviousRoom());
        }
        return destination;
    }
}