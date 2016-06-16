package ua.logic.util;

import ua.logic.entity.Verb;
import ua.logic.entity.VerbStatistic;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

public class Persistence {
    private Persistence() {
    }

    //    private void saveToCsv() {
//        try(FileWriter writer = new FileWriter("irregularVerbsStatistic.csv", true)) {
//            for (VerbStatistic statistic : verbsAnswer) {
//                String text = statistic.getVerbAnswer().getFirstForm()
//                        + "," + statistic.getVerbAnswer().getSecondForm()
//                        + "," + statistic.getVerbAnswer().getThirdForm();
//
//                writer.write(text);
//            }
//
//            //writer.flush();
//        }
//        catch(IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }

    public static void loadFromCsv(List list, boolean statistic) {
        String csvFile;
        if (statistic) {
            csvFile = "irregularVerbsStatistic.csv";
        } else {
            csvFile = "irregularVerbs.csv";
        }

        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                String[] row = line.split(cvsSplitBy);

                if (statistic) {
                    Verb verbStandard = new Verb(row[0], row[1], row[2]);
                    Verb verbAnswer = new Verb(row[3], row[4], row[5]);
                    list.add(new VerbStatistic(verbStandard, verbAnswer, LocalDateTime.parse(row[6])));
                } else {
                    list.add(new Verb(row[0], row[1], row[2]));
                }

            }

        } catch (FileNotFoundException e) {
            //e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void saveToCsv(VerbStatistic statistic) {
        try(FileWriter writer = new FileWriter("irregularVerbsStatistic.csv", true)) {
            String text = statistic.getVerbStandard().getFirstForm()
                    + "," + statistic.getVerbStandard().getSecondForm()
                    + "," + statistic.getVerbStandard().getThirdForm()
                    + "," + statistic.getVerbAnswer().getFirstForm()
                    + "," + statistic.getVerbAnswer().getSecondForm()
                    + "," + statistic.getVerbAnswer().getThirdForm()
                    + "," + statistic.getDateTime()
                    + "\n";
            writer.write(text);

            //writer.flush();
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
