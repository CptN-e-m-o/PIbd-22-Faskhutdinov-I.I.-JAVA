package com.company;

public class AirfieldNotFoundException extends Exception {

    public AirfieldNotFoundException(int index) {
        super("Не найден транспорт по указанному месту");
    }
}