package ch.makery.address;

import java.io.IOException;

import ch.makery.address.model.Person;
import ch.makery.address.view.PersonOverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	/**
	 * La informacion de las Personas como una observable List
	 */
	private ObservableList<Person> personData = FXCollections.observableArrayList();
	
    /**
     * Constructor
     */
	public MainApp() {
        // Datos de ejemplo
        personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Anna", "Best"));
        personData.add(new Person("Stefan", "Meier"));
        personData.add(new Person("Martin", "Mueller"));
    }
	
	/**
	 * Retorna la informacion como una observable List de Personas
	 * @return
	 */
	public ObservableList<Person> getPersonData() {
        return personData;
    }
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AddressApp");
		initRootLayout();
		showPersonOverview();
	}
	
	/**
	 * Inicializa el root layout
	 * @param args
	 */
	public void initRootLayout(){
		try {
			// Carga el root layout desde el archivo fxml
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			// Mostrar el scene que contiene el root layout
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Muestra el detalle de la persona dentro del root layout
	 * @param args
	 */
	
    public void showPersonOverview() {
        try {
            // Carga el detalle de la persona.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Configura el detalle de la persona en el centro del root layout
            rootLayout.setCenter(personOverview);
            
            // Entrega a la aplicacion principal acceso al controller 
            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }	
	
    /**
     * Retorna el main stage
     * @param args
     */
    public Stage getPrimaryStage(){
    	return primaryStage;
    }
    
	public static void main(String[] args) {
		launch(args);
	}
}
