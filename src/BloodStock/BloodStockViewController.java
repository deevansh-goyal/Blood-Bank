package BloodStock;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import BloodBank.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BloodStockViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtABN;

    @FXML
    private TextField txtABP;

    @FXML
    private TextField txtAN;

    @FXML
    private TextField txtAP;

    @FXML
    private TextField txtBN;

    @FXML
    private TextField txtBP;

    @FXML
    private TextField txtON;

    @FXML
    private TextField txtOP;
    
    Connection con;
    PreparedStatement pst;
    ResultSet table;
    
    void setValues()
    {
    	try {
			pst=con.prepareStatement("Select * from total_blood_record");
			table = pst.executeQuery();
			while(table.next())
			{
				txtAP.setText(String.valueOf(table.getInt("aP")));
				txtAN.setText(String.valueOf(table.getInt("aN")));
				txtBP.setText(String.valueOf(table.getInt("bP")));
				txtBN.setText(String.valueOf(table.getInt("bN")));
				txtOP.setText(String.valueOf(table.getInt("oP")));
				txtON.setText(String.valueOf(table.getInt("oN")));
				txtABP.setText(String.valueOf(table.getInt("abP")));
				txtABN.setText(String.valueOf(table.getInt("abN")));

			}
		}
    	catch (SQLException e) 
    	{
			e.printStackTrace();
		}
    }
    @FXML
    void initialize() {
    	
    	con=DatabaseConnection.doConnect();
    	setValues();
    }

}
