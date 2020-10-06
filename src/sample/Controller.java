package sample;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField vertices;

    @FXML
    private Button doDijkstraButton;

    @FXML
    void initialize() {
        doDijkstraButton.setOnAction(actionEvent -> {
            Dijkstra dijkstra = new Dijkstra();
            int v = Integer.parseInt(vertices.getText());

            PipelineConnection pipelineConnection = null;
            try {
                pipelineConnection = new PipelineConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            String getTaskQuery = "select * from csvread('/home/borislav/Education/FXproject/task', null, 'charset=UTF-8 fieldSeparator=;');";
            Statement statement = null;
            try {
                statement = pipelineConnection.getConnection().createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ResultSet resultSet = null;
            try {
                resultSet = statement.executeQuery(getTaskQuery);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            while (true) {
                try {
                    if (!resultSet.next()) break;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    dijkstra.checkDijkstra((resultSet.getInt("start") - 1), (resultSet.getInt("end") - 1), v);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public TextField getVertices() {
        return vertices;
    }

    public void setVertices(TextField vertices) {
        this.vertices = vertices;
    }
}


