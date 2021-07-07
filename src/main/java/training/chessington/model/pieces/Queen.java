package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Queen extends AbstractPiece {
    public Queen(PlayerColour colour) {
        super(PieceType.QUEEN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMoves = new ArrayList<>();
        getQueenMoves(board, from, allowedMoves, -1, -1);
        getQueenMoves(board, from, allowedMoves, -1, 1);
        getQueenMoves(board, from, allowedMoves, 1, -1);
        getQueenMoves(board, from, allowedMoves, 1, 1);
        getQueenMoves(board, from, allowedMoves, 0, 1);
        getQueenMoves(board, from, allowedMoves, 1, 0);
        getQueenMoves(board, from, allowedMoves, 0, -1);
        getQueenMoves(board, from, allowedMoves, -1, 0);
        return allowedMoves;
    }

    private void getQueenMoves(Board board, Coordinates from, List<Move> moves, int dirX, int dirY){
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
