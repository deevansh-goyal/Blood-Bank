package issueBlood;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import BloodBank.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class issueBloodViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBG;

    @FXML
    private DatePicker date;

    @FXML
    private TextField txtHos;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtReason;

    @FXML
    private TextField txtUnit;

    @FXML
    private TextField txtnname;

    Connection con;
    PreparedStatement pst;
    LocalDate da;
    Date d;
    String bg;
    
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }
    
    @FXML
    void doUpdate(ActionEvent event) {
    	da=date.getValue();
    	d=Date.valueOf(da);
    	
    		try {
        		bg=comboBG.getSelectionModel().getSelectedItem();
    			pst=con.prepareStatement("update total_blood_record set "+bg+" = "+bg+"-?");
    			pst.setInt(1, Integer.parseInt(txtUnit.getText()));
    			boolean see=checkUnits();
    			if(see==false)
    				{showMsg("Not enough blood units...");
    				return;}
    			pst.executeUpdate();
    			showMsg("Data Updated in unit record ...");
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    		try {
				pst=con.prepareStatement("insert into issued values(?,?,?,?,?,?)");
				pst.setString(1, txtnname.getText());
				pst.setString(2, txtMobile.getText());
				pst.setString(3, txtHos.getText());
				pst.setString(4, txtReason.getText());
				pst.setString(5, comboBG.getSelectionModel().getSelectedItem());
				pst.setDate(6, d);
				pst.executeUpdate();
				
				showMsg("Record Updated in Issued....");
			} 
    		catch (SQLException e) 
    		{
				e.printStackTrace();
    		}
    }
    ResultSet table;
    boolean checkUnits()
    {
    	try {
    		bg=comboBG.getSelectionModel().getSelectedItem();
			pst=con.prepareStatement("Select * from total_blood_record");
			table=pst.executeQuery();
			while(table.next()) {
				if(table.getInt(bg)<Integer.parseInt(txtUnit.getText())){
					return false;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return true;
    }
    ArrayList<String>  bgroupp = new ArrayList<String>(Arrays.asList("oP","oN","aP","aN","bP","bN","abP","abN"));
    @FXML
    void initialize() {
    	con=DatabaseConnection.doConnect();
    	comboBG.getItems().addAll(bgroupp);
    }

}
