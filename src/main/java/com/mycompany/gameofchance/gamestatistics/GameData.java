package com.mycompany.gameofchance.gamestatistics;

import com.mycompany.gameofchance.jedi.Jedi;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameData {
    private static List<Jedi> jedi = new ArrayList<>();

    public static List<Jedi> read(String file) throws Exception {
        jedi.clear();
        Scanner scanner = new Scanner(new File(file));
        while (scanner.hasNext()) {
            String jedi = scanner.nextLine();
            String[] fieldsOfJedi = jedi.split(",");
            Jedi oneOfJedi = new Jedi();
            oneOfJedi.setName(fieldsOfJedi[0]);
            oneOfJedi.setStrengthOfHit(Integer.parseInt(fieldsOfJedi[1]));
            oneOfJedi.setLife(Integer.parseInt(fieldsOfJedi[2]));
            oneOfJedi.setWonGame(Integer.parseInt(fieldsOfJedi[3]));
            GameData.jedi.add(oneOfJedi);
        }
        scanner.close();
        return jedi;
    }

    public static void writeToCsv(Jedi jedi, String filePath) throws Exception {
        File file = new File(filePath);
        FileWriter fileWriter = null;
        fileWriter = new FileWriter(file, true);
        fileWriter.append(jedi.getName());
        fileWriter.append(",");
        fileWriter.append(String.valueOf(jedi.getStrengthOfHit()));
        fileWriter.append(",");
        fileWriter.append(String.valueOf(jedi.getLife()));
        fileWriter.append(",");
        fileWriter.append(String.valueOf(jedi.getWonGame()));
        fileWriter.append("\n");
        fileWriter.flush();
        fileWriter.close();
    }
}
