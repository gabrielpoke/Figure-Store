package com.figure.store.repositorytests.product;

import com.figure.store.domain.model.product.ActionFigure;
import com.figure.store.domain.repository.product.ActionFigureRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * author LucasDonizeti
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ActionFigureTests {
    @Autowired
    ActionFigureRepository actionFigureRepository;
    @Rule
    public ExpectedException thrown= ExpectedException.none();




    @Test
    public void SaveTest() {
        ActionFigure actionFigure=generateActionFigure();

        actionFigureRepository.save(actionFigure);

        ActionFigure actionFigureReturned = actionFigureRepository.getOne((long) 8000);


        Assertions.assertNotNull(actionFigureReturned);
        Assertions.assertEquals(actionFigureReturned.getId(), actionFigure.getId());
    }

    @Test
    public void DeleteTest() {
        ActionFigure actionFigure=generateActionFigure();

        actionFigureRepository.save(actionFigure);

        ActionFigure actionFigureReturned = actionFigureRepository.getOne((long) 8000);

        Assertions.assertNotNull(actionFigureReturned);

        actionFigureRepository.deleteById(actionFigureReturned.getId());

        Assertions.assertFalse(actionFigureRepository.existsById(actionFigureReturned.getId()));
    }
    @Test
    public void PutTest() {
        ActionFigure actionFigure=generateActionFigure();

        actionFigureRepository.save(actionFigure);

        ActionFigure actionFigureReturned = actionFigureRepository.getOne((long) 8000);

        Assertions.assertNotNull(actionFigureReturned);

        actionFigureReturned.setName("Vegeta SS2");

        actionFigureRepository.save(actionFigureReturned);

        ActionFigure actionFigureAltered = actionFigureRepository.getOne((long) 8000);

        Assertions.assertNotNull(actionFigureAltered);

        Assertions.assertEquals(actionFigureAltered.getName(), actionFigureReturned.getName());
    }

    private ActionFigure generateActionFigure(){
        ActionFigure actionFigure=new ActionFigure();
        actionFigure.setId((long) 8000);
        actionFigure.setName("Vegeta");
        actionFigure.setPrice(8000.0);
        actionFigure.setShortDescription("its over 8000!");
        actionFigure.setLongDescription("the best action figure ever!");
        actionFigure.setApproximateHeight(1);
        actionFigure.setRecommendedAge(18);
        return actionFigure;
    }
}
