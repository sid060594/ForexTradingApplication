package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static Stage stage = new Stage();

	@Override
	public void start(Stage stage) throws Exception {

		try {
			this.stage = stage;
//			Parent root = FXMLLoader.load(getClass().getResource("/view/LoginScene.fxml"));
			Parent root = FXMLLoader.load(getClass().getResource("/view/TradingView.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/firstpage.css").toExternalForm());
			stage.setScene(scene);
			stage.setTitle("Foreign eXchange Trading App");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
