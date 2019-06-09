package app.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class MainController implements Initializable {
    @FXML
    TreeView<String> treeView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        treeView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        treeView.setRoot(new TreeItem<>("Hello"));
        treeView.setShowRoot(false);


//        treeView.getRoot().getChildren().add(new TreeItem<>("Child 1"));
//        treeView.getRoot().getChildren().add(new TreeItem<>("Child 2"));
//        treeView.getRoot().getChildren().add(new TreeItem<>("Child 3"));

    }
}
