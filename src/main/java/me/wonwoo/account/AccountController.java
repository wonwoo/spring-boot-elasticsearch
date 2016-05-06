package me.wonwoo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wonwoo on 2016. 4. 25..
 */
@RestController
public class AccountController {

  @Autowired
  private AccountRepository accountRepository;

  @GetMapping
  public List<Account> accounts(){
    return (List<Account>) accountRepository.findAll();
  }

  @GetMapping("/{name}")
  public List<Account> account(@PathVariable String name){
    return accountRepository.findByname(name);
  }
}
