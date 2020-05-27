package chess;

import boardgame.Position;

public class ChessPosition {

	private char column;
	private int row;
	
	
	public ChessPosition(char column, int row) {
		if (column < 'a' || column > 'h' || row < 1 || row >8) {
			throw new ChessException("Error to instanciate position. Minimum of a or 1 to column or row, maximum of h or 8 to column o row");
		}
		this.column = column;
		this.row = row;
	}


	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	protected Position toPosition() {
		return new Position(8- row, column - 'a');
	}
	
	protected static ChessPosition fromPosition(Position position) {
		return new ChessPosition((char)('a' + position.getCollumn()), 8 - position.getRow());
	}
	
	@Override
	public String toString() {
		return "" + column + row;
	}
}
