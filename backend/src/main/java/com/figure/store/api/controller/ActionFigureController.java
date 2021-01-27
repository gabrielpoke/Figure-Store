package com.figure.store.api.controller;

import com.figure.store.domain.model.product.ActionFigure;
import com.figure.store.domain.service.product.ActionFigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * author LucasDonizeti
 */

@RestController
@RequestMapping("/action-figures")
public class ActionFigureController {

    private final ActionFigureService actionFigureService;

    @Autowired
    public ActionFigureController(ActionFigureService actionFigureService){
        this.actionFigureService=actionFigureService;
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(actionFigureService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(actionFigureService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ActionFigure actionFigure){
        System.out.println(actionFigure.getName());
        return new ResponseEntity<>(actionFigureService.save(actionFigure), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        actionFigureService.removeById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateActionFigures(@Valid @RequestBody ActionFigure actionFigure) {
        return new ResponseEntity<>(actionFigureService.updateActionFigures(actionFigure),HttpStatus.OK);
    }

}
