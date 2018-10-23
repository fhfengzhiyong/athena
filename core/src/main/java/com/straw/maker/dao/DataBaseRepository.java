package com.straw.maker.dao;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

/**
 * @author straw(fengzy)
 * @description
 * @date 2018/10/8
 */

@Service("dataBase")
public class DataBaseRepository {

    @Autowired
    HikariDataSource dataSource;

    public Connection queryDb() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
