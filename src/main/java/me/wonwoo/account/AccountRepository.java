package me.wonwoo.account;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by wonwoo on 2016. 4. 25..
 */
public interface AccountRepository extends ElasticsearchRepository<Account, Long> {

  List<Account> findByname(String name);

  Account findByemail(String email);
}
