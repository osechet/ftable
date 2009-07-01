/*
 * Condition.java
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
package net.druppi.swing.table.format;

import net.druppi.swing.FTable;

/**
 * A Condition defines one method that returns <code>true</code> if the condition is
 * matched.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 8, 2009
 */
public abstract class Condition {

    /**
     * Returns <code>true</code> if the condition is matched. The given indices are the
     * indices in the model.
     *
     * @param table the associated table.
     * @param rowIndex the cell's row index.
     * @param columnIndex the cell's column index.
     * @return <code>true</code> if the condition is matched, <code>false</code>
     *         otherwise.
     */
    public abstract boolean matches(final FTable table, final int rowIndex, final int columnIndex);

}
