package fr.uge.jee.hibernate.students;

import jakarta.persistence.*;

@Entity
@Table(name = "Address")
public class Address {
    @Id
    @GeneratedValue
    @Column(name="ADDRESSID")
    private long id;
    @Column(name = "STREETNAME")
    private String streetName;
    @Column(name = "CITYNAME")
    private String cityName;
    @Column(name = "COUNTRYNAME")
    private String countryName;

    public Address() {}
    public Address(String streetName, String cityName, String countryName) {
        this.streetName = streetName;
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
