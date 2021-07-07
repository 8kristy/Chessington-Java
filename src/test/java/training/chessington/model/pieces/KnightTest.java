package training.chessington.model.pieces;

import static training.chessington.model.pieces.PieceAssert.*;

import java.util.List;

import org.junit.Test;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import static org.assertj.core.api.Assertions.*;

public class KnightTest {
    @Test
    public void whiteKnightCanMoveCorrectly() {
        // Arrange
        Piece knight = new Knight(PlayerColour.WHITE);
        
        // Assert
        KnightCanMoveCorrectly(knight);
    }

    @Test
    public void blackKnightCanMoveCorrectly() {
        // Arrange
        Piece knight = new Knight(PlayerColour.BLACK);
        
        // Assert
        KnightCanMoveCorrectly(knight);
    }

    private void KnightCanMoveCorrectly(Piece knight) {
        // Arrange
        Board board = Board.empty();
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, knight);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        knightCanMoveInDirection(moves, coords, 2, 1);
        knightCanMoveInDirection(moves, coords, -2, 1);
        knightCanMoveInDirection(moves, coords, 2, -1);
        knightCanMoveInDirection(moves, coords, -2, -1);
        knightCanMoveInDirection(moves, coords, 1, 2);
        knightCanMoveInDirection(moves, coords, -1, 2);
        knightCanMoveInDirection(moves, coords, 1, -2);
        knightCanMoveInDirection(moves, coords, -1, -2);
    }

    private void knightCanMoveInDirection(List<Move> moves, Coordinates coords, int xDirection, int yDirection) {
        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(xDirection, yDirection)));
    }

    @Test
    public void whiteKnightCantMoveIncorrectly() {
        // Arrange
        Piece knight = new Knight(PlayerColour.WHITE);
        
        // Assert
        KnightCanMoveCorrectly(knight);
    }

    @Test
    public void blackKnightCantMoveIncorrectly() {
        // Arrange
        Piece knight = new Knight(PlayerColour.BLACK);
        
        // Assert
        KnightCanMoveCorrectly(knight);
    }

    private void KnightCantMoveIncorrectly(Piece knight) {
        // Arrange
        Board board = Board.empty();
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, knight);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        knightCantMoveInDirection(moves, coords, 0, 1);
        knightCantMoveInDirection(moves, coords, -2, 2);
        knightCantMoveInDirection(moves, coords, 2, -4);
        knightCantMoveInDirection(moves, coords, -3, -3);
        knightCantMoveInDirection(moves, coords, 3, 0);
        knightCantMoveInDirection(moves, coords, 2, -3);
        knightCantMoveInDirection(moves, coords, 0, 2);
        knightCantMoveInDirection(moves, coords, -4, -4);
    }

    private void knightCantMoveInDirection(List<Move> moves, Coordinates coords, int xDirection, int yDirection) {
        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(xDirection, yDirection)));
    }

    @Test
    public void whiteKnightCantMoveOffBoard() {
        // Arrange
        Piece knight = new Knight(PlayerColour.WHITE);
     
        Board board = Board.empty();
        Coordinates coords = new Coordinates(6, 6);
        board.placePiece(coords, knight);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        knightCantMoveInDirection(moves, coords, 2, 1);
        knightCantMoveInDirection(moves, coords, 2, -1);
        knightCantMoveInDirection(moves, coords, 1, 2);
        knightCantMoveInDirection(moves, coords, -1, 2);
    }

    @Test
    public void blackKnightCantMoveOffBoard() {
        // Arrange
        Piece knight = new Knight(PlayerColour.BLACK);
     
        Board board = Board.empty();
        Coordinates coords = new Coordinates(1, 1);
        board.placePiece(coords, knight);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        knightCantMoveInDirection(moves, coords, -2, 1);
        knightCantMoveInDirection(moves, coords, -2, -1);
        knightCantMoveInDirection(moves, coords, 1, -2);
        knightCantMoveInDirection(moves, coords, -1, -2);
    }

    @Test
    public void whiteKnightCanJumpOver() {
        // Arrange
        Piece knight = new Knight(PlayerColour.WHITE);

        // Assert
        knightCanJumpOver(knight);
    }

    @Test
    public void blackKnightCanJumpOver() {
        // Arrange
        Piece knight = new Knight(PlayerColour.BLACK);

        // Assert
        knightCanJumpOver(knight);
    }

    private void knightCanJumpOver(Piece knight) {
        // Arrange
        Board board = Board.empty();
        Coordinates coords = new Coordinates(4, 4);
        board.placePiece(coords, knight);

        Piece whitePawn = new Pawn(PlayerColour.WHITE);
        Piece blackPawn = new Pawn(PlayerColour.BLACK);
        Coordinates coordsWhitePawn = coords.plus(0,1);
        Coordinates coordsBlackPawn = coords.plus(-2,0);
        board.placePiece(coordsWhitePawn, whitePawn);
        board.placePiece(coordsBlackPawn, blackPawn);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(1, 2)));
        assertThat(moves).contains(new Move(coords, coords.plus(2, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-2, 1)));
        assertThat(moves).contains(new Move(coords, coords.plus(-2, -1)));
    }

    @Test
    public void whiteKnightCanCaptureBlack() {
        // Arrange
        Piece knight = new Knight(PlayerColour.WHITE);
        Piece pawn = new Pawn(PlayerColour.BLACK);
        
        // Assert
        knightCanCaptureOpponent(knight, pawn);
    }

    @Test
    public void blackKnightCanCaptureWhite() {
        // Arrange
        Piece knight = new Knight(PlayerColour.BLACK);
        Piece pawn = new Pawn(PlayerColour.WHITE);

        // Assert
        knightCanCaptureOpponent(knight, pawn);
    }

    private void knightCanCaptureOpponent(Piece knight, Piece opponent) {
        // Arrange
        Board board = Board.empty();
        Coordinates coords = new Coordinates(5, 6);
        Coordinates coordsOpp = coords.plus(2, -1);
        board.placePiece(coords, knight);
        board.placePiece(coordsOpp, opponent);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).contains(new Move(coords, coords.plus(2, -1)));
    }

    @Test
    public void whiteKnightCantCaptureWhite() {
        // Arrange
        Piece knight = new Knight(PlayerColour.WHITE);
        Piece pawn = new Pawn(PlayerColour.WHITE);
        
        // Assert
        knightCantCaptureAlly(knight, pawn);
    }

    @Test
    public void blackKnightCanCaptureBlack() {
        // Arrange
        Piece knight = new Knight(PlayerColour.BLACK);
        Piece pawn = new Pawn(PlayerColour.BLACK);

        // Assert
        knightCantCaptureAlly(knight, pawn);
    }

    private void knightCantCaptureAlly(Piece knight, Piece ally) {
        // Arrange
        Board board = Board.empty();
        Coordinates coords = new Coordinates(2, 3);
        Coordinates coordsAlly = coords.plus(-2, 1);
        board.placePiece(coords, knight);
        board.placePiece(coordsAlly, ally);

        // Act
        List<Move> moves = knight.getAllowedMoves(coords, board);

        // Assert
        assertThat(moves).doesNotContain(new Move(coords, coords.plus(-2, 1)));
    }
}
