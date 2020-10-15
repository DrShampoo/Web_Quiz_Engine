package engine.model;

import java.util.List;

public class ClientAnswer {
    private List<Integer> answer;

    public ClientAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public ClientAnswer() {
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
}
