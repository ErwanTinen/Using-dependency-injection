package com.emse.spring.faircorp.dao;

import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public class HeaterDaoCustomImpl implements HeaterDaoCustom {

    @PersistenceContext
    private EntityManager em;


    @Transactional
    @Modifying
    @Override
    public List<Heater> deleteAllHeatersInRoom(Long id){
        String jpql = "delete from Heater h where h.room.id = :id";
        em.createQuery(jpql).setParameter("id", id).executeUpdate();
        String jpql1 = " select h from Heater h where h.room.id = :id";
        return em.createQuery(jpql1, Heater.class)
                .setParameter("id", id)
                .getResultList();

    }
}
