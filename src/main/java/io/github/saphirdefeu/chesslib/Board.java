package io.github.saphirdefeu.chesslib;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a chess board.<br><br>
 * Has a {@link io.github.saphirdefeu.chesslib.GameState} that stores data that can be translated directly into a FEN string
 */
public final class Board {

    private GameState state;

    /**
     * Creates a {@code Board} object using a FEN string as a starting state
     * @param fen The FEN string to start the board on
     * @throws IllegalArgumentException if {@code fen} is {@code null}
     */
    @Contract("null -> fail")
    public Board(String fen) throws IllegalArgumentException {
        if(fen == null) throw new IllegalArgumentException("fen cannot be null");

        this.state = GameState.parseFENString(fen);
    }

    /**
     * Creates a {@code Board} object using {@link io.github.saphirdefeu.chesslib.GameState#STARTING_POSITION_FEN}
     */
    public Board() {
        this.state = GameState.parseFENString(GameState.STARTING_POSITION_FEN);
    }

    /**
     * Retrieves the {@link io.github.saphirdefeu.chesslib.Piece} object located at {@code index}
     * @param index The index of the square to check, with square 0 being A1 and square 63 being H8
     * @return The {@link io.github.saphirdefeu.chesslib.Piece} at {@code index}
     */
    public @NotNull Piece pieceAt(int index) {
        if(index < 0 || index >= 64) throw new IllegalArgumentException("index cannot be outside range [0,63]");
        return this.state.getBoard()[index];
    }
}
