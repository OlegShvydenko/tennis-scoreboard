package persistence.repository;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import persistence.entity.Match;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class MatchRepository implements IMatchRepository {

    Session session;
    Transaction transaction;

    public MatchRepository(Session session, Transaction transaction) {
        this.transaction = transaction;
        this.session = session;
    }

    public MatchRepository() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        this.transaction = session.beginTransaction();
    }
    @Override
    public List<Match> getAllMatches() {
        CriteriaBuilder cBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Match> cQuery = cBuilder.createQuery(Match.class);
        Root<Match> rootEntry = cQuery.from(Match.class);
        CriteriaQuery<Match> all = cQuery.select(rootEntry);

        TypedQuery<Match> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public void saveMatch(Match match) {
        session.save(match);
        transaction.commit();
    }

    @Override
    public List<Match> getMatchesByPlayerName() {
        return null;
    }
}
