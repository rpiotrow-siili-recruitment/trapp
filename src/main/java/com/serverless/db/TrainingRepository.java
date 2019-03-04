package com.serverless.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.serverless.db.model.Training;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TrainingRepository {
    private static final Logger LOG = LogManager.getLogger(TrainingRepository.class);

    public List<Training> getTrainings() {
        return Client.getConnection().map(con -> {
            final List<Training> trainings = new ArrayList<>();
            try {                
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from trainings");
                while (rs.next()) {
                    Training training = new Training();
                    training.id = rs.getInt("id");
                    training.name = rs.getString("name");
                    training.duration = rs.getInt("duration");
                    training.date = rs.getTimestamp("date").toString();
                    trainings.add(training);
                }
            } catch (SQLException exception) {
                LOG.warn(exception);
            }
            return trainings;

        }).orElse(Collections.emptyList());
    }
}