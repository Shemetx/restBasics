package com.epam.esm.dao;

import com.epam.esm.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


/**
 * The interface User dao.
 */
public interface UserDao extends JpaRepository<User,Integer> {
    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    Optional<User> findById(Integer id);

    Optional<User> findByUsername(String username);

    @Query(value = "select *\n" +
            "from (select e.id, sum(w.cost) maxCost\n" +
            "      from user e\n" +
            "               join user_order w on e.id = w.user_id\n" +
            "      group by e.id\n" +
            "     ) us\n" +
            "where us.maxCost = (select max(maxCost)\n" +
            "                    from (select sum(w.cost) maxCost\n" +
            "                          from user e\n" +
            "                                   join user_order w on e.id = w.user_id\n" +
            "                          group by e.id\n" +
            "                         ) u)",nativeQuery = true)
    Integer findUserIdWithMaxCost();
}
