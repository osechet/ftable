/*
 * FormatManagerTest.java
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
package net.druppi.swing.table.format;

import static org.junit.Assert.*;

import java.awt.Color;

import net.druppi.swing.FTable;

import org.junit.Test;

/**
 * @author Olivier Sechet
 * @version 1.0 - Apr 21, 2010
 */
public class FormatManagerTest {

    /**
     * Test method for {@link net.druppi.swing.table.format.FormatManager#getCellFormat(net.druppi.swing.table.format.Alignment, net.druppi.swing.table.format.categories.FormatCategory, java.awt.Font, java.awt.Color, java.awt.Color)}.
     */
    @Test
    public void testGetCellFormatAlignmentFormatCategoryFontColorColor() {
        final FTable table = new FTable();
        final FormatManager formatManager = new FormatManager(table);
        final CellFormat cellFormat = formatManager.getCellFormat(null, null, null, null, null);

        final CellFormat cellFormat2 = formatManager.getCellFormat(null, null, null, null, null);
        assertSame(cellFormat, cellFormat2);
        
        final CellFormat cellFormat3 = formatManager.getCellFormat(null, null, null, null, null);
        assertSame(cellFormat, cellFormat3);
    }

    /**
     * Test method for {@link net.druppi.swing.table.format.FormatManager#getCellFormat(net.druppi.swing.table.format.CellFormat, net.druppi.swing.table.format.CellFormat)}.
     */
    @Test
    public void testGetCellFormatCellFormatCellFormat() {
        final FTable table = new FTable();
        final FormatManager formatManager = new FormatManager(table);
        final CellFormat cellFormat1 = formatManager.getCellFormat(null, null, null, Color.BLUE, null);
        final CellFormat cellFormat2 = formatManager.getCellFormat(null, null, null, null, Color.RED);

        final CellFormat cellFormat3 = formatManager.getCellFormat(cellFormat1, cellFormat2);
        assertEquals(cellFormat3.getForeground(), cellFormat1.getForeground());
        assertEquals(cellFormat3.getBackground(), cellFormat2.getBackground());
        
        // TODO: test the others possibilitiess
    }

}
