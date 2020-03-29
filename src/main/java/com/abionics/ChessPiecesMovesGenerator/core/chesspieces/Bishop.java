package com.abionics.ChessPiecesMovesGenerator.core.chesspieces;

import com.abionics.ChessPiecesMovesGenerator.core.Point;

import java.util.HashSet;

public class Bishop implements ChessPiece {
    @Override
    public HashSet<Point> available(Point position, Point size) {
        int min = Math.max(size.x, size.y);
        HashSet<Point> available = new HashSet<>(2 * min - 2);
        for (int p = -min + 1; p < min; p++) {
            available.add(new Point(p, p));
            available.add(new Point(-p, p));
        }
        available.remove(Point.zero);
        return available;
    }
}
