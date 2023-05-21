package com.asociatialocatari.gal.base.repositories;

//import com.asociatialocatari.gal.base.models.User;
import com.asociatialocatari.gal.base.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import java.util.Set;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    //Set<UserRole> findRolesByUser(User user);
    // @Query("SELECT orc FROM OfferCompatibilityRules orc " +
    //         "WHERE orc.offer = ?1 AND orc.subjProductId = ?2 AND orc.objProductId = ?3")
    //Set<UserRole> findUserRolesByUser(User user);
}
