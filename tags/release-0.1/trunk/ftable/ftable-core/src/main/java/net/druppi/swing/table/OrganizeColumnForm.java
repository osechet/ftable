/*
 * OrganizeColumnForm.java
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

import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.TableColumnModel;

import net.druppi.lang.NotImplementedException;
import net.druppi.util.ResourceManager;


/**
 *
 * @author Olivier Sechet
 * @version 1.0 - Apr 16, 2009
 */
@SuppressWarnings("serial")
public class OrganizeColumnForm extends PropertiesPanel {

    /**
     * Creates new form OrganizeColumnForm.
     */
    public OrganizeColumnForm() {
        initComponents();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(JTable table) {
        TableColumnModel model = table.getColumnModel();
        availableList.setModel(new ColumnListModel(model));
        visibleList.setModel(new ColumnListModel(model));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply() {
        throw new NotImplementedException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTitle() {
        return ResourceManager.getResourceMap(getClass()).getString("OrganizeColumnForm.title"); //$NON-NLS-1$;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        availableLabel = new JLabel();
        availableScrollPane = new JScrollPane();
        availableList = new JList();
        visibleLabel = new JLabel();
        visibleScrollPane = new JScrollPane();
        visibleList = new JList();
        moveDownButton = new JButton();
        moveUpButton = new JButton();
        hideAllButton = new JButton();
        hideButton = new JButton();
        showButton = new JButton();
        showAllButton = new JButton();

        setName("Form"); // NOI18N

        availableLabel.setText(ResourceManager.getResourceMap(OrganizeColumnForm.class).getString("OrganizeColumnForm.availableLabel.text")); // NOI18N
        availableLabel.setName("availableLabel"); // NOI18N

        availableScrollPane.setName("availableScrollPane"); // NOI18N

        availableList.setName("availableList"); // NOI18N
        availableScrollPane.setViewportView(availableList);

        visibleLabel.setText(ResourceManager.getResourceMap(OrganizeColumnForm.class).getString("OrganizeColumnForm.visibleLabel.text")); // NOI18N
        visibleLabel.setName("visibleLabel"); // NOI18N

        visibleScrollPane.setName("visibleScrollPane"); // NOI18N

        visibleList.setName("visibleList"); // NOI18N
        visibleScrollPane.setViewportView(visibleList);

        moveDownButton.setIcon(new ImageIcon(getClass().getResource("/toolbarButtonGraphics/navigation/Down16.gif"))); // NOI18N
        moveDownButton.setToolTipText(ResourceManager.getResourceMap(OrganizeColumnForm.class).getString("OrganizeColumnForm.moveDownButton.toolTipText")); // NOI18N
        moveDownButton.setMargin(new Insets(2, 2, 2, 2));
        moveDownButton.setName("moveDownButton"); // NOI18N
        moveDownButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                moveDown(evt);
            }
        });

        moveUpButton.setIcon(new ImageIcon(getClass().getResource("/toolbarButtonGraphics/navigation/Up16.gif"))); // NOI18N
        moveUpButton.setToolTipText(ResourceManager.getResourceMap(OrganizeColumnForm.class).getString("OrganizeColumnForm.moveUpButton.toolTipText")); // NOI18N
        moveUpButton.setMargin(new Insets(2, 2, 2, 2));
        moveUpButton.setName("moveUpButton"); // NOI18N
        moveUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                moveUp(evt);
            }
        });

        hideAllButton.setIcon(new ImageIcon(getClass().getResource("/toolbarButtonGraphics/media/Rewind16.gif"))); // NOI18N
        hideAllButton.setToolTipText(ResourceManager.getResourceMap(OrganizeColumnForm.class).getString("OrganizeColumnForm.hideAllButton.toolTipText")); // NOI18N
        hideAllButton.setMargin(new Insets(2, 2, 2, 2));
        hideAllButton.setName("hideAllButton"); // NOI18N
        hideAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                hideAll(evt);
            }
        });

        hideButton.setIcon(new ImageIcon(getClass().getResource("/toolbarButtonGraphics/navigation/Back16.gif"))); // NOI18N
        hideButton.setToolTipText(ResourceManager.getResourceMap(OrganizeColumnForm.class).getString("OrganizeColumnForm.hideButton.toolTipText")); // NOI18N
        hideButton.setMargin(new Insets(2, 2, 2, 2));
        hideButton.setName("hideButton"); // NOI18N
        hideButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                hideColumns(evt);
            }
        });

        showButton.setIcon(new ImageIcon(getClass().getResource("/toolbarButtonGraphics/navigation/Forward16.gif"))); // NOI18N
        showButton.setToolTipText(ResourceManager.getResourceMap(OrganizeColumnForm.class).getString("OrganizeColumnForm.showButton.toolTipText")); // NOI18N
        showButton.setMargin(new Insets(2, 2, 2, 2));
        showButton.setName("showButton"); // NOI18N
        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                showColumns(evt);
            }
        });

        showAllButton.setIcon(new ImageIcon(getClass().getResource("/toolbarButtonGraphics/media/FastForward16.gif"))); // NOI18N
        showAllButton.setToolTipText(ResourceManager.getResourceMap(OrganizeColumnForm.class).getString("OrganizeColumnForm.showAllButton.toolTipText")); // NOI18N
        showAllButton.setMargin(new Insets(2, 2, 2, 2));
        showAllButton.setName("showAllButton"); // NOI18N
        showAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                showAll(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(availableLabel, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(availableScrollPane, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(hideAllButton)
                    .addComponent(hideButton)
                    .addComponent(showButton)
                    .addComponent(showAllButton))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(visibleLabel, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(visibleScrollPane, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(moveDownButton)
                    .addComponent(moveUpButton))
                .addContainerGap())
        );

        layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {hideAllButton, hideButton, showAllButton, showButton});

        layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {moveDownButton, moveUpButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(availableLabel)
                    .addComponent(visibleLabel))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(moveUpButton)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(moveDownButton)
                        .addContainerGap(55, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addComponent(availableScrollPane, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(visibleScrollPane, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(hideAllButton)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(hideButton)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(showButton)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(showAllButton)))
                        .addContainerGap())))
        );

        layout.linkSize(SwingConstants.VERTICAL, new Component[] {hideAllButton, hideButton, showAllButton, showButton});

        layout.linkSize(SwingConstants.VERTICAL, new Component[] {moveDownButton, moveUpButton});

        moveDownButton.getAccessibleContext().setAccessibleName(ResourceManager.getResourceMap(OrganizeColumnForm.class).getString("OrganizeColumnForm.moveDownButton.AccessibleContext.accessibleName")); // NOI18N
        moveUpButton.getAccessibleContext().setAccessibleName(ResourceManager.getResourceMap(OrganizeColumnForm.class).getString("OrganizeColumnForm.moveUpButton.AccessibleContext.accessibleName")); // NOI18N
        hideAllButton.getAccessibleContext().setAccessibleName(ResourceManager.getResourceMap(OrganizeColumnForm.class).getString("OrganizeColumnForm.hideAllButton.AccessibleContext.accessibleName")); // NOI18N
        hideButton.getAccessibleContext().setAccessibleName(ResourceManager.getResourceMap(OrganizeColumnForm.class).getString("OrganizeColumnForm.hideButton.AccessibleContext.accessibleName")); // NOI18N
        showButton.getAccessibleContext().setAccessibleName(ResourceManager.getResourceMap(OrganizeColumnForm.class).getString("OrganizeColumnForm.showButton.AccessibleContext.accessibleName")); // NOI18N
        showAllButton.getAccessibleContext().setAccessibleName(ResourceManager.getResourceMap(OrganizeColumnForm.class).getString("OrganizeColumnForm.showAllButton.AccessibleContext.accessibleName")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Called when the moveUp button is activated.
     *
     * @param evt an event.
     */
    private void moveUp(ActionEvent evt) {//GEN-FIRST:event_moveUp
        throw new NotImplementedException();
    }//GEN-LAST:event_moveUp

    /**
     * Called when the moveDown button is activated.
     *
     * @param evt an event.
     */
    private void moveDown(ActionEvent evt) {//GEN-FIRST:event_moveDown
        throw new NotImplementedException();
    }//GEN-LAST:event_moveDown

    /**
     * Called when the hideAll button is activated.
     *
     * @param evt an event.
     */
    private void hideAll(ActionEvent evt) {//GEN-FIRST:event_hideAll
        throw new NotImplementedException();
    }//GEN-LAST:event_hideAll

    /**
     * Called when the hide button is activated.
     *
     * @param evt an event.
     */
    private void hideColumns(ActionEvent evt) {//GEN-FIRST:event_hideColumns
        throw new NotImplementedException();
    }//GEN-LAST:event_hideColumns

    /**
     * Called when the show button is activated.
     *
     * @param evt an event.
     */
    private void showColumns(ActionEvent evt) {//GEN-FIRST:event_showColumns
        throw new NotImplementedException();
    }//GEN-LAST:event_showColumns

    /**
     * Called when the showAll button is activated.
     *
     * @param evt an event.
     */
    private void showAll(ActionEvent evt) {//GEN-FIRST:event_showAll
        throw new NotImplementedException();
    }//GEN-LAST:event_showAll


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel availableLabel;
    private JList availableList;
    private JScrollPane availableScrollPane;
    private JButton hideAllButton;
    private JButton hideButton;
    private JButton moveDownButton;
    private JButton moveUpButton;
    private JButton showAllButton;
    private JButton showButton;
    private JLabel visibleLabel;
    private JList visibleList;
    private JScrollPane visibleScrollPane;
    // End of variables declaration//GEN-END:variables

}
