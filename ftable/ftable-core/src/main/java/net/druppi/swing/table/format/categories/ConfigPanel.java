/*
 * ConfigPanel.java
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

import java.text.Format;

import javax.swing.JPanel;

/**
 * @author Olivier Sechet
 * @version 1.0 - Apr 13, 2009
 */
@SuppressWarnings("serial")
public abstract class ConfigPanel extends JPanel {

    /** The modified property is used when the panel changes. */
    public static final String MODIFIED = "modified"; //$NON-NLS-1$

    /**
     * Sets the format that is configured by this panel.
     *
     * @param format the new format.
     */
    public abstract void setFormat(Format format);

    /**
     * Sets the sample value.
     *
     * @param value the sample value.
     */
    public void setSampleValue(final Object value) {
        // no op
    }
}
