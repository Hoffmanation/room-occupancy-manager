package org.roommanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomOccupancyRequest {
    private int freePremiumRooms;
    private int freeEconomyRooms;
    private double[] guests;
}