package org.us.famulei.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.us.famulei.model.Carrier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarrierJDBCDaoImpl implements ICarrierDao {
    static final String DB_URL = "jdbc:postgresql://localhost:5430/carriers_db";
    static final String USER = "admin";
    static final String PASS = "Carriers123!";

    @Override //wk2.1 pt2 01:29:30
    public List<Carrier> getCarriers() {
        Logger logger = LoggerFactory.getLogger(CarrierJDBCDaoImpl.class);

        logger.debug("Start to getCarriers from Postgres via JDBC.");

        //Step1: Prepare the required data model
        List<Carrier> carriers = new ArrayList<Carrier>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //Step2: Open a connection ->5 key points to connect db
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //Step3: Execute a query
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM carriers";
            rs = stmt.executeQuery(sql);
            logger.info("Connects to DB successfully and execute the query.");

            //Step4: Extract data from result set
            while(rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String location = rs.getString("location");

                Carrier carrier = new Carrier();
                carrier.setId(id);
                carrier.setName(name);
                carrier.setDescription(description);
                carrier.setLocation(location);
                carriers.add(carrier);
            }

        } catch (SQLException e) {
            logger.error("Unable to connect to db or execute query", e);

        } finally {
            //Step5: finally block used to close resources
            try {
                if(rs != null) rs.close();
                if(stmt != null) stmt.close();
                if(conn != null) conn.close();
            } catch (SQLException e) {
                logger.error("Unable to close db connection", e);
                e.printStackTrace();
            }
        }

        logger.info("Finish getDepartments, %s", carriers);
        return carriers;
    }

}
