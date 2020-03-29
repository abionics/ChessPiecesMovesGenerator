package com.abionics.ChessPiecesMovesGenerator.core.chesspieces;

import com.abionics.ChessPiecesMovesGenerator.core.Point;

import java.util.ArrayList;
import java.util.HashSet;

public interface ChessPiece {
    default ArrayList<Point> generate(Point position, Point size) {
        ArrayList<Point> positions = new ArrayList<>();
        var available = available(size);
        for (var move : available) {
            var current = Point.add(position, move);
            if (check(current, size)) {
                positions.add(current);
            }
        }
        return positions;
    }

    default boolean check(Point position, Point size) {
        return position.x >= 0 && position.y >= 0 && position.x < size.x && position.y < size.y;
    }

    HashSet<Point> available(Point size);
}
