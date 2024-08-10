package persistence.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import persistence.entity.Match;
import util.HibernateUtil;

import java.util.Collections;
import java.util.List;

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
        Transaction transaction = session.beginTransaction();
        session.persist(match);
        transaction.commit();
    }

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
        Query<Match> query = session.createQuery(
                "from Match where playerOne.name = :name or playerTwo.name = :name", Match.class);

        query.setFirstResult(from);
        query.setMaxResults(max);
        query.setParameter("name", name);

        List<Match> list = query.list();

        if (list == null || list.isEmpty()) return Collections.emptyList();
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
