/*
 * AbstractCategory.java
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
package net.druppi.swing.table.format.categories;

import net.druppi.util.ResourceManager;
import net.druppi.util.ResourceMap;


/**
 * An abstract FormatCategory that defines most of the methods.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 10, 2009
 */
abstract class AbstractCategory implements FormatCategory {

    /** The name of the category. */
    private final String name;

    /**
     * Creates a new FormatCategory.
     *
     * @param name the category's name.
     */
    protected AbstractCategory(final String name) {
        this.name = name;
    }

    /**
     * Returns the name of the category.
     *
     * @return the name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the class resource map. All the categories share the same resource map.
     *
     * @return the class resource map.
     */
    protected static ResourceMap getResourceMap() {
        return ResourceManager.getResourceMap(AbstractCategory.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof AbstractCategory)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        AbstractCategory that = (AbstractCategory) obj;
        return this.name.equals(that.name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
