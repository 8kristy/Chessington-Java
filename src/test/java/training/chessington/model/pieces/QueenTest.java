package training.chessington.model.pieces;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

public class QueenTest {

    @Test
    public void whiteQueenCanMoveInAllDirections() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.WHITE);
        // Assert
        queenCanMoveInAllHorizontalAndVerticalDirections(board, queen);
    }

    @Test
    public void blackQueenCanMoveInAllDirections() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.BLACK);
        // Assert
        queenCanMoveInAllHorizontalAndVerticalDirections(board, queen);
    }

    private void queenCanMoveInAllHorizontalAndVerticalDirections(Board board, Piece queen) {
        // Arrange
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(coords, board);

        // Assert
        // Vertical moves
        for (int i = 0; i < 8; i++) {
            if (i != 4)
            assertThat(moves.contains(new Move(coords, new Coordinates(4, i))));
        }
        // Horizontal moves
        for (int i = 0; i < 8; i++) {
            if (i != 4)
                assertThat(moves.contains(new Move(coords, new Coordinates(i, 4))));
        }

        // Diagonal moves
        for(int i = 1; i<=3; i++){
            assertThat(moves).contains(new Move(coords, coords.plus(-i, i)));
        }
        for(int i = 1; i<=4; i++){
            assertThat(moves).contains(new Move(coords, coords.plus(-i, -i)));
        }
        for(int i = 1; i<=3; i++){
            assertThat(moves).contains(new Move(coords, coords.plus(i, i)));
        }
        for(int i = 1; i<=3; i++){
            assertThat(moves).contains(new Move(coords, coords.plus(i, -i)));
        }


    }

    @Test
    public void whiteQueenCannotGoOutsideOfBoardBounds() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(3, 3);
        board.placePiece(coords, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(coords, board);

        // Assert
        // Straight
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, -1))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, 8))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(-1, 3))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(8, 3))));
        // Diagonal
        assertThat(!moves.contains(new Move(coords, new Coordinates(-1, -1))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(-1, 7))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(7, -1))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(8, 8))));
    }

    @Test
    public void blackQueenCannotGoOutsideOfBoardBounds() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(3, 3);
        board.placePiece(coords, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(coords, board);

        // Assert
        // Straight
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, -1))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, 8))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(-1, 3))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(8, 3))));
        // Diagonal
        assertThat(!moves.contains(new Move(coords, new Coordinates(-1, -1))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(-1, 7))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(7, -1))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(8, 8))));
    }

    @Test
    public void whiteQueenGetsBlockedByOtherWhitePieces() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.WHITE);
        // Assert
        queenGetsBlockedByOtherSameColourPieces(board, queen);

    }

    @Test
    public void blackQueenGetsBlockedByOtherBlackPieces() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.BLACK);
        // Assert
        queenGetsBlockedByOtherSameColourPieces(board, queen);
    }

    public void queenGetsBlockedByOtherSameColourPieces(Board board, Piece queen) {
        Coordinates coords = new Coordinates(3, 3);
        
        // Straight 
        Coordinates coords1 = new Coordinates(1, 3);
        Coordinates coords2 = new Coordinates(3, 2);
        Coordinates coords3 = new Coordinates(3, 7);
        Coordinates coords4 = new Coordinates(7, 3);

        // Diagonal
        Coordinates coords5 = new Coordinates(2, 2);
        Coordinates coords6 = new Coordinates(1, 5);
        Coordinates coords7 = new Coordinates(6, 0);
        Coordinates coords8 = new Coordinates(7, 7);

        board.placePiece(coords, queen);
        // Straight 
        board.placePiece(coords1, queen);
        board.placePiece(coords2, queen);
        board.placePiece(coords3, queen);
        board.placePiece(coords4, queen);

        // Diagonal
        board.placePiece(coords5, queen);
        board.placePiece(coords6, queen);
        board.placePiece(coords7, queen);
        board.placePiece(coords8, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(coords, board);

        // Assert

        // Not allowed behind piece
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, 0))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, 1))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(0, 3))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(7, 3))));

        assertThat(!moves.contains(new Move(coords, new Coordinates(0, 0))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(1, 1))));
        assertThat(!moves.contains(new Move(coords, new Coordinates(0, 6))));

        // Allowed before piece
        assertThat(moves.contains(new Move(coords, new Coordinates(2, 3))));
        assertThat(moves.contains(new Move(coords, new Coordinates(3, 4))));
        assertThat(moves.contains(new Move(coords, new Coordinates(3, 5))));
        assertThat(moves.contains(new Move(coords, new Coordinates(3, 6))));
        assertThat(moves.contains(new Move(coords, new Coordinates(4, 3))));
        assertThat(moves.contains(new Move(coords, new Coordinates(5, 3))));
        
        assertThat(moves.contains(new Move(coords, new Coordinates(2, 4))));
        assertThat(moves.contains(new Move(coords, new Coordinates(4, 2))));
        assertThat(moves.contains(new Move(coords, new Coordinates(4, 4))));
        assertThat(moves.contains(new Move(coords, new Coordinates(5, 1))));
        assertThat(moves.contains(new Move(coords, new Coordinates(5, 5))));
        assertThat(moves.contains(new Move(coords, new Coordinates(6, 6))));
    }

    @Test
    public void whiteQueenCanCaptureBlackPieces() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.WHITE);
        Piece enemy = new Pawn(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(3, 3);
        Coordinates enemyCoords = new Coordinates(3, 6);

        board.placePiece(coords, queen);
        board.placePiece(enemyCoords, enemy);

        // Act
        List<Move> moves = queen.getAllowedMoves(coords, board);

        // Assert
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, 6))));
    }

    @Test
    public void blackQueenCanCaptureWhitePieces() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.BLACK);
        Piece enemy = new Pawn(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(3, 3);
        Coordinates enemyCoords = new Coordinates(3, 6);

        board.placePiece(coords, queen);
        board.placePiece(enemyCoords, enemy);

        // Act
        List<Move> moves = queen.getAllowedMoves(coords, board);

        // Assert
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, 6))));
    }

    @Test
    public void whiteQueenCannotCaptureWhitePieces() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.WHITE);
        Piece enemy = new Pawn(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(3, 3);
        Coordinates enemyCoords = new Coordinates(3, 6);

        board.placePiece(coords, queen);
        board.placePiece(enemyCoords, enemy);

        // Act
        List<Move> moves = queen.getAllowedMoves(coords, board);

        // Assert
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, 6))));
    }

    @Test
    public void blackQueenCannotCaptureBlackPieces() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.BLACK);
        Piece enemy = new Pawn(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(3, 3);
        Coordinates enemyCoords = new Coordinates(3, 6);

        board.placePiece(coords, queen);
        board.placePiece(enemyCoords, enemy);

        // Act
        List<Move> moves = queen.getAllowedMoves(coords, board);

        // Assert
        assertThat(!moves.contains(new Move(coords, new Coordinates(3, 6))));

    }

}
