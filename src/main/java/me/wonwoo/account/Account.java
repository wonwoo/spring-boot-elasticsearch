package me.wonwoo.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by wonwoo on 2016. 4. 25..
 */
@Document(indexName = "account", type = "account", shards = 1, replicas = 0, refreshInterval = "-1")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

  @Id
  private String id;

  private String name;

  private String email;

  public Account(String name, String email) {
    this.name = name;
    this.email = email;
  }
}
