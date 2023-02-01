package com.example.kurs6.model.service.impl;

import com.example.kurs6.enity.Client;
import com.example.kurs6.exception.DaoException;
import com.example.kurs6.exception.ServiceException;
import com.example.kurs6.model.dao.impl.ClientDaoImpl;
import com.example.kurs6.model.service.ClientService;
import com.example.kurs6.validator.ClientValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.kurs6.command.ParameterAndAttribute.*;
import static com.example.kurs6.enity.Client.*;


public class ClientServiceImpl implements ClientService {
    private static final Logger logger = LogManager.getLogger();
    private final ClientDaoImpl clientDao = ClientDaoImpl.getInstance();
    private final ClientValidator clientValidator = new ClientValidator();

    @Override
    public boolean create(Map<String, String> userData) throws ServiceException {

        boolean isCreated = false;

        String name = userData.get(USER_NAME);
        String surname = userData.get(USER_SURNAME);
        String patronymic = userData.get(USER_PATRONYMIC);
        String DOB = userData.get(USER_DOB);
        String sex = userData.get(SEX);
        String passportSerial = userData.get(PASSPORT_SERIAL);
        String passportNumber = userData.get(PASSPORT_NUMBER);
        String kemVidan = userData.get(PASSPORT_KEM_VIDAN);
        String dateOfIssue = userData.get(PASSPORT_DOI);
        String idNumber = userData.get(USER_ID_NUMBER);
        String POB = userData.get(USER_POB);
        String livingCity = userData.get(USER_LIVING_CITY);
        String livingAddress = userData.get(USER_LIVING_ADDRESS);
        String registrationCity = userData.get(USER_REGISTRATION_CITY);
        String registrationAddress = userData.get(USER_REGISTRATION_ADDRESS);
        String maritalStatus = userData.get(USER_MARITAL_STATUS);
        String citizenship = userData.get(USER_CITIZENSHIP);
        String disability = userData.get(USER_DISABILITY);
        String pensioner = userData.get(USER_PENSIONER);
        String militaryService = userData.get(USER_MILITARY_SERVICE);

        Client client = Client.builder().name(name).surname(surname)
                .patronymic(patronymic).DOB(LocalDate.parse(DOB)).sex(Sex.valueOf(sex)).passportSerial(passportSerial).passportNumber(Long.parseLong(passportNumber))
                .kemVidan(kemVidan).dateOfIssue(LocalDate.parse(dateOfIssue)).idNumber(idNumber).POB(POB).livingCity(City.valueOf(livingCity)).livingAddress(livingAddress)
                .registrationCity(City.valueOf(registrationCity)).registrationAddress(registrationAddress).maritalStatus(MaritalStatus.valueOf(maritalStatus)).citizenship(Country.valueOf(citizenship))
                .disability(Disability.valueOf(disability)).pensioner(Boolean.valueOf(pensioner)).militaryService(Boolean.valueOf(militaryService)).build();
        try {
            isCreated = clientDao.create(client);
        } catch (DaoException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        }

        return isCreated;
    }

    @Override
    public boolean updateClientInfo(Map<String, String> clientData) throws ServiceException {
        boolean isChanged = false;
        logger.info(clientData);
        if (true) {

            Client client = builder().id(Long.parseLong(clientData.get(USER_ID))).name(clientData.get(USER_NAME)).surname(clientData.get(USER_SURNAME))
                    .patronymic(clientData.get(USER_PATRONYMIC)).DOB(LocalDate.parse(clientData.get(USER_DOB))).sex(Sex.valueOf(clientData.get(SEX)))
                    .passportSerial(clientData.get(PASSPORT_SERIAL)).passportNumber(Long.parseLong(clientData.get(PASSPORT_NUMBER)))
                    .kemVidan(clientData.get(PASSPORT_KEM_VIDAN)).dateOfIssue(LocalDate.parse(clientData.get(PASSPORT_DOI))).idNumber(clientData.get(USER_ID_NUMBER))
                    .POB(clientData.get(USER_POB)).livingCity(City.valueOf(clientData.get(USER_LIVING_CITY))).livingAddress(clientData.get(USER_LIVING_ADDRESS))
                    .registrationCity(City.valueOf(clientData.get(USER_REGISTRATION_CITY))).registrationAddress(clientData.get(USER_REGISTRATION_ADDRESS))
                    .maritalStatus(MaritalStatus.valueOf(clientData.get(USER_MARITAL_STATUS))).citizenship(Country.valueOf(clientData.get(USER_CITIZENSHIP)))
                    .disability(Disability.valueOf(clientData.get(USER_DISABILITY))).pensioner(Boolean.valueOf(clientData.get(USER_PENSIONER)))
                    .militaryService(Boolean.valueOf(clientData.get(USER_MILITARY_SERVICE))).build();
            try {
                isChanged = clientDao.update(client);
            } catch (DaoException e) {
                throw new ServiceException("Service exception", e);
            }
        }
        return isChanged;
    }


    @Override
    public boolean deleteById(String id) throws ServiceException {
        boolean clientDeleted;

        try {
            Optional<Client> optionalClient = clientDao.findById(id);
            if (optionalClient.isPresent()) {
                Client client = optionalClient.get();
                clientDeleted = clientDao.delete(client);
            } else {
                clientDeleted = false;
            }
        } catch (DaoException e) {
            throw new ServiceException("Service exception", e);
        }
        return clientDeleted;
    }

    @Override
    public Optional<Client> findById(String id) throws ServiceException {
        Optional<Client> optionalUser;
        try {
            optionalUser = clientDao.findById(id);
        } catch (DaoException e) {
            logger.error("Service exception trying find user by id", e);
            throw new ServiceException("Service exception", e);
        }
        return optionalUser;
    }

    @Override
    public Optional<Client> findByPassportNumber(String number) throws ServiceException {
        return Optional.empty();
    }

    @Override
    public List<Client> findAll() throws ServiceException {
        List<Client> foundedClients;
        try {

            foundedClients = clientDao.findAll();

        } catch (DaoException e) {
            throw new ServiceException("Service exception", e);
        }
        return foundedClients;
    }
}
