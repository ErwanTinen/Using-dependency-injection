package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.model.Room;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController // (1)
@RequestMapping("/api/rooms") // (2)
@Transactional // (3)
public class RoomController {

    private final RoomDao roomDao;
    private final HeaterDao heaterDao;
    private final WindowDao windowDao;

    public RoomController(RoomDao roomDao, HeaterDao heaterDao, WindowDao windowDao) { // (4)
        this.roomDao = roomDao;
        this.heaterDao = heaterDao;
        this.windowDao = windowDao;
    }

    @GetMapping // (5)
    public List<RoomDto> findAll() {
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());  // (6)
    }

    @GetMapping(path = "/{id}")
    public RoomDto findById(@PathVariable Long id) {
        return roomDao.findById(id).map(RoomDto::new).orElse(null); // (7)
    }

    @PostMapping // (8)
    public RoomDto create(@RequestBody RoomDto dto) {
        // WindowDto must always contain the window room
        Room room = null;
        // On creation id is not defined
        if (dto.getId() == null) {
            room = roomDao.save(new Room(dto.getName(), dto.getFloor()));
        }
        else {
            room = roomDao.getOne(dto.getId());  // (9)
            room.setFloor(dto.getFloor());
        }
        return new RoomDto(room);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteHeaters(@PathVariable Long id) {
        heaterDao.deleteAllHeatersInRoom(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteWindows(@PathVariable Long id) {
        windowDao.deleteAllWindowsInRoom(id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomDao.deleteById(id);
    }

    @PutMapping(path = "/{id}/switchWindow")
    public WindowDto switchStatus(@PathVariable Long id) {
        Room room = roomDao.findById(id).orElseThrow(IllegalArgumentException::new);
        Set<Window> windows = room.getWindows();
        for(Window window : windows) {
            window.setWindowStatus(window.getWindowStatus() == WindowStatus.OPEN ? WindowStatus.CLOSED : WindowStatus.OPEN);
        }
        return new WindowDto((Window) room.getWindows());
    }

}
