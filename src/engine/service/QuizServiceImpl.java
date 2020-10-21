package engine.service;

import engine.model.Question;
import engine.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuestionRepository questionRepository;


    private ServerAnswer serverAnswer;

    @Override
    public Question getQuestionById(int id) throws NoSuchElementException {
        return questionRepository.findById(id).get();
    }

    @Override
    public ServerAnswer getServiceAnswer(int id, List<Integer> clientAnswer) {
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
    public void createQuestion(Question question) {
        questionRepository.save(question);
    }

    @Override
    public void removeQuestion(int id, String userEmail) {
        Question question = getQuestionById(id);
        if (!userEmail.equals(question.getAuthor())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        questionRepository.delete(question);
    }

    @Override
    public Page<Question> getAllQuestions(int pageNum) {
        Pageable pageable = PageRequest.of(pageNum, 10);
        return questionRepository.findAll(pageable);
    }

    private boolean checkClientAnswer(int id, List<Integer> clientAnswers) {
        boolean isCorrectAnswer = false;
        Question question = getQuestionById(id);
        List<Integer> correctAnswers = question.getAnswer();
        if ((clientAnswers == null || clientAnswers.size() == 0) &&
                (correctAnswers == null || correctAnswers.size() == 0)) {
            isCorrectAnswer = true;
        }
        if (clientAnswers != null && correctAnswers != null) {
            Collections.sort(clientAnswers);
            Collections.sort(correctAnswers);
            isCorrectAnswer = clientAnswers.equals(correctAnswers);
        }

        return isCorrectAnswer;
    }
}
