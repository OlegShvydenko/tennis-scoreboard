package service.model;

import persistence.entity.Match;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MatchesStorage {
    private static final Map<UUID, Match> matchesStorage = new HashMap<>();
    public static void save(UUID uuid, Match match){
        matchesStorage.put(uuid, match);
    }
    public static Match getByUUID(UUID uuid){
        return matchesStorage.get(uuid);
    }
    public static void removeByUUID(UUID uuid){
        matchesStorage.remove(uuid);
    }
    public static boolean checkKey(UUID uuid){
        return matchesStorage.containsKey(uuid);
    }
}
