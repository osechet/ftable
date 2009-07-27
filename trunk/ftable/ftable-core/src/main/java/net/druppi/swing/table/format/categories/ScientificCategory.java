/*
 * ScientificCategory.java
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

import java.text.DecimalFormat;

import net.druppi.swing.table.format.FormatWarpper;




/**
 * The ScientificCategory is used to format numbers as scientific numbers.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 10, 2009
 */
class ScientificCategory extends AbstractCategory {

    /** The default scientific pattern. */
    private static final String DEFAULT_SCIENTIFIC = "0.00E00"; //$NON-NLS-1$
    /** The associated formatter. */
    private final FormatWarpper format;
    /** The configuration panel. */
    private final ScientificConfigForm panel;

    /**
     * Creates a new ScientificCategory.
     */
    public ScientificCategory() {
        super(getResourceMap().getString("FormatListModel.scientific")); //$NON-NLS-1$
        format = new FormatWarpper(new DecimalFormat(DEFAULT_SCIENTIFIC));
        panel = new ScientificConfigForm();
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
