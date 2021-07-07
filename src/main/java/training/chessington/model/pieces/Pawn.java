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

        if (this.getColour().equals(PlayerColour.WHITE)) 
             direction = -1; 

        // If immediate space at the front is not blocked, allow move there
        Coordinates to = from.plus(direction, 0);
        if (checkBoardBounds(to) && board.get(to) == null) {
            allowedMoves.add(new Move(from, to));

            // If this is the first move and space 2 spaces forward is not blocked, allow move there
            Coordinates toFirstMove = from.plus(direction * 2, 0);
            if (!this.hasMoved() && checkBoardBounds(to) && board.get(toFirstMove) == null)
                allowedMoves.add(new Move(from, toFirstMove));
        }

        return allowedMoves;
    }

    private boolean checkBoardBounds(Coordinates to){
        return to.getRow() >= 0 && to.getRow() < 8;
    }


}
