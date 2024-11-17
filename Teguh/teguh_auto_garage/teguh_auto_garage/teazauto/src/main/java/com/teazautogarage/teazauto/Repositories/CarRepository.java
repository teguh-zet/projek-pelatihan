package com.teazautogarage.teazauto.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.teazautogarage.teazauto.Model.Brand;
import com.teazautogarage.teazauto.Model.Car;

public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findByBrand(Brand brand);
    // List<Car> findAllByOrderByKmAsc();
    List<Car> findByStatue(String statue);
    List<Car> findByColorContainingIgnoreCase(String color);
    List<Car> findAllByOrderByPriceAsc(); 
    List<Car> findAllByOrderByPriceDesc();
    List<Car> findByModelContainingIgnoreCase(String model);
}
