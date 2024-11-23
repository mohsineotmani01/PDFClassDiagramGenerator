package org.myproject.generateSource;



public enum EntityType {
    CLASS(1, "Classe"),
    INTERFACE(2, "Interface"),
    ABSTRACT_CLASS(3, "Classe Abstraite");

    private final int choice;
    private final String label;

    EntityType(int choice, String label) {
        this.choice = choice;
        this.label = label;
    }

    public int getChoice() {
        return choice;
    }

    public String getLabel() {
        return label;
    }

    public static EntityType fromChoice(int choice) {
        for (EntityType type : values()) {
            if (type.getChoice() == choice) {
                return type;
            }
        }
        return null;
    }

    public static void displayChoices() {
        System.out.println("Choisissez un type :");
        System.out.println("1. Classe");
        System.out.println("2. Interface");
        System.out.println("3. Classe Abstraite");
        System.out.println("4. Quitter");
    }
}
