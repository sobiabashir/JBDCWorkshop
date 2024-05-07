package model;

import db.MySQLConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CityDAOJDBCImpl implements CityDAO {

private static final String FindById="select * from city where id =?";
private static final String ADD_CITY = "INSERT INTO city (Name,CountryCode,District,Population) VALUES(?,?,?,?)";
private static final String UPDATE_CITY = "UPDATE city SET Name = ?, CountryCode = ?, District = ?, Population = ? WHERE ID = ?";

   // private static final String FindByCode = "select * from city where countrycode =?";
   private City resultSetToCity(ResultSet resultSet) throws SQLException {
       return new City(
               resultSet.getInt("ID"),
               resultSet.getString("Name"),
               resultSet.getString("CountryCode"),
               resultSet.getString("District"),
               resultSet.getInt("Population")
       );
   }
    private PreparedStatement createAddCity(Connection connection, City newCity) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(ADD_CITY, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1,newCity.getName());
        statement.setString(2,newCity.getCountryCode());
        statement.setString(3,newCity.getDistrict());
        statement.setInt(4,newCity.getPopulation());
        statement.executeUpdate();
        return statement;
    }
    private PreparedStatement createUpdateCity(Connection connection, City city) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(UPDATE_CITY);
        statement.setString(1,city.getName());
        statement.setString(2,city.getCountryCode());
        statement.setString(3,city.getDistrict());
        statement.setInt(4,city.getPopulation());
        statement.setInt(5,city.getId());
        return statement;
    }


    public City findCityById(int id) {
        City city = null;
        try {
            Connection connection = MySQLConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM city WHERE ID = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                city = new City(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;


    }

    public City findCityByName(String name) {
       City city = null;
        try {
            Connection connection = MySQLConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM city WHERE Name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                city = new City(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }


    public List<City> findCityByCode(String code) {
        List<City> cities = new ArrayList<>();
        try {
            Connection connection = MySQLConnection.getConnection();

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM city WHERE CountryCode = ?");
            statement.setString(1, code);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                cities.add(resultSetToCity(resultSet));
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }


    public List<City> findAll() {
        List<City> cityList= new ArrayList<>();

        try {
            Connection connection = MySQLConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from city");

            while (resultSet.next()) {

                int citytId = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String countryCode = resultSet.getString(3);
                String district = resultSet.getString(4);
                int population = resultSet.getInt(5);
                cityList.add(new City(citytId, name, countryCode, district, population));
                //return cityList;
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: ");
            e.printStackTrace();
        }
        return cityList;
    }




    public City addCity(City newCity) {
        if(newCity.getId() !=0) {
            return newCity;
        }
        try (
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = createAddCity(connection,newCity);
                ResultSet resultSet = statement.getGeneratedKeys()
        ) {
            int cityId = 0;
            while (resultSet.next()) {
                cityId = resultSet.getInt(1);
            }

            newCity = new City(
                    cityId,
                    newCity.getName(),
                    newCity.getCountryCode(),
                    newCity.getDistrict(),
                    newCity.getPopulation()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newCity;
    }
            //Execute insert query




    public City updateCity (City city) throws IllegalArgumentException {
        if(city.getId() == 0) throw new IllegalArgumentException("Can not update. City doesn't exist");
        try(
                Connection connection = MySQLConnection.getConnection();
                PreparedStatement statement = createUpdateCity(connection,city)
        ){
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }



    public int deleteCity(City city) {
        return 0;
    }
}
