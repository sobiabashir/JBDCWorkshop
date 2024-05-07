package com.test;

import db.MySQLConnection;
import model.City;
import model.CityDAO;
import model.CityDAOJDBCImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import model.CityDAO;
import java.util.ArrayList;
import java.util.List;

public class WorkshopWorlsJDBC {


    public static void main(String[] args) {
     CityDAOJDBCImpl cityDAO = new CityDAOJDBCImpl();



     //System.out.println(cityDAO.findCityById(1));
       // System.out.println(cityDAO.findCityByCode("KOR"));
      // System.out.println(cityDAO.findCityByName("Lahore"));
       // cityDAO.addCity(new City("Islamabad", "PAK", "Rawalpindi", 1000000));;
        //System.out.println(cityDAO.findAll());
        City Tjockhult = new City(3048,"Stockholm","SWE","Lisboa",750348);
        cityDAO.updateCity(Tjockhult);


//ex1();

    }






}
