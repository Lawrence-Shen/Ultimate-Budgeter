//Lawrence Shen. 20304174. August 14th, 2023
//A basic budget management system that calculates the total expenses,
//the percent of total expenses that each expense is equal to,
//and the amount of the income entered that should go towards needs, wants and savings
package application;
	
import java.text.DecimalFormat;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ExpandedBudget.fxml"));
			loader.setController(this);
			Parent root = loader.load();
			Scene scene = new Scene(root,600,400);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML Button btnVisualize;
	@FXML Button btnPlan;
	@FXML Button btnTotal;
	@FXML TextField txtIncome;
	@FXML TextField txtEducation;
	@FXML TextField txtHousing;
	@FXML TextField txtFood;
	@FXML TextField txtTransportation;
	@FXML Label lblResult;
	
	public void initialize(){
		/**
		 * Calculates total expenses for the "Calculate Total Expenses" button
		 */
		btnTotal.setOnAction(event -> {
			double educationNum = Double.parseDouble(txtEducation.getText());
			double housingNum = Double.parseDouble(txtHousing.getText());
			double foodNum = Double.parseDouble(txtFood.getText());
			double transportationNum = Double.parseDouble(txtTransportation.getText());
			double sum = educationNum + housingNum + foodNum + transportationNum;
			
			lblResult.setText("Total Expenses: " + String.valueOf(sum));	
		});
		
		/**
		 * Calculates the percent of total expenses that each expense is for the "Visualize Your Budget" button
		 */
		btnVisualize.setOnAction(event -> {
			DecimalFormat decimalFormat = new DecimalFormat("#.##%");
			double educationNum = Double.parseDouble(txtEducation.getText());
			double housingNum = Double.parseDouble(txtHousing.getText());
			double foodNum = Double.parseDouble(txtFood.getText());
			double transportationNum = Double.parseDouble(txtTransportation.getText());
			double totalExpenses = educationNum + housingNum + foodNum + transportationNum;
			double educationPercent = (educationNum/totalExpenses);
			double housingPercent = (housingNum/totalExpenses);
			double foodPercent = (foodNum/totalExpenses);
			double transportationPercent = (transportationNum/totalExpenses);
			
			lblResult.setText("Education: " + decimalFormat.format(educationPercent)
					+ "\n" + "Housing: " + decimalFormat.format(housingPercent)
					+ "\n" + "Food: " + decimalFormat.format(foodPercent)
					+ "\n" + "Transportation: " + decimalFormat.format(transportationPercent));
		});
		
		/**
		 * Calculates the percent of income that should go towards needs, wants, and savings.
		 * 50% towards needs, 30% towards wants, and 20% to savings
		 */
		btnPlan.setOnAction(event -> {
			double income = Double.parseDouble(txtIncome.getText());
			double needs = income * 0.5;
			double wants = income * 0.3;
			double savings = income * 0.2;
			
			lblResult.setText("Needs: $" + String.valueOf(needs)
					+ "\nWants: $" + String.valueOf(wants)
					+ "\nSavings: $" + String.valueOf(savings));
		});
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
