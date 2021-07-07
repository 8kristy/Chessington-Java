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
        int direction = 1; 

        if (this.getColour().equals(PlayerColour.WHITE)) 
             direction = -1; 

        List<Move> allowedMoves = new ArrayList<>();
        addForwardMoves(board, allowedMoves, from, direction);

        return allowedMoves;
    }

    private void addForwardMoves(Board board, List<Move> allowedMoves, Coordinates from, int direction){
        // If immediate space at the front is not blocked, allow move there
        Coordinates to = from.plus(direction, 0);
        if (isValidMoveForward(board, to)) {
            allowedMoves.add(new Move(from, to));

            // If this is the first move and space 2 spaces forward is not blocked, allow move there
            Coordinates toFirstMove = from.plus(direction * 2, 0);
            if (!this.hasMoved() && isValidMoveForward(board, toFirstMove))
                allowedMoves.add(new Move(from, toFirstMove));
        }
    }

    private boolean isValidMoveForward(Board board, Coordinates to){
        return checkBoardBounds(to) && board.get(to) == null;
    }

    private boolean checkBoardBounds(Coordinates to){
        return to.getRow() >= 0 && to.getRow() < 8;
    }


}
