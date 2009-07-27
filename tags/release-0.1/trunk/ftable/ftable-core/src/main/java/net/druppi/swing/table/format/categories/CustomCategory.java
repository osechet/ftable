/*
 * CustomCategory.java
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
package net.druppi.swing.table.format.categories;

import net.druppi.swing.table.format.FormatWarpper;


/**
 * The CustomCategory is used to format using a user-customized formatter.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 10, 2009
 */
class CustomCategory extends AbstractCategory {

    /** The associated formatter. */
    private final FormatWarpper format;

    /**
     * Creates a new CustomCategory.
     */
    public CustomCategory() {
        super(getResourceMap().getString("FormatListModel.custom"));  //$NON-NLS-1$
        format = new FormatWarpper(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConfigPanel getConfigPanel() {
        return null;
    }

    /**
     * Returns the associated formatter.
     *
     * @return the formatter.
     */
    public FormatWarpper getFormat() {
        return format;
    }

}
