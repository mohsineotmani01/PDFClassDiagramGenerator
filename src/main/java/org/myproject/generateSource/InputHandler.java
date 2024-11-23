package org.myproject.generateSource;


import java.util.List;
import java.util.Scanner;

public class InputHandler {
    public static void collectAttributes(Scanner scanner, List<String> attributes) {
        System.out.print("Voulez-vous ajouter des attributs ? (y/n) : ");
        String response = scanner.nextLine().trim();

        if (!response.equalsIgnoreCase("y")) {
            System.out.println("Aucun attribut ajouté.");
            return;
        }

        System.out.println("Ajoutez des attributs à la classe (entrez 'done' lorsque vous avez terminé) :");

        // Afficher les choix des types une seule fois
        DataType.displayChoices();

        while (true) {
            System.out.print("Votre choix pour le type de l'attribut (1-5 ou 'done') : ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("done")) break;

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide !");
                continue;
            }

            DataType dataType = DataType.fromChoice(choice);

            if (dataType == null) {
                System.out.println("Choix invalide pour le type. Veuillez réessayer.");
                continue;
            }

            System.out.print("Entrez le nom de l'attribut : ");
            String attrName = scanner.nextLine().trim();

            if (!attrName.isEmpty() && Character.isJavaIdentifierStart(attrName.charAt(0)) &&
                    attrName.chars().allMatch(Character::isJavaIdentifierPart)) {
                attributes.add(dataType.getTypeName() + " " + attrName + ";");
            } else {
                System.out.println("Nom de l'attribut invalide. Essayez encore.");
            }
        }
    }
}
