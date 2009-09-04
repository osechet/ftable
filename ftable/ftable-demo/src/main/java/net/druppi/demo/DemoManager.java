/*
 * DemoManager.java
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
package net.druppi.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Olivier Sechet
 * @version 1.0 - Sep 3, 2009
 */
public class DemoManager {

    private List<Demo> demos = new ArrayList<Demo>();

    /**
     * Registers a new demo.
     *
     * @param demo the new demo.
     */
    public void register(final Demo demo) {
        demos.add(demo);
    }

    /**
     * Returns the registered demos.
     *
     * @return the registered demos.
     */
    public Demo[] getDemos() {
        return demos.toArray(new Demo[demos.size()]);
    }

    /**
     * Returns the number of registered demos.
     *
     * @return the number of registered demos.
     */
    public int getDemosCount() {
        return demos.size();
    }

    /**
     * Returns the demo at the specified index.
     *
     * @param index the index of the demo.
     * @return the demo.
     */
    public Demo getDemo(int index) {
        return demos.get(index);
    }
}
