/*
 * FormatManager.java
 *
 * Copyright (C) 2009 Olivier Sechet
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.druppi.swing.table.format;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import net.druppi.swing.FTable;
import net.druppi.swing.table.format.categories.CategoryFactory;
import net.druppi.swing.table.format.categories.FormatCategory;

import com.mallardsoft.tuple.Pair;



/**
 * Describes the format of the cells of a grid. The format can be defined per cell or per
 * row or per column or per type. The priority is as follow : cell > row > column > type.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 7, 2009
 */
public class FormatManager {

    /** The associated table. */
    private final FTable table;

    /** The format per column. */
    private Map<Integer, CellFormat> columns = new HashMap<Integer, CellFormat>();

    /** The format per row. */
    private Map<Integer, CellFormat> rows = new HashMap<Integer, CellFormat>();

    /** The format per cell. */
    private Map<Pair<Integer, Integer>, CellFormat> cells = new HashMap<Pair<Integer, Integer>, CellFormat>();

    /** Formating conditions. */
    private Map<Condition, CellFormat> conditions = new HashMap<Condition, CellFormat>();

    /** The already defined format. */
    private List<CellFormat> formats = new ArrayList<CellFormat>();

    /** The default format when no format has been defined. */
    private CellFormat defaultFormat;

    /** A unique renderer shared between all the cells. */
    private Renderer renderer = new Renderer();

    /** A unique editor shared between all the cells. */
    private Editor editor = new Editor();

    /**
     * Creates a new FormatManager.
     *
     * @param table the associated table.
     */
    public FormatManager(final FTable table) {
        this.table = table;

        defaultFormat = new CellFormat(null, Alignment.LEFT,
                CategoryFactory.createGeneralCategory(), table.getFont(),
                table.getForeground(), table.getBackground());
    }

    /**
     * Creates and/or returns a CellFormat with the given properties. If an equivalent
     * CellFormat already exists, it is returned. Otherwise, a new one is returned.
     *
     * @param alignment the text alignment.
     * @param formatter the text formatter.
     * @param font the font.
     * @param foreground the text color.
     * @param background the background color.
     * @return a CellFormat.
     * @see CellFormat#CellFormat(Alignment, FormatCategory, Font, Color, Color)
     */
    public CellFormat getCellFormat(final Alignment alignment,
            final FormatCategory formatter, final Font font, final Color foreground,
            final Color background) {
        CellFormat cellFormat = new CellFormat(defaultFormat, alignment, formatter, font,
                foreground, background);
        // Check if such a format exist.
        int index = formats.indexOf(cellFormat);
        if (index >= 0) {
            return formats.get(index);
        }
        // An equivalent format does not exist. The new one is added to the list and
        // returned.
        formats.add(cellFormat);
        return cellFormat;
    }

    /**
     * Defines the format for the specified column.
     *
     * @param columnIndex the column index.
     * @param format the format.
     */
    public final void setColumnFormat(final int columnIndex, final CellFormat format) {
        columns.put(columnIndex, format);
    }

    /**
     * Defines the format for the specified row.
     *
     * @param rowIndex the row index.
     * @param format the format.
     */
    public final void setRowFormat(final int rowIndex, final CellFormat format) {
        rows.put(rowIndex, format);
    }

    /**
     * Defines the format for the specified cell.
     *
     * @param rowIndex the row index.
     * @param columnIndex the column index.
     * @param format the format.
     */
    public final void setFormat(final int rowIndex, final int columnIndex,
            final CellFormat format) {
        cells.put(Pair.from(rowIndex, columnIndex), format);
    }

    /**
     * Defines a conditional formating.
     *
     * @param condition the condition to match.
     * @param format the format.
     */
    public final void setConditionFormat(final Condition condition,
            final CellFormat format) {
        conditions.put(condition, format);
    }

    /**
     * Returns the format of the specified cell. Returns null if no format is defined for
     * the given cell.
     *
     * @param rowIndex the row index.
     * @param columnIndex the column index.
     *
     * @return the format.
     */
    final CellFormat getFormat(final int rowIndex, final int columnIndex) {
        CellFormat format = null;
        for (Iterator<Condition> iterator = conditions.keySet().iterator(); iterator.hasNext();) {
            Condition condition = iterator.next();
            if (condition.matches(table, rowIndex, columnIndex)) {
                format = conditions.get(condition);
            }
        }
        if (format == null) {
            format = cells.get(Pair.from(rowIndex, columnIndex));
            if (format == null) {
                format = rows.get(rowIndex);
                if (format == null) {
                    format = columns.get(columnIndex);
                    if (format == null) {
                        format = defaultFormat;
                    }
                }
            }
        }
        return format;
    }

    /**
     * Returns an appropriate renderer for the cell specified by this <code>row</code> and
     * <code>column</code>.
     *
     * @param row the row of the cell to render, where 0 is the first row
     * @param column the column of the cell to render, where 0 is the first column
     *
     * @return the assigned renderer; if <code>null</code> returns the default renderer
     *         for this type of object.
     * @see FTable#getCellRenderer(int, int)
     */
    public TableCellRenderer getCellRenderer(final int row, final int column) {
        int rowIndex = table.convertRowIndexToModel(row);
        int columnIndex = table.convertColumnIndexToModel(column);
        renderer.setCellFormat(getFormat(rowIndex, columnIndex));
        renderer.setRenderer(table.getDefaultRenderer(table.getModel().getColumnClass(columnIndex)));
        return renderer;
    }

    /**
     * Returns an appropriate editor for the cell specified by <code>row</code> and
     * <code>column</code>.
     *
     * @param row the row of the cell to edit, where 0 is the first row
     * @param column the column of the cell to edit, where 0 is the first column
     *
     * @return the editor for this cell; if <code>null</code> return the default editor
     *         for this type of cell.
     * @see FTable#getCellEditor(int, int)
     */
    public TableCellEditor getCellEditor(final int row, final int column) {
        int rowIndex = table.convertRowIndexToModel(row);
        int columnIndex = table.convertColumnIndexToModel(column);
        editor.setCellFormat(getFormat(rowIndex, columnIndex));
        editor.setEditor(table.getDefaultEditor(table.getModel().getColumnClass(columnIndex)));
        return editor;
    }

}
