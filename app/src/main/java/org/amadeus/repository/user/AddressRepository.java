package org.amadeus.repository.user;


import org.amadeus.entity.user.address.AddressEntity;
import org.amadeus.entity.user.info.UserInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity,Long> {

    AddressEntity findByUserInformation(UserInformationEntity entity);
}
