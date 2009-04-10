/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sourceforge.musicsvg.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import net.sourceforge.musicsvg.model.Song;
import org.apache.log4j.Logger;

/**
 *
 * @author Dav
 */
public class LibrarieMainPanel extends JPanel {

    private JTable jTable;
    private JComponent statusBar;
    private static final Logger LOG = Logger.getLogger(LibrarieMainPanel.class);
    private JScrollPane jScrollPane2;

    public LibrarieMainPanel(JComponent statusBar, AbstractTableModel model) {
        this.statusBar = statusBar;
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jTable.setModel(model);
        jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JTable table;
                if(e.getClickCount() >= 2) {
                    
                    table = (JTable)e.getSource();
                    SongTableModel model = (SongTableModel) table.getModel();
                    Song s = model.getItemsAt(table.getSelectedRow());
                    LOG.debug("Lancement de Guitar Pro sur le ficiher..."+s.getFile().getAbsolutePath());
                    try {
                        Runtime.getRuntime().exec("C:\\Program Files\\Guitar Pro 5\\GP5.exe " + s.getFile().getAbsolutePath());
                    } catch (IOException ex) {
                        LOG.error("Erreur lors du lancement de Guitar Pro", ex);
                    }
                }
            }

        });
        jScrollPane2.setViewportView(jTable);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        add(Box.createHorizontalGlue());
        add(jScrollPane2);
        add(statusBar);
    }

}
