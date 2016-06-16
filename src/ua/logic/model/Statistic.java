package ua.logic.model;

public class Statistic {
    private int totalAnswers = 0;
    private int correctAnswers = 0;

    public Statistic() {
    }

    public int getTotalAnswers() {
        return totalAnswers;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void incrementTotalAnswers(boolean answerIsCorrect) {
        totalAnswers++;
        if (answerIsCorrect) {
            correctAnswers++;
        }
    }

    public String getStatistic() {
        return "" + correctAnswers + "/" + totalAnswers;
    }
}
