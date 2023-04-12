package popUpHistory;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class popUpViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void chkDonorHis(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/exploreInfo/exploreInfoView.fxml")); 
							
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
    void chkIssueHis(ActionEvent event) {
    	try{
    		Parent root=FXMLLoader.load(getClass().getResource("/showAll/showAllView.fxml")); 
							
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
