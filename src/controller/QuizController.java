package controller;


import org.springframework.beans.factory.annotation.Autowired;
import service.QuizService;
import service.ServerAnswer;
import model.Question;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import service.ClientAnswer;

import java.util.List;


@RestController()
public class QuizController {

    @Autowired
    private QuizService quizService;

    @GetMapping(path = "/api/quizzes/{id}")
    public Question getQuiz (@PathVariable int id) {
      try {
          return quizService.getQuestionById(id);
      } catch (IndexOutOfBoundsException e) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping(path = "/api/quizzes/{id}/solve")
    public ServerAnswer getAnswer(@PathVariable int id,
                                  @RequestBody ClientAnswer clientAnswer) {
        return quizService.getServiceAnswer(id, clientAnswer.getArrayAnswers());
    }

    @PostMapping(path = "/api/quizzes", consumes = "application/json")
    public Question addQuestion(@RequestBody Question question) {
        if (question.getText() == null || question.getTitle() == null || question.getOptions() == null ||
        question.getOptions().length < 2 || question.getText().equals("") ||
        question.getTitle().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        quizService.addQuestionToList(question);
        question.setId(quizService.getAllQuestions().size());
        return question;
    }

    @GetMapping(path = "/api/quizzes")
    public List<Question> getAllQuestions() {
        return quizService.getAllQuestions();
    }
}
