package org.roommanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Java config for Springfox swagger documentation plugin
 *
 * @author Hoffman
 */
@Configuration
public class SwaggerConfig {

	@Bean
	OpenAPI customOpenAPI() {

		return new OpenAPI().components(
				new Components())
				.info(new Info()
					.title("Spring Boot Room Occupancy Manager sample application").version("1.0")
					.termsOfService("Room Occupancy Manager Service")
					.description(
							"This is REST API documentation of the Spring Boot Room Occupancy Manage backend.")
					.license(swaggerLicense()).contact(swaggerContact()));
	}

	private Contact swaggerContact() {
		Contact rommManagerContact = new Contact();
		rommManagerContact.setName("Oren Hoffman");
		rommManagerContact.setEmail("orenhoffman1777@gmail.com");
		rommManagerContact.setUrl("https://github.com/Hoffmanation/room-occupancy-manager");
		return rommManagerContact;
	}

	private License swaggerLicense() {
		License roomManagerLicense = new License();
		roomManagerLicense.setName("Apache 2.0");
		roomManagerLicense.setUrl("http://www.apache.org/licenses/LICENSE-2.0");
		roomManagerLicense.setExtensions(Collections.emptyMap());
		return roomManagerLicense;
	}

}
