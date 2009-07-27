/*
 * PopupConfigurator.java
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
package net.druppi.swing.table.config;

import net.druppi.swing.FTable;


/**
 * Helper class to easily add a popup menu to a JXTable.
 *
 * @author Olivier Sechet
 * @version 1.0 - Mar 24, 2009
 */
public final class PopupConfigurator {

    /**
     * This class is not instanciable.
     */
    private PopupConfigurator() {
        // no op
    }

    /**
     * Creates a popup menu for the given table.
     *
     * @param table the table to which the menu is added.
     */
    public static void createPopupMenu(final FTable table) {
        FTableConfigurator.createPopupMenu(table);
    }

}
