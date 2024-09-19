package io.github.saphirdefeu.chesslib;

import java.util.Objects;

/**
 * Class representing a chess piece. Has a type and color
 */
public class Piece {
    private Type type;
    private final Color color;

    /**
     * Enum for piece types
     */
    public enum Type {
        /**
         * A pawn - has a value of 1 point
         */
        PAWN,
        /**
         * A knight - has a value of 3 points
         */
        KNIGHT,
        /**
         * A bishop - has a value of 3 points
         */
        BISHOP,
        /**
         * A rook - has a value of 5 points
         */
        ROOK,
        /**
         * A queen - has a value of 9 points
         */
        QUEEN,
        /**
         * A king - has no determined value (invaluable)
         */
        KING
    }

    /**
     * Enum for player colors
     */
    public enum Color {
        /**
         * The white pieces
         */
        WHITE,
        /**
         * The black pieces
         */
        BLACK
    }

    /**
     * Creates a {@code Piece} object from a corresponding {@code type} and {@code color}
     * @param type The type of that piece
     * @param color The color of that piece
     * @throws IllegalArgumentException if {@code type} or {@code color} is {@code null}
     */
    public Piece(Type type, Color color) throws IllegalArgumentException {
        if(type == null) throw new IllegalArgumentException("type cannot be null");
        if(color == null) throw new IllegalArgumentException("color cannot be null");
        this.type = type;
        this.color = color;
    }

    /**
     * Promotes this piece to another type
     * @param promotionType The type to be promoted to
     * @return Whether the promotion was successful
     * @throws IllegalArgumentException if {@code promotionType} is {@code null},
     * {@link io.github.saphirdefeu.chesslib.Piece.Type#KING} or
     * {@link io.github.saphirdefeu.chesslib.Piece.Type#PAWN}
     */
    public boolean promote(Type promotionType) throws IllegalArgumentException {
        if(promotionType == null) throw new IllegalArgumentException("promotionType cannot be null");
        if(promotionType == Type.PAWN || promotionType == Type.KING) throw new IllegalArgumentException("promotionType cannot be pawn or king");
        if(this.type != Type.PAWN) return false;
        this.type = promotionType;
        return true;
    }

    /**
     * Retrieves this piece's color
     * @return A {@link io.github.saphirdefeu.chesslib.Piece.Color} enum constant
     */
    public Color getColor() {
        return color;
    }

    /**
     * Retrieves this piece's type
     * @return A {@link io.github.saphirdefeu.chesslib.Piece.Type} enum constant
     */
    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return getType() == piece.getType() && getColor() == piece.getColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType(), getColor());
    }
}
