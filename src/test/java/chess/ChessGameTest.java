package chess;

import chess.domain.ChessGame;
import chess.domain.board.Chessboard;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Piece;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ChessGameTest {
    @ParameterizedTest(name = "잘못된 위치 입력시 예외가 발생한다")
    @MethodSource("invalidSquareProvider")
    void moveToInvalidSquare(Square source, Square target) {
        ChessGame chessGame = new ChessGame();

        Assertions.assertThatThrownBy(() -> chessGame.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "이동 가능한 위치 입력시 기물이 이동한다")
    @MethodSource("validSquareProvider")
    void moveToValidSquare(Square source, Square target) {
        ChessGame chessGame = new ChessGame();
        Chessboard chessboard = chessGame.getChessboard();
        Piece expected = chessboard.getPieceAt(source);
        chessGame.move(source, target);


        Assertions.assertThat(chessboard.getPieceAt(target))
                .isEqualTo(expected);
    }

    @DisplayName("대각선에 상대 폰이 있을 경우, 이동할 수 있다.")
    @Test
    void movePawnIfPresentEnemyAtDiagonal() {
        Square source = new Square(File.A, Rank.TWO);
        Square target = new Square(File.B, Rank.THREE);
        ChessGame chessGame = new ChessGame();
        Chessboard chessboard = chessGame.getChessboard();
        Piece expected = chessboard.getPieceAt(source);

        chessboard.swapPiece(new Square(File.B, Rank.SEVEN), new Square(File.B, Rank.THREE));
        chessGame.move(source, target);

        Assertions.assertThat(chessboard.getPieceAt(target))
                .isEqualTo(expected);
    }

    @DisplayName("Pawn의 시작 위치가 아닌 경우, 2칸을 이동할 수 없다.")
    @Test
    void moveTwoRankPawnIfNotStartSquareFailTest() {
        Square source = new Square(File.A, Rank.TWO);
        Square movedSquare = new Square(File.A, Rank.THREE);
        Square target = new Square(File.A, Rank.FIVE);

        ChessGame chessGame = new ChessGame();
        Chessboard chessboard = chessGame.getChessboard();

        chessboard.swapPiece(source, movedSquare);

        Assertions.assertThatThrownBy(() -> chessGame.move(movedSquare, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Pawn 앞에 장애물(아군, 적 상관 x)이 있을 경우, 이동할 수 없다.")
    @Test
    void movePawnIfPresentPieceAtTargetSquareFailTest() {
        Square source = new Square(File.A, Rank.TWO);
        Square movedSquare = new Square(File.A, Rank.SIX);
        Square target = new Square(File.A, Rank.SEVEN);

        ChessGame chessGame = new ChessGame();
        Chessboard chessboard = chessGame.getChessboard();

        chessboard.swapPiece(source, movedSquare);

        Assertions.assertThatThrownBy(() -> chessGame.move(movedSquare, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> invalidSquareProvider() {
        return Stream.of(
                Arguments.arguments(new Square(File.A, Rank.ONE), new Square(File.A, Rank.THREE)),
                Arguments.arguments(new Square(File.A, Rank.TWO), new Square(File.A, Rank.FIVE)),
                Arguments.arguments(new Square(File.A, Rank.THREE), new Square(File.A, Rank.FOUR)),
                Arguments.arguments(new Square(File.A, Rank.TWO), new Square(File.B, Rank.FOUR)),
                Arguments.arguments(new Square(File.A, Rank.TWO), new Square(File.B, Rank.THREE)),
                Arguments.arguments(new Square(File.A, Rank.TWO), new Square(File.B, Rank.TWO))
        );
    }

    static Stream<Arguments> validSquareProvider() {
        return Stream.of(
                Arguments.arguments(new Square(File.A, Rank.TWO), new Square(File.A, Rank.THREE)),
                Arguments.arguments(new Square(File.A, Rank.TWO), new Square(File.A, Rank.FOUR))
        );
    }


}
