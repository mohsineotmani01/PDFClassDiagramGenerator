package org.myproject.generateSource ;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JavaFileGenerator {
    private final FileHandler fileHandler = new FileHandler();

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenue dans l'application de génération de fichiers Java !");
        System.out.println("Entrez 'exit' pour quitter à tout moment.");

        while (true) {
            // Affichage des options de type d'entité
            System.out.println("Choisissez un type :");
            System.out.println("1. Classe");
            System.out.println("2. Interface");
            System.out.println("3. Classe Abstraite");
            System.out.println("4. Quitter");

            System.out.print("Votre choix (1-4) : ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("4") || input.equalsIgnoreCase("exit")) {
                System.out.println("Au revoir !");
                break;
            }

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide !");
                continue;
            }

            EntityType entityType = EntityType.fromChoice(choice);

            if (entityType == null) {
                System.out.println("Choix invalide. Veuillez réessayer.");
                continue;
            }

            // Étape 2 : Saisie du nom
            System.out.print("Entrez le nom de la " + entityType.getLabel() + " : ");
            String name = scanner.nextLine().trim();

            if (!isValidJavaIdentifier(name)) {
                System.out.println("Nom invalide. Assurez-vous que le nom est un identifiant Java valide.");
                continue;
            }

            // Étape 3 : Relations (extends/implements)
            List<String> relations = new ArrayList<>();
            if (entityType == EntityType.CLASS || entityType == EntityType.ABSTRACT_CLASS) {
                System.out.print("Voulez-vous étendre une classe parent ? (y/n) : ");
                if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                    System.out.print("Entrez le nom de la classe parent : ");
                    relations.add("extends " + scanner.nextLine().trim());
                }
            }

            if (entityType == EntityType.CLASS || entityType == EntityType.INTERFACE) {
                System.out.print("Voulez-vous implémenter une ou plusieurs interfaces ? (y/n) : ");
                if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                    System.out.print("Entrez les noms des interfaces séparés par des virgules : ");
                    String[] interfaces = scanner.nextLine().split(",");
                    relations.add("implements " + String.join(", ", interfaces).trim());
                }
            }

            // Étape 4 : Collecte des attributs pour les classes
            List<String> attributes = new ArrayList<>();
            if (entityType == EntityType.CLASS || entityType == EntityType.ABSTRACT_CLASS) {
                InputHandler.collectAttributes(scanner, attributes);
            }

            // Génération du fichier
            fileHandler.generateFile(entityType, name, relations, attributes);
        }
    }

    private boolean isValidJavaIdentifier(String name) {
        return !name.isEmpty() && Character.isJavaIdentifierStart(name.charAt(0)) &&
                name.chars().allMatch(Character::isJavaIdentifierPart);
    }
}