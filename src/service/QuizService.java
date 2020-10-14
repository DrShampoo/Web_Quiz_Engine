package service;

import model.Question;

import java.util.List;

public interface QuizService {

    Question getQuestionById(int id);

    ServerAnswer getServiceAnswer(int id, int[] clientAnswer);

    void addQuestionToList(Question question);

    List<Question> getAllQuestions();
}
