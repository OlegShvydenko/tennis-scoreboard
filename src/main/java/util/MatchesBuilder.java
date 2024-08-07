package util;

import persistence.entity.Match;
import persistence.entity.Player;
import persistence.repository.IMatchRepository;
import persistence.repository.IPlayerRepository;
import persistence.repository.MatchRepository;
import persistence.repository.PlayerRepository;

import java.util.Objects;
import java.util.Random;

public class MatchesBuilder {
    IPlayerRepository playerRepository;
    IMatchRepository matchRepository;
    private final String[] namesOfPlayers = {"Alex", "Bob", "Peter", "Steve", "Dave", "Alan"};

    public MatchesBuilder(PlayerRepository playerRepository, MatchRepository matchRepository) {
        this.playerRepository = playerRepository;
        this.matchRepository = matchRepository;
    }

    public MatchesBuilder() {
        this.playerRepository = new PlayerRepository();
        this.matchRepository = new MatchRepository();
        createPlayers();
        createRandomMatches(16);
    }

    private void createRandomMatches(int numberOfMatches) {
        for (int i = 0; i < numberOfMatches; i++) {
            String name = getRandomName();
            String otherName = getRandomName();
            while (Objects.equals(name, otherName)) {
                otherName = getRandomName();
            }
            Match match = new Match();
            match.setPlayerOne(playerRepository.getPlayerByName(name));
            match.setPlayerTwo(playerRepository.getPlayerByName(otherName));
            match.setWinner(playerRepository.getPlayerByName(otherName));
            matchRepository.addNewMatch(match);
        }
    }

    private String getRandomName() {
        return namesOfPlayers[new Random().nextInt(namesOfPlayers.length)];
    }

    private void createPlayers() {
        for (String namesOfPlayer : namesOfPlayers) {
            Player player = new Player(namesOfPlayer);
            playerRepository.addNewPlayer(player);
        }
    }
}
