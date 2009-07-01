/*
 * Editor.java
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
import java.awt.Component;
import java.io.Serializable;
import java.util.EventObject;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;

/**
 * This class uses a CellFormat to format a delegate cell editor.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 8, 2009
 */
@SuppressWarnings("serial")
class Editor implements TableCellEditor, Serializable {

    /** The default format of the cell. */
    private CellFormat format;

    /** The editor to use. */
    private TableCellEditor editor;

    /**
     * Creates a new Editor.
     */
    public Editor() {
        // no op
    }

    /**
     * Sets the editor to use.
     *
     * @param editor an editor.
     */
    public void setEditor(final TableCellEditor editor) {
        this.editor = editor;
    }

    /**
     * Sets the CellFormat to use.
     *
     * @param format the new CellFormat.
     */
    public void setCellFormat(final CellFormat format) {
        this.format = format;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Component getTableCellEditorComponent(final JTable table, final Object value,
            final boolean isSelected, final int row, final int column) {
        Component component = editor.getTableCellEditorComponent(table,
                value, isSelected, row, column);

        if (!isSelected) {
            Color foreground = format.getForeground();
            component.setForeground(foreground);

            Color background = format.getBackground();
            component.setBackground(background);
        }

        component.setFont(format.getFont());

        if (component instanceof JTextField) {
            JTextField textfield = (JTextField) component;
            textfield.setBorder(new LineBorder(Color.BLACK, 1));
            // Select all the text to ensure a new input will erase the old text.
            textfield.selectAll();
        }

        return component;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean shouldSelectCell(final EventObject anEvent) {
        return editor.shouldSelectCell(anEvent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean stopCellEditing() {
        return editor.stopCellEditing();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cancelCellEditing() {
        editor.cancelCellEditing();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getCellEditorValue() {
        return editor.getCellEditorValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCellEditable(final EventObject anEvent) {
//        // The editor's component appears only after a double-click
//        if (anEvent instanceof MouseEvent) {
//            return ((MouseEvent) anEvent).getClickCount() >= 2;
//        }
//        return true;
        return editor.isCellEditable(anEvent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCellEditorListener(final CellEditorListener l) {
        editor.addCellEditorListener(l);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeCellEditorListener(final CellEditorListener l) {
        editor.removeCellEditorListener(l);
    }

}
