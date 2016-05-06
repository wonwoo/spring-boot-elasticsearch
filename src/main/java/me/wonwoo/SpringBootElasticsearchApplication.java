package me.wonwoo;

import me.wonwoo.account.Account;
import me.wonwoo.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootElasticsearchApplication.class, args);
	}

//	@Autowired
//	private AccountRepository accountRepository;
//
//	@Bean
//	public CommandLineRunner commandLineRunner(){
//		return args ->
//			Arrays.asList(
//				new Account("wonwoo","wonwoo@test.com"),
//				new Account("kevin","kevin@test.com")
//			).forEach(accountRepository::save);
//	}
}
