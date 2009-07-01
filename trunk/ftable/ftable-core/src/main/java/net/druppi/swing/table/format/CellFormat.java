/*
 * CellFormat.java
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

import java.awt.Color;
import java.awt.Font;

import net.druppi.swing.table.format.categories.FormatCategory;


/**
 * Describes the format of a cell or a group of cells.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 7, 2009
 */
public class CellFormat {

    /**
     * The parent CellFormat. The parent's properties is used when they are not defined in
     * the child.
     */
    private CellFormat parent;
    /** The text alignment. */
    private Alignment alignment;
    /** The text formatter. If <code>null</code>, no format is applied to the text. */
    private FormatCategory formatter;
    /** The font. If <code>null</code>, the default font is used. */
    private Font font;
    /** The text color. If <code>null</code>, the default color is used. */
    private Color foreground;
    /** The cell's background color. If <code>null</code>, the default color is used. */
    private Color background;

    /**
     * Creates a new CellFormat.
     *
     * @param parent the parent CellFormat.
     * @param alignment the text alignment.
     * @param formatter the text formatter.
     * @param font the font.
     * @param foreground the text color.
     * @param background the background color.
     */
    CellFormat(final CellFormat parent, final Alignment alignment, final FormatCategory formatter,
            final Font font, final Color foreground, final Color background) {
        this.parent = parent;
        this.alignment = alignment;
        this.formatter = formatter;
        this.font = font;
        this.foreground = foreground;
        this.background = background;
    }

    /**
     * Returns the text alignment.
     *
     * @return the text alignment.
     */
    public final Alignment getAlignment() {
        if (alignment != null) {
            return alignment;
        }
        return parent.getAlignment();
    }

    /**
     * Sets the text alignment.
     *
     * @param alignment the new alignment.
     */
    public final void setAlignment(final Alignment alignment) {
        this.alignment = alignment;
    }

    /**
     * Returns the text formatter.
     *
     * @return the text formatter.
     */
    public final FormatCategory getFormatter() {
        if (formatter != null) {
            return formatter;
        }
        return parent.formatter;
    }

    /**
     * Sets the text formatter.
     *
     * @param formatter the new formatter.
     */
    public final void setFormatter(final FormatCategory formatter) {
        this.formatter = formatter;
    }

    /**
     * Returns the font.
     *
     * @return the font.
     */
    public final Font getFont() {
        if (font != null) {
            return font;
        }
        return parent.font;
    }

    /**
     * Sets the font.
     *
     * @param font the new font.
     */
    public final void setFont(final Font font) {
        this.font = font;
    }

    /**
     * Returns the foreground color.
     *
     * @return the foreground color.
     */
    public final Color getForeground() {
        if (foreground != null) {
            return foreground;
        }
        return parent.foreground;
    }

    /**
     * Sets the foreground color.
     *
     * @param foreground the new foreground color.
     */
    public final void setForeground(final Color foreground) {
        this.foreground = foreground;
    }

    /**
     * Returns the background color.
     *
     * @return the background color.
     */
    public final Color getBackground() {
        if (background != null) {
            return background;
        }
        return parent.background;
    }

    /**
     * Sets the background color.
     *
     * @param background the new background color.
     */
    public final void setBackground(final Color background) {
        this.background = background;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (!(obj instanceof CellFormat)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        CellFormat that = (CellFormat) obj;
        return this.alignment == that.alignment
                && (this.font != null && this.font.equals(that.font))
                && (this.foreground != null && this.foreground.equals(that.foreground))
                && (this.background != null && this.background.equals(that.background))
                && (this.formatter != null && this.formatter.equals(that.formatter));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        // TODO: create a better hashcode
        return (this.alignment != null ? this.alignment.hashCode() : 0)
                | (this.font != null ? this.font.hashCode() : 0)
                | (this.foreground != null ? this.foreground.hashCode() : 0)
                | (this.background != null ? this.background.hashCode() : 0)
                | (this.formatter != null ? this.formatter.hashCode() : 0);
    }
}
