package com.cargodelivery;

import com.cargodelivery.model.City;
import com.cargodelivery.model.User;
import com.cargodelivery.repository.CityRepository;
import com.cargodelivery.repository.impl.CityRepositoryImpl;
import com.cargodelivery.repository.impl.UserRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;


public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        String userName = "root";
        String password = "1111";
        String connectionUrl = "jdbc:mysql://localhost:3306/cargodelivery?useSSL=true&characterEncoding=utf8";
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password)) {

            UserRepositoryImpl repository = new UserRepositoryImpl(connection);
//            User user = new User.Builder().setName("Юліана").setSurname("Akimenko")
//                    .setCity("Dnepr").setPhone("+380678858858").setMail("alla@gmail.com")
//                    .setPassword("4444").build();
//            System.out.println(repository.save(user));
//            User byId = repository.findById(5);
//            User byLogin = repository.findByLogin("bar@gmail.com");
//            CityRepository cityRepository = new CityRepositoryImpl(connection);
//            City city = new City();
//            city.setName("Миколаїв");
//            System.out.println(cityRepository.save(city));
//            cityRepository.findByName("Vinnitsa");
//            Set<City> all = cityRepository.findAll();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
