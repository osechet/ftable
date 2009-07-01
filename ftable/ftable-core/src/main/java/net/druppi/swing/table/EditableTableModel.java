/*
 * EditableTableModel.java
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

import javax.swing.table.TableModel;

/**
 * @author Olivier Sechet
 * @version 1.0 - Mar 25, 2009
 */
public interface EditableTableModel extends TableModel {

    /**
     * Insert <code>count</code> empty rows of data at the specified index.
     *
     * @param index the index of the row where the rows will be inserted.
     * @param count the number of rows to insert.
     */
    void insertRows(int index, int count);

    /**
     * Deletes <code>count</code> rows from index.
     *
     * @param index the index of the first row to delete.
     * @param count the number of rows to delete.
     */
    void deleteRows(int index, int count);
}
