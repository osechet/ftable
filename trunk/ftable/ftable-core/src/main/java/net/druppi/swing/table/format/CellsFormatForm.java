/*
 * CellsFormatForm.java
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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.druppi.swing.FontChooser;
import net.druppi.swing.FTable;
import net.druppi.swing.table.format.categories.ConfigPanel;
import net.druppi.swing.table.format.categories.FormatCategory;
import net.druppi.swing.table.format.categories.FormatListModel;
import net.druppi.util.ResourceManager;


/**
 * The CellsFormatForm displays several tabs that can be used to configure the format of
 * the cells of a table. The available configuration are the format, the font, the
 * alignment and the background.
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 9, 2009
 */
@SuppressWarnings("serial")
public class CellsFormatForm extends javax.swing.JPanel {

    /** The class logger. */
    private static Logger logger = Logger.getLogger(CellsFormatForm.class.getName());

    /** The sample value used in the preview areas. */
    private Object sampleValue;

    /** The modification listener shared by all the configuration panels. */
    private final ModificationHandler modificationHandler;

    /**
     * Creates new form CellsFormatForm.
     */
    public CellsFormatForm() {
        initComponents();

        // Other initializations
        modificationHandler = new ModificationHandler();

        // By default, the first category is selected.
        categoryList.setSelectedIndex(0);
    }

    /**
     * Initializes the panel using the given table.
     *
     * @param table the table to use to initialize the panel.
     */
    public void initFrom(final FTable table) {
        int row = table.getSelectedRow();
        int column = table.getSelectedColumn();
        if (row >= 0 && column >= 0) {
            this.sampleValue = table.getValueAt(row, column);
            CellFormat format = table.getFormatManager().getFormat(row, column);
            categoryList.setSelectedValue(format.getFormatter(), true);
            alignmentComboBox.setSelectedItem(format.getAlignment());
            fontChooser.setSelectedFont(format.getFont());
            fontChooser.setTextColor(format.getForeground());
            backgroundChooser.setColor(format.getBackground());
        }

        updateTextPreview();
    }

    /**
     *
     */
    public FormatCategory getSelectedFormat() {
        FormatCategory category = (FormatCategory) categoryList.getSelectedValue();
        return category;
    }

    /**
     *
     */
    public net.druppi.swing.table.format.Alignment getSelectedAlignment() {
        int index = alignmentComboBox.getSelectedIndex();
        return net.druppi.swing.table.format.Alignment.values()[index];
    }

    /**
     *
     */
    public Font getSelectedFont() {
        return fontChooser.getSelectedFont();
    }

    /**
     *
     */
    public Color getSelectedForeground() {
        return fontChooser.getTextColor();
    }

    /**
     *
     */
    public Color getSelectedBackground() {
        return backgroundChooser.getColor();
    }

