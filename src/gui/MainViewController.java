package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable{

	@FXML
	private MenuItem mnuItemSeller;
	
	@FXML
	private MenuItem mnuItemDepartment;
	
	@FXML
	private MenuItem mnuItemAbout;
	
	@FXML
	public void onMnuItemSellerAction() {
		System.out.println("onMnuItemSellerAction");
	}
	
	@FXML
	public void onMnuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml");
	}
	
	@FXML
	public void onMnuItemAboutAction() {
		loadView("/gui/About.fxml");
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}
	
	private synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			
			// pega o 1o elemento da tela principal (ScrollPane)
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			// Guardar o mainMenu para adicionar depois (1o filho de VBox)
			Node mainMenu = mainVBox.getChildren().get(0);
					
			// Apagar os filhos atuais do VBox para depois adicionar novamente junto com os novos �tens da tela que ser� apresentada
			mainVBox.getChildren().clear();
			
			// Adicionar novamente o mainMenu do formul�rio principal
			mainVBox.getChildren().add(mainMenu);
			
			// Adicionar os �tens da tela que ser� chamada
			mainVBox.getChildren().addAll(newVBox.getChildren());
		}
		catch(IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}