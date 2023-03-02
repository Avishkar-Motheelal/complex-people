package com.example.complexpeople.repository;

import com.example.complexpeople.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //    @Query(value = """
//            SELECT u.* FROM Users u
//            INNER JOIN People p on u.PeopleId = p.PeopleId
//            INNER JOIN ContactDetails cd on p.ContactDetailsId = cd.ContactDetailsId
//            WHERE cd.EmailAddress = ?1
//        """, nativeQuery = true)
    Optional<User> findByEmail(String email);

    Optional<User> findByUserId(long id);

    Boolean existsByEmail(String email);
}
