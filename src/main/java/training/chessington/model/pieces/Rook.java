package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Rook extends AbstractPiece {
    public Rook(PlayerColour colour) {
        super(PieceType.ROOK, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMoves = new ArrayList<>();
        addMovesInDirection(board, allowedMoves, from, 0, 1);
        addMovesInDirection(board, allowedMoves, from, 1, 0);
        addMovesInDirection(board, allowedMoves, from, 0, -1);
        addMovesInDirection(board, allowedMoves, from, -1, 0);
        return allowedMoves;
    }

    private void addMovesInDirection(Board board, List<Move> allowedMoves, Coordinates from, int xDirection, int yDirection){
        Coordinates to = from.plus(xDirection, yDirection);
        while(isValidMove(board, to)) {
            allowedMoves.add(new Move(from, to));
            if(board.get(to) != null) break;
            to = to.plus(xDirection, yDirection);
        }
    }

    private boolean isValidMove(Board board, Coordinates to) {
        return checkBoardBounds(to) && (board.get(to) == null || !board.get(to).getColour().equals(this.getColour()));
    }

}
