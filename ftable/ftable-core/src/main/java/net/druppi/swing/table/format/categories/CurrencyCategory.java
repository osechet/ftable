/*
 * CurrencyCategory.java
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
 * The CurrencyCategory is used to format currencies.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 10, 2009
 */
class CurrencyCategory extends AbstractCategory {

    /** The configuration panel. */
    private final ConfigPanel panel;

    /** The associated formatter. */
    private final FormatWarpper format;

    /**
     * Creates a new CurrencyCategory.
     */
    public CurrencyCategory() {
        super(getResourceMap().getString("FormatListModel.currency")); //$NON-NLS-1$
        format = new FormatWarpper(NumberFormat.getCurrencyInstance());
        panel = new CurrencyConfigForm();
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
