package meTaylor.JavaFiles;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class historyController{
  
  @FXML
  private ListView <String> historyList;

  public void intializeCalc(ArrayList<String> calculationHistory) {
    calculationHistory.forEach((calc)->{
      historyList.getItems().add(calc);
    });
  }

  @FXML
  public void onMouseClick(MouseEvent event){
    Button clkd = (Button) event.getSource();
    String btnValue = clkd.getText();

    switch(btnValue){
      case "Clear History":
        historyList.getItems().clear();
        mainController.clearCalculationHistory();
      break;
    }
  }
}