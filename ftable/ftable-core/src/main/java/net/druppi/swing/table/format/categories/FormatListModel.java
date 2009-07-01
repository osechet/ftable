/*
 * FormatListModel.java
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

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;


/**
 * @author Olivier Sechet
 * @version 1.0 - Apr 10, 2009
 */
@SuppressWarnings("serial")
public class FormatListModel extends AbstractListModel {

    /** The available formatters. */
    private final List<FormatCategory> categories = new ArrayList<FormatCategory>();

    /**
     * Creates a new FormatListModel.
     */
    public FormatListModel() {
        categories.add(new GeneralCategory());
        categories.add(new NumberCategory());
        categories.add(new CurrencyCategory());
        categories.add(new DateCategory());
        categories.add(new TimeCategory());
        categories.add(new PercentageCategory());
//        categories.add(new FractionCategory());
        categories.add(new ScientificCategory());
        categories.add(new TextCategory());
//        categories.add(new SpecialCategory());
        categories.add(new CustomCategory());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getElementAt(final int index) {
        return categories.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return categories.size();
    }

}
