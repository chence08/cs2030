UnaryOperator<Room> takeSword = room -> {
    Pair<Integer, Optional<Sword>> pair = room.getSword();
    Optional<Sword> sword = pair.second();
    int idx = pair.first();
    if (sword.isEmpty()) {
        System.out.println("--> There is no sword.");
    } else {
        Sword actualSword = sword.get();
        if (actualSword.isTaken()) {
            System.out.println("--> You already have sword.");
        } else {
            System.out.println("--> You have taken sword.");
            return new Room(room.getName(),
                            room.getThings().set(idx, actualSword.take()),
                            room.getPreviousRoom());
        }
    }
    return room;
}

UnaryOperator<Room> killTroll = room -> {
    Pair<Integer, Optional<Troll>> pair = room.getTroll();
    Optional<Troll> troll = pair.second();
    int idx = pair.first();

    boolean hasSword = room.getSword().second().isPresent();
    if (troll.isEmpty()) {
        System.out.println("--> There is no troll.");
    } else if (hasSword) {
        boolean swordIsTaken = room.getSword().second().get().isTaken();
        if (swordIsTaken) {
            System.out.println("--> Troll is killed.");
            return new Room(room.getName(),
                            room.getThings().remove(idx).second(),
                            room.getPreviousRoom());
        } else {
            System.out.println("--> You have no sword.");
        }
    } else {
        System.out.println("--> You have no sword.");
    }
    return room;
}

// Assume you always have sword when this method is called?
UnaryOperator<Room> dropSword = room -> {
    Pair<Integer, Optional<Sword>> pair = room.getSword();
    Sword droppedSword = pair.second().get().drop();
    int idx = pair.first();
    System.out.println("--> You have dropped sword.");
    return new Room(room.getName(),
                    room.getThings().set(idx, droppedSword),
                    room.getPreviousRoom());
}