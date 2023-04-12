package showAll;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import BloodBank.DatabaseConnection;
import bloodCollData.donordBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

public class showAllViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> comboBG;

    @FXML
    private DatePicker date;

    @FXML
    private TableView<IssueBean> tablegrid;

    ResultSet table;
	Connection con;
	   
    
   
	@FXML
    void doShow(ActionEvent event) {
    	TableColumn<IssueBean, String> nname=new TableColumn<IssueBean, String>("Needy Name");
    	nname.setCellValueFactory(new PropertyValueFactory<>("nname"));//same as bean property
    	nname.setMinWidth(100);
    	
    	TableColumn<IssueBean, String> mobile=new TableColumn<IssueBean, String>("Mobile No.");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//same as bean property
    	mobile.setMinWidth(100);
    	
    	TableColumn<IssueBean, String> hospital=new TableColumn<IssueBean, String>("Hospital");
    	hospital.setCellValueFactory(new PropertyValueFactory<>("hospital"));//same as bean property
    	hospital.setMinWidth(100);
    	
    	TableColumn<IssueBean, String> reason=new TableColumn<IssueBean, String>("Reason");
    	reason.setCellValueFactory(new PropertyValueFactory<>("reason"));//same as bean property
    	reason.setMinWidth(100);
    	
    	TableColumn<IssueBean, String> doi=new TableColumn<IssueBean, String>("Date of Issue");
    	doi.setCellValueFactory(new PropertyValueFactory<>("doi"));//same as bean property
    	doi.setMinWidth(100);
    	
    	tablegrid.getColumns().clear();
    	tablegrid.getColumns().addAll(nname,mobile,hospital,reason,doi);
    	
    	ObservableList<IssueBean>allRecords=getAllObjects();	
    	

    	tablegrid.setItems(allRecords);
    	
    	try {
			writeExcel(allRecords);
			System.out.println("Exported to excel..");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
      
	public void writeExcel( ObservableList<IssueBean> list) throws Exception {
        Writer writer = null;
        try {
        	File file = new File("Users.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="nname,mobile,hospital,reason,doi\n";
            writer.write(text);
            for (IssueBean p : list)
            {
				text = p.getMobile()+ "," + p.getNname()+ "," + p.getMobile()+ "," + p.getHospital()+"," + p.getReason()+"," + p.getDoi()+"\n";
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
	ObservableList<IssueBean> getAllObjects()
	   {
		   String bg= comboBG.getSelectionModel().getSelectedItem();
		    ObservableList<IssueBean> ary=FXCollections.observableArrayList();
	    	PreparedStatement pst;
	    	try {
	    		if(bg.equals("All"))
	    		{
	    			pst=con.prepareStatement("select * from issued where doi= ? ");
	    			pst.setDate(1, Date.valueOf(date.getValue()));
			    	table=pst.executeQuery();
	    		}
	    		else
	    		{
			    	pst=con.prepareStatement("select * from issued where doi= ? and bgroup=?"+bg);
			    	pst.setDate(1, Date.valueOf(date.getValue()));
			    	table=pst.executeQuery();
	    		}
	    	while(table.next())
	    	{
	    		String nname=table.getString("nname");//col wala name
	    		String mobile=table.getString("mobile");
	    		String hospital=table.getString("hospital");
	    		String reason=table.getString("reason");
	    		String doi=String.valueOf(table.getDate("doi"));
	    		IssueBean obj=new IssueBean(nname,mobile,hospital,reason,doi);
	    		ary.add(obj);
 		    		
	    		System.out.println(nname+"  "+mobile+"  "+hospital+"  "+reason+"  "+doi);
	    	}
	    	}
	    	catch(Exception exp)
	    	{ 	
	    		System.out.println(exp);
	    	}
	    	System.out.println(ary.size());
	    	return ary;
	    }
	   
	ArrayList<String>  bgroupp = new ArrayList<String>(Arrays.asList("oP","oN","aP","aN","bP","bN","abP","abN","All"));
    @FXML
    void initialize() {
    	 con=DatabaseConnection.doConnect();
    	 comboBG.getItems().addAll(bgroupp);
    }

}
