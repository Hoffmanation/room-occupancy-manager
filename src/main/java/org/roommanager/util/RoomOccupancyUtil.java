package org.roommanager.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RoomOccupancyUtil {
    private static final Logger logger = LoggerFactory.getLogger(RoomOccupancyUtil.class);
	
	@	Autowired
    private ObjectMapper objectMapper ;

    /**
     * Converts a Java object to a JSON string.
     *
     * @param object The object to convert to JSON.
     * @return The JSON string representation of the object.
     * @throws JsonProcessingException If there is an error during conversion.
     */
    public  String toJson(Object object)  {
        try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			logger.error("Failed to convert object to JSON" , e) ;
			if (object!=null) {
				return object.toString();
			}
			throw new IllegalArgumentException("Object cannot be null") ;
		}
    }

}
