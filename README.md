# Room Occupancy Manager REST API

##Overview
The Room Occupancy Manager REST API provides an endpoint for optimizing room occupancy based on the number of available rooms and guests' willingness to pay. This API is designed to help manage and optimize the use of premium and economy rooms to maximize revenue.

#Endpoints

### Optimize Room Occupancy
- URL: /api/room-occupancy-manager/optimize
- Method: POST
- Description: This endpoint optimizes room occupancy based on the given request. It calculates the optimal usage and revenue for premium and economy rooms.

#####Request Body:
The request body should be a JSON object representing a RoomOccupancyRequest. This object contains the following fields:

		-freePremiumRooms: The number of free premium rooms.
		-freeEconomyRooms: The number of free economy rooms.
		-guests: A list of doubles representing the guests' willingness to pay.
	
##### Example:

	{
	  "freePremiumRooms": 8,
	  "freeEconomyRooms": 2,
	  "guests": [
	  23, 45, 155, 374, 22, 99.99, 100, 101, 115, 209
	  ]
	}
	
#####Response Body:
The response body will be a JSON object representing a RoomOccupancyResponse. This object contains the following fields:

	-usagePremium: The number of premium rooms used.
	-usageEconomy: The number of economy rooms used.
	-premiumRevenue: The revenue generated from premium rooms.
	-economyRevenue: The revenue generated from economy rooms.
	-message: contaning a textual representation of all values

##### Example:

	{
	  "usagePremium": 8,
	  "usageEconomy": 2,
	  "premiumRevenue": 1198.99,
	  "economyRevenue": 45.000000000000014,
	  "message": "Usage Premium: 8, Premium Revenue: 1198.99 Euro | Usage Economy: 2, Economy Revenue 45.00 Euro"
	}

## Running Up Environment Locally (via docker)
- From CMD or GitBash when on root directory: $./start.sh

## Access OpenAPI Spec
- Access Swagger page by http://localhost:8080/room-occupancy-manager/swagger-ui.html
