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

    public static final String EQUIPMENT_PATH = BASE_PATH + "/equipment";
    public static final String EQUIPMENT_BY_ID_PATH = EQUIPMENT_PATH + "/{id}";

    public static final String ARMOUR_PATH = BASE_PATH + "/armour";
    public static final String ARMOUR_BY_ID_PATH = ARMOUR_PATH + "/{id}";

    public static final String EXOTIC_BEAST_PATH = BASE_PATH + "/exotic_beasts";
    public static final String EXOTIC_BEAST_BY_ID_PATH = EXOTIC_BEAST_PATH + "/{id}";

    public static final String UPGRADE_PATH = BASE_PATH + "/upgrate";
    public static final String UPGRADE_BY_ID_PATH = UPGRADE_PATH + "/{id}";

    // ===== Сообщения =====
    public static final String UNIT_CREATED = "Unit successfully created";
    public static final String UNIT_UPDATED = "Unit successfully updated";
    public static final String UNIT_DELETED = "Unit successfully deleted";
    public static final String UNIT_NOT_FOUND = "Unit not found with id: ";

    public static final String EQUIPMENT_CREATED = "Equipment successfully created";
    public static final String EQUIPMENT_UPDATED = "Equipment successfully updated";
    public static final String EQUIPMENT_DELETED = "Equipment successfully deleted";
    public static final String EQUIPMENT_NOT_FOUND = "Equipment not found with id: ";

    public static final String ARMOUR_CREATED = "Armour successfully created";
    public static final String ARMOUR_UPDATED = "Armour successfully updated";
    public static final String ARMOUR_DELETED = "Armour successfully deleted";
    public static final String ARMOUR_NOT_FOUND = "Armour not found with id: ";

    public static final String EXOTIC_BEAST_CREATED = "Exotic beast successfully created";
    public static final String EXOTIC_BEAST_UPDATED = "Exotic beast successfully updated";
    public static final String EXOTIC_BEAST_DELETED = "Exotic beast successfully deleted";
    public static final String EXOTIC_BEAST_NOT_FOUND = "Exotic beast not found with id: ";

    public static final String UPGRADE_CREATED = "Upgrate successfully created";
    public static final String UPGRADE_UPDATED = "Upgrate successfully updated";
    public static final String UPGRADE_DELETED = "Upgrate successfully deleted";
    public static final String UPGRADE_NOT_FOUND = "Upgrate not found with id: ";
}