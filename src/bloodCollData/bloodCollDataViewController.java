package bloodCollData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import BloodBank.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class bloodCollDataViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private TableView<donordBean> tablee;

    @FXML
    private TextField txtMobile;

    
    Connection con;
    PreparedStatement pst;
    
    ResultSet tab;
    
    int f=0;

    void fetchTable() {
    	TableColumn<donordBean, String> mobile=new TableColumn<donordBean, String>("Mobile No.");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//same as bean property
    	mobile.setMinWidth(100);
    	
    	TableColumn<donordBean, String> name=new TableColumn<donordBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));//same as bean property
    	name.setMinWidth(100);
    	
    
    	TableColumn<donordBean, String> bgroup=new TableColumn<donordBean, String>("bgroup");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));//same as bean property
    	bgroup.setMinWidth(100);
    	
    	TableColumn<donordBean, String> dor=new TableColumn<donordBean, String>("Date of Registration");
    	dor.setCellValueFactory(new PropertyValueFactory<>("dor"));//same as bean property
    	dor.setMinWidth(100);
    	
         tablee.getColumns().clear();
    	
    	tablee.getColumns().addAll(mobile,name,bgroup,dor);
    	
    	
    }
    
    @FXML
    void allDetails(ActionEvent event) {
    	f=1;
    	ObservableList<donordBean>allRecords=getAllObjects();	
    	
    	tablee.setItems(allRecords);
    	try {
			writeExcel(allRecords);
			System.out.println("Exported to excel..");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void donorDetails(ActionEvent event) {
    	f=0;
    	
    	ObservableList<donordBean>allRecords=getAllObjects();	
    	
    	tablee.setItems(allRecords);
    	
    	try {
			writeExcel(allRecords);
			System.out.println("Exported to excel..");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    public void writeExcel( ObservableList<donordBean> list) throws Exception {
        Writer writer = null;
        try {
        	File file = new File("Users.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="mobile,name,bgroup,dor\n";
            writer.write(text);
            for (donordBean p : list)
            {
				text = p.getMobile()+ "," + p.getName()+ "," + p.getBgroup()+ "," + p.getDor()+"\n";
                writer.write(text);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
           
            writer.flush();
             writer.close();
        }
    }
    ObservableList<donordBean> getAllObjects()
	   {
 	
		    ObservableList<donordBean> ary=FXCollections.observableArrayList();
	    	PreparedStatement pst;
	    	
	    	try {
	    		if(f==1) 
	    		{
	    			pst=con.prepareStatement("select * from donors");
	    	        tab=pst.executeQuery();
	    		}
	    		else
	    		{
	    			String mn=txtMobile.getText();
	    			pst=con.prepareStatement("select distinct mobile,name,bgroup,dor from donors where mobile=?");
	    			pst.setString(1, mn);
	                tab=pst.executeQuery();
	    		}
	    		
	    	while(tab.next())
	    	{
	    		String mobile=tab.getString("mobile");
	    		String name=tab.getString("name");
	    		String bgroup=tab.getString("bgroup");
	    		String dor=String.valueOf(tab.getDate("dor"));
	    		donordBean obj=new donordBean(mobile,name,bgroup,dor);
	    		ary.add(obj);
		    		
	    		System.out.println(name+"  "+mobile+"  "+dor);
	    	}
	    	}
	    	catch(Exception exp)
	    	{ 	
	    		System.out.println(exp);
	    	}
	    	System.out.println(ary.size());
	    	return ary;
	    }
    @FXML
    void initialize() {
    	fetchTable();
        con=DatabaseConnection.doConnect();
    }

}
