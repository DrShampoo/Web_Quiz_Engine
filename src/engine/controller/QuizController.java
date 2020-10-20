package engine.controller;
import engine.model.ClientAnswer;
import engine.model.Question;
import engine.service.QuizServiceImpl;
import engine.service.ServerAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class QuizController {

    @Autowired
    private QuizServiceImpl quizService;

    @GetMapping(path = "/api/quizzes/{id}")
    public Question getQuiz (@PathVariable int id) {
      try {
          return quizService.getQuestionById(id);
      } catch (NoSuchElementException e) {
          throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping(path = "/api/quizzes/{id}/solve")
    public ServerAnswer getAnswerToClient(@PathVariable int id,
                                          @RequestBody ClientAnswer clientAnswer) {
        return quizService.getServiceAnswer(id, clientAnswer.getAnswer());
    }

    @PostMapping(path = "/api/quizzes", consumes = "application/json")
    public Question addQuestion(@RequestBody Question question, Principal principal) {
        if (question.getText() == null || question.getTitle() == null || question.getOptions() == null ||
        question.getOptions().size() < 2 || question.getText().equals("") ||
        question.getTitle().equals("")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        question.setAuthor(principal.getName());
        quizService.createQuestion(question);
        return question;
    }

    @DeleteMapping(path = "/api/quizzes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuiz (@PathVariable int id, Principal principal) {
        try {
            quizService.removeQuestion(id, principal.getName());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/api/quizzes")
    public List<Question> getAllQuestions() {
        return quizService.getAllQuestions();
    }
}
