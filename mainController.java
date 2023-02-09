package meTaylor.JavaFiles;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import meTaylor.Main;

public class mainController extends Exception {

  @FXML
  private Label expression;

  @FXML
  private Label result;

  private Boolean aDecimal = false;//determines if a decimal can be placed

  private static ArrayList<String> calculationHistory = new ArrayList<>();

  public void addToCalcHistory(String expression, String result){
      calculationHistory.add(expression + " = " + result);
  }
  public void addToCalcHistoryForBinary(String expression, String result){
      calculationHistory.add(expression + " = (Binary) " + result);  
  }
  public void addToCalcHistoryForHex(String expression, String result){
      calculationHistory.add(expression + " = (Hexidecimal) " + result);
  }
  public void addToCalcHistoryForOctal(String expression, String result){
      calculationHistory.add(expression + " = (Octal) " + result);  
  }
  public void addToCalcHistoryForDecimal(String expression, String result){
      calculationHistory.add(expression + " = (Decimal) " + result);  
  }
  public void addToCalcHistoryForInv(String expression, String result){
    calculationHistory.add("1 / " + expression + " = " + result);
  }

  public void insertNumber(String number) {
    expression.setText(expression.getText() + number);
  }

  public void insertOperator(String operator) {
    expression.setText(expression.getText() + operator);
  }

  public void clrExpression() {
    expression.setText("");
  }

  public void clrResult() {
    result.setText("");
  }

  public Label getExpression() {
    return expression;
  }

  public void setExpression(String Expr) {
    expression.setText(Expr);
  }

  public void delExpression() {
    expression.setText(expression.getText().substring(0, expression.getText().length() - 1));
  }

  public Label getResult() {
    return result;
  }

  public void setResult(String newResult) {
    result.setText(newResult);
  }

  public void insertDecimal(String dec) {
    expression.setText(expression.getText() + dec);
  }

  public void setBoo(Boolean b) {//handles wether a decimal can be placed
    this.aDecimal = b;
  }

  public Boolean getBoo() {
    return this.aDecimal;
  }

  public boolean isJustNum() {
    if ((getExpression().getText().indexOf("/") != -1) ||
        (getExpression().getText().indexOf("*") != -1) ||
        (getExpression().getText().indexOf("-") != -1) ||
        (getExpression().getText().indexOf("+") != -1)) {
      return false;
    }
    return true;
  }

