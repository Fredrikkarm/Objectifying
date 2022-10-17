package application;
	


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	@Override
 	public void start(Stage primaryStage) throws Exception {
		try {
			
			//Instantiating JSON
			JSONReader jsonr = new JSONReader();
			//Instantiating CSV
			CSVReader csvr = new CSVReader();
			
			
			//adding buttons and naming them Load CSV and Load JSON
			Button b1 = new Button("Load CSV");
			Button b2 = new Button("Load JSON");
			
			//EventHandler Load CSV
			//ActionEvent with handle method to add tables and fill the tables with appropriate information.
			//The PropertyValueFactory adds get and capitalize first letter to call the method getOrderDate and so on.
			EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					
					BorderPane root = new BorderPane();
					
					TableView<CSVBean> table = new TableView<>();
					//creating table column and naming it ORDERDATE
					TableColumn<CSVBean, String>orderDateCol = new TableColumn<>("ORDERDATE");
					//taking orderDate and adding get and capitalizing the first letter calling getOrderDate in CSVBean
					orderDateCol.setCellValueFactory(new PropertyValueFactory<CSVBean, String>("orderDate"));
					
					TableColumn<CSVBean, String> regionCol = new TableColumn<>("REGION");
					regionCol.setCellValueFactory(new PropertyValueFactory<CSVBean, String>("region"));
					
					TableColumn<CSVBean, String> rep1Col = new TableColumn<>("REP1");
					rep1Col.setCellValueFactory(new PropertyValueFactory<CSVBean, String>("rep1"));
					
					TableColumn<CSVBean, String>rep2Col = new TableColumn<>("REP2");
					rep2Col.setCellValueFactory(new PropertyValueFactory<CSVBean, String>("rep2"));
					
					TableColumn<CSVBean, String> itemCol = new TableColumn<>("ITEM");
					itemCol.setCellValueFactory(new PropertyValueFactory<CSVBean, String>("item"));
					
					TableColumn<CSVBean, Integer> unitsCol = new TableColumn<>("UNITS");
					unitsCol.setCellValueFactory(new PropertyValueFactory<CSVBean, Integer>("units"));
					
					TableColumn<CSVBean, String> unitCostCol = new TableColumn<>("UNITCOST");
					unitCostCol.setCellValueFactory(new PropertyValueFactory<CSVBean, String>("unitCost"));
					
					TableColumn<CSVBean, String> totalCol = new TableColumn<>("TOTAL");
					totalCol.setCellValueFactory(new PropertyValueFactory<CSVBean, String>("total"));
					
					//adding the tables i created above to table
					table.getColumns().add(orderDateCol);
					table.getColumns().add(regionCol);
					table.getColumns().add(rep1Col);
					table.getColumns().add(rep2Col);
					table.getColumns().add(itemCol);
					table.getColumns().add(unitsCol);
					table.getColumns().add(unitCostCol);
					table.getColumns().add(totalCol);
					
					//this is making so that the sizing of the table is constrained aka making the sizes fixed and even
					table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
					
					
					//reading and appending sample.csv and removes the header
					//loop for adding one row of information minus header for as long as getOrderDate has a new row of information
					for(int i=1; i<= csvr.getOrderDate().size()-1; i++) {
						int unitsInt = Integer.parseInt(csvr.getUnits().get(i));
						
					table.getItems().add(new CSVBean(
							csvr.getOrderDate().get(i), 
							csvr.getRegion().get(i), 
							csvr.getRep1().get(i), 
							csvr.getRep2().get(i),
							csvr.getItem().get(i),
							unitsInt,
							csvr.getUnitCost().get(i),
							csvr.getTotal().get(i)));
					}
				
					root.setCenter(table);
					
					Scene scene = new Scene(root, 800, 800);
					primaryStage.setTitle("TableView CSV");
					primaryStage.setScene(scene);
					primaryStage.show();
					
					
				}	

			};
			//EventHandler Load JSON
			//same thing as event1, adding an ActionEvent with a method creating tables and calling for information in get methods in JSONBean
			//instead of CSVBean
			EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					BorderPane root = new BorderPane();
					
					TableView<JSONBean> table = new TableView<>();
					//creating the table column and naming it ORDERDATE
					TableColumn<JSONBean, String> orderDateCol = new TableColumn<>("ORDERDATE");
					//calling for the method getOrderDate in JSONBean
					orderDateCol.setCellValueFactory(new PropertyValueFactory<JSONBean, String>("orderDate"));
					
					TableColumn<JSONBean, String> regionCol = new TableColumn<>("REGION");
					regionCol.setCellValueFactory(new PropertyValueFactory<JSONBean, String>("region"));
					
					TableColumn<JSONBean, String> rep1Col = new TableColumn<>("REP1");
					rep1Col.setCellValueFactory(new PropertyValueFactory<JSONBean, String>("rep1"));
					
					TableColumn<JSONBean, String> rep2Col = new TableColumn<>("REP2");
					rep2Col.setCellValueFactory(new PropertyValueFactory<JSONBean, String>("rep2"));
					
					TableColumn<JSONBean, String> itemCol = new TableColumn<>("ITEM");
					itemCol.setCellValueFactory(new PropertyValueFactory<JSONBean, String>("item"));
					
					TableColumn<JSONBean, Integer> unitsCol = new TableColumn<>("UNITS");
					unitsCol.setCellValueFactory(new PropertyValueFactory<JSONBean, Integer>("units"));
					
					TableColumn<JSONBean, String> unitCostCol = new TableColumn<>("UNITCOST");
					unitCostCol.setCellValueFactory(new PropertyValueFactory<JSONBean, String>("unitCost"));
					
					TableColumn<JSONBean, String> totalCol = new TableColumn<>("TOTAL");
					totalCol.setCellValueFactory(new PropertyValueFactory<JSONBean, String>("total"));
					
					//adding the columns to table
					table.getColumns().add(orderDateCol);
					table.getColumns().add(regionCol);
					table.getColumns().add(rep1Col);
					table.getColumns().add(rep2Col);
					table.getColumns().add(itemCol);
					table.getColumns().add(unitsCol);
					table.getColumns().add(unitCostCol);
					table.getColumns().add(totalCol);
					
					//making the positions fixed in the view
					table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
					
					
					//reading and appending sample.csv and removes the header
					//loop for adding rows to the table for as long as getOrderDate have a new row
					for(int i=1; i<= jsonr.getOrderDate().size()-1; i++) {
						int unitsInt = Integer.parseInt(csvr.getUnits().get(i));
						
					table.getItems().add(new JSONBean(
							jsonr.getOrderDate().get(i),
							jsonr.getRegion().get(i), 
							jsonr.getRep1().get(i), 
							jsonr.getRep2().get(i),
							jsonr.getItem().get(i),
							unitsInt,
							jsonr.getUnitCost().get(i),
							jsonr.getTotal().get(i)));
					}
				
					root.setCenter(table);
					
					Scene scene = new Scene(root, 800, 800);
					primaryStage.setTitle("TableView JSON");
					primaryStage.setScene(scene);
					primaryStage.show();
					
				}
			};
			
			b2.setLayoutX(100);
			//adding the events to the buttons
			b1.setOnAction(event1);
			b2.setOnAction(event2);
			
			
			//final
			primaryStage.setTitle("Fredriks program");
			Group root = new Group();
			Scene scene = new Scene (root);
			primaryStage.setScene(scene);
			
			//print button
			root.getChildren().add(b1);
			root.getChildren().add(b2);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
		}
}

