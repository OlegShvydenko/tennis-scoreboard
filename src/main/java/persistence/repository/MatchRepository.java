package persistence.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import persistence.entity.Match;
import util.HibernateUtil;

import java.util.Collections;
import java.util.List;

// TODO разделять логические части кода переходами строк во всех методах
// TODO заменить все deprecated методы аналогами из документации, если доки нет выкачать с помощью идеи
// TODO исправить все подсветы под кодом
public class MatchRepository implements IMatchRepository {

    Session session;

    public MatchRepository(Session session) {
        this.session = session;
    }

    public MatchRepository() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public void addNewMatch(Match match) {
        // TODO без транзакций нельзя совершать операции записи/апдейта/удаления
        // TODO здесь сохранение дублируется, удалить
        session.save(match);
        Transaction transaction = session.beginTransaction();
        session.persist(match);
        transaction.commit();
    }
    // TODO ниже исправленный пример строгой типизации
    @Override
    public List<Match> getMatchesFromGiven(int from, int max) {
        Query<Match> query = session.createQuery("from Match ", Match.class);
        query.setFirstResult(from);
        query.setMaxResults(max);
        List<Match> list = query.list();
        if (list == null || list.isEmpty()) return Collections.emptyList();
        else return list;
    }

    @Override
    public List<Match> getMatchesFromGivenWherePlayer(String name, int from, int max) {
        Query query = session.createQuery("from Match where playerOne.name = :name or playerTwo.name = :name");
        query.setFirstResult(from);
        query.setMaxResults(max);
        query.setParameter("name", name);
        List<Match> list = query.list();
        if (list == null || list.isEmpty()) return null;
        else return list;
    }

    @Override
    public int getNumberOfMatches() {
        String hql = "SELECT COUNT(*) FROM Match ";
        Query<Long> query = session.createQuery(hql, Long.class);
        Long count = query.uniqueResult();
        return Math.toIntExact(count != null ? count : 0);
    }

    @Override
    public int getNumberOfMatchesWherePlayer(String name) {
        String hql = "SELECT COUNT(*) FROM Match where playerOne.name = :name or playerTwo.name = :name";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("name", name);
        Long count = query.uniqueResult();
        return Math.toIntExact(count != null ? count : 0);
    }
}
