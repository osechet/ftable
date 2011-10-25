/*
 * JTableDemo.java
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
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


/**
 * @author Olivier Sechet
 * @version 1.0 - Sep 3, 2009
 */
public final class JTableDemo extends AbstractDemo {

    private JTextField filterText;
    private TableRowSorter<TableModel> sorter;
    private JPanel panel;

    /**
     * Creates a new JTableDemo.
     */
    public JTableDemo() {
        super("Rendering with standard JTable", "/net/druppi/demo/JTableDemo"); //$NON-NLS-1$ //$NON-NLS-2$
        createPanel();
    }

    /**
     *
     */
    @SuppressWarnings("serial")
    private void createPanel() {
        panel = new JPanel();

        // Blue background
        final BackgroundRenderer renderer1 = new BackgroundRenderer(new Color(224, 224, 255));
        // Green background
        final BackgroundRenderer renderer2 = new BackgroundRenderer(new Color(224, 255, 224));
        // Right alignment
        final AlignmentRenderer renderer3 = new AlignmentRenderer(JLabel.RIGHT);

        panel.setLayout(new BorderLayout());
        final JTable table = new JTable(new TableDemoModel()) {
            @Override
            public TableCellRenderer getCellRenderer(int row, int column) {
                Object value = getModel().getValueAt(row, column);
                if (value != null && value.toString().indexOf("1") >= 0) { //$NON-NLS-1$
                    return renderer3;
                }
                if (convertRowIndexToModel(row) == 3) {
                    return renderer2;
                }
                if (convertColumnIndexToModel(column) == 2) {
                    return renderer1;
                }
                return super.getCellRenderer(row, column);
            }
        };
        table.setRowSelectionAllowed(true);
        table.setColumnSelectionAllowed(true);
        panel.add(new JScrollPane(table));
        filterText = new JTextField();
        panel.add(filterText, BorderLayout.SOUTH);

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

    /**
     * @author Olivier Sechet
     * @version 1.0 - Jul 31, 2009
     */
    @SuppressWarnings("serial")
    static class AlignmentRenderer extends DefaultTableCellRenderer {

        /** JLabel.LEFT, JLabel.CENTER or JLabel.RIGHT */
        private int alignment;

        public AlignmentRenderer(final int alignment) {
            this.alignment = alignment;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (component instanceof JLabel) {
                ((JLabel) component).setHorizontalAlignment(alignment);
            }
            return component;
        }
    }

    /**
     * @author Olivier Sechet
     * @version 1.0 - Jul 31, 2009
     */
    @SuppressWarnings("serial")
    static class BackgroundRenderer extends DefaultTableCellRenderer {

        private Color background;

        public BackgroundRenderer(final Color background) {
            this.background = background;
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            component.setBackground(background);
            return component;
        }
    }

}