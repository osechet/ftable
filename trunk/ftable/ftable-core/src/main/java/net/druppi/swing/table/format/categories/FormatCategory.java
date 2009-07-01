/*
 * FormatCategory.java
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
 * A FormatCategory describes a formatter available in the CellsFormatPanel. It also
 * provides a panel to configure that formatter.<br>
 * The FormatCategory does not use a simple Format instance but a FormatWarpper instance,
 * in order to ensure that the conversion always works. Indeed a FormatWarpper can take a
 * delegate Format that does the conversion. If the delegate cannot do the conversion, the
 * FormatWarpper returns the input type.
 *
 * @see FormatWarpper
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 10, 2009
 */
public interface FormatCategory {

    /**
     * Returns the name of the category.
     *
     * @return the name of the category.
     */
    String getName();

    /**
     * Returns the associated formatter.
     *
     * @return the formatter.
     */
    FormatWarpper getFormat();

    /**
     * Returns the configuration panel. The configuration panel is used to configure the
     * category. If the method returns <code>null</code>, no panel is used. It means the
     * category cannot be configured.
     *
     * @return the options panel or <code>null</code>.
     */
    ConfigPanel getConfigPanel();

}
