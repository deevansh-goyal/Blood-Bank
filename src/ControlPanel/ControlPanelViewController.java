package ControlPanel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControlPanelViewController {

    @FXML
    private ResourceBundle resources;

    
    
    @FXML
    private URL location;

    @FXML
    void chkHistory(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/popUpHistory/popUpView.fxml")); 
							
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void chkIssue(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/bloodCollData/bloodCollDataView.fxml")); 
							
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    
    @FXML
    void meetDev(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/developer/developerView.fxml")); 
							
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doDonate(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/BloodUnitCollection/BloodUnitView.fxml")); 
							
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doIssue(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/issueBlood/issueBloodView.fxml")); 
						
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void doRegister(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/BloodBank/DonorMasterView.fxml")); 
						
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void isAvailable(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/BloodStock/BloodStockView.fxml")); 
							
			Scene scene = new Scene(root);
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.show();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    @FXML
    void initialize() {

    }

}
