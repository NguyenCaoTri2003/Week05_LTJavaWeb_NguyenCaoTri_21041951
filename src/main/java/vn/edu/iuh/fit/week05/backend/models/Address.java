package vn.edu.iuh.fit.week05.backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.edu.iuh.fit.week05.backend.enums.Country;
import com.neovisionaries.i18n.CountryCode;


@Getter
@Setter
@ToString
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String number;
    private String street;
    private String city;
    private CountryCode country;
    private String zipcode;

    public Address() {
    }


    public Address(String city, CountryCode country, String number, String street, String zipcode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.number = number;
        this.zipcode = zipcode;
    }
}
