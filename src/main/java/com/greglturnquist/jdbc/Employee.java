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

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

/**
 * @author Greg Turnquist
 */
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Employee {

	@Id
	private Long id;
	private String firstName;
	private String lastName;
	private String role;

	Employee(String firstName, String lastName, String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}
}
