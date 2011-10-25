/*
 * TimeCategory.java
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

import java.text.DateFormat;

import net.druppi.swing.table.format.FormatWarpper;




/**
 * The TimeCategory is used to format times.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 10, 2009
 */
class TimeCategory extends AbstractCategory {

    /** The associated formatter. */
    private final FormatWarpper format;
    /** The configuration panel. */
    private final TimeConfigForm panel;

    /**
     * Creates a new TimeCategory.
     */
    public TimeCategory() {
        super(getResourceMap().getString("FormatListModel.time")); //$NON-NLS-1$
        format = new FormatWarpper(DateFormat.getTimeInstance());
        panel = new TimeConfigForm();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConfigPanel getConfigPanel() {
        return panel;
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
