import java.util.Locale;
import java.util.ResourceBundle;

import controlador.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.GestioLlistes;
import model.Init_windows;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{      
        try{
            /* 
            La variable bool sirve para indicar si se quiere reiniciar toda la base de datos
                - True: Reiniciar todo (Eliminar las tablas y volver a crearlas)
                - False: Seguir usando una base de datos anterior
            
            NOTA IMPORTANTE: Una vez ejecutado el programa por primera vez, deshabilita la opcion de reiniciar
            porque sino se eliminaran los cambios realizados 
             */
            GestioLlistes model = new GestioLlistes(true);

            FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("vista/app.fxml"), ResourceBundle.getBundle("strings", Locale.getDefault()));
            Controller controlador = new Controller();
            Init_windows init_windows = new Init_windows(model,controlador);
            
            controlador.injecta(init_windows,model);
    
            mainLoader.setControllerFactory(c -> controlador);
    
            Parent root = mainLoader.load();
            primaryStage.setTitle("Expedients");
            primaryStage.setScene(new Scene(root, 720, 380));
            primaryStage.show();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}