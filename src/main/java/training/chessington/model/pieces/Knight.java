package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Knight extends AbstractPiece {
    public Knight(PlayerColour colour) {
        super(PieceType.KNIGHT, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();
        addMove(board, moves, from, from.plus(-2, 1));
        addMove(board, moves, from, from.plus(-2, -1));
        addMove(board, moves, from, from.plus(1, 2));
        addMove(board, moves, from, from.plus(-1, 2));
        addMove(board, moves, from, from.plus(1, -2));
        addMove(board, moves, from, from.plus(-1, -2));
        addMove(board, moves, from, from.plus(2, 1));
        addMove(board, moves, from, from.plus(2, -1));
        return moves;
    }

    private void addMove(Board board, List<Move> moves, Coordinates from, Coordinates to){
        if (isValidMove(board, to))
            moves.add(new Move(from, to));
    }

    private boolean isValidMove(Board board, Coordinates to){
        return checkBoardBounds(to) && (board.get(to) == null || board.get(to).getColour() != this.colour);
    }
}
