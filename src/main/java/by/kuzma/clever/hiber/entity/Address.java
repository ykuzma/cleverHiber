package by.kuzma.clever.hiber.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Address {
    @Column(name = "post_index")
    private int postIndex;
    private String street;
    private String houseNumber;
    private String building;
    private String room;
}
