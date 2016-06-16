package ua.logic.model;

import ua.logic.entity.Verb;

enum State {SHOW_CORRECT_ANSWER, REQUEST_ANSWER}

public class TestingVerbModel {

    private State state;
    private Verb currentVerb;
    private final Questions questions = new Questions();
    private final Answers answers = new Answers();
    private int totalAnswers = 0;
    private int correctAnswers = 0;
    private String firstFormText;
    private String secondFormText;
    private String thirdFormText;

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

    public TestingVerbModel() {
        setQuestion();
    }

    public void refreshCurrentVerb() {
        questions.removeCollectAnswerFromQuestion(answers.getAnswers());

        currentVerb = questions.getQuestion();
    }

    public void incrementTotalAnswers(boolean IsCorrect) {
        totalAnswers++;
        if (IsCorrect) {
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
        answers.add(getCurrentVerb(), verbAnswer);
        if (!answerIsCorrect) {
            showCorrectAnswer();
        } else {
            setQuestion();
        }

        incrementTotalAnswers(answerIsCorrect);
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

    public Verb getCurrentVerb() {
        return currentVerb;
    }
}
