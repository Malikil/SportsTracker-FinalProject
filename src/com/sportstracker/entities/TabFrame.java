package com.sportstracker.entities;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

//Implementation with close event handeling
public class TabFrame extends JFrame {
private ClosableTabbedPane tabbedPane;

 public TabFrame() {
     tabbedPane = new ClosableTabbedPane() {
         public boolean tabAboutToClose(int tabIndex) {
             String tab = tabbedPane.getTabTitleAt(tabIndex);
             int choice = JOptionPane.showConfirmDialog(null, 
                "You are about to close '" + 
                tab + "'\nDo you want to proceed ?", 
                "Confirmation Dialog", 
                JOptionPane.INFORMATION_MESSAGE);
             return choice == 0;
             // if returned false tab
             // closing will be canceled
             }
         };
     getContentPane().add(tabbedPane);
     }
}