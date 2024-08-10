package util;

import persistence.repository.IPlayerRepository;
import persistence.repository.PlayerRepository;

import java.util.Objects;

public class Validator {
    private final IPlayerRepository playerRepository;
    public Validator(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    public Validator() {
        this.playerRepository = new PlayerRepository();
    }

    public boolean checkNames(String firstName, String secondName){
        return Objects.equals(firstName, secondName);
    }
    public boolean checkExistenceOfPlayer(String name){
        return !Objects.equals(name, "") && playerRepository.getPlayerByName(name) == null && name != null;
    }
}
