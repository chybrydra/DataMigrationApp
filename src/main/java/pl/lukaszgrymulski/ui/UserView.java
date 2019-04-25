package pl.lukaszgrymulski.ui;

import pl.lukaszgrymulski.migration.MigrationFactory;

import java.io.File;
import java.util.Scanner;

public class UserView {

    public static File currentFile = new File("/");
    public static Scanner scanner = new Scanner(System.in);


    public void startUserInferface() {
        printCommand("==== MIGRATION", "TOOL ====");
        printHelp();
        printCurrentLocation();

        while (true) {
            System.out.print(currentFile.getAbsolutePath() + " > ");
            String userDecision = scanner.nextLine();
            System.out.println(currentFile.getAbsolutePath() + " > " + userDecision);
            processUserDecision(userDecision);
        }
    }

    private void processUserDecision(String userDecision) {
        if (userDecision.matches("^mt migrate -f .+")) {
            migrateWithGivenAbsolutePath(userDecision);
        }
        if (userDecision.equals("mt list")){
            showFilesInDirectory();
        }
        if (userDecision.matches("mt next=.+")) {
            goToDirectory(userDecision);
        }
        if (userDecision.equals("mt prev")) {
            goToPreviousDirectory();
        }
        if (userDecision.equals("mt migrate")) {
            migrateWithCurrentPath();
        }
        if (userDecision.equals("mt help")) {
            printHelp();
        }
        if (userDecision.matches("mt dir=.+")) {
            goToAbsoluteDirectory(userDecision);
        }
        if (userDecision.equals("mt exit")) {
            exitUserInterface();
        }
    }

    private void exitUserInterface() {
        System.exit(4);
    }

    private void goToAbsoluteDirectory(String userDecision) {
        String absolutePath = userDecision.substring(userDecision.indexOf("=")+1);
        File file = new File(absolutePath);
        if (file.exists()) {
            currentFile = file;
        }
    }

    private void migrateWithCurrentPath() {
        if (!currentFile.isFile()) {
            System.out.println(" >> You have not chosen a file to migrate!");
            return;
        }
        boolean fileIsXml = currentFile.getAbsolutePath().endsWith(".xml") || currentFile.getAbsolutePath().endsWith(".XML");
        boolean fileIsCsv = currentFile.getAbsolutePath().endsWith(".csv") || currentFile.getAbsolutePath().endsWith(".CSV");
        if (!(fileIsCsv || fileIsXml)) {
            System.out.println(" >> Cannot migrate this file! Only *.xml and *.csv are accepted!");
            return;
        }
        MigrationFactory migrationFactory = new MigrationFactory();
        migrationFactory.migrate(currentFile);
    }

    private void goToPreviousDirectory() {
        if (currentFile.getParent() != null) {
            File file = new File(currentFile.getParent());
            currentFile = file;
        } else {
            System.out.println(" >> cannot go further back!");
        }
    }

    private void goToDirectory(String userDecision) {
        String newDirectoryTail = userDecision.substring(userDecision.indexOf("=")+1);
        String currentPath = currentFile.getAbsolutePath();
        String slash = null;
        if (currentPath.endsWith("\\") || currentPath.endsWith("/")) {
            slash = "";
        } else {
            slash = "/";
        }
        File file = new File(currentPath + slash + newDirectoryTail);
        if (file.exists()) {
            currentFile = file;
        }
    }

    private void showFilesInDirectory() {
        if (currentFile.isDirectory()) {
            String[] subdirs = currentFile.list();
            if (subdirs != null && subdirs.length > 0) {
                for (int i = 0; i < subdirs.length; i++) {
                    System.out.println(" - " + subdirs[i]);
                }
            } else {
                System.out.println(" >> Nothing to list here!");
            }
        } else {
            System.out.println(" >> Nothing to list here!");
        }
    }

    private void migrateWithGivenAbsolutePath(String userDecision) {
        String path = userDecision.split(" ")[3];
        File file = new File(path);
        MigrationFactory migrationFactory = new MigrationFactory();
        migrationFactory.migrate(new File(path));
    }

    public void printCommand(String command, String description) {
        System.out.printf("%30s | %-60s\n", command, description);
    }

    public void printCurrentLocation() {
        System.out.println("CURRENT LOCATION: " + currentFile.getAbsolutePath());
    }

    public void printHelp() {
        printCommand("COMMAND", "DESCRIPTION");
        printCommand("mt help", "help");
        printCommand("mt prev","previous directory");
        printCommand("mt next=[filename]","next directory");
        printCommand("mt dir=[absolutePath]","go to absolute directory");
        printCommand("mt list","list available files/directories");
        printCommand("mt migrate","migrate chosen file");
        printCommand("mt migrate -f [absolutePath]","migrate chosen file");
        printCommand("mt exit","exit app");
    }
}
