package org.example.respsitory;

import org.example.model.LinkToShort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LinkToShortRepository extends JpaRepository<LinkToShort, Long> {

    LinkToShort findTopByLinkMd5EndsWithIgnoreCase(String linkMd5);

//    default LinkToShort findByIdOrderByIdAsc(Long id, int limit) {
//        System.out.println("test");
//        return null;
//    }

}
