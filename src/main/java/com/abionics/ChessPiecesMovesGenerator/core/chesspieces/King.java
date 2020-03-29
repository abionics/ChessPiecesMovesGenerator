package com.abionics.ChessPiecesMovesGenerator.core.chesspieces;

import com.abionics.ChessPiecesMovesGenerator.core.Point;

import java.util.HashSet;

public class King implements ChessPiece {
    @Override
    public HashSet<Point> available(Point position, Point size) {
        HashSet<Point> available = new HashSet<>(8);
        for (int x = -1; x <= 1; x++)
            for (int y = -1; y <= 1; y++)
                available.add(new Point(x, y));
        available.remove(Point.zero);
        return available;
    }
}
