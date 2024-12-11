package by.kuzma.clever.hiber.repository;

import by.kuzma.clever.hiber.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

}
