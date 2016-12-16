package rocket.app.view;

import java.text.DecimalFormat;

import eNums.eAction;
import exceptions.RateException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import rocket.app.MainApp;
import rocketBase.RateBLL;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	private MainApp mainApp;

	ObservableList<String> loanTerm = FXCollections.observableArrayList("15 Years", "30 Years");

	@FXML
	private TextField IncomeField;
	@FXML
	private TextField DownPaymentField;
	@FXML
	private TextField ExpensesField;
	@FXML
	private TextField CreditScoreField;
	@FXML
	private TextField HouseCostField;
	@FXML
	private ComboBox<String> LoanTermBox;
	@FXML
	private Button BtuCalculate;
	@FXML
	private Label PITI;
	@FXML
	private Label error;
	@FXML
	private Label mortgage;
	@FXML
	private Label error2;
	

	@FXML
	private void initialize() {
		LoanTermBox.setValue("15 Years");
		LoanTermBox.setItems(loanTerm);
		error.setVisible(false);
		error2.setVisible(false);
	}
	
	

	// TODO - RocketClient.RocketMainController

	// Create private instance variables for:
	// TextBox - txtIncome
	// TextBox - txtExpenses
	// TextBox - txtCreditScore
	// TextBox - txtHouseCost
	// ComboBox - loan term... 15 year or 30 year
	// Labels - various labels for the controls
	// Button - button to calculate the loan payment
	// Label - to show error messages (exception throw, payment exception)

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	// TODO - RocketClient.RocketMainController
	// Call this when btnPayment is pressed, calculate the payment
	@FXML
	public void btnCalculatePayment(ActionEvent event) {
		Object message = null;
		// TODO - RocketClient.RocketMainController

		Action a = new Action(eAction.CalculatePayment);
		LoanRequest lq = new LoanRequest();
		try {
			//lq.setdRate(RateBLL.getRate(CreditScoreField.getAnchor()));
			lq.setiCreditScore(Integer.valueOf(CreditScoreField.getText()));
			//lq.setdRate(a.getLoanRequest().getdRate());
			if (LoanTermBox.getValue() == "15 Years") {
				lq.setiTerm(15 * 12);
			}else if(LoanTermBox.getValue() == "30 Years") {
				lq.setiTerm(30 * 12);
			}else{
				error.setText("Choose a loan Term");
			}
		
			lq.setExpenses(Integer.valueOf(ExpensesField.getText()));
			lq.setIncome(Integer.valueOf(IncomeField.getText()));
			lq.setiDownPayment(Integer.valueOf(DownPaymentField.getText()));
			lq.setdAmount(Integer.valueOf(HouseCostField.getText()));
		} catch (Exception e) {
			error.setText("ERROR!!!");
			error.setVisible(true);
			e.printStackTrace();
		}
		// TODO - RocketClient.RocketMainController
		// set the loan request details... rate, term, amount, credit score,
		// downpayment
		// I've created you an instance of lq... execute the setters in lq

		a.setLoanRequest(lq);

		// send lq as a message to RocketHub
		mainApp.messageSend(lq);
	}

	public void HandleLoanRequestDetails(LoanRequest lRequest) {
		error.setVisible(false);
		error2.setVisible(false);
		// TODO - RocketClient.HandleLoanRequestDetails
		// lRequest is an instance of LoanRequest.
		// after it's returned back from the server, the payment (dPayment)
		// should be calculated.
		// Display dPayment on the form, rounded to two decimal places
		DecimalFormat df = new DecimalFormat(".##");
		double payment = (-1)*(lRequest.getdPayment());
		String dpayment = df.format(payment);
		mortgage.setText("$: "+ dpayment);
		double dPITI = lRequest.getPITI();
		if(dPITI < payment){
			error.setText("House Cost too high");
			error.setVisible(true);
			String sPITI = df.format(dPITI);
			PITI.setText("$: "+ sPITI);
		}else if (dPITI >= payment){
		String sPITI = df.format(dPITI);
		PITI.setText("$: "+ sPITI);
		}else{
			error.setText("Impossible");
			error.setVisible(true);
		}
		if(dPITI < 0){
			error2.setVisible(true);
			PITI.setText("N/A");
		}
	}
}

