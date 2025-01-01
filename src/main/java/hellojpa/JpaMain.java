package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction ts = entityManager.getTransaction();
        ts.begin();
        // process
        Member member = new Member();
        member.setId(1L);
        member.setName("sumin");
        entityManager.persist(member);

        ts.commit();
        //
        entityManager.close();
        emf.close();

    }
}
