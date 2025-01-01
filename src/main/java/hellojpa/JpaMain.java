package hellojpa;

import jakarta.persistence.*;
import jakarta.persistence.EntityManager;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); // 쓰레드간 공유 x

        EntityTransaction ts = em.getTransaction(); // transaction  jpa 데이터변경은
        ts.begin();
        // process
        try {
            // 비영속
            Member member = new Member();
            member.setId(51L);
            member.setName("chat");

            // 영속
            System.out.println("-----Before-----");
            em.persist(member);
            System.out.println("-----After-----");

            // 조회를 할떄 1차 cache에 저장
            Member member1 = em.find(Member.class, 51L);
            System.out.println("member1.getId() = " + member1.getId());
            System.out.println("member1 = " + member1.getName());


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
