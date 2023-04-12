package BloodUnitCollection;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ResourceBundle;
import BloodBank.DatabaseConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BloodUnitViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker date;

    @FXML
    private ImageView image;

    @FXML
    private TextField txtBGNE;

    @FXML
    private TextField txtMobile;
    
    Connection con;
    PreparedStatement pst,pst2;
    LocalDate d;
    Date da;
    
    ResultSet table;
    
    void showWMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.WARNING);
    	alert.setTitle("Warning");
    	alert.setContentText(msg);
    	alert.show();
    }
    
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Updated");
    	alert.setContentText(msg);
    	alert.show();
    }
   
    @FXML
    void doClear(ActionEvent event) {
    	txtMobile.setText("");
    	txtBGNE.setText("");
    	date.setValue(null);
    	image.setImage(null);;
    	
    }

    boolean chkMN(String mn) 
    {
    	boolean jasoos=false;
    	try 
    	{
			pst=con.prepareStatement("Select * from donors where mobile=?");
			pst.setString(1, mn);
			
			table=pst.executeQuery();
			
			while(table.next()) 
			{
				jasoos=true;
			}
		}
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
        return jasoos;
    	
    }
    
    @FXML
    void doSearch(ActionEvent event) {
    	boolean isThere= chkMN(txtMobile.getText());
    	if(isThere==true) 
    	{
	    	try {
				pst=con.prepareStatement("Select distinct bgroup,picpath from donors where mobile=?");
				pst.setString(1, txtMobile.getText());
				table=pst.executeQuery();
				while(table.next()) 
				{
					txtBGNE.setText(table.getString("bgroup"));
					image.setImage(new Image(table.getString("picpath")));
				}
			}
	    	catch (SQLException e) 
	    	{
				e.printStackTrace();
			}
	    	
    	}
    	else
    		showWMsg("No Record Found...");
    	
    }
    
    @FXML
    void doUpdate(ActionEvent event) {
    	d=  date.getValue();
   	 da = Date.valueOf(d);
   	 String bg;
    	try {
			pst=con.prepareStatement("Insert into donations value(?,?,?)");
			pst.setString(1, txtMobile.getText());
			pst.setString(2, txtBGNE.getText());
			pst.setDate(3,da);
			pst.executeUpdate();
			
			showMsg("Record Updated...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	try {
    		bg=txtBGNE.getText();
			pst=con.prepareStatement("update total_blood_record set "+bg+" = "+bg+"+?");
			pst.setInt(1, 1);
			pst.executeUpdate();
			showMsg("Data Updated ...");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	
    }

    
    @FXML
    void initialize() {
    	con=DatabaseConnection.doConnect();
    }

}
