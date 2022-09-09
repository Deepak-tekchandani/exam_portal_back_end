package com.exam.portal.examModel.controller;

import com.exam.portal.dto.StatusDTO;
import com.exam.portal.examModel.entity.CategoryEntity;
import com.exam.portal.examModel.entity.QuizEntity;
import com.exam.portal.examModel.service.CategoryService;
import com.exam.portal.examModel.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private QuizService quizService;

    //add category
    @PostMapping("/")
    public ResponseEntity<?> create(@ModelAttribute CategoryEntity categoryEntity){
        try {
            CategoryEntity category = this.categoryService.create(categoryEntity);
            return ResponseEntity.ok(category);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Exception occurred!"+e);
        }
    }

    //update
    @PutMapping("/update")
    public ResponseEntity<?>update(@ModelAttribute CategoryEntity categoryEntity){
        try{
            CategoryEntity entity=categoryService.getById(categoryEntity.getId());
            if(entity!=null){
                categoryService.update(categoryEntity);
                return new ResponseEntity<>(new StatusDTO(1, "Category Updated Successfully"), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new StatusDTO(0, "Category not found"), HttpStatus.NOT_FOUND);
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
                CategoryEntity category = this.categoryService.getById(id);
                if (category!=null) {
//                    deleteChild(id);
                    categoryService.delete(id);
                    return new ResponseEntity<>(new StatusDTO(1, "Category deleted Successfully"), HttpStatus.OK);
                }
                return new ResponseEntity<StatusDTO>(new StatusDTO(0, "category not found!"), HttpStatus.NOT_FOUND);
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
                CategoryEntity category = this.categoryService.getById(id);
                if (category!=null) {
                    return ResponseEntity.ok(category);
                }
                return ResponseEntity.ok("Category Not Found");
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
            Set<CategoryEntity> entities = categoryService.getAll();
            return ResponseEntity.ok(entities);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Exception occurred!"+e);
        }
    }

//    public void deleteChild(Long id) {
//        List<QuizEntity> quizEntities = quizService.findAllByCategoryEntityId(id);
//        if (quizEntities != null) {
//            for (QuizEntity quizEntity : quizEntities) {
//                //DeleteChild if exists
////                retroFacPrerequisitDocsController.deleteChild(retroFacPrerequisitDocsEntity.getId());
//                quizEntity.setCategoryEntity(null);
//                quizService.update(quizEntity);
//            }
//        }
//    }//end deleteChild
}
