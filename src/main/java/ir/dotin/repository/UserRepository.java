package ir.dotin.repository;

import ir.dotin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE User user SET user.isActive = 0 WHERE user.id = :userId")
    void deactivateUser(@Param("userId") Long userId);
}
