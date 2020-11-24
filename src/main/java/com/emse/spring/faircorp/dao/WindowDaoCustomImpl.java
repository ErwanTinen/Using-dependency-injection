package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class WindowDaoCustomImpl implements WindowDaoCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Window> findRoomOpenWindows(Long id) {
        String jpql = "select w from Window w where w.room.id = :id and w.windowStatus= :status";
        return em.createQuery(jpql, Window.class)
                .setParameter("id", id)
                .setParameter("status", WindowStatus.OPEN)
                .getResultList();
    }

    @Transactional
    @Modifying
    @Override
    public List<Window> deleteAllWindowsInRoom(Long id){
        String jpql = "delete from Window w where w.room.id = :id";
        em.createQuery(jpql).setParameter("id", id).executeUpdate();
        String jpql1 = " select w from Window w where w.room.id = :id";
        return em.createQuery(jpql1, Window.class)
                .setParameter("id", id)
                .getResultList();

    }
}
