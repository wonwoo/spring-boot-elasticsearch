package me.wonwoo;

import me.wonwoo.account.Account;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.stream.Collectors.*;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Created by wonwoo on 2016. 5. 6..
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchTemplateTest {

  @Autowired
  private ElasticsearchTemplate elasticsearchTemplate;

  @Test
  public void getAccount() {
    GetQuery getQuery = new GetQuery();
    getQuery.setId("AVSAOJvzW3yAedZaYx2w");
    Account account = elasticsearchTemplate.queryForObject(getQuery, Account.class);
    System.out.println(account);
  }

  @Test
  public void getAccountTemplateIndexAndTypeTest() {
    SearchQuery searchQuery = new NativeSearchQueryBuilder()
      .withQuery(matchAllQuery())
      .withIndices("account")
      .withTypes("account")
      .build();
    List<Account> accounts = elasticsearchTemplate.queryForList(searchQuery, Account.class);
    System.out.println(accounts);
  }

  @Test
  public void getAccountTemplateCountTest() {
    SearchQuery searchQuery = new NativeSearchQueryBuilder()
      .withQuery(matchAllQuery())
      .withIndices("account")
      .withTypes("account")
      .build();
    long count = elasticsearchTemplate.count(searchQuery, Account.class);
    System.out.println(count);
  }

  @Test
  public void getAccountTemplateIndexAndTypeFieldsTest() {
    SearchQuery searchQuery = new NativeSearchQueryBuilder()
      .withQuery(matchAllQuery())
      .withIndices("account")
      .withTypes("account")
      .withFields("name")
      .build();
    List<Account> accounts = elasticsearchTemplate.queryForList(searchQuery, Account.class);
    System.out.println(accounts);
  }

  @Test
  public void getAccountTemplatePage() {
    SearchQuery searchQuery = new NativeSearchQueryBuilder().withIndices("account")
      .withTypes("account").withQuery(matchAllQuery())
      .withSort(new FieldSortBuilder("name").order(SortOrder.DESC))
      .withPageable(new PageRequest(0, 5)).build();
    Page<Account> accounts = elasticsearchTemplate.queryForPage(searchQuery, Account.class);

    String str = accounts.getContent().stream()
      .map(i -> i.toString())
      .collect(joining("\n"));
    System.out.println(str);
    System.out.println(accounts.getContent().size());
  }

  @Test
  public void getAccountTemplateSearchTest() {

    SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQuery("name", "wonwoo")).build();
//    SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(matchAllQuery())
//      .withFilter(boolQuery().filter(termQuery("name", "wonwoo"))).build();
    List<Account> accounts = elasticsearchTemplate.queryForList(searchQuery, Account.class);

    String str = accounts.stream()
      .map(i -> i.toString())
      .collect(joining("\n"));
    System.out.println(str);
    System.out.println(accounts.size());
  }

  @Test
  public void getAccountTemplateSearchWildcardQueryTest() {

    SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(wildcardQuery("name", "*wonwoo*")).build();

    List<Account> accounts = elasticsearchTemplate.queryForList(searchQuery, Account.class);
    String str = accounts.stream()
      .map(i -> i.toString())
      .collect(joining("\n"));
    System.out.println(str);
    System.out.println(accounts.size());
  }

  @Test
  public void getAccountTemplateSearchQueryStringQueryTest() {

    SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryStringQuery("*wonwoo*")).build();

    List<Account> accounts = elasticsearchTemplate.queryForList(searchQuery, Account.class);
    String str = accounts.stream()
      .map(i -> i.toString())
      .collect(joining("\n"));
    System.out.println(str);
    System.out.println(accounts.size());
  }

  @Test
  public void saveTemplateTest() {
    Account account = new Account("kk", "kk@test.com");
    IndexQuery indexQuery = new IndexQuery();
    indexQuery.setObject(account);
    String index = elasticsearchTemplate.index(indexQuery);
    System.out.println(index);
  }

  @Test
  public void updateTemplateTest() {
    IndexRequest indexRequest = new IndexRequest();
    indexRequest.source("name", "wowowowo");
    UpdateQuery updateQuery = new UpdateQueryBuilder().withId("AVSAOJvzW3yAedZaYx2w")
      .withClass(Account.class).withIndexRequest(indexRequest).build();
    UpdateResponse update = elasticsearchTemplate.update(updateQuery);
    System.out.println(update);
  }

  @Test
  public void deleteTemplateTest() {
    String delete = elasticsearchTemplate.delete(Account.class, "AVSEzpSE4HLWkOEZq-DT");
    System.out.println(delete);
  }
}
