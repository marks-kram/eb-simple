package de.test.beanstalk.simple.repository;

import de.test.beanstalk.simple.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {


    Optional<Item> findByUuid(String uuid);
    boolean existsByUuid(String uuid);

    @Transactional
    void deleteByUuid(String uuid);
}
