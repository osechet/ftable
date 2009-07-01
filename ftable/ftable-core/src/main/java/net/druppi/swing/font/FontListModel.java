/*
 * FontListModel.java
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
package net.druppi.swing.font;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.Arrays;

import javax.swing.AbstractListModel;

/**
 * @author Olivier Sechet
 * @version 1.0 - Apr 9, 2009
 */
@SuppressWarnings("serial")
public class FontListModel extends AbstractListModel {

    /** The default font size. */
    private static final int DEFAULT_SIZE = 12;

    /** The list of fonts. */
    private final String[] fonts;

    /**
     * Creates a new FontListModel.
     */
    public FontListModel() {
        fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        Arrays.sort(fonts);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getElementAt(final int index) {
        return fonts[index];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return fonts.length;
    }

    /**
     * Returns the font at the specified index.
     *
     * @param index the index.
     * @return the font.
     */
    public Font getFont(final int index) {
        return new Font(fonts[index], Font.PLAIN, DEFAULT_SIZE);
    }
}
