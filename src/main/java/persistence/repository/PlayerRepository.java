package persistence.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import persistence.entity.Player;
import util.HibernateUtil;

import java.util.List;

// TODO разделять логические части кода переходами строк во всех методах
// TODO заменить все deprecated методы аналогами из документации, если доки нет выкачать с помощью идеи
// TODO исправить все подсветы под кодом
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
        // TODO без транзакций нельзя совершать операции записи/апдейта/удаления
        // TODO здесь сохранение дублируется, удалить
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
