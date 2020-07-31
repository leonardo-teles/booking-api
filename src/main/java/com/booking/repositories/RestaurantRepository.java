package com.booking.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.booking.entities.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	Optional<Restaurant> findById(Long id);
	
	Optional<Restaurant> findByName(String name);
	
	@Query("SELECT r FROM Restaurant r")
	public List<Restaurant> findRestaurants();
}
