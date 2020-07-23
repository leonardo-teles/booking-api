package com.boot.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.boot.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	Optional<Reservation> findById(Long id);
	
	Optional<Reservation> findByLocator(String locator);
	
	@Modifying
	@Transactional
	Optional<Reservation> deleteByLocator(String locator);
	
	Optional<Reservation> findByTurnAndRestaurantId(String turn, Long restaurantId);
}
