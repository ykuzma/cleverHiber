package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.entity.Car;
import by.kuzma.clever.hiber.entity.CarShowroom;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarShowroomRepository extends JpaRepository<CarShowroom, UUID> {

    @Override
    @Query("select cs from CarShowroom cs left join fetch cs.cars where cs.id= :uuid")
    Optional<CarShowroom> findById(UUID uuid);


    @Override
    @EntityGraph(value = "CarShowroom.withCarAndCategory")
    List<CarShowroom> findAll();
}
