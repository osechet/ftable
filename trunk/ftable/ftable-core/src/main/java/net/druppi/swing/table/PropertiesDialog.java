/*
 * PropertiesDialog.java
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
package net.druppi.swing.table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import net.druppi.swing.OKCancelForm;
import net.druppi.util.ResourceManager;

/**
 * A dialog used to display table properties. The dialog contains a JTabbedPane that can
 * be customized. For each kind of properties, a new panel can be added with the
 * <code>addTab()</code> method.
 * 
 * @author Olivier Sechet
 * @version 1.0 - Mar 24, 2009
 */
@SuppressWarnings("serial")
public class PropertiesDialog extends javax.swing.JDialog {

    /**
     * Creates new form PropertiesDialog.
     * 
     * @param parent the Frame from which the dialog is displayed.
     * @param modal specifies whether dialog blocks user input to other top-level windows
     *        when shown. If true, the modality type property is set to
     *        DEFAULT_MODALITY_TYPE, otherwise the dialog is modeless.
     */
    public PropertiesDialog(final Frame parent, final boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * Adds a new tab to the dialog.
     * 
     * @param panel the panel to add as a tab.
     */
    public final void addTab(final PropertiesPanel panel) {
        tabbedPane.addTab(panel.getTitle(), panel);
    }

    /**
     * @return
     */
    public PropertiesPanel[] getPropertiesPanels() {
        Component[] components = tabbedPane.getComponents();
        return (PropertiesPanel[]) components;
    }

    /**
     * @param tabIndex
     */
    public void setTabVisible(int tabIndex) {
        tabbedPane.setSelectedIndex(tabIndex);
    }

    /**
     * Closes the dialog.
     * 
     * @param evt an event.
     */
    private void close() {
        setVisible(false);
    }

    /**
     * Apply the options.
     * 
     * @param evt an event.
     */
    private void apply() {
        PropertiesPanel panel = (PropertiesPanel) tabbedPane.getSelectedComponent();
        panel.apply();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING:
     * Do NOT modify this code. The content of this method is always regenerated by the
     * Form Editor.
     */
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedPane = new JTabbedPane();
        okCancelForm = new OKCancelForm(new OKHandler(), new ApplyHandler(),
                new CancelHandler());

        setTitle(ResourceManager.getResourceMap(getClass()).getString(
                "PropertiesDialog.title")); // NOI18N
        setName("Form"); // NOI18N

        tabbedPane.setName("tabbedPane"); // NOI18N
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        okCancelForm.setName("okCancelForm"); // NOI18N
        getContentPane().add(okCancelForm, BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private OKCancelForm okCancelForm;
    /** The tabbedPane for the PropertiesPanel. */
    private JTabbedPane tabbedPane;

    // End of variables declaration//GEN-END:variables

    /**
     * The OK button listener.
     * 
     * @author Olivier Sechet
     * @version 1.0 - Apr 16, 2009
     */
    private class OKHandler implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent evt) {
            apply();
            close();
        }
    }

    /**
     * The Apply button listener.
     * 
     * @author Olivier Sechet
     * @version 1.0 - Apr 16, 2009
     */
    private class ApplyHandler implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent evt) {
            apply();
        }
    }

    /**
     * The Cancel button listener.
     * 
     * @author Olivier Sechet
     * @version 1.0 - Apr 16, 2009
     */
    private class CancelHandler implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent evt) {
            close();
        }
    }

}
