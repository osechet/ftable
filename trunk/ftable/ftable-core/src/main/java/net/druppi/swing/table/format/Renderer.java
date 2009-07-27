/*
 * Renderer.java
 *
 * Copyright (C) 2009 Olivier Sechet
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, see <http://www.gnu.org/licenses/>.
 */
package net.druppi.swing.table.format;

import java.awt.Color;
import java.awt.Component;
import java.io.Serializable;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;


/**
 * This class uses a CellFormat to format a delegate cell renderer.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 8, 2009
 */
@SuppressWarnings("serial")
class Renderer implements TableCellRenderer, Serializable {

    /** The default format of the cell. */
    private CellFormat format;

    /** The renderer to use. */
    private TableCellRenderer renderer;

    /**
     * Creates a new Renderer.
     */
    public Renderer() {
        // no op
    }

    /**
     * The renderer to use.
     *
     * @param renderer a renderer.
     */
    public void setRenderer(final TableCellRenderer renderer) {
        this.renderer = renderer;
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
    public final Component getTableCellRendererComponent(final JTable table,
            final Object value, final boolean isSelected, final boolean hasFocus,
            final int row, final int column) {
        Component component = renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (!isSelected || hasFocus) {
            Color foreground = format.getForeground();
            component.setForeground(foreground);

            Color background = format.getBackground();
            component.setBackground(background);
        }

        component.setFont(format.getFont());

        if (component instanceof JComponent) {
            if (hasFocus) {
                ((JComponent) component).setBorder(new LineBorder(Color.BLACK, 1));
            } else {
                ((JComponent) component).setBorder(null);
            }
        }

        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;

            switch (format.getAlignment()) {
            case LEFT:
                label.setHorizontalAlignment(JLabel.LEFT);
                break;
            case CENTER:
                label.setHorizontalAlignment(JLabel.CENTER);
                break;
            case RIGHT:
                label.setHorizontalAlignment(JLabel.RIGHT);
                break;
            default:
                // logger.warning("Wrong alignment."); //$NON-NLS-1$
                break;
            }

            label.setText(format.getFormatter().getFormat().format(value));
        }

        return component;
    }

}