    /**
     * Update the preview text.
     */
    private void updateTextPreview() {
        if (sampleValue != null) {
            FormatCategory category = (FormatCategory) categoryList.getSelectedValue();
            FormatWarpper format = category.getFormat();
            try {
                String text = format.format(sampleValue);
                sampleLabel.setText(text);
            } catch (IllegalArgumentException ex) {
                // Should never happen since the FormatWarpper class manage this case
                logger.warning("The " + category.getName() + " format is not applicable to " + sampleValue.getClass()); //$NON-NLS-1$ //$NON-NLS-2$
                sampleLabel.setText(sampleValue.toString());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING:
     * Do NOT modify this code. The content of this method is always regenerated by the
     * Form Editor.
     */
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        noConfigPanel = new JPanel();
        noConfigTextArea = new JTextArea();
        tabbedPane = new JTabbedPane();
        numberPanel = new JPanel();
        categoryLabel = new JLabel();
        categoryScrollPane = new JScrollPane();
        categoryList = new JList();
        samplePanel = new JPanel();
        sampleLabel = new JLabel();
        formatOptionsPanel = new JPanel();
        alignmentPanel = new JPanel();
        textAlignmentLabel = new JLabel();
        horzAlignmentLabel = new JLabel();
        alignmentComboBox = new JComboBox();
        fontPanel = new JPanel();
        fontChooser = new FontChooser();
        borderPanel = new JPanel();
        notAvailableLabel = new JLabel();
        backgroundPanel = new JPanel();
        backgroundChooser = new JColorChooser();

        noConfigPanel.setName("noConfigPanel"); // NOI18N

        noConfigTextArea.setEditable(false);
        noConfigTextArea.setFont(categoryLabel.getFont());
        noConfigTextArea.setLineWrap(true);
        noConfigTextArea.setText(ResourceManager.getResourceMap(getClass()).getString("CellsFormatForm.noConfigTextArea.text")); // NOI18N
        noConfigTextArea.setWrapStyleWord(true);
        noConfigTextArea.setName("noConfigTextArea"); // NOI18N
        noConfigTextArea.setOpaque(false);

        GroupLayout noConfigPanelLayout = new GroupLayout(noConfigPanel);
        noConfigPanel.setLayout(noConfigPanelLayout);
        noConfigPanelLayout.setHorizontalGroup(
            noConfigPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(noConfigPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noConfigTextArea, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        noConfigPanelLayout.setVerticalGroup(
            noConfigPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(noConfigPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noConfigTextArea)
                .addContainerGap())
        );

        setName("Form"); // NOI18N
        setLayout(new BorderLayout());

        tabbedPane.setName("tabbedPane"); // NOI18N

        numberPanel.setName("numberPanel"); // NOI18N

        categoryLabel.setText(ResourceManager.getResourceMap(getClass()).getString("CellsFormatForm.categoryLabel.text")); // NOI18N
        categoryLabel.setName("categoryLabel"); // NOI18N

        categoryScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        categoryScrollPane.setName("categoryScrollPane"); // NOI18N

        categoryList.setModel(new FormatListModel());
        categoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        categoryList.setName("categoryList"); // NOI18N
        categoryList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                categoryListValueChanged(evt);
            }
        });
        categoryScrollPane.setViewportView(categoryList);

        samplePanel.setBorder(BorderFactory.createTitledBorder(ResourceManager.getResourceMap(getClass()).getString("CellsFormatForm.numberPanel.samplePanel.title"))); // NOI18N
        samplePanel.setName("samplePanel"); // NOI18N

        sampleLabel.setText(ResourceManager.getResourceMap(CellsFormatForm.class).getString("CellsFormatForm.sampleLabel.text")); // NOI18N
        sampleLabel.setName("sampleLabel"); // NOI18N

        GroupLayout samplePanelLayout = new GroupLayout(samplePanel);
        samplePanel.setLayout(samplePanelLayout);
        samplePanelLayout.setHorizontalGroup(
            samplePanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(Alignment.TRAILING, samplePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sampleLabel, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addContainerGap())
        );
        samplePanelLayout.setVerticalGroup(
            samplePanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(samplePanelLayout.createSequentialGroup()
                .addComponent(sampleLabel)
                .addGap(0, 0, 0))
        );

        formatOptionsPanel.setName("formatOptionsPanel"); // NOI18N
        formatOptionsPanel.setLayout(new BorderLayout());

        GroupLayout numberPanelLayout = new GroupLayout(numberPanel);
        numberPanel.setLayout(numberPanelLayout);

        numberPanelLayout.setHorizontalGroup(
            numberPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(numberPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(numberPanelLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(categoryLabel)
                    .addComponent(categoryScrollPane, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(numberPanelLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(formatOptionsPanel, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                    .addComponent(samplePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        numberPanelLayout.setVerticalGroup(
            numberPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(numberPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(numberPanelLayout.createParallelGroup(Alignment.LEADING, false)
                    .addGroup(numberPanelLayout.createSequentialGroup()
                        .addComponent(samplePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(formatOptionsPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(numberPanelLayout.createSequentialGroup()
                        .addComponent(categoryLabel)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(categoryScrollPane, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(106, Short.MAX_VALUE))
        );

        tabbedPane.addTab(ResourceManager.getResourceMap(getClass()).getString("CellsFormatForm.numberPanel.TabConstraints.tabTitle"), numberPanel); // NOI18N
        alignmentPanel.setName("alignmentPanel"); // NOI18N

        textAlignmentLabel.setText(ResourceManager.getResourceMap(getClass()).getString("CellsFormatForm.textAlignmentLabel.text")); // NOI18N
        textAlignmentLabel.setName("textAlignmentLabel"); // NOI18N

        horzAlignmentLabel.setText(ResourceManager.getResourceMap(getClass()).getString("CellsFormatForm.horzAlignmentLabel.text")); // NOI18N
        horzAlignmentLabel.setName("horzAlignmentLabel"); // NOI18N

        alignmentComboBox.setModel(new DefaultComboBoxModel(net.druppi.swing.table.format.Alignment.values()));
        alignmentComboBox.setName("alignmentComboBox"); // NOI18N

        GroupLayout alignmentPanelLayout = new GroupLayout(alignmentPanel);
        alignmentPanel.setLayout(alignmentPanelLayout);

        alignmentPanelLayout.setHorizontalGroup(
            alignmentPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(alignmentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(alignmentPanelLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(textAlignmentLabel)
                    .addGroup(alignmentPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(alignmentPanelLayout.createParallelGroup(Alignment.LEADING)
                            .addComponent(alignmentComboBox, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
                            .addComponent(horzAlignmentLabel))))
                .addContainerGap(313, Short.MAX_VALUE))
        );
        alignmentPanelLayout.setVerticalGroup(
            alignmentPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(alignmentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textAlignmentLabel)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(horzAlignmentLabel)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(alignmentComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(260, Short.MAX_VALUE))
        );

        tabbedPane.addTab(ResourceManager.getResourceMap(getClass()).getString("CellsFormatForm.alignmentPanel.TabConstraints.tabTitle"), alignmentPanel); // NOI18N
        fontPanel.setName("fontPanel"); // NOI18N
        fontPanel.setLayout(new BorderLayout());

        fontChooser.setName("fontChooser"); // NOI18N

        fontPanel.add(fontChooser, BorderLayout.CENTER);

        tabbedPane.addTab(ResourceManager.getResourceMap(getClass()).getString("CellsFormatForm.fontPanel.TabConstraints.tabTitle"), fontPanel); // NOI18N
        borderPanel.setName("borderPanel"); // NOI18N

        notAvailableLabel.setText(ResourceManager.getResourceMap(getClass()).getString("CellsFormatForm.notAvailableLabel.text")); // NOI18N
        notAvailableLabel.setName("notAvailableLabel"); // NOI18N

        GroupLayout borderPanelLayout = new GroupLayout(borderPanel);
        borderPanel.setLayout(borderPanelLayout);

        borderPanelLayout.setHorizontalGroup(
            borderPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(borderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(notAvailableLabel)
                .addContainerGap(358, Short.MAX_VALUE))
        );
        borderPanelLayout.setVerticalGroup(
            borderPanelLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(borderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(notAvailableLabel)
                .addContainerGap(308, Short.MAX_VALUE))
        );

        tabbedPane.addTab(ResourceManager.getResourceMap(getClass()).getString("CellsFormatForm.borderPanel.TabConstraints.tabTitle"), borderPanel); // NOI18N
        backgroundPanel.setName("backgroundPanel"); // NOI18N
        backgroundPanel.setLayout(new BorderLayout());

        backgroundChooser.setName("backgroundChooser"); // NOI18N

        backgroundPanel.add(backgroundChooser, BorderLayout.CENTER);

        tabbedPane.addTab(ResourceManager.getResourceMap(getClass()).getString("CellsFormatForm.backgroundPanel.TabConstraints.tabTitle"), backgroundPanel); // NOI18N
        add(tabbedPane, BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Called when the selection of the category list changes.
     *
     * @param evt an event.
     */
    private void categoryListValueChanged(ListSelectionEvent evt) {// GEN-FIRST:event_categoryListValueChanged
        if (!evt.getValueIsAdjusting()) {
            FormatCategory category = (FormatCategory) categoryList.getSelectedValue();

            if (formatOptionsPanel.getComponentCount() > 0) {
                ConfigPanel oldPanel = (ConfigPanel) formatOptionsPanel.getComponent(0);
                if (oldPanel != null) {
                    formatOptionsPanel.remove(oldPanel);
                    oldPanel.removePropertyChangeListener(modificationHandler);
                }
            }

            ConfigPanel panel = category.getConfigPanel();
            if (panel != null) {
                panel.setFormat(category.getFormat().getFormat());
                panel.setSampleValue(sampleValue);
                formatOptionsPanel.add(panel);
                formatOptionsPanel.revalidate();
                formatOptionsPanel.repaint();

                panel.addPropertyChangeListener(ConfigPanel.MODIFIED, modificationHandler);
            } else {
                noConfigTextArea.setText(ResourceManager.getResourceMap(getClass()).getString(
                        "CellsFormatForm.noConfigTextArea.text", category.getName())); //$NON-NLS-1$
                // FIXME: When the information panel is displayed, the dialog height is
                // anormaly modified.
                // formatOptionsPanel.add(noConfigPanel);
                formatOptionsPanel.revalidate();
                formatOptionsPanel.repaint();
            }
            updateTextPreview();
        }
    }// GEN-LAST:event_categoryListValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JComboBox alignmentComboBox;
    private JPanel alignmentPanel;
    private JColorChooser backgroundChooser;
    private JPanel backgroundPanel;
    private JPanel borderPanel;
    private JLabel categoryLabel;
    private JList categoryList;
    private JScrollPane categoryScrollPane;
    private FontChooser fontChooser;
    private JPanel fontPanel;
    private JPanel formatOptionsPanel;
    private JLabel horzAlignmentLabel;
    private JPanel noConfigPanel;
    private JTextArea noConfigTextArea;
    private JLabel notAvailableLabel;
    private JPanel numberPanel;
    private JLabel sampleLabel;
    private JPanel samplePanel;
    private JTabbedPane tabbedPane;
    private JLabel textAlignmentLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * This class is used to listen for modifications in the configuration panels.
     *
     * @author Olivier Sechet
     * @version 1.0 - Apr 13, 2009
     */
    private final class ModificationHandler implements PropertyChangeListener {

        /**
         * {@inheritDoc}
         */
        @Override
        public void propertyChange(final PropertyChangeEvent evt) {
            updateTextPreview();
        }
    }

}
