package hellojpa;

import jakarta.persistence.*;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Map;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); // 쓰레드간 공유 x

        EntityTransaction ts = em.getTransaction(); // transaction  jpa 데이터변경은
        ts.begin();
        // process
        try {
            Member member1 = new Member();
            member1.setUserName("a");

            Member member2 = new Member();
            member2.setUserName("b");

            Member member3 = new Member();
            member3.setUserName("c");
            System.out.println("--------------------------------");
            em.persist(member1);
            em.persist(member2);
            em.persist(member3);

            System.out.println(member1.getId());
            System.out.println(member2.getId());
            System.out.println(member3.getId());
            System.out.println("--------------------------------");

            ts.commit();

        } catch (Exception e){
            System.out.println("exception");
            System.out.println(e);
            ts.rollback();;

        } finally {
            em.close();
        }
        emf.close();

    }
}
