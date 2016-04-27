package ua.logic.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ua.logic.model.TestingVerbModel;

public class reviewController {
    @FXML
    private TextField firstForm;
    @FXML
    private TextField secondForm;
    @FXML
    private TextField thirdForm;
    @FXML
    private Label correctlyRatio;

    private TestingVerbModel testingVerbModel;

    @FXML
    private void initialize() {
        testingVerbModel = new TestingVerbModel();

        fillForm();
    }

    @FXML
    private void clickNext() {
        testingVerbModel.next(firstForm.getText(), secondForm.getText(), thirdForm.getText());

        fillForm();
    }

    private void fillForm() {
        firstForm.setText(testingVerbModel.getFirstFormText());
        secondForm.setText(testingVerbModel.getSecondFormText());
        thirdForm.setText(testingVerbModel.getThirdFormText());

        setDisableOnUnfilledTextEdit();

        correctlyRatio.setText(testingVerbModel.getStatistic());
    }

    private void setDisableOnUnfilledTextEdit() {
        if (thirdForm.getText().isEmpty()) {
            thirdForm.setDisable(false);
            thirdForm.requestFocus();
        } else {
            thirdForm.setDisable(true);
        }

        if (secondForm.getText().isEmpty()) {
            secondForm.setDisable(false);
            secondForm.requestFocus();
        } else {
            secondForm.setDisable(true);
        }

        if (firstForm.getText().isEmpty()) {
            firstForm.setDisable(false);
            firstForm.requestFocus();
        } else {
            firstForm.setDisable(true);
        }
    }


}
