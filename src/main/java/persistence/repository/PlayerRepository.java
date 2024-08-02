package persistence.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import persistence.entity.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
        session.save(player);
        Transaction transaction = session.beginTransaction();
        session.persist(player);
        transaction.commit();
    }

    @Override
    public Player getPlayerByName(String name) {
        Query query = session.createQuery("from Player where name = :name");
        query.setParameter("name", name);
        List<Player> list = query.list();
        if (list == null || list.isEmpty()) return null;
        else return list.get(0);
    }
}
