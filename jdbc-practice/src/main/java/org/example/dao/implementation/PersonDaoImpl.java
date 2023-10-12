package org.example.dao.implementation;

import org.example.config.DataSource;
import org.example.dao.PersonDao;
import org.example.entity.Person;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonDaoImpl implements PersonDao {

    private final DataSource dataSource;

    public PersonDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Person create(Person person) throws SQLException {
        String sql = "INSERT INTO person " +
                "(name, age, birthday, is_married, creation_date_time) " +
                "VALUES (?, ?, ?, ?, ?);";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = this.dataSource.getConnection();
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setDate(3, java.sql.Date.valueOf(person.getBirthday()));
            statement.setBoolean(4, person.getIsMarried());
            statement.setTimestamp(5, Timestamp.valueOf(person.getCreationDateTime()));

            statement.executeUpdate();
            connection.commit();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                person.setId(resultSet.getLong(1));
            }
            return person;
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException next) {
                e.setNextException(next);
            }
            throw e;
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException ignored) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    @Override
    public Person update(Person person) throws SQLException {
        String sql = "UPDATE person SET " +
                "name = ?, " +
                "age = ?, " +
                "birthday = ?, " +
                "is_married = ?, " +
                "creation_date_time = ? " +
                "WHERE id = ?;";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = this.dataSource.getConnection();
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(sql);
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setDate(3, java.sql.Date.valueOf(person.getBirthday()));
            statement.setBoolean(4, person.getIsMarried());
            statement.setTimestamp(5, Timestamp.valueOf(person.getCreationDateTime()));
            statement.setLong(6, person.getId());

            statement.execute();
            connection.commit();

            if (statement.getUpdateCount() < 1) {
                throw new SQLException(
                        "Nothing to update. Person with id = " + person.getId() + " not found.", sql);
            } else {
                return person;
            }
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException next) {
                e.setNextException(next);
            }
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    @Override
    public Optional<Person> findById(Long id) throws SQLException {
        String sql = "SELECT * FROM person WHERE id = ?;";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setLong(1, id);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(toPerson(resultSet));
                    } else {
                        return Optional.empty();
                    }
                }
            }
        }
    }

    @Override
    public List<Person> search(
            String name,
            Integer minAge, Integer maxAge,
            LocalDate minBirthDate, LocalDate maxBirthDate,
            Boolean isMarried,
            LocalDateTime minCreationDateTime, LocalDateTime maxCreationDateTime,
            int from, int size) throws SQLException {

        String sql = "SELECT * FROM person WHERE " +
                "name LIKE ? " +
                "AND age BETWEEN ? AND ? " +
                "AND birthday BETWEEN ? AND ? " +
                "AND is_married = ? " +
                "AND creation_date_time BETWEEN ? AND ? " +
                "ORDER BY id LIMIT ? OFFSET ?;";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, "%" + name + "%");
                statement.setInt(2, minAge);
                statement.setInt(3, maxAge);
                statement.setDate(4, Date.valueOf(minBirthDate));
                statement.setDate(5, Date.valueOf(maxBirthDate));
                statement.setBoolean(6, isMarried);
                statement.setTimestamp(7, Timestamp.valueOf(minCreationDateTime));
                statement.setTimestamp(8, Timestamp.valueOf(maxCreationDateTime));
                statement.setInt(9, size);
                statement.setInt(10, from);

                try (ResultSet resultSet = statement.executeQuery()) {
                    List<Person> people = new ArrayList<>();
                    while (resultSet.next()) {
                        people.add(toPerson(resultSet));
                    }
                    return people;
                }
            }
        }
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        String sql = "DELETE FROM person WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = this.dataSource.getConnection();
            connection.setAutoCommit(false);

            statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            statement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException next) {
                e.setNextException(next);
            }
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ignored) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    private Person toPerson(ResultSet resultSet) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getLong("id"));
        person.setName(resultSet.getString("name"));
        person.setAge(resultSet.getInt("age"));
        person.setBirthday(resultSet.getDate("birthday").toLocalDate());
        person.setIsMarried(resultSet.getBoolean("is_married"));
        person.setCreationDateTime(resultSet.getTimestamp("creation_date_time").toLocalDateTime());
        return person;
    }
}
