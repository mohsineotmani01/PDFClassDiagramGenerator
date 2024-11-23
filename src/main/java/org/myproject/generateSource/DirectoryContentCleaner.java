package org.myproject.generateSource;

import java.io.File;

public class DirectoryContentCleaner {

    /**
     * Supprime tout le contenu d'un dossier sans supprimer le dossier lui-même.
     *
     * @param directoryPath Le chemin du dossier dont le contenu doit être supprimé.
     */
    public static void clearDirectoryContent(String directoryPath) {
        File directory = new File(directoryPath);

        // Vérifie si le dossier existe
        if (!directory.exists()) {
            System.out.println("Le dossier n'existe pas.");
            return;
        }

        // Vérifie si c'est bien un dossier
        if (!directory.isDirectory()) {
            System.out.println("Ce n'est pas un dossier valide.");
            return;
        }

        // Liste des fichiers et sous-dossiers du dossier
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                // Si le fichier est un dossier, on le supprime récursivement
                if (file.isDirectory()) {
                    clearDirectoryContent(file.getPath()); // Appel récursif pour nettoyer les sous-dossiers
                }
                // Supprime le fichier ou le dossier vide
                if (file.delete()) {
                    System.out.println();
                } else {
                    System.out.println("Échec de la suppression du fichier : " + file.getPath());
                }
            }
        }
    }

}
