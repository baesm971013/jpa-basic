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
            Member member = new Member();
            member.setId(1L);
            member.setName("sumin");
            member.setRoleType(RoleType.ADMIN);
            em.persist(member);
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
