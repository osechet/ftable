/*
 * FTableConfigurator.java
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
package net.druppi.swing.table.config;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import net.druppi.swing.FTable;
import net.druppi.swing.table.OrganizeColumnForm;
import net.druppi.swing.table.PropertiesDialog;
import net.druppi.util.ResourceManager;
import net.druppi.util.ResourceMap;


/**
 * @author Olivier Sechet
 * @version 1.0 - Apr 16, 2009
 */
final class FTableConfigurator {

    /**
     * Creates a new FTableConfigurator.
     */
    private FTableConfigurator() {
        // no op
    }

    /**
     * Creates a popup menu for the given table.
     *
     * @param table the table to which the menu is added.
     */
    public static void createPopupMenu(final FTable table) {
        final JPopupMenu popup = new JPopupMenu();
        ResourceMap resourceMap = ResourceManager.getResourceMap(PopupConfigurator.class);
        final JMenuItem propertiesItem = new JMenuItem(new ShowPropertiesTabAction(resourceMap.getString("PropertiesAction.text"), table, -1)); //$NON-NLS-1$
        final JMenuItem cellFormatItem = new JMenuItem(new CellFormatAction(table));
        final JMenuItem columnItem = new JMenuItem(new ShowPropertiesTabAction(resourceMap.getString("OrganizeColumnAction.text"), table, 0)); //$NON-NLS-1$

        MouseAdapter mouseHandler = new MouseAdapter() {
            /*
             * @see java.awt.event.MouseAdapter#mouseReleased(java.awt.event.MouseEvent)
             */
            @Override
            public void mouseReleased(final MouseEvent evt) {
                if (SwingUtilities.isRightMouseButton(evt)) {
                    popup.removeAll();

                    if (evt.getSource() == table) {
                        int row = table.getSelectedRow();
                        int column = table.getSelectedColumn();
                        if (row >= 0 && column >= 0) {
                            // FIXME: add working copy/paste actions
                            popup.add(new JMenuItem("Cut"));
                            popup.add(new JMenuItem("Copy"));
                            popup.add(new JMenuItem("Paste"));
                            popup.addSeparator();

                            // The format operation is available only if at least one cell
                            // is selected.
                            popup.add(cellFormatItem);
                            popup.addSeparator();
                        }
                    }
                    popup.add(columnItem);
                    popup.addSeparator();
                    popup.add(propertiesItem);

                    // Show the popup only if it contains items
                    if (popup.getComponentCount() > 0) {
                        popup.show((Component) evt.getSource(), evt.getX(), evt.getY());
                    }
                }
            }
        };
        table.getTableHeader().addMouseListener(mouseHandler);
        table.addMouseListener(mouseHandler);
    }

    /**
     * Create the PropertiesDialog associated with the table.
     *
     * @param table the table.
     * @return the PropertiesDialog.
     */
    private static PropertiesDialog createDialog(final FTable table) {
        PropertiesDialog dialog;
        Container ancestor = table.getTopLevelAncestor();
        if (ancestor instanceof Frame) {
            Frame parent = (Frame) ancestor;
            dialog = new PropertiesDialog(parent, false);
        } else if (ancestor instanceof Dialog) {
            Dialog parent = (Dialog) ancestor;
            dialog = new PropertiesDialog(parent, false);
        } else {
            dialog = new PropertiesDialog((Frame) null, false);
        }

//        dialog.addTab(new TableFilterForm(table));
        OrganizeColumnForm panel = new OrganizeColumnForm();
        panel.init(table);
        dialog.addTab(panel);

        dialog.pack();
        dialog.setLocationRelativeTo(ancestor);

        return dialog;
    }

    /**
     * This action displays the properties dialog of a table, and opens a specific tab.
     *
     * @author Olivier Sechet
     * @version 1.0 - Apr 16, 2009
     */
    @SuppressWarnings("serial")
    private static class ShowPropertiesTabAction extends AbstractAction {

        /** The associated table. */
        private final FTable table;
        /** The index of the tab to display. */
        private final int tabIndex;
        /** The properties dialog. */
        private PropertiesDialog dialog;

        /**
         * Creates a new FTableConfigurator.ShowPropertiesTabAction.
         *
         * @param name the action's name.
         * @param table the associated table.
         * @param tabIndex the index of the tab to display. -1 to display the last visible tab.
         */
        public ShowPropertiesTabAction(final String name, final FTable table, final int tabIndex) {
            super(name);
            this.table = table;
            this.tabIndex = tabIndex;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(final ActionEvent evt) {
            if (dialog == null) {
                dialog = createDialog(table);
            }
            if (tabIndex >= 0) {
                dialog.setTabVisible(tabIndex);
            }
            dialog.setVisible(true);
        }
    }
}
