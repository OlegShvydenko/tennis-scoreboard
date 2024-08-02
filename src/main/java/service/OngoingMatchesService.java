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
    private final IMatchRepository matchRepository;

    public OngoingMatchesService(IPlayerRepository playerRepository, IMatchRepository matchRepository) {
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    public OngoingMatchesService() {
        this.playerRepository = new PlayerRepository();
        this.matchRepository = new MatchRepository();
    }

    public UUID newMatch(String firstName, String secondName){
        Match match = new Match(getOrCreatePlayer(firstName), getOrCreatePlayer(secondName));
        UUID uuid = createUUID();
        MatchesStorageService.saveMatch(uuid, match);
        return uuid;
    }
    private UUID createUUID(){
        UUID uuid = UUID.randomUUID();
        if (MatchesStorageService.checkKey(uuid)    ) uuid = createUUID();
        return uuid;
    }
    private Player getOrCreatePlayer(String name){
        Player player = playerRepository.getPlayerByName(name);
        if (player == null) {
            player = new Player(name);
            playerRepository.addNewPlayer(player);
        }
        return player;
    }
}
