/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coep.btech.stqa.medical.store;

import com.mysql.jdbc.Connection;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.*;
import java.io.FileNotFoundException;
import com.itextpdf.text.Document;

/**
 *
 * @author dell
 */

public class Employee_Window extends javax.swing.JFrame {

    /**
     * Creates new form Employee_Window
     */
    public Employee_Window() {
        initComponents();
        setEnabled(true);
        jLabel_total_cost.setText("0");
        DefaultTableModel tablemodel = (DefaultTableModel)this.jTable_Bill.getModel();
        ListSelectionModel model = jTable_Bill.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if(! model.isSelectionEmpty()) {
                    try {
                    int selectedRow = model.getMinSelectionIndex();
                    float total_cost = Float.parseFloat(jLabel_total_cost.getText().toString());
                    total_cost = total_cost - Float.parseFloat(jTable_Bill.getValueAt(selectedRow,4).toString());
                    Integer cost = (int)total_cost;
                    jLabel_total_cost.setText(cost.toString());
                    for(int i = selectedRow+1; i<jTable_Bill.getRowCount(); i++) {
                        jTable_Bill.setValueAt((Integer.parseInt(jTable_Bill.getValueAt(i, 0).toString())-1), i, 0);
                    }
                    tablemodel.removeRow(selectedRow);
                    serial_no = serial_no -1;
                    }catch(Exception e) {
                        
                    }
                }
            }
        });
        /*this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "clickButton");
        this.getRootPane().getActionMap().put("clickButton",new AbstractAction() {
            public void actionPerformed(ActionEvent ae)
            {
                AddItemToBill.doClick();
            }
        });*/
        
        DefaultTableCellRenderer alignment = new DefaultTableCellRenderer();
        alignment.setHorizontalAlignment(JLabel.CENTER);
        JTable_info.getColumnModel().getColumn(0).setCellRenderer(alignment);
        JTable_info.getColumnModel().getColumn(1).setCellRenderer(alignment);
        for(int i = 0; i < jTable_Bill.getColumnCount(); i++)
            jTable_Bill.getColumnModel().getColumn(i).setCellRenderer(alignment);
            
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        jLabel_date_field.setText(timeStamp);
        
        boolean exist;
        Integer bill_no;
      
        try(Connection conn = MySQL_Connector.getConnection()) {
            String conn_query = "select medc_name from medicine";
            ResultSet res = MySQL_Connector.runQuery(conn, conn_query);
            while(res.next()) {
                jComboBox1_select.addItem(res.getString("medc_name"));
            }
            jComboBox1_select.setVisible(true);
            conn.close();
            res.close();
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try(Connection conn = MySQL_Connector.getConnection()) {
            String query1 = "select * from sales";
            ResultSet rs1 = MySQL_Connector.runQuery(conn, query1);
            exist = rs1.next();
            if(exist == false) {
                bill_no = 1;
                jLabel_bill_no.setText(bill_no.toString());
            }
            else {
                String query4 = "select bill_no from sales order by bill_no desc limit 1";
                ResultSet rs4 = MySQL_Connector.runQuery(conn, query4);
                if(rs4.next()) {
                    bill_no = Integer.parseInt(rs4.getString("bill_no").toString());
                    bill_no++;
                    jLabel_bill_no.setText(bill_no.toString());
                }
            }
            conn.close();
        }catch(SQLException e) {
             Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField_quantity = new javax.swing.JTextField();
        AddItemToBill = new javax.swing.JButton();
        jComboBox1_select = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTable_info = new javax.swing.JTable();
        jPanel_bill = new javax.swing.JPanel();
        jButton_save = new javax.swing.JButton();
        jButton_print = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Bill = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel_total_cost = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel_bill_no = new javax.swing.JLabel();
        newBill = new javax.swing.JButton();
        jLabel_date = new javax.swing.JLabel();
        jLabel_date_field = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Medical Store: Bill Generator");
        setEnabled(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Select", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(4, 2, 2))); // NOI18N

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel1.setText("Search the medicine:");

        jLabel2.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel2.setText("Quantity of medicines:");

        jTextField_quantity.setText("1");
        jTextField_quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_quantityActionPerformed(evt);
            }
        });

        AddItemToBill.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        AddItemToBill.setText("Add Item to Bill");
        AddItemToBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddItemToBillActionPerformed(evt);
            }
        });

        jComboBox1_select.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1_selectItemStateChanged(evt);
            }
        });
        jComboBox1_select.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jComboBox1_selectFocusGained(evt);
            }
        });
        jComboBox1_select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1_selectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextField_quantity, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(AddItemToBill, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1_select, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1_select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTextField_quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(AddItemToBill)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Information", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(1, 1, 1))); // NOI18N

        JTable_info.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Medicine ID", null},
                {"Medicine Company", null},
                {"Medicine Description", null},
                {"Medicine Power", null},
                {"Available Quantity", null},
                {"Cost/Strip", null},
                {"Medicines/Strip", null},
                {"Shelf Number", null}
            },
            new String [] {
                "Term", "Value"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTable_info.setRowHeight(29);
        JTable_info.setRowMargin(2);
        jScrollPane2.setViewportView(JTable_info);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        jPanel_bill.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bill Generator", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 12), new java.awt.Color(1, 1, 1))); // NOI18N

        jButton_save.setText("Save Bill");
        jButton_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_saveActionPerformed(evt);
            }
        });

        jButton_print.setText("Print Bill");
        jButton_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_printActionPerformed(evt);
            }
        });

        jTable_Bill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sr.No", "Medicine Name", "Cost per item", "Quantity", "Total Cost"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable_Bill.setColumnSelectionAllowed(true);
        jTable_Bill.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable_Bill);
        jTable_Bill.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel3.setText("Total Cost of Bill :");

        jLabel_total_cost.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel_total_cost.setEnabled(false);
        jLabel_total_cost.setOpaque(true);

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel5.setText("Bill Number:");

        jLabel_bill_no.setEnabled(false);
        jLabel_bill_no.setOpaque(true);

        newBill.setText("New Bill");
        newBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBillActionPerformed(evt);
            }
        });

        jLabel_date.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel_date.setText("Date:");

        jLabel_date_field.setEnabled(false);
        jLabel_date_field.setOpaque(true);

        javax.swing.GroupLayout jPanel_billLayout = new javax.swing.GroupLayout(jPanel_bill);
        jPanel_bill.setLayout(jPanel_billLayout);
        jPanel_billLayout.setHorizontalGroup(
            jPanel_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_billLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel_billLayout.createSequentialGroup()
                        .addGroup(jPanel_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel_billLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel_billLayout.createSequentialGroup()
                                .addComponent(jButton_save, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_print, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(92, 92, 92)))
                        .addGroup(jPanel_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_total_cost, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_billLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(newBill, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48))
                    .addGroup(jPanel_billLayout.createSequentialGroup()
                        .addGroup(jPanel_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel_billLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel_bill_no, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87)
                                .addComponent(jLabel_date)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel_date_field, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(34, Short.MAX_VALUE))))
        );
        jPanel_billLayout.setVerticalGroup(
            jPanel_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_billLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel_bill_no, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel_date_field, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel_date)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_total_cost, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel_billLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_save)
                    .addComponent(jButton_print)
                    .addComponent(newBill))
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jPanel_bill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_bill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_quantityActionPerformed
   
    private void InsertIntoSales() {
        int i;
        String medicine_name;
        float total_cost;
        Integer quantity;
        Integer bill_no = Integer.parseInt(jLabel_bill_no.getText());
        String timestamp = jLabel_date_field.getText();
        for(i = 0; i < jTable_Bill.getRowCount(); i++) {
            medicine_name = jTable_Bill.getValueAt(i, 1).toString();
            quantity = Integer.parseInt(jTable_Bill.getValueAt(i, 3).toString());
            total_cost = Float.parseFloat(jTable_Bill.getValueAt(i, 4).toString());
            try(Connection conn = MySQL_Connector.getConnection()) {
                String query3 = "select medc_id from medicine where medc_name = '"+medicine_name+"'";
                ResultSet rs2 = MySQL_Connector.runQuery(conn, query3);
                String medicine_id = "";
                if(rs2.next())
                    medicine_id = rs2.getString("medc_id").toString();
                String query2 = "insert into sales values ('"+bill_no+"', '"+timestamp+"','"+medicine_id+"' , '"+quantity+"', '"+total_cost+"')";
                MySQL_Connector.runUpdateQuery(conn, query2);
                String sub_query = "select medc_quantity_in_tablets from medicine where medc_name = '"+medicine_name+"'";
                ResultSet sub_rs = MySQL_Connector.runQuery(conn, sub_query);
                Integer medc_quantity = 0;
                if(sub_rs.next()) {
                    medc_quantity = Integer.parseInt(sub_rs.getString("medc_quantity_in_tablets"));
                }
                medc_quantity = medc_quantity - quantity;
                String query4 = "update medicine set medc_quantity_in_tablets = '"+medc_quantity+"' where medc_name = '"+medicine_name+"'";
                MySQL_Connector.runUpdateQuery(conn, query4);
            }catch(Exception e) {
                 Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }
    
    public void actionPerformed(ActionEvent e) {
      DefaultTableModel model = (DefaultTableModel)this.jTable_Bill.getModel();
      if (jTable_Bill.getSelectedRow() != -1) {
            model.removeRow(jTable_Bill.getSelectedRow());
        }
    }
    
    private void jButton_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_printActionPerformed
        jButton_print.setEnabled(false);
        InsertIntoSales();
        PrinterJob pj = PrinterJob.getPrinterJob();
        //PageFormat pf = pj.pageDialog(pj.defaultPage());
        if (pj.printDialog()) {
            try {
                pj.print();
            }
            catch (PrinterException exc) {
                JOptionPane.showMessageDialog(null, "Printing Error!");
            }
        }
        try {
            Document document = new Document(PageSize.A4, 90, 90, 90, 90);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/home/abhishek/file.pdf"));
            document.open();
            PdfContentByte cb = writer.getDirectContent();
            PdfTemplate tp = cb.createTemplate(jPanel_bill.getWidth(), jPanel_bill.getHeight());
            Graphics2D g2 = tp.createGraphics(jPanel_bill.getWidth(), jPanel_bill.getHeight());
            g2.scale(0.8, 1.0);
            jPanel_bill.print(g2);
            g2.dispose();
            cb.addTemplate(tp, 5, 60);
            document.close();
        } catch (DocumentException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Employee_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setVisible(false);
        Employee_Window stocks = new Employee_Window();
        stocks.setVisible(true);
    }//GEN-LAST:event_jButton_printActionPerformed

     public int print(Graphics g, PageFormat pf, int index) throws
            PrinterException {

        Graphics2D g2 = (Graphics2D) g;
        if (index >= 1) {
            return Printable.NO_SUCH_PAGE;
        } else {

            jPanel_bill.printAll(g2);
            return Printable.PAGE_EXISTS;
        }

    }
    
    private void jButton_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_saveActionPerformed
        jButton_save.setEnabled(false);
        InsertIntoSales();
        this.setVisible(false);
        Employee_Window stocks = new Employee_Window();
        stocks.setVisible(true);

    }//GEN-LAST:event_jButton_saveActionPerformed
    
    public int serial_no = 1;
   // public static Integer total_bill = 0;
    int flag = 0;
    private void AddItemToBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddItemToBillActionPerformed
        if(Integer.parseInt(JTable_info.getValueAt(4, 1).toString()) < Integer.parseInt(jTextField_quantity.getText())) {
            JOptionPane.showMessageDialog(null, "Entered quantity exceeds available quantity.");
            return;
        }
        for(int i = 0; i<jTable_Bill.getRowCount(); i++) {
            if(jTable_Bill.getValueAt(i, 1) == jComboBox1_select.getSelectedItem().toString()) {
                flag = 1;
                break;
            }
        }
        if(flag == 0) {
            //JOptionPane.showMessageDialog(null, serial_no);
            DefaultTableModel model = (DefaultTableModel)this.jTable_Bill.getModel();
            model.addRow(new Object[]{null,null,null,null,null});
            String medicine_name = jComboBox1_select.getSelectedItem().toString();
            int quantity = Integer.parseInt(jTextField_quantity.getText());
            int cost_per_tablet = (int)Math.ceil(Float.parseFloat(JTable_info.getValueAt(5, 1).toString())/Float.parseFloat(JTable_info.getValueAt(6,1).toString()));
            jTable_Bill.setValueAt(serial_no, serial_no-1, 0);
            jTable_Bill.setValueAt(medicine_name, serial_no-1, 1);
            jTable_Bill.setValueAt(cost_per_tablet, serial_no-1, 2);
            jTable_Bill.setValueAt(quantity, serial_no-1, 3);
            float bill = quantity * cost_per_tablet;
            jTable_Bill.setValueAt(bill, serial_no-1, 4);  
            serial_no++;

            Integer total_bill = (int)Math.ceil(Float.parseFloat(jLabel_total_cost.getText().toString()));
            total_bill = total_bill + (int)Math.ceil(bill);

            jLabel_total_cost.setText(total_bill.toString());
        }else {
            flag = 0;
            JOptionPane.showMessageDialog(null, "Entry already exists in the Bill. Delete the entry first and then re-insert again.");
        }
    }//GEN-LAST:event_AddItemToBillActionPerformed

    private void showInformation() {
        try(Connection conn = MySQL_Connector.getConnection()) {
            String medicine_name = jComboBox1_select.getSelectedItem().toString();
            String query = "select * from medicine where medc_name = '"+medicine_name+"'";
            ResultSet rs = MySQL_Connector.runQuery(conn, query);
            if(rs.next())
                JTable_info.setValueAt(rs.getString("medc_id"), 0, 1);
                String query2 = "select supplier_name from supplier where supplier_id = '"+rs.getString("medc_supplier_id")+"'";
                ResultSet rs1 = MySQL_Connector.runQuery(conn, query2);
                if(rs1.next())
                    JTable_info.setValueAt(rs1.getString("supplier_name"), 1, 1);
                JTable_info.setValueAt(rs.getString("medc_description"), 2,1);
                JTable_info.setValueAt(rs.getString("medc_power"), 3, 1);
                JTable_info.setValueAt(rs.getString("medc_quantity_in_tablets"), 4, 1);
                JTable_info.setValueAt(rs.getString("medc_cost_per_strip"), 5, 1);
                JTable_info.setValueAt(rs.getString("medc_per_strip"), 6, 1);
                JTable_info.setValueAt(rs.getString("shelf_no"), 7, 1);
                conn.close();
                rs.close();
                rs1.close();
        }catch(SQLException e) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    private void newBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBillActionPerformed
        this.setVisible(false);
        Employee_Window stocks = new Employee_Window();
        stocks.setVisible(true);
    }//GEN-LAST:event_newBillActionPerformed

    private void jComboBox1_selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1_selectActionPerformed
         showInformation();
    }//GEN-LAST:event_jComboBox1_selectActionPerformed

    private void jComboBox1_selectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1_selectItemStateChanged
        showInformation();
    }//GEN-LAST:event_jComboBox1_selectItemStateChanged

    private void jComboBox1_selectFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jComboBox1_selectFocusGained
        showInformation();
    }//GEN-LAST:event_jComboBox1_selectFocusGained

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Employee_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Employee_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Employee_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Employee_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Employee_Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddItemToBill;
    private javax.swing.JTable JTable_info;
    private javax.swing.JButton jButton_print;
    private javax.swing.JButton jButton_save;
    private javax.swing.JComboBox<String> jComboBox1_select;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel_bill_no;
    private javax.swing.JLabel jLabel_date;
    private javax.swing.JLabel jLabel_date_field;
    private javax.swing.JLabel jLabel_total_cost;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_bill;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable_Bill;
    private javax.swing.JTextField jTextField_quantity;
    private javax.swing.JButton newBill;
    // End of variables declaration//GEN-END:variables
}
