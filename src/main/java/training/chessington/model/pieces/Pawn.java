package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMoves = new ArrayList<>();
        int direction = 1; 

        if(this.getColour().equals(PlayerColour.WHITE)) 
             direction = -1; 

        allowedMoves.add(new Move(from,from.plus(direction, 0)));

        if (!this.hasMoved())
            allowedMoves.add(new Move(from, from.plus(direction * 2, 0)));

        return allowedMoves;
    }
}
