package org.myproject.generateSource;


public enum DataType {
    INT(1, "int"),
    DOUBLE(2, "double"),
    STRING(3, "String"),
    BOOLEAN(4, "boolean"),
    CHAR(5, "char");

    private final int choice;
    private final String typeName;

    DataType(int choice, String typeName) {
        this.choice = choice;
        this.typeName = typeName;
    }

    public int getChoice() {
        return choice;
    }

    public String getTypeName() {
        return typeName;
    }

    public static DataType fromChoice(int choice) {
        for (DataType dataType : values()) {
            if (dataType.getChoice() == choice) {
                return dataType;
            }
        }
        return null; // Retourne null si le choix est invalide
    }

    public static void displayChoices() {
        System.out.println("Choisissez un type de donnée :");
        for (DataType dataType : values()) {
            System.out.println(dataType.getChoice() + ". " + dataType.getTypeName());
        }
    }
}