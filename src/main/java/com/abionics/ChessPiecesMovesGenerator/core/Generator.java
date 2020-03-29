package com.abionics.ChessPiecesMovesGenerator.core;

import com.abionics.ChessPiecesMovesGenerator.core.chesspieces.*;
import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashMap;

public class Generator {
    private Point size;
    private Canvas canvas;
    private HashMap<Point, Integer> enumeration;
    private HashMap<Integer, ArrayList<Integer>> allocations;

    public Generator(Point size, Canvas canvas) {
        this.size = size;
        this.canvas = canvas;
        this.enumeration = enumerateBoard(size);
    }

    public HashMap<Integer, ArrayList<Integer>> generate(ChessPieces type, int delay) {
        ChessPiece chessPiece;
        switch (type) {
            case PAWN:
                chessPiece = new Pawn();
                break;
            case KNIGHT:
                chessPiece = new Knight();
                break;
            case BISHOP:
                chessPiece = new Bishop();
                break;
            case ROOK:
                chessPiece = new Rook();
                break;
            case QUEEN:
                chessPiece = new Queen();
                break;
            case KING:
                chessPiece = new King();
                break;
            default:
                throw new RuntimeException("Unknown chess piece type");
        }
        allocations = new HashMap<>();
        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++) {
                Point current = new Point(x, y);
                var positions = chessPiece.generate(current, size);
                allocate(current, positions);
                draw(current, positions);
                delay(delay);
            }
        }
        return allocations;
    }

    private HashMap<Point, Integer> enumerateBoard(Point size) {
        HashMap<Point, Integer> enumeration = new HashMap<>();
        int k = 1;
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                Point point = new Point(x, y);
                enumeration.put(point, k);
                k++;
            }
        }
        return enumeration;
    }

    private void allocate(Point current, ArrayList<Point> positions) {
        ArrayList<Integer> list = new ArrayList<>(positions.size());
        for (var position : positions)
            list.add(enumeration.get(position));
        int number = enumeration.get(current);
        allocations.put(number, list);
    }

    private void draw(Point current, ArrayList<Point> positions) {
        clean();
        draw(current, Color.PURPLE);
        for (var position : positions)
            draw(position, Color.GREEN);
        drawGrid();
    }

    private void draw(Point position, Color color) {
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        double width = canvas.getWidth() / size.x;
        double height = canvas.getHeight() / size.y;
        Platform.runLater(() -> {
            graphics.setFill(color);
            graphics.fillRect(position.x * width, position.y * height, width, height);
        });
    }

    private void clean() {
        Platform.runLater(() -> {
            GraphicsContext graphics = canvas.getGraphicsContext2D();
            graphics.setFill(Color.LIGHTGREY);
            graphics.setStroke(Color.BLACK);
            graphics.setLineWidth(2);
            graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        });
    }

    private void drawGrid() {
        double width = canvas.getWidth() / size.x;
        double height = canvas.getHeight() / size.y;
        Platform.runLater(() -> {
            GraphicsContext graphics = canvas.getGraphicsContext2D();
            graphics.setLineWidth(1);
            for (int x = 0; x < size.x; x++) {
                for (int y = 0; y < size.y; y++) {
                    Point point = new Point(x, y);
                    String text = enumeration.get(point).toString();
                    double px = x * width + width / 2 - 7;
                    double py = y * height + height / 2 + 5;
                    graphics.strokeText(text, px, py);
                }
            }
        });
    }

    static private void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
