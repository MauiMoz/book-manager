package org.example.bookmanager.backend.model;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Objects;


@Embeddable
public class Address {

    @NotBlank(message = "Nation is required")
    @Pattern(regexp = "^$|[A-Z]{2}", message = "Nation must be an Alpha-2 ISO code (e.g. AT, DE, IT)")
    private String nation;

    @NotBlank(message = "City is required")
    @Pattern(regexp = "^$|[a-zA-Z\\s\\-.']+", message = "City must contain only letters")
    private String city;

    @NotBlank(message = "Street is required")
    @Pattern(regexp = "^$|[a-zA-Z\\s\\-']+", message = "Street must contain only letters")
    private String street;

    @NotBlank(message = "Number is required")
    @Pattern(regexp = "^$|\\d+[a-zA-Z]?", message = "Number must contain at least one digit")
    private String number;

    @NotBlank(message = "Postal code is required")
    @Pattern(regexp = "^$|\\d{4,10}", message = "Postal code must be between 4 and10 digits long")
    private String postalCode;

    public Address() {}

    public Address(String nation, String city, String street, String number, String postalCode) {
        this.nation = nation;
        this.city = city;
        this.street = street;
        this.number = number;
        this.postalCode = postalCode;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation != null ? nation.toUpperCase() : null;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(nation, address.nation) && Objects.equals(city, address.city) &&
                Objects.equals(street, address.street) && Objects.equals(number, address.number) &&
                Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nation, city, street, number, postalCode);
    }
}
