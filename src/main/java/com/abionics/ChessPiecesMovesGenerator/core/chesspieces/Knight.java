package com.abionics.ChessPiecesMovesGenerator.core.chesspieces;

import com.abionics.ChessPiecesMovesGenerator.core.Point;

import java.util.HashSet;

public class Knight implements ChessPiece {
    @Override
    public HashSet<Point> available(Point size) {
        HashSet<Point> available = new HashSet<>(8);
        for (int x = -1; x <= 1; x += 2) {
            for (int y = -1; y <= 1; y += 2) {
                available.add(new Point(2 * x, y));
                available.add(new Point(x, 2 * y));
            }
        }
        return available;
    }
}
