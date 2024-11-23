package org.myproject;

import org.myproject.utils.FileUtils;
import org.myproject.utils.GraphUtils;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.myproject.generateSource.JavaFileGenerator;

import static org.myproject.generateSource.DirectoryContentCleaner.clearDirectoryContent;

public class Main {

    public static void main(String[] args) throws InterruptedException {


        JavaFileGenerator generator = new JavaFileGenerator();
        generator.run();

        ///generate uml

        Scanner scanner = new Scanner(System.in);
        String nameFile;

        do {
            System.out.print("Saisir un nom valide de votre diagrame : ");
            nameFile = scanner.nextLine();
        } while (nameFile.isEmpty());
        String nameFNotSpace = nameFile.replaceAll("\\s+", "");

        String sourceDir = "src/main/java/org/myproject/source";
        if (isDirectoryEmpty(sourceDir)) {
            System.out.println("Le répertoire source est vide.");
        } else {
            // Pause de 100ms
            String markdownCode = GraphUtils.createGraph("org.myproject.source");
            FileUtils.writeToMarkdownFile("src/markdown/" + nameFNotSpace + ".md", markdownCode);

            try {
                ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\nodejs\\npx.cmd", "mmdc", "-i", "src/markdown/" + nameFNotSpace + ".md", "-o", "src/output/" + nameFNotSpace + ".pdf");
                processBuilder.inheritIO();

                Process process = processBuilder.start();
                int exitCode = process.waitFor();

                if (exitCode == 0) {
                    System.out.println("Diagramme généré et sauvegardé sous output.pdf.");
                } else {
                    System.err.println("Une erreur est survenue lors de la génération du diagramme.");
                }

               // String directoryPath = "src/main/java/org/myproject/source/"; // Remplacez par le chemin du dossier à nettoyer
               // clearDirectoryContent(directoryPath);

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
    public static boolean isDirectoryEmpty(String directoryPath) {
        try {
            // Utilise Files.list pour lister les fichiers dans le répertoire
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath))) {
                return !stream.iterator().hasNext(); // Si aucun fichier n'est trouvé, le répertoire est vide
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false; // En cas d'erreur, on considère que le répertoire n'est pas vide
        }
    }
}


