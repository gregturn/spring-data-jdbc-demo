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

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.mapping.model.DefaultNamingStrategy;
import org.springframework.data.jdbc.mapping.model.JdbcPersistentProperty;
import org.springframework.data.jdbc.mapping.model.NamingStrategy;

/**
 * @author Greg Turnquist
 */
@Configuration
public class JdbcConfig {

	@Bean
	NamingStrategy namingStrategy() {

		Map<String, String> columnAliases = new HashMap<>();
		columnAliases.put("Manager.id", "Manager_id");

		Map<String, String> reverseColumnAliases = new HashMap<>();
		reverseColumnAliases.put("Employee", "manager_id");

		return new DefaultNamingStrategy() {

			@Override
			public String getColumnName(JdbcPersistentProperty property) {

				String defaultName = super.getColumnName(property);
				String key = getTableName(property.getOwner().getType()) + "." + defaultName;
				return columnAliases.getOrDefault(key, defaultName);
			}

			@Override
			public String getReverseColumnName(JdbcPersistentProperty property) {

				String defaultName = super.getReverseColumnName(property);
				return reverseColumnAliases.getOrDefault(defaultName, defaultName);
			}
		};
	}
}
