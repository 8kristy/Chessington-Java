package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class King extends AbstractPiece {
    public King(PlayerColour colour) {
        super(PieceType.KING, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();
        int x = from.getRow();
        int y = from.getCol();
        for (int i =x-1; i < x+2; i++){
            for (int j = y-1; j < y+2; j++){
                Coordinates to = new Coordinates(i, j);
                if (isValidMove(board, to) && !(i == x && j == y)){
                    moves.add(new Move(from, to));
                }
            }
        }
        return moves;
    }

    private boolean isValidMove(Board board, Coordinates to){
        return checkBoardBounds(to) && (board.get(to) == null || board.get(to).getColour() != this.colour);
    }
}
