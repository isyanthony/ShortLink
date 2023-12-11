package org.example.repository;

import jakarta.annotation.Resource;
import java.util.List;
import org.example.model.LinkToShort;
import org.example.respsitory.LinkToShortRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;


/**
 * learn how to use jpa
 */
@SpringBootTest
public class TestLinkToShortRepository {

    @Resource
    LinkToShortRepository repository;

    /**
     * save of update
     */
    @Test
    public void save() {
        LinkToShort avc = LinkToShort.builder()
          .linkMd5("12312312312adawdaw3")
          .shortUrl("avccccccccc")
          .build();

        // Hibernate: select next_val as id_val from link_to_short_seq for update
        // Hibernate: update link_to_short_seq set next_val= ? where next_val=?
        // Hibernate: insert into link_to_short (link_md5,short_url,id) values (?,?,?)
        LinkToShort save = repository.save(avc);
        System.out.println(save);
    }


    /**
     * match any of no-null field
     */
    @Test
    public void queryMatchingAny() {
        LinkToShort build = LinkToShort.builder()
          .id(1L)
          .linkMd5("123123123123")
          .build();

        // select id,link_md5,short_url from link_to_short  where link_md5=? or id=?
        List<LinkToShort> all = repository.findAll(Example.of(build, ExampleMatcher.matchingAny()));
        System.out.println(all);
    }


    /**
     * match all no-null field
     */
    @Test
    public void queryMatchingAll() {
        LinkToShort build = LinkToShort.builder()
          .id(1L)
          .linkMd5("123123123123")
          .build();

        // select id,link_md5,short_url from link_to_short  where link_md5=? and id=?
        List<LinkToShort> all = repository.findAll(Example.of(build, ExampleMatcher.matchingAll()));
        System.out.println(all);
    }


    /**
     * ignore one of fields and cannot be empty
     */
    @Test
    public void queryMatchingAllWithIgnore() {
        LinkToShort build = LinkToShort.builder()
          .id(1L)
          .linkMd5("123123123123")
          .build();

        // select id,link_md5,short_url from link_to_short  where link_md5=?
        List<LinkToShort> all = repository.findAll(Example.of(build, ExampleMatcher.matchingAll().withIgnorePaths("id")));
        System.out.println(all);
    }

    /**
     * withMatcher can modify match case
     */
    @Test
    public void queryMatchingAllWithMatcher() {
        LinkToShort build = LinkToShort.builder()
          .id(1L)
          .linkMd5("123123123123")
          .build();

        // select id,link_md5,short_url from link_to_short  where link_md5=?
        // StringMatcher
        // DEFAULT -> =
        // EXACT -> =
        // STARTING -> ?%
        // ENDING -> ?%
        // CONTAINING -> %?%
        // REGEX ->
        List<LinkToShort> all = repository.findAll(Example.of(build, ExampleMatcher.matchingAll().withMatcher("linkMd5", GenericPropertyMatcher.of(
          StringMatcher.CONTAINING))));
        System.out.println(all);
    }

    /**
     * withMatcher can modify match case
     */
    @Test
    public void Test() {
        LinkToShort build = LinkToShort.builder()
          .id(1L)
          .linkMd5("123123123123")
          .build();

        LinkToShort topByLinkMd5AfterAnd = repository.findTopByLinkMd5EndsWithIgnoreCase(build.getLinkMd5());
    }
}
