package org.nan.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreateExceptionServiceImpl implements CreateExceptionService{
    public void createException() {
        try {
            firstException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void firstException() {
        List emptyArray = null;

        try {
            emptyArray.size();
        } catch (RuntimeException ex) {
            throw new RuntimeException(secondException(), ex);
        }
    }

    private String secondException() {
        try {
            List notEmptyArray = new ArrayList();
            notEmptyArray.get(20).toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return "";
    }

}
