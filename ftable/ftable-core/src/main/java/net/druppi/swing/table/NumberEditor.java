/*
 * NumberEditor.java
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
package net.druppi.swing.table;

import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.DefaultCellEditor;
import javax.swing.InputMap;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;

/**
 * @author Olivier Sechet
 * @version 1.0 - Apr 2, 2009
 */
@SuppressWarnings("serial")
public class NumberEditor extends DefaultCellEditor {

    /** The text formatter. */
    private static NumberFormat formatter = NumberFormat.getInstance();

    /**
     * Creates a new NumberEditor.
     *
     * @param textField
     */
    public NumberEditor() {
        super(createFormattedTextField(formatter));
        final JFormattedTextField textField = ((JFormattedTextField) getComponent());

        textField.setName("Table.editor"); //$NON-NLS-1$
        textField.setHorizontalAlignment(JTextField.RIGHT);
        // remove action listener added in DefaultCellEditor
        textField.removeActionListener(delegate);
        // replace the delegate created in DefaultCellEditor
        delegate = new EditorDelegate() {
                @Override
                public void setValue(final Object value) {
                    ((JFormattedTextField) getComponent()).setValue(value);
                }

                @Override
                public Object getCellEditorValue() {
                    JFormattedTextField textField = ((JFormattedTextField) getComponent());
                    try {
                        textField.commitEdit();
                        return textField.getValue();
                    } catch (ParseException ex) {
                        return null;
                    }
                }
        };
        textField.addActionListener(delegate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Component getTableCellEditorComponent(final JTable table, final Object value,
            final boolean isSelected, final int row, final int column) {
        Component comp = super.getTableCellEditorComponent(table, value, isSelected, row, column);
        if (comp instanceof JTextField) {
            ((JTextField) comp).selectAll();
        }
        return comp;
    }

    /**
     * Use a static method so that we can do some stuff before calling the
     * superclass.
     *
     * @param formatter the formatter to use in the textField.
     * @return a JFormattedTextField.
     */
    private static JFormattedTextField createFormattedTextField(final NumberFormat formatter) {
        final JFormattedTextField textField = new JFormattedTextField(formatter);
        /*
         * FIXME: I am sure there is a better way to do this, but I don't know
         * what it is. JTable sets up a binding for the ESCAPE key, but
         * JFormattedTextField overrides that binding with it's own. Remove the
         * JFormattedTextField binding.
         */
        InputMap map = textField.getInputMap();
        while (map != null) {
            map.remove(KeyStroke.getKeyStroke("pressed ESCAPE")); //$NON-NLS-1$
            map = map.getParent();
        }
        /*
         * Set an input verifier to prevent the cell losing focus when the value
         * is invalid
         */
        textField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(final JComponent input) {
                JFormattedTextField ftf = (JFormattedTextField) input;
                return ftf.isEditValid();
            }
        });
        /*
         * The formatted text field will not call stopCellEditing() until the
         * value is valid. So do the red border thing here.
         */
        textField.addPropertyChangeListener("editValid", //$NON-NLS-1$
                new PropertyChangeListener() {
                    public void propertyChange(final PropertyChangeEvent evt) {
                        if (evt.getNewValue() == Boolean.TRUE) {
                            ((JFormattedTextField) evt.getSource())
                                    .setBorder(new LineBorder(Color.black));
                        } else {
                            ((JFormattedTextField) evt.getSource())
                                    .setBorder(new LineBorder(Color.red));
                        }
                    }
                });
        return textField;
    }
}
