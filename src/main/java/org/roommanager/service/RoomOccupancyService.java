package org.roommanager.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.roommanager.model.RoomOccupancyRequest;
import org.roommanager.model.RoomOccupancyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service class for optimizing room occupancy for the  room-occupancy-manager app.
 * Allocates guests to premium and economy rooms based on their willingness to pay.
 * @author Hoffman
 */

@Service
public class RoomOccupancyService {
    private static final Logger logger = LoggerFactory.getLogger(RoomOccupancyService.class);
    private final String RESPONSE_MESSAGE = "Usage Premium: %d, Premium Revenue: %.2f Euro | Usage Economy: %d, Economy Revenue %.2f Euro" ;

    /**
     * Optimizes room occupancy based on the given request.
     *
     * @param {@link RoomOccupancyRequest} The request containing the number of free premium and economy rooms and the guests' willingness to pay.
     * @return {@link RoomOccupancyResponse} A response containing the usage and revenue of premium and economy rooms.
     */
    public RoomOccupancyResponse optimizeRoomOccupancy(RoomOccupancyRequest roomOccupancyReq) {
    	logger.info("optimizeRoomOccupancy start");

    	//Extract request parameters
    	int freePremiumRooms = roomOccupancyReq.getFreePremiumRooms() ;
    	int freeEconomyRooms = roomOccupancyReq.getFreeEconomyRooms() ;
    	double[] guests = roomOccupancyReq.getGuests() ;
    	
    	//Initialize data structure helpers
    	List<Double>  economyGuests = new ArrayList<Double>() ;
    	List<Double>  guestsWithoutRooms = new ArrayList<Double>() ;
        
        int usagePremium = 0;
        int usageEconomy = 0;
        double premiumRevenue = 0.0;
        double economyRevenue = 0.0;
         
         //First sort guests from high to low before iteration
         var sortedGuests = Arrays.stream(guests)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
         
         logger.info(String.format("Sorted Guests List: %s", sortedGuests));

        for (double guest : sortedGuests) {

            // Assign guest to Premium room
            if (guest >= 100 && usagePremium < freePremiumRooms) {
                usagePremium++;
                premiumRevenue += guest;
            }

            // Assign guest to Economy room
            else if (guest < 100 && usageEconomy < freeEconomyRooms) {
            	economyGuests.add(guest) ;
                usageEconomy++;
                economyRevenue += guest;
            }
            
            else {
            	guestsWithoutRooms.add(guest) ;
            }
        }

        // Upgrade economy guests to premium rooms if there are free premium rooms left
        if (usagePremium < freePremiumRooms &&  usageEconomy >= freeEconomyRooms) { 
        	
        	System.err.println(String.format(
        									"{premiumUsage: %d, premiumRevenue: %f},\n"
        								+ "{economyUsage: %d, economyRevenue: %f}", 
        									usagePremium, premiumRevenue, usageEconomy, economyRevenue));
            
        	int availablePremiumRooms =  freePremiumRooms  - usagePremium;
        	
        	//llop through the number of available room upgrade and relocate them.
        	for (int i = 0; i <availablePremiumRooms; i++) {
               
        		double guestToUpgrate = economyGuests.get(i);
        		double guestToDowngrade = guestsWithoutRooms.get(i);
        		
        		if (usagePremium < freePremiumRooms ) {
        			
        			usagePremium++;
        			premiumRevenue += guestToUpgrate;
                  
        			economyRevenue -= guestToUpgrate;
                    economyRevenue += guestToDowngrade;
        		}
        	} ;
        }

    	logger.info("optimizeRoomOccupancy finish");
        return RoomOccupancyResponse
        		.builder()
        		.economyRevenue(economyRevenue)
        		.usageEconomy(usageEconomy)
        		.premiumRevenue(premiumRevenue)
        		.usagePremium(usagePremium)
                .message(String.format(RESPONSE_MESSAGE, usagePremium, premiumRevenue, usageEconomy, economyRevenue))
        		.build();
    }
}
