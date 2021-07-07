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

        // Assert
        // Vertical moves
        for (int i = 0; i < 8; i++) {
            if (i != 3)
                assert(moves.contains(new Move(coords, new Coordinates(3, i))));
        }
        // Horizontal moves
        for (int i = 0; i < 8; i++) {
            if (i != 3)
            assert(moves.contains(new Move(coords, new Coordinates(3, i))));
        }

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

    public void rookCannotMoveDiagonally(Board board, Piece rook) {
        // Arrange
        Coordinates coords = new Coordinates(3, 3);
        board.placePiece(coords, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert
        int i = 6;
        int j = 0;

        while(i >= 0 && j < 7){
            assert(!moves.contains(new Move(coords, new Coordinates(i, j))));
            i--;
            j++;
        }

        i = 0;
        j = 0;
        
        while(i < 8 && j < 8){
            assert(!moves.contains(new Move(coords, new Coordinates(i, j))));
            i++;
            j++;
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
        assert(!moves.contains(new Move(coords, new Coordinates(3, -1))));
        assert(!moves.contains(new Move(coords, new Coordinates(3, 8))));
        assert(!moves.contains(new Move(coords, new Coordinates(-1, 3))));
        assert(!moves.contains(new Move(coords, new Coordinates(8, 3))));

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
        assert(!moves.contains(new Move(coords, new Coordinates(3, -1))));
        assert(!moves.contains(new Move(coords, new Coordinates(3, 8))));
        assert(!moves.contains(new Move(coords, new Coordinates(-1, 3))));
        assert(!moves.contains(new Move(coords, new Coordinates(8, 3))));
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
        Coordinates coords4 = new Coordinates(6, 3);

        board.placePiece(coords, rook);
        board.placePiece(coords1, rook);
        board.placePiece(coords2, rook);
        board.placePiece(coords3, rook);
        board.placePiece(coords4, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(coords, board);

        // Assert

        // Not allowed behind piece
        assert(!moves.contains(new Move(coords, new Coordinates(3, 0))));
        assert(!moves.contains(new Move(coords, new Coordinates(3, 1))));
        assert(!moves.contains(new Move(coords, new Coordinates(0, 3))));
        assert(!moves.contains(new Move(coords, new Coordinates(7, 3))));

        // Allowed before piece
        assert(moves.contains(new Move(coords, new Coordinates(2, 3))));
        assert(moves.contains(new Move(coords, new Coordinates(3, 4))));
        assert(moves.contains(new Move(coords, new Coordinates(3, 5))));
        assert(moves.contains(new Move(coords, new Coordinates(3, 6))));
        assert(moves.contains(new Move(coords, new Coordinates(4, 3))));
        assert(moves.contains(new Move(coords, new Coordinates(5, 3))));
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
        assert(moves.contains(new Move(coords, new Coordinates(3, 6))));
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
        assert(moves.contains(new Move(coords, new Coordinates(3, 6))));
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
        assert(!moves.contains(new Move(coords, new Coordinates(3, 6))));
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
        assert(!moves.contains(new Move(coords, new Coordinates(3, 6))));

    }

}
