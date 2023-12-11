package org.example.respsitory;

import org.example.model.LinkDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LinkDetailRepository extends JpaRepository<LinkDetail, Long> {

}
