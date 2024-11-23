package org.myproject.generateSource;

public enum TypeElement {
    CLASSE(1, "Classe"),
    INTERFACE(2, "Interface");

    private final int choice;
    private final String label;

    TypeElement(int choice, String label) {
        this.choice = choice;
        this.label = label;
    }

    public int getChoice() {
        return choice;
    }

    public String getLabel() {
        return label;
    }

    public static TypeElement fromChoice(int choice) {
        for (TypeElement type : values()) {
            if (type.getChoice() == choice) {
                return type;
            }
        }
        return null; // Retourne null si le choix est invalide
    }

    public static void displayChoices() {
        System.out.println("Choisissez un type :");
        for (TypeElement type : values()) {
            System.out.println(type.getChoice() + ". " + type.getLabel());
        }
    }
}
