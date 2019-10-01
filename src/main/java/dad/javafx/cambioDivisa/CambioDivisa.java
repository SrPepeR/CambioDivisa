package dad.javafx.cambioDivisa;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CambioDivisa extends Application {
	
	private Stage primaryStage;
	
	private TextField primeraText;
	private ComboBox<Divisa> primeraCombo;
	private TextField segundaText;
	private ComboBox<Divisa> segundaCombo;
	private Button calculaButton;
	
	private Divisa Euro = new Divisa("Euro", 1.0);
	private Divisa Libra = new Divisa("Libra", 0.8873);
	private Divisa Dolar = new Divisa("Dolar", 1.20007);
	private Divisa Yen = new Divisa("Yen", 133.59);
	
	public void start(Stage primaryStage) throws Exception {
		
		this.primaryStage = primaryStage;
		
		primeraText = new TextField();
		primeraText.setPromptText("Primera divisa");
		primeraText.setPrefWidth(50);
		segundaText = new TextField();
		segundaText.setPromptText("Segunda divisa");
		segundaText.setEditable(false);
		segundaText.setPrefWidth(50);
		
		primeraCombo = new ComboBox<Divisa>();
		primeraCombo.getItems().addAll(Euro, Dolar, Yen, Libra);
		primeraCombo.setPromptText("Seleccione divisa");
		primeraCombo.setPrefWidth(75);
		primeraCombo.getSelectionModel().selectFirst();
		segundaCombo = new ComboBox<Divisa>();
		segundaCombo.getItems().addAll(Euro, Dolar, Yen, Libra);
		segundaCombo.setPromptText("Seleccione divisa");
		segundaCombo.setPrefWidth(75);
		segundaCombo.getSelectionModel().select(Dolar);
		
		calculaButton = new Button();
		calculaButton.setText("CALCULAR");
		calculaButton.setDefaultButton(true);
		calculaButton.setAlignment(Pos.CENTER);

		calculaButton.setOnAction( e -> onCalculaButtonAction() );
		
		HBox primeraBox = new HBox(5, primeraText, primeraCombo);
		primeraBox.setAlignment(Pos.CENTER);
		
		HBox segundaBox = new HBox(5, segundaText, segundaCombo);
		segundaBox.setAlignment(Pos.CENTER);
		
		VBox root = new VBox(5, primeraBox, segundaBox, calculaButton);
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("Cambio de divisa");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	private void onCalculaButtonAction() {
		
		Divisa origen = this.primeraCombo.getSelectionModel().getSelectedItem();
		Divisa destino = this.segundaCombo.getSelectionModel().getSelectedItem();
		
		Double cantidad;
		try {
			cantidad = Double.parseDouble(this.primeraText.getText());
			this.segundaText.setText(destino.fromEuro(origen.toEuro(cantidad)).toString());
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(this.primaryStage);
			alert.setWidth(200);
			alert.setResizable(false);
			alert.setHeaderText("Debe introducir números");
			alert.setContentText("En la primera casilla debe introducir números para convertir.");
			alert.showAndWait();
			
			try {
				start(this.primaryStage);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}

	public static void main(String[] args) {
		
		launch(args);

	}

}
