package chess.domain.piece;

import chess.domain.board.Square;

public class Pawn extends Piece {

    private static final int WHITE_MAX_MOVABLE_RANK = 2;
    private static final int WHITE_MIN_MOVABLE_RANK = 1;
    private static final int BLACK_MAX_MOVABLE_RANK = -2;
    private static final int BLACK_MIN_MOVABLE_RANK = -1;
    private static final int MIN_MOVABLE_FILE = 1;

    public Pawn(Camp camp) {
        super(camp);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.PAWN;
    }

    @Override
    public boolean canMove(Square source, Square target) {

        if (isWhite()) {
            return canMoveWhite(source, target);
        }

        return canMoveBlack(source, target);
    }

    private boolean canMoveBlack(Square source, Square target) {
        int rankDifference = source.calculateRankDifference(target);
        int fileDistance = source.calculateFileDistance(target);

        //뒤로 가는거 + 옆으로 가는거 검증
        if (rankDifference >= 0) {
            return false;
        }
        //시작 위치
        if (source.isRankSeven()) {
            return rankDifference >= BLACK_MAX_MOVABLE_RANK && fileDistance == 0 ||
                    rankDifference == BLACK_MIN_MOVABLE_RANK && fileDistance == 1;
        }

        return rankDifference == BLACK_MIN_MOVABLE_RANK && fileDistance <= 1;
    }

    private boolean canMoveWhite(Square source, Square target) {
        int rankDifference = source.calculateRankDifference(target);
        int fileDistance = source.calculateFileDistance(target);

        //뒤로 가는거 + 옆으로 가는거 검증
        if (rankDifference <= 0) {
            return false;
        }
        //시작 위치
        if (source.isRankTwo()) {
            return rankDifference <= WHITE_MAX_MOVABLE_RANK && fileDistance == 0 ||
                    rankDifference == WHITE_MIN_MOVABLE_RANK && fileDistance == 1;
        }

        return rankDifference == WHITE_MIN_MOVABLE_RANK && fileDistance <= 1;
    }
}