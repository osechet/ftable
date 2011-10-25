/*
 * TableColumnComparator.java
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
package net.druppi.swing.table;

import java.util.Comparator;

import javax.swing.table.TableColumn;

/**
 * A TableColumn comparator. It uses the string representation of the column's identifier
 * to compare.
 *
 * @author Olivier Sechet
 * @version 1.0 - Jul 30, 2009
 */
public class TableColumnComparator implements Comparator<TableColumn> {

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(final TableColumn o1, final TableColumn o2) {
        Object identifier1 = o1.getIdentifier();
        if (identifier1 == null) {
            identifier1 = ""; //$NON-NLS-1$
        }
        Object identifier2 = o2.getIdentifier();
        if (identifier2 == null) {
            identifier2 = ""; //$NON-NLS-1$
        }
        return identifier1.toString().compareTo(identifier2.toString());
    }

}
