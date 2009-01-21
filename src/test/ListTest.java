/*
 * @(#)ListTest.java  1.0  13 February 2005
 *
 * Copyright (c) 2004 Werner Randelshofer
 * Staldenmattweg 2, Immensee, CH-6405, Switzerland.
 * All rights reserved.
 *
 * The copyright of this software is owned by Werner Randelshofer. 
 * You may not use, copy or modify this software, except in  
 * accordance with the license agreement you entered into with  
 * Werner Randelshofer. For details see accompanying license terms. 
 */

package test;

import java.awt.event.*;
import javax.swing.event.*;

/**
 * ListTest.
 *
 * @author  Werner Randelshofer
 * @version 1.0  13 February 2005  Created.
 */
public class ListTest extends javax.swing.JPanel {
    
    /** Creates new form. */
    public ListTest() {
        initComponents();
        stripedList.putClientProperty("Quaqua.List.style","striped");
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane2 = new javax.swing.JScrollPane();
        list = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jScrollPane8 = new javax.swing.JScrollPane();
        stripedList = new javax.swing.JList();
        jLabel6 = new javax.swing.JLabel();

        setLayout(new java.awt.GridBagLayout());

        list.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Apple", "Banana", "Ravioli", "Pizza" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(list);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane2, gridBagConstraints);

        jLabel5.setText("Plain Style");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 12);
        add(jLabel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 12, 0);
        add(jSeparator10, gridBagConstraints);

        stripedList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Apple", "Banana", "Ravioli", "Pizza", "Basketball", "Soccer", "Football", "Hockey", "Blue", "Violet", "Red", "Yellow" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane8.setViewportView(stripedList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane8, gridBagConstraints);

        jLabel6.setText("Striped Style");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 12);
        add(jLabel6, gridBagConstraints);

    }//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JList list;
    private javax.swing.JList stripedList;
    // End of variables declaration//GEN-END:variables
    
}