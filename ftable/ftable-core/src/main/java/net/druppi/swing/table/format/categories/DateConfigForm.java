/*
 * DateConfigForm.java
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
package net.druppi.swing.table.format.categories;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.druppi.util.LocaleRenderer;
import net.druppi.util.ResourceManager;



/**
 * Configuration panel of the DateCategory.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 13, 2009
 */
@SuppressWarnings("serial")
class DateConfigForm extends ConfigPanel {

    /** The managed Format. */
    private SimpleDateFormat format;

    /** The sample date. */
    private Date sampleDate;

    /**
     * Creates new form DateConfigForm.
     */
    public DateConfigForm() {
        initComponents();

        sampleDate = new Date();

        // Build the locale list
        List<LocaleRenderer> list = new ArrayList<LocaleRenderer>();
        Locale[] locales = DateFormat.getAvailableLocales();
        for (Locale locale : locales) {
            list.add(new LocaleRenderer(locale));
        }
        Collections.sort(list);
        localeComboBox.setModel(new DefaultComboBoxModel(list.toArray(new LocaleRenderer[list.size()])));

        localeComboBox.setSelectedItem(new LocaleRenderer(Locale.getDefault()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFormat(final Format format) {
        this.format = (SimpleDateFormat) format;

        updateView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSampleValue(final Object value) {
        if (value instanceof Date) {
            this.sampleDate = (Date) value;
        } else if (value instanceof Integer) {
            this.sampleDate = new Date((Integer) value);
        } else if (value instanceof Long) {
            this.sampleDate = new Date((Long) value);
        } else {
            this.sampleDate = new Date();
        }

        updateView();
    }

    /**
     * Updates the view.
     */
    private void updateView() {
        DefaultListModel model = (DefaultListModel) typeList.getModel();
        model.clear();

        LocaleRenderer renderer = (LocaleRenderer) localeComboBox.getSelectedItem();
        fillPatternList(model, renderer.getLocale());

        // Select the current pattern
        if (format != null) {
            boolean found = false;
            int size = model.getSize();
            for (int i = 0; i < size && !found; i++) {
                DateRenderer dateRenderer = (DateRenderer) model.getElementAt(i);
                if (dateRenderer.getFormat().toPattern().equals(format.toPattern())) {
                    typeList.setSelectedIndex(i);
                    found = true;
                }
            }
            if (!found) {
                typeList.setSelectedIndex(0);
            }
        }
    }

    /**
     * Fills the pattern list.
     *
     * @param model the list model to update.
     * @param locale the selected locale.
     */
    protected void fillPatternList(final DefaultListModel model, final Locale locale) {
        model.addElement(new DateRenderer((SimpleDateFormat) SimpleDateFormat.getDateInstance(DateFormat.SHORT, locale)));
        model.addElement(new DateRenderer((SimpleDateFormat) SimpleDateFormat.getDateInstance(DateFormat.MEDIUM, locale)));
        model.addElement(new DateRenderer((SimpleDateFormat) SimpleDateFormat.getDateInstance(DateFormat.LONG, locale)));
        model.addElement(new DateRenderer((SimpleDateFormat) SimpleDateFormat.getDateInstance(DateFormat.FULL, locale)));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        typeLabel = new JLabel();
        typeScrollPane = new JScrollPane();
        typeList = new JList();
        localeLabel = new JLabel();
        localeComboBox = new JComboBox();

        setName("Form"); // NOI18N

        typeLabel.setText(ResourceManager.getResourceMap(DateConfigForm.class).getString("DateConfigForm.typeLabel.text")); // NOI18N
        typeLabel.setName("typeLabel"); // NOI18N

        typeScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        typeScrollPane.setName("typeScrollPane"); // NOI18N

        typeList.setModel(new DefaultListModel());
        typeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        typeList.setName("typeList"); // NOI18N
        typeList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                typeListValueChanged(evt);
            }
        });
        typeScrollPane.setViewportView(typeList);

        localeLabel.setText(ResourceManager.getResourceMap(DateConfigForm.class).getString("DateConfigForm.localeLabel.text")); // NOI18N
        localeLabel.setName("localeLabel"); // NOI18N

        localeComboBox.setName("localeComboBox"); // NOI18N
        localeComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                localeComboBoxItemStateChanged(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                    .addComponent(typeScrollPane, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                    .addComponent(typeLabel)
                    .addComponent(localeLabel)
                    .addComponent(localeComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(typeLabel)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(typeScrollPane, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(localeLabel)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(localeComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Called when the selection of the locale comboBox changes.
     *
     * @param evt an event.
     */
    private void localeComboBoxItemStateChanged(ItemEvent evt) {//GEN-FIRST:event_localeComboBoxItemStateChanged
        int index = typeList.getSelectedIndex();
        updateView();
        typeList.setSelectedIndex(index);
    }//GEN-LAST:event_localeComboBoxItemStateChanged

    /**
     * Called when the selection of the type list changes.
     *
     * @param evt an event.
     */
    private void typeListValueChanged(ListSelectionEvent evt) {//GEN-FIRST:event_typeListValueChanged
        if (!evt.getValueIsAdjusting()) {
            DateRenderer renderer = (DateRenderer) typeList.getSelectedValue();
            if (renderer != null) {
                // Change the locale
                LocaleRenderer localeRenderer = (LocaleRenderer) localeComboBox.getSelectedItem();
                format.setDateFormatSymbols(new DateFormatSymbols(localeRenderer.getLocale()));

                // Change the pattern
                format.applyPattern(renderer.getFormat().toPattern());

                firePropertyChange(MODIFIED, false, true);
            }
        }
    }//GEN-LAST:event_typeListValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JComboBox localeComboBox;
    private JLabel localeLabel;
    private JLabel typeLabel;
    private JList typeList;
    private JScrollPane typeScrollPane;
    // End of variables declaration//GEN-END:variables

    /**
     * A renderer to display a DateFormat in a JComboBox.
     *
     * @author Olivier Sechet
     * @version 1.0 - Apr 13, 2009
     */
    protected final class DateRenderer {

        /** The format to display. */
        private final SimpleDateFormat format;

        /**
         * Creates a new DateRenderer.
         *
         * @param format the format to display.
         */
        public DateRenderer(final SimpleDateFormat format) {
            this.format = format;
        }

        /**
         * Returns the associated format.
         *
         * @return the associated format.
         */
        public SimpleDateFormat getFormat() {
            return format;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return format.format(sampleDate);
        }
    }
}
