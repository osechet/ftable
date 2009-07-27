/*
 * FontComparator.java
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
package net.druppi.util;

import java.awt.Font;
import java.util.Comparator;

import org.apache.commons.lang.builder.CompareToBuilder;


/**
 * A singleton class that can be used to compare two <code>java.awt.Font</code>.
 *
 * @author Olivier Sechet
 * @version 1.0 - Jul 1, 2009
 */
public class FontComparator implements Comparator<Font> {

    /**
     * Creates a new FontComparator.
     */
    private FontComparator() {
        // no op
    }

    /**
     * Returns the FontComparator's instance.
     *
     * @return the singleton instance.
     */
    public static FontComparator getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * The singleton holder.
     *
     * @author Olivier Sechet
     * @version 1.0 - Jul 1, 2009
     */
    private static class SingletonHolder {
        private final static FontComparator INSTANCE = new FontComparator();
    }

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(Font o1, Font o2) {
        return new CompareToBuilder()
            .append(o1.getName(), o2.getName())
            .append(o1.getStyle(), o2.getStyle())
            .append(o1.getSize(), o2.getSize()).toComparison();
    }

}
