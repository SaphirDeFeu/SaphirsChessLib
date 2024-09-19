import io.github.saphirdefeu.chesslib.Board;
import io.github.saphirdefeu.chesslib.Piece;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TChess {

    @Test
    public void onBoardCreation_isA1WhiteRook() {
        Board board = new Board();
        Piece piece = board.pieceAt(0);
        Assertions.assertEquals(piece, new Piece(Piece.Type.ROOK, Piece.Color.WHITE));
    }

}
