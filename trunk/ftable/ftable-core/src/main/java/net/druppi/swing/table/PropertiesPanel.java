/*
 * PropertiesPanel.java
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
package net.druppi.swing.table;

import javax.swing.JPanel;
import javax.swing.JTable;

/**
 * An abstract class to override in order to define a new panel for the PropertiesDialog.
 *
 * @author Olivier Sechet
 * @version 1.0 - Mar 25, 2009
 */
@SuppressWarnings("serial")
public abstract class PropertiesPanel extends JPanel {

    /**
     * Returns the title of the panel.
     *
     * @return the title of the panel.
     */
    public abstract String getTitle();

    /**
     * Apply the options of the panel.
     */
    public abstract void apply();

    /**
     * Initializes the panel with the given table.
     *
     * @param table the associated table.
     */
    public abstract void init(final JTable table);

}
