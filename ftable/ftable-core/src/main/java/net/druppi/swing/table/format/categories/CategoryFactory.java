/*
 * CategoryFactory.java
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

/**
 * This class exists to easily create a default FormatCategory (all the FormatCategory
 * classes are package protected).
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 14, 2009
 */
public final class CategoryFactory {

    /**
     * Creates a new CategoryFactory.
     */
    private CategoryFactory() {
        // no op
    }

    /**
     * Creates a default FormatCategory.
     *
     * @return the newly created FormatCategory.
     */
    public static FormatCategory createGeneralCategory() {
        return new GeneralCategory();
    }
}
