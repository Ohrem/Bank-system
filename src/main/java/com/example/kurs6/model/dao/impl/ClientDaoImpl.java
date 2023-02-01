package com.example.kurs6.model.dao.impl;

import com.example.kurs6.enity.Client;
import com.example.kurs6.exception.DaoException;
import com.example.kurs6.model.connection.ConnectionPool;
import com.example.kurs6.model.dao.ClientDao;
import com.example.kurs6.model.dao.mapper.ClientRowMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientDaoImpl implements ClientDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String SQL_ADD_NEW_USER = "INSERT INTO users (name, surname, third_name, date_of_birth, sex) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_ADD_NEW_PASSPORT = "INSERT INTO passports (passport_serial, passport_number, output_organisation, output_date, identification_number, users_user_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_ADD_NEW_ADDRESS = "INSERT INTO addresses (place_of_birth, living_city, living_address, registration_city, registration_address, users_user_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_ADD_NEW_SOCIAL_INFO = "INSERT INTO social_info (marital_status, citizenship, is_invalid, is_pensiya, is_conscript, users_user_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_USER = "UPDATE users SET name = ?, surname = ?, third_name = ?, date_of_birth = ?, sex = ? WHERE user_id = ?";
    private static final String SQL_UPDATE_PASSPORT = "UPDATE passports SET passport_serial = ?, passport_number = ?, output_organisation = ?, output_date = ?, identification_number = ? WHERE users_user_id = ?";
    private static final String SQL_UPDATE_ADDRESS = "UPDATE addresses SET place_of_birth = ?, living_city = ?, living_address = ?, registration_city = ?, registration_address = ? WHERE users_user_id = ?";
    private static final String SQL_UPDATE_SOCIAL_INFO = "UPDATE social_info SET marital_status = ?, citizenship = ?, is_invalid = ?, is_pensiya = ?, is_conscript = ? WHERE users_user_id = ?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE user_id = ?";
    private static final String SQL_FIND_ALL_USERS = "SELECT user_id, name, surname, third_name, date_of_birth, sex FROM users";
    private static final String SQL_FIND_USER_BY_ID = "SELECT users.*, passports.*, addresses.*, social_info.* FROM users LEFT JOIN passports ON users.user_id = passports.users_user_id LEFT JOIN addresses ON users.user_id = addresses.users_user_id LEFT JOIN social_info ON users.user_id = social_info.users_user_id WHERE users.user_id = ?";
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static ClientDaoImpl INSTANCE = new ClientDaoImpl();


    public static ClientDaoImpl getInstance(){
        if (INSTANCE == null){
            INSTANCE = new ClientDaoImpl();
        }
        return INSTANCE;
    }

    @Override
    public boolean create(Client client) throws DaoException {
        boolean userAdded = false;
        try(Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement userStatement = connection.prepareStatement(SQL_ADD_NEW_USER, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement passportStatement = connection.prepareStatement(SQL_ADD_NEW_PASSPORT);
                 PreparedStatement addressStatement = connection.prepareStatement(SQL_ADD_NEW_ADDRESS);
                 PreparedStatement socInfoStatement = connection.prepareStatement(SQL_ADD_NEW_SOCIAL_INFO)) {
                connection.setAutoCommit(false);
                userStatement.setString(1, client.getName());
                userStatement.setString(2, client.getSurname());
                userStatement.setString(3, client.getPatronymic());
                userStatement.setString(4, client.getDOB().format(DateTimeFormatter.ISO_DATE));
                userStatement.setString(5, client.getSex().toString());
                userStatement.executeUpdate();
                ResultSet rs = userStatement.getGeneratedKeys();
                if (rs.next()){
                    final String userId = rs.getString(1);
                    passportStatement.setString(1, client.getPassportSerial());
                    passportStatement.setLong(2, client.getPassportNumber());
                    passportStatement.setString(3, client.getKemVidan());
                    passportStatement.setString(4, client.getDateOfIssue().format(DateTimeFormatter.ISO_DATE));
                    passportStatement.setString(5, client.getIdNumber());
                    passportStatement.setString(6, userId);
                    passportStatement.executeUpdate();
                    addressStatement.setString(1, client.getPOB());
                    addressStatement.setString(2, client.getLivingCity().toString());
                    addressStatement.setString(3, client.getLivingAddress());
                    addressStatement.setString(4, client.getRegistrationCity().toString());
                    addressStatement.setString(5, client.getRegistrationAddress());
                    addressStatement.setString(6, userId);
                    addressStatement.executeUpdate();
                    socInfoStatement.setString(1, client.getMaritalStatus().toString());
                    socInfoStatement.setString(2, client.getCitizenship().toString());
                    socInfoStatement.setString(3, client.getDisability().toString());
                    socInfoStatement.setBoolean(4, client.getPensioner());
                    socInfoStatement.setBoolean(5, client.getMilitaryService());
                    socInfoStatement.setString(6, userId);
                    socInfoStatement.executeUpdate();
                    connection.commit();
                    userAdded = true;
                    logger.debug("commit");
                }
            } catch (SQLException e){
                connection.rollback();
                logger.debug("rollback");
                throw new SQLException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            logger.error("dao exception", e);
            throw new DaoException(e);
        }
        return userAdded;
    }

    @Override
    public boolean update(Client client) throws DaoException {
        boolean updated;
        try(Connection connection = connectionPool.getConnection()){
            try (PreparedStatement updateUser = connection.prepareStatement(SQL_UPDATE_USER);
                 PreparedStatement updatePassport = connection.prepareStatement(SQL_UPDATE_PASSPORT);
                 PreparedStatement updateAddress = connection.prepareStatement(SQL_UPDATE_ADDRESS);
                 PreparedStatement updateSocial = connection.prepareStatement(SQL_UPDATE_SOCIAL_INFO)){
                connection.setAutoCommit(false);
                updateUser.setString(1, client.getName());
                updateUser.setString(2, client.getSurname());
                updateUser.setString(3, client.getPatronymic());
                updateUser.setString(4, client.getDOB().format(DateTimeFormatter.ISO_DATE));
                updateUser.setString(5, client.getSex().toString());
                updateUser.setLong(6, client.getId());
                updateUser.executeUpdate();
                updatePassport.setString(1, client.getPassportSerial());
                updatePassport.setLong(2, client.getPassportNumber());
                updatePassport.setString(3, client.getKemVidan());
                updatePassport.setString(4, client.getDateOfIssue().format(DateTimeFormatter.ISO_DATE));
                updatePassport.setString(5, client.getIdNumber());
                updatePassport.setLong(6, client.getId());
                updatePassport.executeUpdate();
                updateAddress.setString(1, client.getPOB());
                updateAddress.setString(2, client.getLivingCity().toString());
                updateAddress.setString(3, client.getLivingAddress());
                updateAddress.setString(4, client.getRegistrationCity().toString());
                updateAddress.setString(5, client.getRegistrationAddress());
                updateAddress.setLong(6, client.getId());
                updateAddress.executeUpdate();
                updateSocial.setString(1, client.getMaritalStatus().toString());
                updateSocial.setString(2, client.getCitizenship().toString());
                updateSocial.setString(3, client.getDisability().toString());
                updateSocial.setBoolean(4, client.getPensioner());
                updateSocial.setBoolean(5, client.getMilitaryService());
                updateSocial.setLong(6, client.getId());
                updateSocial.executeUpdate();
                updated = true;
                connection.commit();
                logger.debug("upd commit");
            } catch (SQLException e){
                connection.rollback();
                logger.debug("upd rollback");
                throw new SQLException(e);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            logger.debug("dao exception", e);
            throw new DaoException(e);
        }
        return updated;
    }

    @Override
    public boolean delete(Client client) throws DaoException {
        boolean deleted = false;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement deleteClient = connection.prepareStatement(SQL_DELETE_USER_BY_ID)){
            deleteClient.setString(1, String.valueOf(client.getId()));
            int result = deleteClient.executeUpdate();
            if(result != 0) {
                deleted = true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return deleted;
    }

    @Override
    public List<Client> findAll() throws DaoException {
        List<Client> clients = new ArrayList<>();
        ClientRowMapper mapper = new ClientRowMapper();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement findAllStatement = connection.prepareStatement(SQL_FIND_ALL_USERS);
             ResultSet rs = findAllStatement.executeQuery()){
            while (rs.next()){
                Client client = mapper.mapRowLite(rs);
                clients.add(client);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return clients;
    }

    @Override
    public Optional<Client> findById(String id) throws DaoException {
        ClientRowMapper mapper = new ClientRowMapper();
        Optional<Client> optionalClient;
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)){
            statement.setString(1, id);
            try(ResultSet rs = statement.executeQuery()){
                if(rs.next()){
                    Client client = mapper.mapRow(rs);
                    optionalClient = Optional.of(client);
                } else{
                    optionalClient = Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return optionalClient;
    }
}