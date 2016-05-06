package me.wonwoo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wonwoo on 2016. 5. 5..
 */
@Service
public class AccountService {

  @Autowired
  private AccountRepository accountRepository;

  public Account save(Account account){
    return accountRepository.save(account);
  }
}
