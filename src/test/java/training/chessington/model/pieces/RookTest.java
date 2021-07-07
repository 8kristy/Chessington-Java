package training.chessington.model.pieces;

import static training.chessington.model.pieces.PieceAssert.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

public class RookTest {

    @Test
    public void whiteRookCanMoveInAllHorizontalAndVerticalDirections() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        // Assert
        rookCanMoveInAllHorizontalAndVerticalDirections(board, rook);
    }

    @Test
    public void blackRookCanMoveInAllHorizontalAndVerticalDirections() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.BLACK);
        // Assert
        rookCanMoveInAllHorizontalAndVerticalDirections(board, rook);
    }

    private void rookCanMoveInAllHorizontalAndVerticalDirections(Board board, Piece rook) {
        // Arrange
        Coordinates coords = new Coordinates(3, 3);
        board.placePiece(coords, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);
        List<Move> allowedMoves = new ArrayList<>();

        // Vertical moves
        for (int i = 0; i < 8; i++) {
            if (i != 3)
                allowedMoves.add(new Move(coords, new Coordinates(3, i)));
        }
        // Horizontal moves
        for (int i = 0; i < 8; i++) {
            if (i != 3)
                allowedMoves.add(new Move(coords, new Coordinates(i, 3)));
        }

        // Assert
        assertThat(moves.equals(allowedMoves));
    }

    @Test
    public void whiteRookCannotMoveDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        // Assert
        rookCannotMoveDiagonally(board, rook);
    }

    @Test
    public void blackRookCannotMoveDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.BLACK);
        // Assert
        rookCannotMoveDiagonally(board, rook);
    }

    @Test
    public void rookCannotMoveDiagonally(Board board, Piece rook) {
        // Arrange
        Coordinates coords = new Coordinates(3, 3);
        board.placePiece(coords, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        for (int i = 6; i >= 0; i--) {
            for (int j = 0; j < 7; j++) {
                assertThat(!moves.contains(new Move(coords, new Coordinates(i, j))));
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                assertThat(!moves.contains(new Move(coords, new Coordinates(i, j))));
            }
        }
    }

    @Test
    public void whiteRookCannotGoOutsideOfBoardBounds() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(3, 3);
        board.placePiece(coords, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, -1))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, 8))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(-1, 3))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(8, 3))));

    }

    @Test
    public void blackRookCannotGoOutsideOfBoardBounds() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(3, 3);
        board.placePiece(coords, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, -1))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, 8))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(-1, 3))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(8, 3))));
    }

    @Test
    public void whiteRookGetsBlockedByOtherWhitePieces() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        // Assert
        rookGetsBlockedByOtherSameColourPieces(board, rook);

    }

    @Test
    public void blackRookGetsBlockedByOtherBlackPieces() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.BLACK);
        // Assert
        rookGetsBlockedByOtherSameColourPieces(board, rook);
    }

    public void rookGetsBlockedByOtherSameColourPieces(Board board, Piece rook) {
        Coordinates coords = new Coordinates(3, 3);
        Coordinates coords1 = new Coordinates(1, 3);
        Coordinates coords2 = new Coordinates(3, 2);
        Coordinates coords3 = new Coordinates(3, 7);
        Coordinates coords4 = new Coordinates(7, 3);

        board.placePiece(coords, rook);
        board.placePiece(coords1, rook);
        board.placePiece(coords2, rook);
        board.placePiece(coords3, rook);
        board.placePiece(coords4, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert

        // Not allowed behind piece
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, 0))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, 1))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(0, 3))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(7, 3))));

        // Allowed before piece
        assertThat(moves.contains(new Move(coords, new Coordinates(2, 3))));
        assertThat(moves.contains(new Move(coords, new Coordinates(3, 4))));
        assertThat(moves.contains(new Move(coords, new Coordinates(3, 5))));
        assertThat(moves.contains(new Move(coords, new Coordinates(3, 6))));
        assertThat(moves.contains(new Move(coords, new Coordinates(4, 3))));
        assertThat(moves.contains(new Move(coords, new Coordinates(5, 3))));
    }

    @Test
    public void whiteRookCanCaptureBlackPieces() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Piece enemy = new Pawn(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(3, 3);
        Coordinates enemyCoords = new Coordinates(3, 6);

        board.placePiece(coords, rook);
        board.placePiece(enemyCoords, enemy);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, 6))));
    }

    @Test
    public void blackRookCanCaptureWhitePieces() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.BLACK);
        Piece enemy = new Pawn(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(3, 3);
        Coordinates enemyCoords = new Coordinates(3, 6);

        board.placePiece(coords, rook);
        board.placePiece(enemyCoords, enemy);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, 6))));
    }

    @Test
    public void whiteRookCannotCaptureWhitePieces() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Piece enemy = new Pawn(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(3, 3);
        Coordinates enemyCoords = new Coordinates(3, 6);

        board.placePiece(coords, rook);
        board.placePiece(enemyCoords, enemy);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, 6))));
    }

    @Test
    public void blackRookCannotCaptureBlackPieces() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.BLACK);
        Piece enemy = new Pawn(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(3, 3);
        Coordinates enemyCoords = new Coordinates(3, 6);

        board.placePiece(coords, rook);
        board.placePiece(enemyCoords, enemy);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, 6))));

    }

}
