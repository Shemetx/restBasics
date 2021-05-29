package com.epam.esm.dao;

import com.epam.esm.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * The interface Tag dao.
 */
public interface TagDao extends JpaRepository<Tag, Integer> {

    Page<Tag> findAll(Pageable pageable);

    /**
     * Find by name.
     *
     * @param name the name
     * @return the optional
     */
    Optional<Tag> findByName(String name);

    /**
     * Find by id tag.
     *
     * @param id the id
     * @return the tag
     */
    Optional<Tag> findById(Integer id);

    /**
     * Find most used  certificate tag.
     *
     * @param userId the user id
     * @return the optional
     */
    @Query(value = "select *\n" +
            "from tag\n" +
            "where id = (select tag_id\n" +
            "            from certificates_tags gc\n" +
            "                     join order_items oi on gc.cert_id = oi.item_id\n" +
            "                     join user_order uo on oi.order_id = uo.id\n" +
            "            where uo.user_id = ?1\n" +
            "            group by tag_id\n" +
            "            order by count(*) desc\n" +
            "            limit 1);", nativeQuery = true)
    Optional<Tag> findMostUsed(Integer userId);
}
