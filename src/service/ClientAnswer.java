package service;

public class ClientAnswer {
    private int[] arrayAnswers;

    public ClientAnswer(int[] arrayAnswers) {
        this.arrayAnswers = arrayAnswers;
    }

    public ClientAnswer() {
    }

    public int[] getArrayAnswers() {
        return arrayAnswers;
    }

    public void setArrayAnswers(int[] arrayAnswers) {
        this.arrayAnswers = arrayAnswers;
    }
}
