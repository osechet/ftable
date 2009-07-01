/*
 * CellFormatTest.java
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

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Font;

import org.junit.Test;

/**
 * @author Olivier Sechet
 * @version 1.0 - Jul 1, 2009
 */
public class CellFormatTest {

    /**
     * Test method for {@link net.druppi.swing.table.format.CellFormat#hashCode()}.
     */
    @Test
    public void testHashCode() {
        CellFormat f1 = new CellFormat(null, Alignment.LEFT, null, new Font("Arial", Font.PLAIN, 10), Color.BLACK, Color.WHITE);
        CellFormat f2 = new CellFormat(null, Alignment.LEFT, null, new Font("Arial", Font.PLAIN, 10), Color.BLACK, Color.WHITE);
        CellFormat f3 = new CellFormat(null, Alignment.CENTER, null, new Font("Arial", Font.PLAIN, 10), Color.BLACK, Color.WHITE);
        CellFormat f4 = new CellFormat(null, Alignment.LEFT, null, new Font("Lucida", Font.PLAIN, 10), Color.BLACK, Color.WHITE);
        CellFormat f5 = new CellFormat(null, Alignment.LEFT, null, new Font("Arial", Font.PLAIN, 10), Color.BLUE, Color.WHITE);
        CellFormat f6 = new CellFormat(null, Alignment.LEFT, null, new Font("Arial", Font.PLAIN, 10), Color.BLACK, Color.BLUE);

        assertEquals(f1.hashCode(), f1.hashCode());
        assertEquals(f1.hashCode(), f2.hashCode());
        assertTrue(f1.hashCode() != f3.hashCode());
        assertTrue(f1.hashCode() != f4.hashCode());
        assertTrue(f1.hashCode() != f5.hashCode());
        assertTrue(f1.hashCode() != f6.hashCode());
    }

    /**
     * Test method for {@link net.druppi.swing.table.format.CellFormat#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject() {
        CellFormat f1 = new CellFormat(null, Alignment.LEFT, null, new Font("Arial", Font.PLAIN, 10), Color.BLACK, Color.WHITE);
        CellFormat f2 = new CellFormat(null, Alignment.LEFT, null, new Font("Arial", Font.PLAIN, 10), Color.BLACK, Color.WHITE);
        CellFormat f3 = new CellFormat(null, Alignment.CENTER, null, new Font("Arial", Font.PLAIN, 10), Color.BLACK, Color.WHITE);
        CellFormat f4 = new CellFormat(null, Alignment.LEFT, null, new Font("Lucida", Font.PLAIN, 10), Color.BLACK, Color.WHITE);
        CellFormat f5 = new CellFormat(null, Alignment.LEFT, null, new Font("Arial", Font.PLAIN, 10), Color.BLUE, Color.WHITE);
        CellFormat f6 = new CellFormat(null, Alignment.LEFT, null, new Font("Arial", Font.PLAIN, 10), Color.BLACK, Color.BLUE);

        assertEquals(f1, f1);
        assertEquals(f1, f2);
        assertFalse(f1.equals(f3));
        assertFalse(f1.equals(f4));
        assertFalse(f1.equals(f5));
        assertFalse(f1.equals(f6));


    }

}
