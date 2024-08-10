package service;

import persistence.entity.Match;
import persistence.entity.Player;
import persistence.repository.IPlayerRepository;
import persistence.repository.PlayerRepository;
import service.model.MatchesStorage;

import java.util.UUID;

public class OngoingMatchesService {
    private final IPlayerRepository playerRepository;

    public OngoingMatchesService(IPlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public OngoingMatchesService() {
        this.playerRepository = new PlayerRepository();
    }

    public UUID createMatchAndGetUUID(String firstName, String secondName) {
        UUID uuid = createUUID();
        MatchesStorage.save(uuid, createNewMatch(firstName, secondName));
        return uuid;
    }

    private Match createNewMatch(String firstName, String secondName) {
        return new Match(getOrCreatePlayer(firstName), getOrCreatePlayer(secondName));
    }

    private UUID createUUID() {
        UUID uuid = UUID.randomUUID();
        if (MatchesStorage.checkKey(uuid)) uuid = createUUID();
        return uuid;
    }

    private Player getOrCreatePlayer(String name) {
        Player player = playerRepository.getPlayerByName(name);
        if (player == null) {
            playerRepository.addNewPlayer(new Player(name));
            player = playerRepository.getPlayerByName(name);
        }
        return player;
    }
}
