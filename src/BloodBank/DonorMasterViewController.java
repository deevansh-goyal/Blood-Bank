package BloodBank;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class DonorMasterViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboGender;

    @FXML
    private ImageView image;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

    @FXML
    private ComboBox<String> comboBG;

    @FXML
    private TextField txtDisease;

    @FXML
    private TextField txtMN;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTown;
    
    Connection con;
    PreparedStatement pst;
    
    ResultSet table;
    
    File file;
    
    
    void showMsg(String msg)
    {
    	Alert alert=new Alert(AlertType.INFORMATION);
    	alert.setTitle("Title");
    	alert.setContentText(msg);
    	alert.show();
    }
    
    @FXML
    void doBrowse(ActionEvent event) 
    {
    	FileChooser fc = new FileChooser();
    	file = fc.showOpenDialog(image.getScene().getWindow());
    	try
    	{
    		URL url = file.toURI().toURL();
    		image.setImage(new Image(url.toExternalForm()));
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
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
    void doCancel(ActionEvent event) 
    {
    	
		   try 
		    {
				pst=con.prepareStatement("delete from donors where mobile=?");
				pst.setString(1, txtMN.getText());
				
				int count=pst.executeUpdate();
				
				if(count==0)
				{
					showMsg("Not registered...");
				}
				else
				{
					showMsg("Registration cancelled....");
				}
			} 
		   	catch (SQLException e) 
		   	{
				e.printStackTrace();
			}
		    	
    }

    @FXML
    void doClear(ActionEvent event) 
    {
    	txtMN.setText("");
    	txtName.setText("");
    	comboGender.getSelectionModel().clearSelection();
    	txtAddress.setText("");
    	comboBG.getSelectionModel().clearSelection();
    	txtAge.setText("");
    	txtTown.setText("");
    	txtDisease.setText("");
    	image.setImage(new Image(""));
    	
    }
    
    boolean findMN(String mn) 
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
				txtName.setText(table.getString("name"));
				txtAge.setText(String.valueOf(table.getInt("Age")));
				txtAddress.setText(table.getString("address"));
				txtTown.setText(table.getString("city"));
				comboBG.getSelectionModel().select(table.getString("bgroup"));
				txtDisease.setText(table.getString("disease"));
				comboGender.getSelectionModel().select(table.getString("gender"));
				image.setImage(new Image(table.getString("picpath")));
				break;
				
			}
		} catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	return jasoos;
    	
    }
    
    @FXML
    void doFind(ActionEvent event) 
    {
    	boolean isThere=findMN(txtMN.getText());
    	if(isThere==false)
    	{
    		showMsg("No record found...");
    	}
    	
    }

    @FXML
    void doRegister(ActionEvent event) 
    {
    	String mobileN=txtMN.getText();
    	if(chkMN(mobileN)==true)
    		{
    		showMsg("Mobile No. already Registered...");
    		return;
    		}
    	try 
    	{
			pst= con.prepareStatement("Insert into donors values(?,?,?,?,?,?,?,?,?,current_date())");
			pst.setString(1, txtMN.getText());
			pst.setString(2, file.getAbsolutePath());
			pst.setString(3, txtName.getText());
			pst.setString(4, comboGender.getSelectionModel().getSelectedItem());
			pst.setString(5, txtAddress.getText());
			pst.setString(6, txtTown.getText());
			pst.setString(7, comboBG.getSelectionModel().getSelectedItem());
			pst.setInt(8, Integer.parseInt(txtAge.getText()));
			pst.setString(9, txtDisease.getText());
			pst.executeUpdate();
			showMsg("Registration Successfull....");
			
		} 
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    }

    @FXML
    void doUpdate(ActionEvent event) 
    {
    	String mobileN=txtMN.getText();
    	if(chkMN(mobileN)==true) 
    	{
	    	try 
	    	{
				pst=con.prepareStatement("update donors set name=?,picpath=?,gender=?,age=?,address=?,bgroup=?,disease=?,city=? where mobile=?");
				pst.setString(9, mobileN);
				pst.setString(1, txtName.getText());
				pst.setString(2, file.getAbsolutePath());
				pst.setString(3, comboGender.getSelectionModel().getSelectedItem());
				pst.setInt(4, Integer.parseInt(txtAge.getText()));
				pst.setString(5, txtAddress.getText());
				pst.setString(6, comboBG.getSelectionModel().getSelectedItem());
				pst.setString(7, txtDisease.getText());
				pst.setString(8, txtTown.getText());
				pst.executeUpdate();
				showMsg("Updated....");
				
			} 
	    	catch (SQLException e) 
	    	{
				e.printStackTrace();
	    	}
    	}
    	else
    		showMsg("Mobile No. not registered....Plz Register First");
    }
    
    ArrayList<String>  genders = new ArrayList<String>(Arrays.asList("Male","Female","Trans"));
    ArrayList<String>  bgroupp = new ArrayList<String>(Arrays.asList("oP","oN","aP","aN","bP","bN","abP","abN"));
    @FXML
    void initialize() 
    {
    	con=DatabaseConnection.doConnect();
    	comboGender.getItems().addAll(genders);
    	comboBG.getItems().addAll(bgroupp);

    }

}
