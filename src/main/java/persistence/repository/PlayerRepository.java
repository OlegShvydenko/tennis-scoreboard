package persistence.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import persistence.entity.Player;
import util.HibernateUtil;

import java.util.List;

public class PlayerRepository implements IPlayerRepository {
    Session session;

    public PlayerRepository(Session session) {
        this.session = session;
    }

    public PlayerRepository() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public void addNewPlayer(Player player) {
        Transaction transaction = session.beginTransaction();

        session.persist(player);

        transaction.commit();
    }

    @Override
    public Player getPlayerByName(String name) {
        Query<Player> query = session.createQuery("from Player where name = :name", Player.class);
        query.setParameter("name", name);

        List<Player> list = query.list();

        if (list == null || list.isEmpty()) return null;
        else return list.get(0);
    }
}
