package login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class LoginViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField id;

    @FXML
    private PasswordField pass;
    
    void showWMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.ERROR);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.WARNING);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }
    
    @FXML
    void doLogin(ActionEvent event) {
    	if(id.getText().equalsIgnoreCase("Admin@123") && pass.getText().equalsIgnoreCase("123"))
    	{
    		
    		//showMsg("Login Successfull...");
    		try{
	    		Parent root=FXMLLoader.load(getClass().getResource("/ControlPanel/ControlPanelView.fxml")); 
								//OR
				//Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("marks/card/MarksCard.fxml")); 
				Scene scene = new Scene(root);
				Stage stage=new Stage();
				stage.setScene(scene);
				stage.show();
				//to hide parent window
				Scene scene1=(Scene)id.getScene();
				   scene1.getWindow().hide();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
    		
    	}
    	else
    		showWMsg("Invalid Login...");
    }

    @FXML
    void initialize() {
        
    }

}
