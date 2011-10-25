/*
 * AbstractDemo.java
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;


/**
 * @author Olivier Sechet
 * @version 1.0 - Sep 3, 2009
 */
public abstract class AbstractDemo implements Demo {

    /** The demo's name. */
    private final String name;

    /** The demo's source code. */
    private String source;

    /**
     * Creates a new AbstractDemo.
     *
     * @param name the demo's name.
     * @param source the demo's source.
     */
    public AbstractDemo(final String name, final String resourceName) {
        this.name = name;
        loadSource(resourceName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getSource() {
        return source;
    }

    /**
     *
     * @param resourceName
     */
    private void loadSource(final String resourceName) {
        URL url = getClass().getResource(resourceName + ".java"); //$NON-NLS-1$
        System.out.println(resourceName + ".java" + (url == null ? " - not found" : " - found"));
        if (url != null) {
            try {
                StringBuilder buffer = new StringBuilder();
                InputStream in = url.openStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line = reader.readLine();
                while (line != null) {
                    buffer.append(line);
                    buffer.append("\n"); //$NON-NLS-1$
                    line = reader.readLine();
                }
                source = buffer.toString();
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
