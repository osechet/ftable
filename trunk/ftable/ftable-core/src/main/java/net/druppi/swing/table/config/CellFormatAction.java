/*
 * CellFormatAction.java
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
package net.druppi.swing.table.config;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JDialog;

import net.druppi.swing.OKCancelForm;
import net.druppi.swing.FTable;
import net.druppi.swing.table.format.Alignment;
import net.druppi.swing.table.format.CellFormat;
import net.druppi.swing.table.format.CellsFormatForm;
import net.druppi.swing.table.format.categories.FormatCategory;
import net.druppi.util.ResourceManager;


/**
 * Defines an action that display an CellFormatDialog for the associated table.
 *
 * @author Olivier Sechet
 * @version 1.0 - Mar 24, 2009
 */
@SuppressWarnings("serial")
class CellFormatAction extends AbstractAction {

    /** The associated table. */
    private FTable table;

    /** The dialog. */
    private JDialog dialog;

    /** The panel used to define the format. */
    private CellsFormatForm formatPanel;

    /**
     * Creates a new CellFormatAction.
     *
     * @param table the associated table.
     */
    public CellFormatAction(final FTable table) {
        super(ResourceManager.getResourceMap(PopupConfigurator.class).getString("CellFormatAction.text")); //$NON-NLS-1$
        this.table = table;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(final ActionEvent evt) {
        if (dialog == null) {
            createDialog();
        }
        formatPanel.initFrom(table);
        dialog.setVisible(true);
    }

    /**
     *
     */
    private void apply() {
        FormatCategory format = formatPanel.getSelectedFormat();
        Alignment alignment = formatPanel.getSelectedAlignment();
        Font font = formatPanel.getSelectedFont();
        Color foreground = formatPanel.getSelectedForeground();
        Color background = formatPanel.getSelectedBackground();

        CellFormat cellFormat = table.getFormatManager().getCellFormat(alignment, format, font, foreground, background);
        int[] rows = table.getSelectedRows();
        int[] columns = table.getSelectedColumns();
        if (columns.length == table.getColumnCount()) {
            // The whole row is selected
            for (int row : rows) {
                table.getFormatManager().setRowFormat(row, cellFormat);
            }
        } else if (rows.length == table.getRowCount()) {
            // The whole column is selected
            for (int column : columns) {
                table.getFormatManager().setColumnFormat(column, cellFormat);
            }
        } else {
            for (int row : rows) {
                for (int column : columns) {
                    table.getFormatManager().setFormat(row, column, cellFormat);
                }
            }
        }
    }

    /**
     * Closes the dialog.
     */
    private void close() {
        dialog.setVisible(false);
    }

    /**
     * Creates the dialog.
     */
    private void createDialog() {
        Container ancestor = table.getTopLevelAncestor();
        Frame frame = null;
        if (ancestor instanceof Frame) {
            frame = (Frame) ancestor;
        }
        dialog = new JDialog(frame, ResourceManager.getResourceMap(PopupConfigurator.class).getString("CellFormatAction.dialog.title"), false); //$NON-NLS-1$
        formatPanel = new CellsFormatForm();
        dialog.add(formatPanel);

        OKCancelForm okCancelPanel = new OKCancelForm(new OKHandler(), new ApplyHandler(),
                new CancelHandler());
        dialog.add(okCancelPanel, BorderLayout.SOUTH);

        dialog.pack();
        dialog.setLocationRelativeTo(ancestor);
    }

    /**
     * The OK button listener.
     *
     * @author Olivier Sechet
     * @version 1.0 - Apr 16, 2009
     */
    private class OKHandler implements ActionListener {

        /**
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(final ActionEvent evt) {
            apply();
            close();
        }
    }

    /**
     * The Apply button listener.
     *
     * @author Olivier Sechet
     * @version 1.0 - Apr 16, 2009
     */
    private class ApplyHandler implements ActionListener {

        /**
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(final ActionEvent evt) {
            apply();
        }
    }

    /**
     * The Cancel button listener.
     *
     * @author Olivier Sechet
     * @version 1.0 - Apr 16, 2009
     */
    private class CancelHandler implements ActionListener {

        /**
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(final ActionEvent evt) {
            close();
        }
    }
}
