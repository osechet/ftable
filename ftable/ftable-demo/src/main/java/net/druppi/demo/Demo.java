/*
 * Demo.java
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
package net.druppi.demo;

import javax.swing.JFrame;

/**
 * The demo main class.
 *
 * @author Olivier Sechet
 * @version 1.0 - Jul 2, 2009
 */
public class Demo {

    /**
     * The main method. It creates a JFrame and adds components that shows what the library can do.
     *
     * @param args the program arguments.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test"); //$NON-NLS-1$
        frame.add(new TablePanel());
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
