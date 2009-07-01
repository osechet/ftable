/*
 * DateCategory.java
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

import java.text.DateFormat;

import net.druppi.swing.table.format.FormatWarpper;




/**
 * The DateCategory is used to format dates.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 10, 2009
 */
public class DateCategory extends AbstractCategory {

    /** The associated formatter. */
    private final FormatWarpper format;

    /** the configuration panel. */
    private final DateConfigForm panel;

    /**
     * Creates a new DateCategory.
     */
    public DateCategory() {
        super(getResourceMap().getString("FormatListModel.date")); //$NON-NLS-1$
        format = new FormatWarpper(DateFormat.getDateInstance());
        panel = new DateConfigForm();
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
