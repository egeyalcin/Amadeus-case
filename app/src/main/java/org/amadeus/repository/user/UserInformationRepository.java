package org.amadeus.repository.user;

import org.amadeus.entity.user.info.UserInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformationEntity,Long> {

    Optional<UserInformationEntity> findByUserEmail(String email);

}
