package com.snapp.expense_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EntityScan(basePackages = {
        "com.snapp.expense_tracker.*"
})
@EnableAsync
public class ExpenseTrackerApplication {

	static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApplication.class, args);
	}

}
