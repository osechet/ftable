/*
 * ResourceMap.java
 *
 * Copyright (C) 2009 Olivier Sechet
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.druppi.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * @author Olivier Sechet
 * @version 1.0 - Mar 27, 2009
 */
public class ResourceMap {

    /** The class LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(ResourceMap.class.getName());

    /** The path to the associated bundle file. */
    private final ResourceBundle bundle;

    /**
     * Creates a new ResourceMap.
     *
     * @param bundleName the full path to the bundle.
     */
    public ResourceMap(final String bundleName) {
        this.bundle = ResourceBundle.getBundle(bundleName);
    }

    /**
     * Returns the string associated with the given key.
     *
     * @param key the message's key.
     * @return the message.
     */
    public final String getString(final String key) {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException ex) {
            LOGGER.info("Key not found: " + key); //$NON-NLS-1$
            return "!" + key + "!"; //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * Returns the string associated with the given key and replace the parameters with
     * the given arguments.
     *
     * @param key the message's key.
     * @param args message's arguments.
     * @return the message.
     * @see java.text.MessageFormat
     */
    public final String getString(final String key, final Object ... args) {
        return MessageFormat.format(getString(key), args);
    }

    /**
     * Returns the mnemonic character associated with the given key. The bundle's message
     * must be a single character string.
     *
     * @param key the mnemonic's key.
     * @return the mnemonic character.
     */
    public final char getMnemonic(final String key) {
        return getString(key).charAt(0);
    }

}
