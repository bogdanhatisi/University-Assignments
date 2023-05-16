package org.example.API;

import org.example.domain.Proba;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

public class RestTest {
    public static final String URL = "http://localhost:8080/api/proba";

    private final RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable) throws ServiceException {
        try {
            return callable.call();
        } catch (ResourceAccessException | HttpClientErrorException e) { // server down, resource exception
            throw new ServiceException(e);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Proba[] getAll() throws ServiceException {
        return execute(() -> restTemplate.getForObject(URL, Proba[].class));
    }

    public Proba getById(String id) throws ServiceException {
        return execute(() -> restTemplate.getForObject(String.format("%s/%s", URL, id), Proba.class));
    }

    public Proba create(Proba proba) throws ServiceException {
        return execute(() -> restTemplate.postForObject(URL, proba, Proba.class));
    }

    public void update(Integer id,Proba proba) throws ServiceException {
        execute(() -> {
            restTemplate.put(URL+"/"+id, proba);
            return null;
        });
    }

    public void delete(String id) throws ServiceException {
        execute(() -> {
            restTemplate.delete(String.format("%s/%s", URL, id));
            return null;
        });
    }

}