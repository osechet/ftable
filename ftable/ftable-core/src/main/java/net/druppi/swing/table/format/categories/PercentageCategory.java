/*
 * PercentageCategory.java
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

import java.text.NumberFormat;

import net.druppi.swing.table.format.FormatWarpper;



/**
 * The PercentageCategory is used to format numbers as percentage.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 10, 2009
 */
class PercentageCategory extends AbstractCategory {

    /** The associated formatter. */
    private final FormatWarpper format;

    /** The configuration panel. */
    private final PercentageConfigForm panel;

    /**
     * Creates a new PercentageCategory.
     */
    public PercentageCategory() {
        super(getResourceMap().getString("FormatListModel.percentage")); //$NON-NLS-1$
        format = new FormatWarpper(NumberFormat.getPercentInstance());
        panel = new PercentageConfigForm();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConfigPanel getConfigPanel() {
        return panel;
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
