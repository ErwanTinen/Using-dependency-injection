package com.emse.spring.faircorp.dao;


import com.emse.spring.faircorp.model.Room;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RoomDaoTest {
    @Autowired
    private RoomDao roomDao;

    //Test if a room is found
    @Test
    public void shouldFindARoom() {
        Room room = roomDao.getOne(-10L);
        Assertions.assertThat(room.getName()).isEqualTo("Room1");
        Assertions.assertThat(room.getCurrentTemperature()).isEqualTo(22.3);
    }
    //Test if a roomcan be found by name
    @Test
    public void shouldFindRoomByName() {
        List<Room> result = roomDao.findByName("Room1");
        Assertions.assertThat(result)
                .hasSize(1)
                .extracting("name");
    }
}
