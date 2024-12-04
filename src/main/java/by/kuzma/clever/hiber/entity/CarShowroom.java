package by.kuzma.clever.hiber.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.NamedEntityGraphs;
import jakarta.persistence.NamedSubgraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "car_showroom")
@Getter
@Setter
@ToString
@NamedEntityGraphs({

        @NamedEntityGraph(
                name = "CarShowroom.withCarAndCategory",
                attributeNodes = {
                        @NamedAttributeNode(value = "cars", subgraph = "carsWithCategory")
                },
                subgraphs = @NamedSubgraph(
                        name = "carsWithCategory",
                        attributeNodes = @NamedAttributeNode("category")
                )
        )
})
public class CarShowroom {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Embedded
    private Address address;


    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "showroom_id")
    private List<Car> cars = new ArrayList<>();

    public void addCar (Car car) {
        getCars().add(car);
    }
    public void removeCar (Car car){
        getCars().remove(car);
    }
}
