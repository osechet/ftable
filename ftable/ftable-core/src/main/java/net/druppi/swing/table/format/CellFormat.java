/*
 * CellFormat.java
 *
 * Copyright (C) 2009 Olivier Sechet
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, see <http://www.gnu.org/licenses/>.
 */
package net.druppi.swing.table.format;

import java.awt.Color;
import java.awt.Font;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import net.druppi.swing.table.format.categories.FormatCategory;
import net.druppi.util.ColorComparator;
import net.druppi.util.FontComparator;

/**
 * Describes the format of a cell or a group of cells.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 7, 2009
 */
public class CellFormat implements Comparable<CellFormat> {

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
    CellFormat(final CellFormat parent, final Alignment alignment,
            final FormatCategory formatter, final Font font, final Color foreground,
            final Color background) {
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
     * Returns a copy of this CellFormat. This method is package protected because it can
     * be called only by the FormatManager.
     *
     * @return a copy of this CellFormat.
     */
    final CellFormat copy() {
        return new CellFormat(parent, alignment, formatter, font, foreground, background);
    }

    /**
     * Merges the given CellFormat with this one. If the given CellFormat is
     * <code>null</code>, nothing is done. The merge operation keeps this CellFormat's
     * properties. That is to say that if one attribute of this CellFormat is already
     * defined, the attribute will not be changed.
     *
     * @param format the CellFormat to merge with this one.
     */
    public final void merge(final CellFormat format) {
        if (format == null) {
            return;
        }
        if (this.alignment == null && format.alignment != null) {
            this.alignment = format.alignment;
        }
        if (this.formatter == null && format.formatter != null) {
            this.formatter = format.formatter;
        }
        if (this.font == null && format.font != null) {
            this.font = format.font;
        }
        if (this.foreground == null && format.foreground != null) {
            this.foreground = format.foreground;
        }
        if (this.background == null && format.background != null) {
            this.background = format.background;
        }
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
        return new EqualsBuilder().append(this.parent, that.parent).append(
            this.alignment, that.alignment).append(this.font, that.font).append(
            this.foreground, that.foreground).append(this.background, that.background)
                .append(this.formatter, that.formatter).isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(13, 47).append(this.parent).append(this.alignment)
                .append(this.font).append(this.foreground).append(this.background)
                .append(this.formatter).toHashCode();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(CellFormat that) {
        return new CompareToBuilder().append(this.parent, that.parent).append(
            this.alignment, that.alignment).append(this.font, that.font,
            FontComparator.getInstance()).append(this.foreground, that.foreground,
            ColorComparator.getInstance()).append(this.background, that.background,
            ColorComparator.getInstance()).append(this.formatter, that.formatter)
                .toComparison();
    }
}
