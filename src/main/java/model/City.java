package model;

import java.util.Objects;

public class City {

    private int city_Id;
    private String name;
    private String countryCode;
    private String district;
    private int population;


    public City(int id, String name, String countryCode, String district, int population) {
        this.city_Id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }

    public City( String name, String countryCode, String district, int population) {
        this.city_Id = city_Id;
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;
    }

    public int getId() {
        return city_Id;

    }

    public String getName() {
        return name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public int getPopulation() {
        return population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = ( City ) o;
        return city_Id == city.city_Id && getPopulation() == city.getPopulation() && Objects.equals(getName(), city.getName()) && Objects.equals(getCountryCode(), city.getCountryCode()) && Objects.equals(getDistrict(), city.getDistrict());
    }

    @Override
    public int hashCode() {
        return Objects.hash(city_Id, getName(), getCountryCode(), getDistrict(), getPopulation());
    }

    @Override
    public String toString() {
        return "City{" +
                "city_Id=" + city_Id +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }
}
