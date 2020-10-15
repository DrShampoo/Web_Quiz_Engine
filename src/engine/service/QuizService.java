package engine.service;

import engine.model.Question;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizService {

    Question getQuestionById(int id);

    ServerAnswer getServiceAnswer(int id, List<Integer> clientAnswer);

    void createQuestion(Question question);

    List<Question> getAllQuestions();
}
