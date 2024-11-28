package by.kuzma.clever.hiber.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "cars")
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "model")
    private String model;
    @Column(name = "mark")
    private String mark;
    @Column(name = "year_of_production")
    private int yearOfProduction;
    @Column(name = "price")
    private double price;
    @ManyToOne
    @Column(name = "category_id")
    private Category category;


}