  @FXML
  public void onMouseClick(MouseEvent event) {
    Button button = (Button) event.getSource();
    String btnValue = button.getText();

    switch (btnValue) {
      case "1":
      case "2":
      case "3":
      case "4":
      case "5":
      case "6":
      case "7":
      case "8":
      case "9":
      case "0":
        insertNumber(btnValue);
        clrResult();
        break;
      case ".":
        if (getBoo() == false) {
          insertDecimal(btnValue);
          setBoo(true);
        } else {
          insertDecimal("");
        }
        break;
      case "/":
      case "*":
      case "-":
      case "+":
        insertOperator(btnValue);
        setBoo(false);
        break;
      case "sin":
      case "cos":
      case "tan":
        insertOperator(btnValue);
        break;
      case "(":
        insertOperator(btnValue);
        break;
      case ")":
        insertOperator(btnValue);
        break;
      case "CE":
        clrExpression();
        clrResult();
        setBoo(false);
        break;
      case "<DEL":
        try {
          delExpression();
          clrResult();
        } catch (Exception e) {
          setResult("EMPTY");
        }
        break;
      case "sqrt":
        insertOperator(btnValue);
        break;
      case "1/x":
        try {
          if (getResult().getText() == ""&&getExpression().getText()=="") {
            insertOperator("1/");
          }else if (getExpression().getText() != "") {
            double result = EvaluateString.evaluate(getExpression().getText());
            double rezult = 1/result;
            setResult(String.valueOf(rezult));
            addToCalcHistoryForInv(expression.getText(), this.result.getText());
            updateHistoryWindow();
          }else {
            double inv = 1 / Double.valueOf(getResult().getText());
            setResult(String.valueOf(inv));
            //setExpression("1/" + String.valueOf(EvaluateString.evaluate(getExpression().getText())));
            addToCalcHistoryForInv(expression.getText(), this.result.getText());
            updateHistoryWindow();
          }
        } catch (Exception e) {
          setResult("ERROR");
        }
        break;
      case "x^x":
        if(getExpression().getText()==""){
          setResult("Enter Base Number");
        }else{
          insertOperator("^"); 
        }
        break;
      case "Binary":
        try {
            if(getResult().getText()=="" && getExpression().getText()==""){
              setResult("ENTER # to CONVERT");
            }else if(getResult().getText()=="ENTER # to CONVERT"){
              setResult("ENTER # to CONVERT");
            }else {
              double result = EvaluateString.evaluate(getExpression().getText());
              int res = (int) result;
              setResult(Integer.toBinaryString(Integer.parseInt(String.valueOf(res))));
              addToCalcHistoryForBinary(expression.getText(), this.result.getText());
              updateHistoryWindow();//update HISTORY with conversion
            }       
          } catch (Exception e) {
            setResult("ERROR");
          }
        break;
      case "Hex":
        try {
          if(getResult().getText()=="" && getExpression().getText()==""){
            setResult("ENTER # to CONVERT");
          }else if(getResult().getText()=="ENTER # to CONVERT"){
            setResult("ENTER # to CONVERT");
          }else {
            double result = EvaluateString.evaluate(getExpression().getText());
            int res = (int) result;
            setResult(Integer.toHexString(Integer.parseInt(String.valueOf(res))));
            addToCalcHistoryForHex(expression.getText(), this.result.getText());
            updateHistoryWindow();//update HISTORY with conversion
          }   
        } catch (Exception e) {
            setResult("ERROR");
        }
        break;
      case "Octal":
      try {
        if(getResult().getText()=="" && getExpression().getText()==""){
          setResult("ENTER # to CONVERT");
        }else if(getResult().getText()=="ENTER # to CONVERT"){
          setResult("ENTER # to CONVERT");
        }else {
          double result = EvaluateString.evaluate(getExpression().getText());
          int res = (int) result;
          setResult(Integer.toOctalString(Integer.parseInt(String.valueOf(res))));
          addToCalcHistoryForOctal(expression.getText(), this.result.getText());
          updateHistoryWindow();//update HISTORY with conversion
        }   
      } catch (Exception e) {
          setResult("ERROR");
      }
      break;
      case "Decimal":
        try {
          if(getResult().getText()=="" && getExpression().getText()==""){
            setResult("ENTER # to CONVERT");
          }else if(getResult().getText()=="ENTER # to CONVERT"){
            setResult("ENTER # to CONVERT");
          }else{
            Double result = EvaluateString.evaluate(getExpression().getText());
            setResult(String.valueOf(result));
            addToCalcHistoryForDecimal(expression.getText(), this.result.getText());
            updateHistoryWindow();//update HISTORY with conversion
          }
        } catch (Exception e) {
          setResult("ERROR");
        }
        break;
      case "=":
        try {
          double result = EvaluateString.evaluate(getExpression().getText());
          setResult(String.valueOf(result));
          addToCalcHistory(getExpression().getText(), String.valueOf(result));  
          updateHistoryWindow();
          setBoo(false);
        } catch (Exception e) {
          setResult("ERROR");
        }
        break;
      case "HISTORY":
        openHistoryWindow();
        break;
        // End Switch
    }
  }

  public void openHistoryWindow() {//when HISTORY button is clicked
    
    try{
      updateHistoryWindow();  
      Main.getHistoryStage().show(); //show history window with .show()
    }catch(Exception e){
      System.out.println(e);
    }
 }

  public void updateHistoryWindow() {//updates values to history window
    
    try{
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/meTaylor/FXML/History.fxml"));
      Parent root = loader.load();
      Main.getHistoryStage().setScene(new Scene(root, 400, 525));
      
      historyController historycontroller = loader.getController();
      historycontroller.intializeCalc(calculationHistory);//Using .intializeCalc method from historyController
    }catch(IOException e){
      System.out.println(e);
    }
  }

  public static void clearCalculationHistory(){
    mainController.calculationHistory.clear();
  }
}