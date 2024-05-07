package model;

import java.util.List;

public interface CityDAO {



    public City findCityById(int id);
    public City findCityByName(String name);
    public List <City> findCityByCode(String code);
    public List<City> findAll();
    City addCity(City city);
    City updateCity(City city);
    int deleteCity(City city);



}
