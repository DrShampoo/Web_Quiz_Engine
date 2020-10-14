package service;

import model.Question;

public interface QuizService {
    Question getQuestion(int id);
    ServerAnswer getServiceAnswer(int id, int[] clientAnswer);
    void addQuestionToList(Question question);
}
