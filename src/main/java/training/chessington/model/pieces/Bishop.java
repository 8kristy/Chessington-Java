package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends AbstractPiece {
    public Bishop(PlayerColour colour) {
        super(PieceType.BISHOP, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMoves = new ArrayList<>();
        getBishopMoves(board, from, allowedMoves, -1, -1);
        getBishopMoves(board, from, allowedMoves, -1, 1);
        getBishopMoves(board, from, allowedMoves, 1, -1);
        getBishopMoves(board, from, allowedMoves, 1, 1);
        return allowedMoves;
    }

    private void getBishopMoves(Board board, Coordinates from, List<Move> moves, int dirX, int dirY){
        Coordinates to = from.plus(dirX, dirY);
        while(isValidMove(board, to)){
            moves.add(new Move(from, to));
            to = to.plus(dirX, dirY);
        }
        if (checkBoardBounds(to) && board.get(to).getColour() != this.colour)
            moves.add(new Move(from, to));
    }

    private boolean isValidMove(Board board, Coordinates to){
        return checkBoardBounds(to) && board.get(to) == null;
    }

}

