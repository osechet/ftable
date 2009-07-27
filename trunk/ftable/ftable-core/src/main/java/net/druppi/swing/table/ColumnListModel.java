/*
 * ColumnListModel.java
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
package net.druppi.swing.table;

import javax.swing.AbstractListModel;
import javax.swing.table.TableColumnModel;

/**
 * @author Olivier Sechet
 * @version 1.0 - Apr 16, 2009
 */
public class ColumnListModel extends AbstractListModel {

    /** The serial version id. */
    private static final long serialVersionUID = -7050510331042231935L;

    /** The managed column model. */
    private final TableColumnModel model;

    /**
     * Creates a new ColumnListModel.
     *
     * @param model the managed column model.
     */
    public ColumnListModel(final TableColumnModel model) {
        this.model = model;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getElementAt(final int index) {
        return model.getColumn(index).getIdentifier();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return model.getColumnCount();
    }

}
