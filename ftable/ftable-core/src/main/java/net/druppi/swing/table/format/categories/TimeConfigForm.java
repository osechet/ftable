/*
 * TimeConfigForm.java
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
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.swing.DefaultListModel;


/**
 * Configuration panel of the TimeCategory. The TimeConfigForm and the DateConfigForm
 * are very similar. Only the content of the "type" list changes.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 13, 2009
 */
@SuppressWarnings("serial")
class TimeConfigForm extends DateConfigForm {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void fillPatternList(final DefaultListModel model, final Locale locale) {
        model.addElement(new DateRenderer((SimpleDateFormat) SimpleDateFormat.getTimeInstance(DateFormat.SHORT, locale)));
        model.addElement(new DateRenderer((SimpleDateFormat) SimpleDateFormat.getTimeInstance(DateFormat.MEDIUM, locale)));
        model.addElement(new DateRenderer((SimpleDateFormat) SimpleDateFormat.getTimeInstance(DateFormat.LONG, locale)));
        model.addElement(new DateRenderer((SimpleDateFormat) SimpleDateFormat.getTimeInstance(DateFormat.FULL, locale)));
    }

}
