package io.cybertech.pd.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@Profile({"development"})
public class MockConfiguration {

	@Bean(name="mockRuns")
	public List<String> getMockRuns() {
		List<String> results = new ArrayList<>();
		results.add("1=1.5871! 3=2.4534\" 2=2.8777#");
		results.add("2=0.5871! 1=1.5534\" 3=2.9907#");
		results.add("3=0.5871! 1=1.5534\" 2=9.9999");
		results.add("2=0.5871! 1=2.3721\" 3=9.9999");
		results.add("2=1.0012! 1=1.5534\" 3=2.1007#");
		return results;
	}
}
