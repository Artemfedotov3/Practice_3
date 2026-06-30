package com.spring.springboot.api.constants;

public final class ApiConstants {

    // Приватный конструктор запрещает создание экземпляров
    private ApiConstants() {
        throw new UnsupportedOperationException("Utility class");
    }

    // ===== Базовые пути =====
    public static final String BASE_PATH = "/api/v1";
    public static final String UNIT_PATH = BASE_PATH + "/units_models";
    public static final String UNIT_BY_ID_PATH = UNIT_PATH + "/{id}";

    // ===== Сообщения =====
    public static final String UNIT_CREATED = "Unit successfully created";
    public static final String UNIT_UPDATED = "Unit successfully updated";
    public static final String UNIT_DELETED = "Unit successfully deleted";
    public static final String UNIT_NOT_FOUND = "Unit not found with id: ";
}