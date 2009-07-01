/*
 * FormatWarpper.java
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

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.logging.Logger;

/**
 * A FormatWarpper is a wrapper Format that uses a delegate Format to parse and format. The
 * advantage is that the result of the format/parse operations can change without changing
 * the instance of the Format. Moreover if the delegate Format cannot perform the
 * conversion, the FormatWarpper returns the input data.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 10, 2009
 */
@SuppressWarnings("serial")
public class FormatWarpper extends Format {

    /** The class logger. */
    private static Logger logger = Logger.getLogger(FormatWarpper.class.getName());

    /** The delegate formatter. */
    private Format delegate;

    /**
     * Creates a new FormatWarpper.
     *
     * @param delegate the delegate Format.
     */
    public FormatWarpper(final Format delegate) {
        this.delegate = delegate;
    }

    /**
     * Returns the delegate format. The delegate format is used to format and parse.
     *
     * @return the delegate format.
     */
    public Format getFormat() {
        return delegate;
    }

    /**
     * Sets the delegate format. The delegate format is used to format and parse.
     *
     * @param delegate the delegate format.
     */
    public void setFormat(final Format delegate) {
        this.delegate = delegate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StringBuffer format(final Object obj, final StringBuffer toAppendTo, final FieldPosition pos) {
        if (delegate != null) {
            try {
                return delegate.format(obj, toAppendTo, pos);
            } catch (IllegalArgumentException ex) {
                logger.warning("The format is not applicable to " + obj.getClass()); //$NON-NLS-1$
            }
        }
        // No formatting
        toAppendTo.append(obj == null ? "" : obj.toString()); //$NON-NLS-1$
        return toAppendTo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object parseObject(final String source, final ParsePosition pos) {
        if (delegate != null) {
            return delegate.parseObject(source, pos);
        }
        return source;
    }

}
