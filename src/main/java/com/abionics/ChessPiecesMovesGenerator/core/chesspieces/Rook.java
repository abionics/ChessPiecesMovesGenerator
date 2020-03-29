package com.abionics.ChessPiecesMovesGenerator.core.chesspieces;

import com.abionics.ChessPiecesMovesGenerator.core.Point;

import java.util.HashSet;

public class Rook implements ChessPiece {
    @Override
    public HashSet<Point> available(Point position, Point size) {
        HashSet<Point> available = new HashSet<>(2 * (size.x + size.y) - 2);
        for (int x = -size.x + 1; x < size.x; x++)
            available.add(new Point(x, 0));
        for (int y = -size.y + 1; y < size.y; y++)
            available.add(new Point(0, y));
        available.remove(Point.zero);
        return available;
    }
}
