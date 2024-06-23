package org.roommanager.rest.controller;


import org.roommanager.model.RoomOccupancyRequest;
import org.roommanager.model.RoomOccupancyResponse;
import org.roommanager.service.RoomOccupancyService;
import org.roommanager.util.RoomOccupancyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing room occupancy optimization.
 * @author Hoffman
 */
@RestController
@RequestMapping("/api/room-occupancy-manager")
public class RoomOccupancyController {
    private static final Logger logger = LoggerFactory.getLogger(RoomOccupancyController.class);

    @Autowired
    private RoomOccupancyService roomOccupancyService;
    @Autowired
    private RoomOccupancyUtil appUtil;
    
    
    /**
     * Optimizes room occupancy based on the given request.
     *
     * @param {@link RoomOccupancyRequest} The request containing the number of free premium and economy rooms and the guests' willingness to pay.
     * @return {@link RoomOccupancyResponse} A response containing the usage and revenue of premium and economy rooms.
     */
    @PostMapping("/optimize")
    public RoomOccupancyResponse optimize(@RequestBody RoomOccupancyRequest roomOccupancy) {
        logger.info(String.format("/optimize start, RoomOccupancyRequest: %s", appUtil.toJson(roomOccupancy)));
        RoomOccupancyResponse result = roomOccupancyService.optimizeRoomOccupancy(roomOccupancy);

        logger.info(String.format("/optimize finish, RoomOccupancyResponse: %s", appUtil.toJson(result)));
        return result ;
    }
}
