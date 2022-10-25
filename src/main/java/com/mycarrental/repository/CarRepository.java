package com.mycarrental.repository;


import com.mycarrental.domain.Car;
import com.mycarrental.dto.CarDTO;
import com.mycarrental.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<Car,Long> {


    @Query("SELECT new com.mycarrental.dto.CarDTO(car) FROM Car car")
    List<CarDTO> findAllCar();

    Optional<CarDTO> findByIdOrderById(Long id) throws ResourceNotFoundException;

}

