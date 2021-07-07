package training.chessington.model.pieces;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

public class BishopTest {
    @Test
    public void whiteBishopCanMoveOnDiagonalUpRight() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        for(int i = 1; i<=3; i++){
            assertThat(moves).contains(new Move(coords, coords.plus(-i, i)));
        }
    }
    
    @Test
    public void whiteBishopCanMoveOnDiagonalUpLeft() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        for(int i = 1; i<=4; i++){
            assertThat(moves).contains(new Move(coords, coords.plus(-i, -i)));
        }
    }

    @Test
    public void whiteBishopCanMoveOnDiagonalDownRight() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        for(int i = 1; i<=3; i++){
            assertThat(moves).contains(new Move(coords, coords.plus(i, i)));
        }
    }

    @Test
    public void whiteBishopCanMoveOnDiagonalDownLeft() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        for(int i = 1; i<=3; i++){
            assertThat(moves).contains(new Move(coords, coords.plus(i, -i)));
        }
    }

    @Test
    public void blackBishopCanMoveOnDiagonalUpRight() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(3, 3);
        board.placePiece(coords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        for(int i = 1; i<=3; i++){
            assertThat(moves).contains(new Move(coords, coords.plus(-i, i)));
        }
    }

    @Test
    public void blackBishopCanMoveOnDiagonalUpLeft() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(3, 3);
        board.placePiece(coords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        for(int i = 1; i<=3; i++){
            assertThat(moves).contains(new Move(coords, coords.plus(-i, -i)));
        }
    }

    @Test
    public void blackBishopCanMoveOnDiagonalDownRight() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(3, 3);
        board.placePiece(coords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        for(int i = 1; i<=4; i++){
            assertThat(moves).contains(new Move(coords, coords.plus(i, i)));
        }
    }

    @Test
    public void blackBishopCanMoveOnDiagonalDownLeft() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(3, 3);
        board.placePiece(coords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        for(int i = 1; i<=3; i++){
            assertThat(moves).contains(new Move(coords, coords.plus(i, -i)));
        }
    }

    public void whiteBishopCantMoveSidewise() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        for(int i = 1; i<=3; i++){
            assertThat(moves).doesNotContain(new Move(coords, coords.plus(i, 0)));
            assertThat(moves).doesNotContain(new Move(coords, coords.plus(0, i)));
            assertThat(moves).doesNotContain(new Move(coords, coords.plus(-i, 0)));
            assertThat(moves).doesNotContain(new Move(coords, coords.plus(0, -i)));
        }
    }

    public void blackBishopCantMoveSidewise() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(3, 3);
        board.placePiece(coords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        for(int i = 1; i<=3; i++){
            assertThat(moves).doesNotContain(new Move(coords, coords.plus(i, 0)));
            assertThat(moves).doesNotContain(new Move(coords, coords.plus(0, i)));
            assertThat(moves).doesNotContain(new Move(coords, coords.plus(-i, 0)));
            assertThat(moves).doesNotContain(new Move(coords, coords.plus(0, -i)));
        }
    }

    @Test
    public void whiteBishopCantMoveOffBoard() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(4,4)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-4,4)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(4,-4)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-5,-5)));
    }

    @Test
    public void blackBishopCantMoveOffBoard() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(3, 3);
        board.placePiece(coords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-4,-4)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-4,4)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(4,-4)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(5,5)));
    }

    @Test
    public void whiteBishopCantJumpOver() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Piece whitePawn = new Pawn(PlayerColour.WHITE);
        Piece blackPawn = new Pawn(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 3);
        Coordinates coordsWhitePawn = coords.plus(1,1);
        Coordinates coordsBlackPawn = coords.plus(-2,-2);
        board.placePiece(coords, bishop);
        board.placePiece(coordsWhitePawn, whitePawn);
        board.placePiece(coordsBlackPawn, blackPawn);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(2,2)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(3,3)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-3,-3)));
    }

    @Test
    public void blackBishopCantJumpOver() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.BLACK);
        Piece whitePawn = new Pawn(PlayerColour.WHITE);
        Piece blackPawn = new Pawn(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(5, 3);
        Coordinates coordsWhitePawn = coords.plus(1,-1);
        Coordinates coordsBlackPawn = coords.plus(-2,2);
        board.placePiece(coords, bishop);
        board.placePiece(coordsWhitePawn, whitePawn);
        board.placePiece(coordsBlackPawn, blackPawn);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(2,-2)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-3,3)));
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-4,4)));
    }

    @Test
    public void whiteBishopCanCaptureBlack() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Piece blackPawn = new Pawn(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(4, 3);
        Coordinates coordsBlackPawn = coords.plus(-2,-2);
        board.placePiece(coords, bishop);
        board.placePiece(coordsBlackPawn, blackPawn);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(-2,-2)));
    }

    @Test
    public void blackBishopCanCaptureWhite() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.BLACK);
        Piece whitePawn = new Pawn(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(5, 3);
        Coordinates coordsWhitePawn = coords.plus(1,-1);
        board.placePiece(coords, bishop);
        board.placePiece(coordsWhitePawn, whitePawn);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(1,-1)));
    }

    @Test
    public void whiteBishopCantCaptureWhite() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Piece whitePawn = new Pawn(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(4, 3);
        Coordinates coordsWhitePawn = coords.plus(1,1);
        board.placePiece(coords, bishop);
        board.placePiece(coordsWhitePawn, whitePawn);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(1,1)));
    }

    @Test
    public void blackBishopCantCaptureBlack() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.BLACK);
        Piece blackPawn = new Pawn(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(5, 3);
        Coordinates coordsBlackPawn = coords.plus(-2,2);
        board.placePiece(coords, bishop);
        board.placePiece(coordsBlackPawn, blackPawn);

        // Act
        List<Move> moves = bishop.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-2,2)));
    }
}
