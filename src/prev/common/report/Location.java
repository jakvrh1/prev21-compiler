package prev.common.report;

import org.antlr.v4.runtime.Token;
import prev.common.logger.*;
import prev.data.ast.tree.AstTree;

/**
 * Description of a location of a part of a source file.
 */
public class Location implements Locatable, Loggable {

	/**
	 * The line number of the first character of the specified part of the source
	 * file.
	 */
	private final int begLine;

	/**
	 * The column number of the first character of the specified part of the source
	 * file.
	 */
	private final int begColumn;

	/**
	 * The line number of the last character of the specified part of the source
	 * file.
	 */
	private final int endLine;

	/**
	 * The column number of the last character of the specified part of the source
	 * file.
	 */
	private final int endColumn;

	/**
	 * Constructs a new location if the position of the first and the last
	 * characters are given.
	 * 
	 * @param begLine   The line number of the first character of the specified part
	 *                  of the source file.
	 * @param begColumn The column number of the first character of the specified
	 *                  part of the source file.
	 * @param endLine   The line number of the last character of the specified part
	 *                  of the source file.
	 * @param endColumn The column number of the last character of the specified
	 *                  part of the source file.
	 */
	public Location(int begLine, int begColumn, int endLine, int endColumn) {
		this.begLine = begLine != 0 ? begLine : endLine;
		this.begColumn = begColumn != 0 ? begColumn : endColumn;
		this.endLine = endLine != 0 ? endLine : begLine;
		this.endColumn = endColumn != 0 ? endColumn : begColumn;
	}

	/**
	 * Constructs a new location if the position of a single character is given.
	 * 
	 * @param line   The line number of the character of the specified part of the
	 *               source file.
	 * @param column The column number of the character of the specified part of the
	 *               source file.
	 */
	public Location(int line, int column) {
		this(line, column, line, column);
	}

	/**
	 * Constructs a new location given an object relating to a part of a source
	 * file.
	 * 
	 * @param that An object relating to a part of a source file.
	 */
	public Location(Locatable that) {
		this(//
				that == null ? 0 : that.location().begLine, that == null ? 0 : that.location().begColumn,
				that == null ? 0 : that.location().endLine, that == null ? 0 : that.location().endColumn);
	}

	/**
	 * Constructs a new location given two objects relating to parts of a source
	 * file.
	 * 
	 * @param beg An object relating to the beginning of part of a source file.
	 * @param end An object relating to the end of part of a source file.
	 */
	public Location(Locatable beg, Locatable end) {
		this(//
				beg == null ? 0 : beg.location().begLine, beg == null ? 0 : beg.location().begColumn,
				end == null ? 0 : end.location().endLine, end == null ? 0 : end.location().endColumn);
	}

	@Override
	public Location location() {
		return this;
	}

	@Override
	public void log(Logger logger) {
		if (logger == null)
			return;
		logger.begElement("location");
		logger.addAttribute("loc", toString());
		logger.addAttribute("begline", Integer.toString(begLine));
		logger.addAttribute("begcolumn", Integer.toString(begColumn));
		logger.addAttribute("endline", Integer.toString(endLine));
		logger.addAttribute("endcolumn", Integer.toString(endColumn));
		logger.endElement();
	}

	static public Location createLocation(Object arg1, Object arg2) {
		Location loc1 = null;
		Location loc2 = null;

		if(arg1 instanceof Token)
			loc1 = new Location(((Token) arg1).getLine(), ((Token) arg1).getCharPositionInLine());
		else
			loc1 = ((AstTree)arg1).location();

		if(arg2 instanceof Token)
			loc2 = new Location(((Token) arg2).getLine(), ((Token) arg2).getCharPositionInLine() + ((Token) arg2).getText().length() - 1);
		else
			loc2 = ((AstTree)arg2).location();

		return new Location(loc1, loc2);
	}

	static public Location createLocation(Object arg1) {
		if(arg1 instanceof Token)
			return createLocation(arg1, arg1);
		else
			return ((AstTree)arg1).location();
	}

	@Override
	public String toString() {
		return begLine + "." + begColumn + " - " + endLine + "." + endColumn;
	}

}
