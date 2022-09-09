package com.exam.portal.examModel.controller;

import com.exam.portal.dto.StatusDTO;
import com.exam.portal.examModel.entity.QuestionEntity;
import com.exam.portal.examModel.entity.QuizEntity;
import com.exam.portal.examModel.service.QuestionService;
import com.exam.portal.examModel.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    //add category
    @PostMapping("/")
    public ResponseEntity<StatusDTO> create(@ModelAttribute QuestionEntity questionEntity){
        try {
//            if(questionEntity.getQuizEntity()!=null) {
//                QuizEntity quizEntity = quizService.getById(questionEntity.getQuizEntity().getId());
//                if (quizEntity == null)
//                    return new ResponseEntity<>(new StatusDTO(0, "Quiz not found"), HttpStatus.NOT_FOUND);
//            }
            QuestionEntity entity = questionService.create(questionEntity);
            return new ResponseEntity<>(new StatusDTO(1, "Question Updated Successfully",entity),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //update
    @PutMapping("/update")
    public ResponseEntity<?>update(@ModelAttribute QuestionEntity questionEntity){
        try{
            QuestionEntity entity=questionService.getById(questionEntity.getId());
            if(entity!=null){
                questionService.update(questionEntity);
                return new ResponseEntity<>(new StatusDTO(1, "Question Updated Successfully"), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new StatusDTO(0, "Question not found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StatusDTO> delete(@PathVariable ("id") Long id){
        try {
            if (id!=null) {
                QuestionEntity entity = this.questionService.getById(id);
                if (entity!=null) {
                    questionService.delete(id);
                    return new ResponseEntity<>(new StatusDTO(1, "Question deleted Successfully"), HttpStatus.OK);
                }
                return new ResponseEntity<StatusDTO>(new StatusDTO(0, "Question not found!"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<StatusDTO>(new StatusDTO(0, "id not found!"), HttpStatus.NOT_FOUND);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred!\n" + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //get Category by id
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable ("id") Long id){
        try {
            if (id!=null) {
                QuestionEntity entity = this.questionService.getById(id);
                if (entity!=null) {
                    return ResponseEntity.ok(entity);
                }
                return ResponseEntity.ok("Question Not Found");
            }
            return ResponseEntity.ok("Id not Found");
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Exception occurred!"+e);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        try {
            Set<QuestionEntity> entities = questionService.getAll();
            return ResponseEntity.ok(entities);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Exception occurred!"+e);
        }
    }

    @GetMapping("/quiz/{id}")
    public ResponseEntity<StatusDTO> getQuestionOfQuiz(@PathVariable("id") Long id){
//        QuizEntity quiz = new QuizEntity();
//        quiz.setId(id);
//        Set<QuestionEntity> questionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
//        return ResponseEntity.ok(questionOfQuiz);

        QuizEntity quizEntity= this.quizService.getById(id);
        Set<QuestionEntity> questions = quizEntity.getQuestionEntities();
        List list = new ArrayList(questions);
        // video (https://www.youtube.com/watch?v=UfRxYuEm6Ws&list=PL0zysOflRCel5kT-AiGyy5oMabnlilkIS&index=24)
        // video # 24 , 20:00 mints
//        if (list.size() > Integer.parseInt(quizEntity.getNumberOfQuestions()));
//        {
//            list = list.subList(0,Integer.parseInt(quizEntity.getNumberOfQuestions() + 1));
//        }
        Collections.shuffle(list);
        return new ResponseEntity<>(new StatusDTO(1, "Questionof Quiz : ",list), HttpStatus.OK);

    }

    @GetMapping(value = "/findAllByParentEntityId/{id}")
    public Set<QuestionEntity> findAllByParentEntityId(@PathVariable Long id) {
        try {
            Set<QuestionEntity> itemEntities = questionService.findAllByQuizEntityId(id);
            return itemEntities;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }//end getAllById


}
