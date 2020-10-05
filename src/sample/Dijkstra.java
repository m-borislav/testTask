package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Dijkstra {

    public void checkDijkstra(int source, int endPoint, int vertices) throws SQLException, ClassNotFoundException {
        List<List<Node>> adjacencyList = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            List<Node> item = new ArrayList<>();
            adjacencyList.add(item);
        }

        PipelineConnection pipelineConnection = new PipelineConnection();

        String getGraphQuery = "select * from csvread('/home/borislav/Education/FXproject/pipelineSys', null, 'charset=UTF-8 fieldSeparator=;');";

        Statement statement = pipelineConnection.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(getGraphQuery);

        boolean isLengthExist = false;

        while (resultSet.next()) {
            adjacencyList.get(resultSet.getInt("startpointId") - 1).add(new Node((resultSet.getInt("endpointId") - 1), resultSet.getInt("length")));

        }

        Graph graph = new Graph(vertices);
        graph.dijkstra(adjacencyList, source);

        if (resultSet.next()) {
            source = resultSet.getInt("startpointId");
            endPoint = resultSet.getInt("endpointId");
        }
/*
        while (resultSet.next()){
             if(adjacencyList.get(resultSet.getInt("length")).isEmpty()){
                isLengthExist = false;
            } else {
                isLengthExist = true;
            }
        }

        if(isLengthExist == false){
            System.out.println("False");
        } else {
            System.out.println("True");
        }*/
        System.out.println("Start point\t\t" + "End point\t\t" + "Length");
        try{
            System.out.println((source + 1) + " \t\t\t\t " + (endPoint + 1) + " \t\t\t\t " + graph.dist[endPoint]);
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("False");
        }
    }
}