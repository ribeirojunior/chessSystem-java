package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	private ChessMatch chessMatch;

	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;

	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	private boolean testRookCastling(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p instanceof Rook && p.getColor() == getColor() && p.getMoveCount() == 0;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// above
		p.setValues(position.getRow() - 1, position.getCollumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// bellow
		p.setValues(position.getRow() + 1, position.getCollumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// left
		p.setValues(position.getRow(), position.getCollumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// right
		p.setValues(position.getRow(), position.getCollumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// NW
		p.setValues(position.getRow() - 1, position.getCollumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// NE
		p.setValues(position.getRow() - 1, position.getCollumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// SW
		p.setValues(position.getRow() + 1, position.getCollumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// SE
		p.setValues(position.getRow() + 1, position.getCollumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getCollumn()] = true;
		}

		// #specialMove Castling

		if (getMoveCount() == 0 && !chessMatch.getCheck()) {
			// Specialmove castling kingside
			Position posT1 = new Position(position.getRow(), position.getCollumn() + 3);
			if (testRookCastling(posT1)) {
				Position p1 = new Position(position.getRow(), position.getCollumn() + 1);
				Position p2 = new Position(position.getRow(), position.getCollumn() + 2);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					mat[position.getRow()][position.getCollumn() + 2] = true;
				}
			}
			// Specialmove castling Queenside
			Position posT2 = new Position(position.getRow(), position.getCollumn() - 4);
			if (testRookCastling(posT2)) {
				Position p1 = new Position(position.getRow(), position.getCollumn() - 1);
				Position p2 = new Position(position.getRow(), position.getCollumn() - 2);
				Position p3 = new Position(position.getRow(), position.getCollumn() - 3);
				if (getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					mat[position.getRow()][position.getCollumn() - 2] = true;
				}
			}

		}
		return mat;
	}
}
