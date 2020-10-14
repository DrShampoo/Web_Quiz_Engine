package service;

import model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import repository.QuestionRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    private ServerAnswer serverAnswer;

    @Override
    public Question getQuestionById(int id) {
        return questionRepository.findById(id).get();
    }

    @Override
    public ServerAnswer getServiceAnswer(int id, int[] clientAnswer) {
        try {
            if (checkClientAnswer(id, clientAnswer)) {
                serverAnswer = new ServerAnswer(true, "Congratulations, you're right!");
            } else {
                serverAnswer = new ServerAnswer(false, "Wrong answer! Please, try again.");
            }
            return serverAnswer;
        } catch (IndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void addQuestionToList(Question question) {
        questionRepository.save(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    private boolean checkClientAnswer(int id, int[] clientAnswers) {
        int[] arrayAnswer = getQuestionById(id - 1).getAnswer();
        if ((clientAnswers == null || clientAnswers.length == 0) &&
                (arrayAnswer == null || arrayAnswer.length == 0)) {
            return true;
        }
        if (clientAnswers != null && arrayAnswer != null) {
            Arrays.sort(clientAnswers);
            Arrays.sort(arrayAnswer);
        }
        return Arrays.equals(clientAnswers, arrayAnswer);
    }
}
