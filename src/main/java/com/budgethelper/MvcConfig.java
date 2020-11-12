/* ICS4U Software Development Project
 * 
 * Configures Spring MVC in the application and set up view controllers to expose the
 * login and logout pages.
 *
 * Author Kaitlyn Song November 13, 2020
 */

package com.budgethelper;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/logout").setViewName("logout");
	}

}
