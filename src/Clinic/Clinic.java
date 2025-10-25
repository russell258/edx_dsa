package Clinic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Clinic {

    File patientFile;
    int day;

    public Clinic(File file) {
        this.patientFile = file;
        day = 1;    
    }

    public Clinic (String fileName) {
        this(new File(fileName));
    }

    public String getFileName() {
        return patientFile.getName();
    }

    public String nextDay(File f) throws FileNotFoundException, InvalidPetException {
        Scanner scanner = new Scanner(f);
        StringBuilder sb = new StringBuilder();

        // single Scanner for user input (do NOT close it; closing System.in will break further input)
        Scanner inputScanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            String name = parts[0];
            String typeOfPet = parts[1];
            String petAttribute = parts[2];
            String timeIn = parts[3];

            // prompt and read health (retry until valid double)
            double health;
            while (true) {
                System.out.println("Consultation for " + name + " the " + typeOfPet + " at " + timeIn + "\n"
                        + "What is the health of " + name + "?");
                String in = inputScanner.nextLine();
                try {
                    health = Double.parseDouble(in.trim());
                    break;
                } catch (NumberFormatException e) {
                    // loop and prompt again
                }
            }

            // prompt and read pain level (retry until valid int)
            int painLevel;
            while (true) {
                System.out.println("On a scale of 1 to 10, how much pain is " + name + " in right now?\n");
                String in = inputScanner.nextLine();
                try {
                    painLevel = Integer.parseInt(in.trim());
                    break;
                } catch (NumberFormatException e) {
                    // loop and prompt again
                }
            }

            int timeTaken;
            if (typeOfPet.equals("Dog")) {
                double droolRate = Double.parseDouble(petAttribute);
                Dog dog = new Dog(name, health, painLevel, droolRate);
                dog.speak();
                timeTaken = dog.treat();
            } else if (typeOfPet.equals("Cat")) {
                int miceCaught = Integer.parseInt(petAttribute);
                Cat cat = new Cat(name, health, painLevel, miceCaught);
                cat.speak();
                timeTaken = cat.treat();
            } else {
                scanner.close();
                // do NOT close inputScanner here
                throw new InvalidPetException("Invalid pet type: " + typeOfPet);
            }

            String timeOut = addTime(timeIn, timeTaken);
            sb.append(name + "," + typeOfPet + "," + petAttribute + "," + "Day " + day + "," + timeIn + "," + timeOut
                    + "," + health + "," + painLevel + "\n");
        }

        day++;
        scanner.close();
        // DO NOT close inputScanner (System.in)
        return sb.toString();
    }

    public String nextDay(String fileName) throws FileNotFoundException, InvalidPetException {
        return nextDay(new File(fileName));
    }

    public boolean addToFile(String patientInfo) {
        if (patientInfo == null) {
            return false;
        }
        String[] parts = patientInfo.split(",", -1);
        if (parts.length < 8) {
            // not enough fields for a single appointment
            return false;
        }
        String name = parts[0];
         // appointment portion excludes name,species,attribute (keep Day,Entry,Exit,Health,Pain...)
        String appointmentTail = String.join(",", java.util.Arrays.copyOfRange(parts, 3, parts.length));

        StringBuilder sb = new StringBuilder();
        Scanner scanner = null;
        boolean found = false;
        try {
            scanner = new Scanner(patientFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) {
                    continue;
                }
                String[] existing = line.split(",", -1);
                if (existing.length > 0 && existing[0].equals(name)) {
                    // existing patient: append appointment fields to their line
                    sb.append(line);
                    if (!line.endsWith(",")) {
                        sb.append(",");
                    }
                    sb.append(appointmentTail);
                    sb.append(System.lineSeparator());
                    found = true;
                } else {
                    sb.append(line).append(System.lineSeparator());
                }
            }
        } catch (FileNotFoundException e) {
            // if file doesn't exist or cannot be read, treat as failure
            return false;
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        if (!found) {
            // new patient: add full patientInfo as a new line
            sb.append(patientInfo).append(System.lineSeparator());
        }

        // rewrite the entire file
        try (PrintWriter writer = new PrintWriter(patientFile)) {
            writer.write(sb.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private String addTime(String timeIn, int treatmentTime) {
        Integer timeInt = Integer.valueOf(timeIn);
        int hours = timeInt / 100;
        int minutes = timeInt % 100;
        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException("Invalid time: " + timeIn);
        }
        int totalMinutes = hours * 60 + minutes + treatmentTime;
        totalMinutes = ((totalMinutes % (24 * 60)) + (24 * 60)) % (24 * 60); // normalize positive
        int newHours = totalMinutes / 60;
        int newMinutes = totalMinutes % 60;
        String timeOut = Integer.toString(newHours * 100 + newMinutes);
        return timeOut;
    }

}
