/*
 * DemoFrame.java
 *
 * Copyright (C) 2009 Olivier Sechet
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.druppi.demo;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JEditorPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;
import javax.swing.text.StyledEditorKit;

import net.druppi.util.ResourceManager;

/**
 *
 * @author Olivier Sechet
 * @version 1.0 - Sep 3, 2009
 */
@SuppressWarnings("serial")
public class DemoFrame extends javax.swing.JFrame {

    /** The associated DemoManager. */
    private final DemoManager demoManager;

    /** The list model. */
    private final DemoListModel model;

    /**
     * Creates new form DemoFrame.
     *
     * @param demoManager the associated DemoManager.
     */
    public DemoFrame(final DemoManager demoManager) {
        this.demoManager = demoManager;
        this.model = new DemoListModel(demoManager);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING:
     * Do NOT modify this code. The content of this method is always regenerated by the
     * Form Editor.
     */
    @SuppressWarnings("nls")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPane = new JSplitPane();
        demosScrollPane = new JScrollPane();
        demosList = new JList();
        tabbedPane = new JTabbedPane();
        guiPanel = new JPanel();
        sourcePanel = new JPanel();
        sourceScrollPane = new JScrollPane();
        sourceEditorPane = new JEditorPane();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(ResourceManager.getResourceMap(getClass()).getString("DemoFrame.title")); // NOI18N
        setLocationByPlatform(true);
        setName("DemoFrame"); // NOI18N

        splitPane.setResizeWeight(0.1);
        splitPane.setName("splitPane"); // NOI18N

        demosScrollPane.setName("demosScrollPane"); // NOI18N

        demosList.setModel(model);
        demosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        demosList.setName("demosList"); // NOI18N
        demosList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent evt) {
                demosListValueChanged(evt);
            }
        });
        demosScrollPane.setViewportView(demosList);

        splitPane.setLeftComponent(demosScrollPane);

        tabbedPane.setName("tabbedPane"); // NOI18N

        guiPanel.setName("guiPanel"); // NOI18N

        guiPanel.setLayout(new BorderLayout());
        tabbedPane.addTab(ResourceManager.getResourceMap(getClass()).getString("DemoFrame.guiPanel.TabConstraints.tabTitle"), guiPanel); // NOI18N
        sourcePanel.setName("sourcePanel"); // NOI18N
        sourcePanel.setLayout(new BorderLayout());

        sourceScrollPane.setName("sourceScrollPane"); // NOI18N

        sourceEditorPane.setContentType(ResourceManager.getResourceMap(getClass()).getString("DemoFrame.sourceEditorPane.contentType")); // NOI18N
        sourceEditorPane.setEditable(false);
        sourceEditorPane.setEditorKit(new StyledEditorKit() {
            @Override
            public Document createDefaultDocument() {
                return new SyntaxDocument();
            }
        });
        sourceEditorPane.setName("sourceEditorPane"); // NOI18N
        sourceScrollPane.setViewportView(sourceEditorPane);


        sourcePanel.add(sourceScrollPane, BorderLayout.CENTER);

        tabbedPane.addTab(ResourceManager.getResourceMap(getClass()).getString("DemoFrame.sourcePanel.TabConstraints.tabTitle"), sourcePanel); // NOI18N
        splitPane.setRightComponent(tabbedPane);

        tabbedPane.getAccessibleContext().setAccessibleName(ResourceManager.getResourceMap(getClass()).getString("DemoFrame.tabbedPane.AccessibleContext.accessibleName")); // NOI18N
        getContentPane().add(splitPane, BorderLayout.CENTER);

        setSize(new Dimension(800, 600));
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Called when the list selection changes.
     *
     * @param evt the associated event.
     */
    private void demosListValueChanged(ListSelectionEvent evt) {// GEN-FIRST:event_demosListValueChanged
        if (evt.getValueIsAdjusting()) {
            return;
        }
        int index = demosList.getSelectedIndex();
        Demo demo = demoManager.getDemo(index);
        guiPanel.removeAll();
        guiPanel.add(demo.getPanel());
        guiPanel.revalidate();
        guiPanel.repaint();

        String source = demo.getSource();
        sourceEditorPane.setText(source);
        sourceEditorPane.setCaretPosition(0);
    }// GEN-LAST:event_demosListValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JList demosList;
    private JScrollPane demosScrollPane;
    private JPanel guiPanel;
    private JEditorPane sourceEditorPane;
    private JPanel sourcePanel;
    private JScrollPane sourceScrollPane;
    private JSplitPane splitPane;
    private JTabbedPane tabbedPane;
    // End of variables declaration//GEN-END:variables

}
