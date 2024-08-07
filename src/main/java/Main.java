import persistence.entity.Match;
import persistence.entity.Player;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistence.repository.IMatchRepository;
import persistence.repository.MatchRepository;
import util.HibernateUtil;
import util.MatchesBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(14 / 5);
        System.out.println(divide(16, 5));
    }
    private static int divide(int a, int b){
        return  (a + (b - 1) ) / b;
    }
}

