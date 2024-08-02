package persistence.repository;

import persistence.entity.Player;

public interface IPlayerRepository {

    void addNewPlayer(Player player);

    Player getPlayerByName(String name);

}
