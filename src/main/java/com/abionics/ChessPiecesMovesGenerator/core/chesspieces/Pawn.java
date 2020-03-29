package com.abionics.ChessPiecesMovesGenerator.core.chesspieces;

import com.abionics.ChessPiecesMovesGenerator.core.Point;

import java.util.HashSet;

public class Pawn implements ChessPiece {
    @Override
    public HashSet<Point> available(Point position, Point size) {
        HashSet<Point> available = new HashSet<>(1);
        if (size.y - position.y == 1)
            return available;
        available.add(new Point(0, -1));
        if (size.y - position.y == 2)
            available.add(new Point(0, -2));
        return available;
    }
}
