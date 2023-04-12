module BloodBankAssistant {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.graphics;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens BloodBank to javafx.graphics, javafx.fxml;
	opens BloodUnitCollection to javafx.graphics, javafx.fxml;
	opens BloodStock to javafx.graphics, javafx.fxml;
	opens ControlPanel to javafx.graphics, javafx.fxml;
	opens login to javafx.graphics, javafx.fxml;
	opens issueBlood to javafx.graphics, javafx.fxml;
	opens showAll to javafx.graphics, javafx.fxml,javafx.base;
	opens exploreInfo to javafx.graphics, javafx.fxml,javafx.base;
	opens bloodCollData to javafx.graphics, javafx.fxml,javafx.base;
	opens developer to javafx.graphics, javafx.fxml,javafx.base;
	opens popUpHistory to javafx.graphics, javafx.fxml,javafx.base;
	
}
