package service;

import persistence.entity.Match;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MatchesStorageService {
    private static Map<UUID, Match> matchesStorage = new HashMap<>();
    public static void saveMatch(UUID uuid, Match match){
        matchesStorage.put(uuid, match);
    }
    public static Match getMatchByUUID(UUID uuid){
        return matchesStorage.get(uuid);
    }
    public static void removeMatchByUUID(UUID uuid){
        matchesStorage.remove(uuid);
    }
    public static boolean checkKey(UUID uuid){
        return matchesStorage.containsKey(uuid);
    }
}
