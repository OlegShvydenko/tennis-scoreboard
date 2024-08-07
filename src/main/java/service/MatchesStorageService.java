package service;

import persistence.entity.Match;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// TODO переименовать в MatchesStorage
// TODO использование названия сущности в методах, которые с ней работают излишне, из названия класса и так понятно, что за сущность хранит и с какой работает этот класс
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
