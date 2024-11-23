package org.myproject.generateSource;


public enum DataTypeM {
    INT(1, "int"),
    DOUBLE(2, "double"),
    STRING(3, "String"),
    BOOLEAN(4, "boolean"),
    CHAR(5, "char"),
    VOID(6, "void"); // Ajout du type void, avec une virgule correcte

    private final int choice;
    private final String typeName;

    DataTypeM(int choice, String typeName) {
        this.choice = choice;
        this.typeName = typeName;
    }

    public int getChoice() {
        return choice;
    }

    public String getTypeName() {
        return typeName;
    }

    public static DataTypeM fromChoice(int choice) {
        for (DataTypeM dataType : values()) {
            if (dataType.getChoice() == choice) {
                return dataType;
            }
        }
        return null; // Retourne null si le choix est invalide
    }

    public static void displayChoices() {
        System.out.println("Choisissez un type :");
        for (DataTypeM dataType : values()) {
            System.out.println(dataType.getChoice() + ". " + dataType.getTypeName());
        }
    }
}