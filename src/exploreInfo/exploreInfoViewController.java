package exploreInfo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class exploreInfoViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<DonorBean> table2;

    @FXML
    private ComboBox<String> comboBG;

    Connection con;
    PreparedStatement pst;
    
    ResultSet tab;
    
    int f=0;
    void fetchTable() {
    	TableColumn<DonorBean, String> mobile=new TableColumn<DonorBean, String>("Mobile No.");
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));//same as bean property
    	mobile.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> name=new TableColumn<DonorBean, String>("Name");
    	name.setCellValueFactory(new PropertyValueFactory<>("name"));//same as bean property
    	name.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> gender=new TableColumn<DonorBean, String>("Gender");
    	gender.setCellValueFactory(new PropertyValueFactory<>("gender"));//same as bean property
    	gender.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> address=new TableColumn<DonorBean, String>("address");
    	address.setCellValueFactory(new PropertyValueFactory<>("address"));//same as bean property
    	address.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> city=new TableColumn<DonorBean, String>("city");
    	city.setCellValueFactory(new PropertyValueFactory<>("city"));//same as bean property
    	city.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> bgroup=new TableColumn<DonorBean, String>("bgroup");
    	bgroup.setCellValueFactory(new PropertyValueFactory<>("bgroup"));//same as bean property
    	bgroup.setMinWidth(100);
    	
    	TableColumn<DonorBean, Integer> age=new TableColumn<DonorBean, Integer>("Age");
    	age.setCellValueFactory(new PropertyValueFactory<>("age"));//same as bean property
    	age.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> disease=new TableColumn<DonorBean, String>("Disease");
    	disease.setCellValueFactory(new PropertyValueFactory<>("disease"));//same as bean property
    	disease.setMinWidth(100);
    	
    	TableColumn<DonorBean, String> dor=new TableColumn<DonorBean, String>("Date of Registration");
    	dor.setCellValueFactory(new PropertyValueFactory<>("dor"));//same as bean property
    	dor.setMinWidth(100);
    	
         table2.getColumns().clear();
    	
    	table2.getColumns().addAll(mobile,name,gender,address,city,bgroup,age,disease,dor);
    	
    }
    @FXML
    void doFetch(ActionEvent event) {
    
    	ObservableList<DonorBean>allRecords=getAllObjects();	

    	table2.setItems(allRecords);
    	
    	try {
			writeExcel(allRecords);
			System.out.println("Exported to excel..");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    void doShowAll(ActionEvent event) {
    	f=1;
    
    	ObservableList<DonorBean>allRecords=getAllObjects();	
    	
    	table2.setItems(allRecords);
    	
    	try {
			writeExcel(allRecords);
			System.out.println("Exported to excel..");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void writeExcel( ObservableList<DonorBean> list) throws Exception {
        Writer writer = null;
        try {
        	File file = new File("Users.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text="mobile,name,gender,address,city,bgroup,age,disease,dor\n";
            writer.write(text);
            for (DonorBean p : list)
            {
				text = p.getMobile()+ "," + p.getName()+ "," + p.getGender()+ "," + p.getAddress()+ "," + p.getBgroup()+ 
						"," + p.getAge()+ "," + p.getDisease()+ "," + p.getDor()+"\n";
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
    ObservableList<DonorBean> getAllObjects()
	   {
    	
		    ObservableList<DonorBean> ary=FXCollections.observableArrayList();
	    	PreparedStatement pst;
	    	
	    	try {
	    		if(f==1)
	    		{	    	pst=con.prepareStatement("select * from donors");
	    	                tab=pst.executeQuery();
	    		}
	    		else
	    		{
	    			String bg= comboBG.getSelectionModel().getSelectedItem();
	    			pst=con.prepareStatement("select * from donors where bgroup = ?");
	    	    	pst.setString(1, bg);
	    	    	tab=pst.executeQuery();
	    		}
	    	while(tab.next())
	    	{
	    		String mobile=tab.getString("mobile");
	    		String name=tab.getString("name");
	    		String gender=tab.getString("gender");
	    		String address=tab.getString("address");
	    		String city=tab.getString("city");
	    		String bgroup=tab.getString("bgroup");
	    		int age=tab.getInt("age");
	    		String disease=tab.getString("disease");
	    		String dor=String.valueOf(tab.getDate("dor"));
	    		DonorBean obj=new DonorBean(mobile,name,gender,address,city,bgroup,age,disease,dor);
	    		ary.add(obj);
		    		
	    		System.out.println(name+"  "+mobile+"  "+gender+"  "+address+"  "+address+"  "+bgroup+"  "+age+"  "+disease+"  "+dor);
	    	}
	    	}
	    	catch(Exception exp)
	    	{ 	
	    		System.out.println(exp);
	    	}
	    	System.out.println(ary.size());
	    	return ary;
	    }
 
    ArrayList<String>  bgroupp = new ArrayList<String>(Arrays.asList("oP","oN","aP","aN","bP","bN","abP","abN"));
    @FXML
    void initialize() {
    	fetchTable();
        con=DatabaseConnection.doConnect();
        comboBG.getItems().addAll(bgroupp);
    }

}
