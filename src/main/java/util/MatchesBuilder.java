package util;

import persistence.entity.Match;
import persistence.entity.Player;
import persistence.repository.IMatchRepository;
import persistence.repository.IPlayerRepository;
import persistence.repository.MatchRepository;
import persistence.repository.PlayerRepository;

import java.util.Objects;
import java.util.Random;
// TODO такие вещи делаются с помощью миграций в бд
public class MatchesBuilder {
    private static IPlayerRepository playerRepository = new PlayerRepository();
    private static IMatchRepository matchRepository = new MatchRepository();
    private static final String[] namesOfPlayers = {"Alex", "Bob", "Peter", "Steve", "Dave", "Alan"};

    public static void initTestData() {
        createPlayers();
        createRandomMatches(16);
    }

    private static void createRandomMatches(int numberOfMatches) {
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

    private static String getRandomName() {
        return namesOfPlayers[new Random().nextInt(namesOfPlayers.length)];
    }

    private static void createPlayers() {
        for (String namesOfPlayer : namesOfPlayers) {
            Player player = new Player(namesOfPlayer);
            playerRepository.addNewPlayer(player);
        }
    }
}
