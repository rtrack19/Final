package rocket.app.view;

import org.apache.poi.ss.formula.functions.FinanceLib;

import eNums.eAction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import rocket.app.MainApp;
import rocketCode.Action;
import rocketData.LoanRequest;

public class MortgageController {

	ObservableList<String> termlist = FXCollections.observableArrayList("15 years","30 years"); 
	@FXML TextField txtCreditScore;
	@FXML TextField txtMortgageAmt;
	@FXML TextField txtIncome;
	@FXML TextField txtExpenses;
	@FXML TextField txtHouseCost;
	@FXML ChoiceBox cmbTerm;
	@FXML private void initialize(){
		cmbTerm.setValue(termlist);
	}
	private TextField txtNew;
	
	private MainApp mainApp;
	

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}
	
	@FXML
	public void btnCalculatePayment(ActionEvent event)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message Here...");
		alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText(txtNew.getText());
		alert.showAndWait().ifPresent(rs -> {
		    if (rs == ButtonType.OK) {
		        System.out.println("Pressed OK.");
		    }
		});
	}
	
	public void HandleLoanRequestDetails(LoanRequest lRequest)
	{
		
		double payment = FinanceLib.pmt(lRequest.getdRate(), lRequest.getiTerm(),lRequest.getiDownPayment(), lRequest.getdAmount(), true);
		System.out.println(payment);
		
		
	}
}
