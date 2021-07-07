package training.chessington.model.pieces;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

public class KingTest {

    @Test
    public void whiteKingCanMoveOneSquareInAllDirections() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        // Assert
        kingCanMoveOneSquareInAllDirections(board, king);
    }

    @Test
    public void blackKingCanMoveOneSquareInAllDirections() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.BLACK);
        // Assert
        kingCanMoveOneSquareInAllDirections(board, king);
    }

    private void kingCanMoveOneSquareInAllDirections(Board board, Piece king) {
        // Arrange
        Coordinates coords = new Coordinates(6, 3);
        board.placePiece(coords, king);

        // Act
        List<Move> moves = king.getAllowedMoves(coords, board);
        System.out.println(moves);
        // Assert
        for (int i = 5; i < 7; i++) {
            for (int j = 2; j < 5; j++) {
                if (!(i == 6 && j == 3)){
                    assert(moves.contains(new Move(coords, new Coordinates(i, j))));
                }
            }
        }
    }

    @Test
    public void whiteKingCannotGoOutsideOfBoardBounds() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(0, 0);
        board.placePiece(coords, king);

        // Act
        List<Move> moves = king.getAllowedMoves(coords, board);

        // Assert
        for (int i = -1; i < 2; i++) {
            assert(!moves.contains(new Move(coords, new Coordinates(-1, i))));
        }
        for (int i = -1; i < 2; i++) {
            assert(!moves.contains(new Move(coords, new Coordinates(i, -1))));
        }
    }

    @Test
    public void blackKingCannotGoOutsideOfBoardBounds() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(0, 0);
        board.placePiece(coords, king);

        // Act
        List<Move> moves = king.getAllowedMoves(coords, board);

        // Assert
        for (int i = -1; i < 2; i++) {
            assert(!moves.contains(new Move(coords, new Coordinates(-1, i))));
        }
        for (int i = -1; i < 2; i++) {
            assert(!moves.contains(new Move(coords, new Coordinates(i, -1))));
        }
    }

    @Test
    public void whiteKingCanCaptureBlackPieces() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Piece enemy = new Pawn(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(3, 3);
        Coordinates enemyCoords = new Coordinates(3, 6);

        board.placePiece(coords, king);
        board.placePiece(enemyCoords, enemy);

        // Act
        List<Move> moves = king.getAllowedMoves(coords, board);

        // Assert
        assert(!moves.contains(new Move(coords, new Coordinates(3, 6))));
    }

    @Test
    public void blackKingCanCaptureWhitePieces() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.BLACK);
        Piece enemy = new Pawn(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(3, 3);
        Coordinates enemyCoords = new Coordinates(3, 6);

        board.placePiece(coords, king);
        board.placePiece(enemyCoords, enemy);

        // Act
        List<Move> moves = king.getAllowedMoves(coords, board);

        // Assert
        assert(!moves.contains(new Move(coords, new Coordinates(3, 6))));
    }

    @Test
    public void whiteKingCannotCaptureWhitePieces() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Piece enemy = new Pawn(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(3, 3);
        Coordinates enemyCoords = new Coordinates(3, 6);

        board.placePiece(coords, king);
        board.placePiece(enemyCoords, enemy);

        // Act
        List<Move> moves = king.getAllowedMoves(coords, board);

        // Assert
        assert(!moves.contains(new Move(coords, new Coordinates(3, 6))));
    }

    @Test
    public void blackKingCannotCaptureBlackPieces() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.BLACK);
        Piece enemy = new Pawn(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(3, 3);
        Coordinates enemyCoords = new Coordinates(3, 6);

        board.placePiece(coords, king);
        board.placePiece(enemyCoords, enemy);

        // Act
        List<Move> moves = king.getAllowedMoves(coords, board);

        // Assert
        assert(!moves.contains(new Move(coords, new Coordinates(3, 6))));

    }

}
