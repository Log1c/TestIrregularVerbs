package ua.logic.model;

import ua.logic.entity.Verb;
import ua.logic.entity.VerbStatistic;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

enum State {SHOW_CORRECT_ANSWER, REQUEST_ANSWER}

public class TestingVerbModel {

    private State state;
    private Verb currentVerb;
    private List<Verb> verbsStandard = new ArrayList<>();
    private int totalAnswers = 0;
    private int correctAnswers = 0;
    private List<VerbStatistic> verbsAnswer = new ArrayList<>();
    private String firstFormText;
    private String secondFormText;
    private String thirdFormText;

    private void saveToCsv() {
        try(FileWriter writer = new FileWriter("irregularVerbsStatistic.csv", true)) {
            for (VerbStatistic statistic : verbsAnswer) {
                String text = statistic.getVerbAnswer().getFirstForm()
                        + "," + statistic.getVerbAnswer().getSecondForm()
                        + "," + statistic.getVerbAnswer().getThirdForm();

                writer.write(text);
            }

            //writer.flush();
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void loadFromCsv(List list, boolean statistic) {
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
                    list.add(new Verb(row[0], row[1], row[2]));
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

    public TestingVerbModel() {
        loadFromCsv(verbsStandard, false);
        loadFromCsv(verbsAnswer, true);//TODO
        setQuestion();
    }

    public void addToStatistic(Verb verbStandard, Verb verbAnswer) {
        verbsAnswer.add(new VerbStatistic(verbStandard, verbAnswer));
    }

    public void refreshCurrentVerb() {
        Set<Verb> correct = new HashSet<>();

        for (VerbStatistic statistic : verbsAnswer) {
            if (statistic.getVerbStandard().equals(statistic.getVerbAnswer())) {
                correct.add(statistic.getVerbStandard());
            }
        }

        Set<Verb> questions = new HashSet<>();
        questions.addAll(verbsStandard);
        questions.removeAll(correct);

        int counter = (int) (Math.random() * questions.size());
        for (Verb verb : questions) {
            if (counter-- == 0) {
                currentVerb = verb;
                return;
            }
        }
    }

    public Verb getCurrentVerb() {
        return currentVerb;
    }

    public void incrementTotalAnswers(boolean collect) {
        totalAnswers++;
        if (collect) {
            correctAnswers++;
        }
    }

    public int getTotalAnswers() {
        return totalAnswers;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public String getStatistic() {
        return "" + getCorrectAnswers() + "/" + getTotalAnswers();
    }

    public boolean checkAnswer(Verb verbAnswer) {
        return currentVerb.equals(verbAnswer);
    }

    public void showCorrectAnswer() {
        state = State.SHOW_CORRECT_ANSWER;

        this.firstFormText = currentVerb.getFirstForm();
        this.secondFormText = currentVerb.getSecondForm();
        this.thirdFormText = currentVerb.getThirdForm();
    }

    public void next(String firstFormText, String secondFormText, String thirdFormText) {
        if (state == State.SHOW_CORRECT_ANSWER) {
            setQuestion();
            return;
        }

        setAnswer(new Verb(firstFormText, secondFormText, thirdFormText));
    }

    public void setAnswer(Verb verbAnswer) {
        boolean answerIsCorrect = checkAnswer(verbAnswer);
        if (!answerIsCorrect) {
            showCorrectAnswer();
        } else {
            setQuestion();
        }

        incrementTotalAnswers(answerIsCorrect);
        addToStatistic(getCurrentVerb(), verbAnswer);
    }

    private void setQuestion() {
        state = State.REQUEST_ANSWER;
        refreshCurrentVerb();

        int random = (int) (Math.random() * 3);
        switch (random) {
            case 0:
                firstFormText = currentVerb.getFirstForm();
                secondFormText = "";
                thirdFormText = "";
                break;
            case 1:
                firstFormText = "";
                secondFormText = currentVerb.getSecondForm();
                thirdFormText = "";
                break;
            case 2:
                firstFormText = "";
                secondFormText = "";
                thirdFormText = currentVerb.getThirdForm();
                break;
            default:
                System.out.println(random);
                throw new IllegalStateException();
        }
    }


    public String getFirstFormText() {
        return firstFormText;
    }

    public String getSecondFormText() {
        return secondFormText;
    }

    public String getThirdFormText() {
        return thirdFormText;
    }
}
