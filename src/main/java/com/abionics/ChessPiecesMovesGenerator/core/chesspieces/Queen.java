package com.abionics.ChessPiecesMovesGenerator.core.chesspieces;

import com.abionics.ChessPiecesMovesGenerator.core.Point;

import java.util.HashSet;

public class Queen implements ChessPiece {
    @Override
    public HashSet<Point> available(Point position, Point size) {
        HashSet<Point> available = new HashSet<>();
        Bishop bishop = new Bishop();
        Rook rook = new Rook();
        available.addAll(bishop.available(position, size));
        available.addAll(rook.available(position, size));
        return available;
    }
}
