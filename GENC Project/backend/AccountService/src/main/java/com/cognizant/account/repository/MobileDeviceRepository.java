package com.cognizant.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.account.model.MobileDevice;

@Repository
public interface MobileDeviceRepository extends JpaRepository<MobileDevice,String> {

	@Query(value = "SELECT mobileno FROM devices u WHERE accountno = ?1", 
			  nativeQuery = true)
	List<String> findAllByAccountNo(String accountNo);

}
