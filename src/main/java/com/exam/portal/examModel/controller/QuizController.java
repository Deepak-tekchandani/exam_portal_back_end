package com.exam.portal.examModel.controller;

import com.exam.portal.dto.StatusDTO;
import com.exam.portal.examModel.entity.QuizEntity;
import com.exam.portal.examModel.service.CategoryService;
import com.exam.portal.examModel.service.QuestionService;
import com.exam.portal.examModel.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CategoryService categoryService;

    //add category
    @PostMapping("/")
    public ResponseEntity<StatusDTO> create(@ModelAttribute QuizEntity quizEntity){
        try {
//            if(quizEntity.getCategoryEntity()!=null) {
//                CategoryEntity categoryEntity = categoryService.getById(quizEntity.getCategoryEntity().getId());
//                if (categoryEntity == null)
//                    return new ResponseEntity<>(new StatusDTO(0, "categoryEntity not found"), HttpStatus.NOT_FOUND);
//            }
            QuizEntity entity = quizService.create(quizEntity);
            return new ResponseEntity<>(new StatusDTO(1, "Quiz Updated Successfully",entity), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new StatusDTO(0, "Exception occurred! "+e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //update
    @PutMapping("/update")
    public ResponseEntity<?>update(@ModelAttribute QuizEntity quizEntity){
        try{
            QuizEntity entity=quizService.getById(quizEntity.getId());
            if(entity!=null){
                quizService.update(quizEntity);
                return new ResponseEntity<>(new StatusDTO(1, "Quiz Updated Successfully"), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new StatusDTO(0, "Quiz not found"), HttpStatus.NOT_FOUND);
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
                QuizEntity entity = this.quizService.getById(id);
                if (entity!=null) {
//                    deleteChild(id);
                    quizService.delete(id);
                    return new ResponseEntity<>(new StatusDTO(1, "Quiz deleted Successfully"), HttpStatus.OK);
                }
                return new ResponseEntity<StatusDTO>(new StatusDTO(0, "Quiz not found!"), HttpStatus.NOT_FOUND);
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
                QuizEntity entity = this.quizService.getById(id);
                if (entity!=null) {
                    return ResponseEntity.ok(entity);
                }
                return ResponseEntity.ok("Quiz Not Found");
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
            Set<QuizEntity> entities = quizService.getAll();
            return ResponseEntity.ok(entities);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Exception occurred!"+e);
        }
    }
//    public void deleteChild(Long id) {
//        Set<QuestionEntity> questionEntities = questionService.findAllByQuizEntityId(id);
//        if (questionEntities != null) {
//            for (QuestionEntity questionEntity : questionEntities) {
//                //DeleteChild if exists
////                retroFacPrerequisitDocsController.deleteChild(retroFacPrerequisitDocsEntity.getId());
//                questionEntity.setQuizEntity(null);
//                questionService.update(questionEntity);
//            }
//        }
//    }//end deleteChild
}
