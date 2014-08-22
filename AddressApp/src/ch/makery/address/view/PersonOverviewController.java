package ch.makery.address.view;

import org.controlsfx.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ch.makery.address.MainApp;
import ch.makery.address.model.Person;
import ch.makery.address.util.DateUtil;

public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;
    
    // Referencia a la clase principal de la aplicacion
    private MainApp mainApp;
    
    /**
     * Constructor, es llamado antes que el metodo initialize()
     */
    public PersonOverviewController() {
    }
    
    /**
     * Inicializa el controller. Este metodo es automaticamente llamado
     * despues de que el archivo fxml esta cargado
     */
    @FXML
    private void initialize() {
        // Inicializa la tabla de Personas con dos columnas.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        // Limpiar la informacion de personas
        showPersonDetails(null);

        // Verificar cambios en la tabla y cambiar la informacion personal
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }
    
    /**
     * Es llamado por el main principal para auto-referenciarse
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Agrega la información de observable List a la tabla
        personTable.setItems(mainApp.getPersonData());
    }
    
    /**
     * Completa todos los campos de texto con los datos de una persona
     * Si el dato es null, todos los campso de texto son borrados
     * @param person entidad persona que puede ser null
     */
    private void showPersonDetails(Person person) {
        if (person != null) {
            // completa los campos con los datos de la persona.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            streetLabel.setText(person.getStreet());
            postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            cityLabel.setText(person.getCity());
            birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            // Si Person es null, limpia los campos.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetLabel.setText("");
            postalCodeLabel.setText("");
            cityLabel.setText("");
            birthdayLabel.setText("");
        }
    }
    
    /**
     * Llamado cuando el usuario clickea el boton Delete
     */
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	personTable.getItems().remove(selectedIndex);
        } else {
        	Dialogs.create()
            .title("No Selection")
            .masthead("No Person Selected")
            .message("Please select a person in the table.")
            .showWarning();
        }
    }
}
