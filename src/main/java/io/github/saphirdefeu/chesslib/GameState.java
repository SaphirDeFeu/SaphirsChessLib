package io.github.saphirdefeu.chesslib;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a chess game's state.<br><br>
 * Can be translated to and from a FEN string.
 */
public class GameState {
    /**
     * The FEN string for a chess starting position
     */
    public static final String STARTING_POSITION_FEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

    /**
     * This {@code GameState}'s board.<br><br>
     * Has 64 squares, indexed from 0 to 63, with 0 being A1, and 63 being H8.
     */
    private final Piece[] board = new Piece[64];

    /**
     * Parses a {@code Forsyth-Edwards Notation} string into a {@link io.github.saphirdefeu.chesslib.GameState} object.<br><br>
     * The method may understand malformed FEN strings, in which case, return value is not guaranteed to be correct.
     *
     * @param fen The FEN string to be parsed
     * @return A {@link io.github.saphirdefeu.chesslib.GameState} object
     * @throws IllegalArgumentException if {@code fen} is {@code null}
     */
    @Contract("null -> fail")
    public static @NotNull GameState parseFENString(String fen) throws IllegalArgumentException {
        if(fen == null) throw new IllegalArgumentException("fen cannot be null");
        GameState gameState = new GameState();

        int index = 56;
        int stringIndex = 0;
        String[] stages = fen.split(" ");

        while(stages[0].length() > stringIndex) {
            char currentCharacter = stages[0].charAt(stringIndex);
            String currentCharString = String.format("%c", currentCharacter);

            if(currentCharacter == '/') {
                index -= 16;
                stringIndex++;
                continue;
            }

            try {
                int num = Integer.parseInt(currentCharString);
                for(int i = 0; i < num; i++) {
                    gameState.getBoard()[index] = null;
                    index++;
                }
                stringIndex++;
                continue;
            } catch(Exception ignored) {}

            Piece.Color color = Piece.Color.WHITE;
            if(currentCharString.toLowerCase().equals(currentCharString)) color = Piece.Color.BLACK;

            Piece.Type type = switch(currentCharString.toLowerCase()) {
                case "p" -> Piece.Type.PAWN;
                case "n" -> Piece.Type.KNIGHT;
                case "b" -> Piece.Type.BISHOP;
                case "r" -> Piece.Type.ROOK;
                case "q" -> Piece.Type.QUEEN;
                case "k" -> Piece.Type.KING;
                default -> null;
            };

            gameState.getBoard()[index] = new Piece(type, color);

            index++;
            stringIndex++;
        }



        return gameState;
    }

    /**
     * Retrieves the current state's {@code board}
     * @return This instance's {@link io.github.saphirdefeu.chesslib.GameState#board}
     */
    public Piece[] getBoard() {
        return board;
    }
}
