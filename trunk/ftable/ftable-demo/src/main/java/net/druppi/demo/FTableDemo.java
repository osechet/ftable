/*
 * FTableDemo.java
 *
 * Copyright (C) 2009 Olivier Sechet
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.druppi.demo;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.druppi.swing.FTable;
import net.druppi.swing.table.config.PopupConfigurator;
import net.druppi.swing.table.format.Alignment;
import net.druppi.swing.table.format.CellFormat;
import net.druppi.swing.table.format.Condition;
import net.druppi.swing.table.format.FormatManager;

/**
 * @author Olivier Sechet
 * @version 1.0 - Sep 3, 2009
 */
public final class FTableDemo extends AbstractDemo {

    private JTextField filterText;
    private TableRowSorter<TableModel> sorter;
    private JPanel panel;

    /**
     * Creates a new FTableDemo.
     */
    public FTableDemo() {
        super("Rendering with FTable", "/net/druppi/demo/FTableDemo"); //$NON-NLS-1$ //$NON-NLS-2$
        createPanel();
    }

    /**
     *
     */
    private void createPanel() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final FTable table = new FTable(new TableDemoModel());
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);
        panel.add(new JScrollPane(table));
        filterText = new JTextField();
        panel.add(filterText, BorderLayout.SOUTH);

        FormatManager formatManager = table.getFormatManager();
        // All the cells of the second column are right-aligned
        CellFormat format1 = formatManager.getCellFormat(null, null, null, null, new Color(224, 224, 255));
        formatManager.setColumnFormat(2, format1);

        // All the cells of the 4th row have a green background
        CellFormat format2 = formatManager.getCellFormat(null, null, null, null, new Color(224, 255, 224));
        formatManager.setRowFormat(3, format2);

        // All the cells whose value contains 1 are right aligned
        CellFormat format3 = formatManager.getCellFormat(Alignment.RIGHT, null, null, null, null);
        formatManager.setConditionFormat(new Condition() {

            @Override
            public boolean matches(final FTable table, final int rowIndex, final int columnIndex) {
                Object value = table.getModel().getValueAt(rowIndex, columnIndex);
                return value != null && value.toString().indexOf("1") >= 0; //$NON-NLS-1$
            }
        }, format3);

        PopupConfigurator.createPopupMenu(table);

        sorter = new TableRowSorter<TableModel>(table.getModel());
        table.setRowSorter(sorter);

        filterText.getDocument().addDocumentListener(new DocumentListener() {

            /**
             * Creates a new filter from the text field content.
             */
            private void newFilter() {
                RowFilter<TableModel, Object> rf = null;
                //If current expression doesn't parse, don't update.
                try {
                    rf = RowFilter.regexFilter(filterText.getText());
                } catch (java.util.regex.PatternSyntaxException e) {
                    return;
                }
                sorter.setRowFilter(rf);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                newFilter();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                newFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newFilter();
            }
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getPanel() {
        return panel;
    }
}