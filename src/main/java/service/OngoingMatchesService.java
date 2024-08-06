package service;

import persistence.entity.Match;
import persistence.entity.Player;
import persistence.repository.IMatchRepository;
import persistence.repository.IPlayerRepository;
import persistence.repository.MatchRepository;
import persistence.repository.PlayerRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OngoingMatchesService {
    private final IPlayerRepository playerRepository;
    public OngoingMatchesService(IPlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public OngoingMatchesService() {
        this.playerRepository = new PlayerRepository();
    }
    public UUID createMatchAndGetUUID(String firstName, String secondName){
        UUID uuid = createUUID();
        MatchesStorageService.saveMatch(uuid, newMatch(firstName, secondName));
        return uuid;
    }
    private Match newMatch(String firstName, String secondName){
        return new Match(getOrCreatePlayer(firstName), getOrCreatePlayer(secondName));
    }
    private UUID createUUID(){
        UUID uuid = UUID.randomUUID();
        if (MatchesStorageService.checkKey(uuid)) uuid = createUUID();
        return uuid;
    }
    private Player getOrCreatePlayer(String name){
        Player player = playerRepository.getPlayerByName(name);
        if (player == null) {
            playerRepository.addNewPlayer(new Player(name));
            player = playerRepository.getPlayerByName(name);
        }
        return player;
    }
}
