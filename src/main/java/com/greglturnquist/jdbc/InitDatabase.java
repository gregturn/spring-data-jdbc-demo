/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.jdbc;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author Greg Turnquist
 * @author Thomas Risberg
 */
@Configuration
public class InitDatabase {

	@Autowired
	Environment environment;

	@Bean
	CommandLineRunner loadUsers(EmployeeRepository employeeRepository) {
		return args -> {

			List<String> profiles = Arrays.asList(environment.getActiveProfiles());
			// only add sample data for local app
			if (!profiles.contains("cloud") && !profiles.contains("kubernetes")) {
				Manager gandalf = new Manager("Gandalf");
				employeeRepository.save(new Employee("Frodo", "Baggins", "ring bearer", gandalf));

				Manager saruman = new Manager("Saruman");
				employeeRepository.save(new Employee("Bilbo", "Baggins", "burglar", saruman));
			}
		};
	}
}
