package org.roommanager.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.roommanager.model.RoomOccupancyRequest;
import org.roommanager.model.RoomOccupancyResponse;
import org.roommanager.service.RoomOccupancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoomOccupancyServiceTest {

	@Autowired
    private  RoomOccupancyService roomOccupancyService; 

    @Test
    void testOptimizeRoomOccupancy() {
        double[] guests = {23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209};
        
//        Test 1
//        ● (input) Free Premium rooms: 3
//        ● (input) Free Economy rooms: 3
//        ● (output) Usage Premium: 3 (EUR 738)
//        ● (output) Usage Economy: 3 (EUR 167.99)
        var request = RoomOccupancyRequest
		.builder()
		.freePremiumRooms(3)
		.freeEconomyRooms(3)
		.guests(guests)
		.build();

        RoomOccupancyResponse result = roomOccupancyService.optimizeRoomOccupancy(request);
        
        assertEquals(3, result.getUsagePremium());
        assertEquals(738, result.getPremiumRevenue(), 0.01);
        assertEquals(3, result.getUsageEconomy());
        assertEquals(167.99, result.getEconomyRevenue(), 0.01);

//        Test 2
//        ● (input) Free Premium rooms: 7
//        ● (input) Free Economy rooms: 5
//        ● (output) Usage Premium: 6 (EUR 1054)
//        ● (output) Usage Economy: 4 (EUR 189.99)
        request = RoomOccupancyRequest
		.builder()
		.freePremiumRooms(7)
		.freeEconomyRooms(5)
		.guests(guests)
		.build();
        
        result = roomOccupancyService.optimizeRoomOccupancy(request);
        
        assertEquals(6, result.getUsagePremium());
        assertEquals(1054, result.getPremiumRevenue(), 0.01);
        assertEquals(4, result.getUsageEconomy());
        assertEquals(189.99, result.getEconomyRevenue(), 0.01);

//        Test 3
//        ● (input) Free Premium rooms: 2
//        ● (input) Free Economy rooms: 7
//        ● (output) Usage Premium: 2 (EUR 583)
//        ● (output) Usage Economy: 4 (EUR 189.99)
        request = RoomOccupancyRequest
		.builder()
		.freePremiumRooms(2)
		.freeEconomyRooms(7)
		.guests(guests)
		.build();
        
        result = roomOccupancyService.optimizeRoomOccupancy(request);
        assertEquals(2, result.getUsagePremium());
        assertEquals(583, result.getPremiumRevenue(), 0.01);
        assertEquals(4, result.getUsageEconomy());
        assertEquals(189.99, result.getEconomyRevenue(), 0.01);

//        Test 4
//        ● (input) Free Premium rooms: 8
//        ● (input) Free Economy rooms: 2
//        ● (output) Usage Premium: 8 (EUR 1198.99)
//        ● (output) Usage Economy: 2 (EUR 45)
        request = RoomOccupancyRequest
		.builder()
		.freePremiumRooms(8)
		.freeEconomyRooms(2)
		.guests(guests)
		.build();
        
        result = roomOccupancyService.optimizeRoomOccupancy(request);
        assertEquals(8, result.getUsagePremium());
        assertEquals(1198.99, result.getPremiumRevenue(), 0.01);
        assertEquals(2, result.getUsageEconomy());
        assertEquals(45, result.getEconomyRevenue(), 0.01);
    }
}
