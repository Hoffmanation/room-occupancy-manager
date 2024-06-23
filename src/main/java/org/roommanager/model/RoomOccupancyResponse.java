package org.roommanager.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomOccupancyResponse {

	int usagePremium;
	int usageEconomy;
	double premiumRevenue;
	double economyRevenue;
	String message ;
}
