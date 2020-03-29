package com.abionics.ChessPiecesMovesGenerator;

import com.abionics.ChessPiecesMovesGenerator.core.ChessPieces;
import com.abionics.ChessPiecesMovesGenerator.core.Generator;
import com.abionics.ChessPiecesMovesGenerator.core.Point;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Controller {
    private static final String FILENAME = "moves.txt";

    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private Canvas boardCanvas;
    @FXML
    private Slider sizeSlider;
    @FXML
    private Slider delaySlider;
    @FXML
    private Button generateButton;


    @FXML
    private void initialize() {
        String[] spaces = Arrays.stream(ChessPieces.values()).map(Enum::name).toArray(String[]::new);
        typeComboBox.getItems().addAll(spaces);
        typeComboBox.setValue(spaces[0]);

        generateButton.setOnAction(actionEvent -> generate());
    }

    private void generate() {
        new Thread(() -> {
            var type = ChessPieces.valueOf(typeComboBox.getValue());
            int size = (int) sizeSlider.getValue();
            int delay = (int) delaySlider.getValue();
            Generator generator = new Generator(new Point(size, size), boardCanvas);
            var allocations = generator.generate(type, delay);
            save(allocations);
        }).start();
    }

    private void save(HashMap<Integer, ArrayList<Integer>> allocations) {
        StringBuilder result = new StringBuilder();
        for (var comb : allocations.entrySet()) {
            int current = comb.getKey();
            ArrayList<Integer> positions = comb.getValue();
            Collections.sort(positions);
            for (var position : positions)
                result.append(current).append("\t->\t").append(position).append('\n');
        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME));
            writer.write(result.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
