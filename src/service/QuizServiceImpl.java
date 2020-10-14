package service;

import model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {

    private List<Question> questionList = new ArrayList<>();
    private ServerAnswer serverAnswer;

    @Override
    public Question getQuestion(int id) {
        return questionList.get(id - 1);
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
        questionList.add(question);
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    private boolean checkClientAnswer(int id, int[] clientAnswers) {
        int[] arrayAnswer = questionList.get(id - 1).getAnswer();
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
