/*
 * GeneralCategory.java
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

import net.druppi.swing.table.format.FormatWarpper;


/**
 * The GeneralCategory is used when the value must not be formatted.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 10, 2009
 */
class GeneralCategory extends AbstractCategory {

    /** The associated formatter. */
    private final FormatWarpper format;

    /**
     * Creates a new GeneralCategory.
     */
    public GeneralCategory() {
        super(getResourceMap().getString("FormatListModel.general")); //$NON-NLS-1$
        format = new FormatWarpper(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConfigPanel getConfigPanel() {
        return null;
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
