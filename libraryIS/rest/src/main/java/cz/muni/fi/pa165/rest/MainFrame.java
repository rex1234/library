package cz.muni.fi.pa165.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cz.muni.fi.pa165.library.dtos.Department;
import cz.muni.fi.pa165.library.dtos.ImpressionTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

/**
 *
 * @author Mjartan
 */
public class MainFrame extends javax.swing.JFrame {

    private static final String API_URL = "http://localhost:8084/library/rest";
    private WebTarget webTarget;
    private List<ImpressionTO> impressions;
    private Gson gson;

    public MainFrame() {
        Client client = ClientBuilder.newClient();
        webTarget = client.target(API_URL);
        gson = new GsonBuilder().
                registerTypeAdapter(LocalDate.class, new JodaTimeSerializer()).create();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lImpressions = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        bLoad = new javax.swing.JButton();
        bDelete = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        bInsert = new javax.swing.JButton();
        tfIsbn = new javax.swing.JTextField();
        tfAuthor = new javax.swing.JTextField();
        tfName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfDate = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lImpressions.setModel(new ImpressionListModel());
        jScrollPane1.setViewportView(lImpressions);

        jLabel1.setText("Impressions:");

        bLoad.setText("Load Impressions");
        bLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoadActionPerformed(evt);
            }
        });

        bDelete.setText("Delete");
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });

        jLabel2.setText("ISBN");

        jLabel3.setText("Author");

        jLabel4.setText("Name");

        bInsert.setText("Insert");
        bInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInsertActionPerformed(evt);
            }
        });

        jLabel5.setText("Released");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(bLoad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfDate)
                                    .addComponent(tfIsbn)
                                    .addComponent(tfName)
                                    .addComponent(tfAuthor)))
                            .addComponent(bInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bLoad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bDelete)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfAuthor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bInsert)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInsertActionPerformed
        try {
            if (isFieldEmpty(tfName) || isFieldEmpty(tfAuthor) || isFieldEmpty(tfIsbn) || isFieldEmpty(tfDate)) {
                showErrorDialog("Please fill in all fields.");
                return;
            }
            ImpressionTO i = new ImpressionTO();
            i.setName(tfName.getText());
            i.setAuthor(tfAuthor.getText());
            i.setIsbn(tfIsbn.getText());
            try {
                i.setReleaseDate(LocalDate.parse(tfDate.getText(), DateTimeFormat.forPattern("dd.MM.yyyy")));
            } catch (IllegalArgumentException e) {
                showErrorDialog("Bad date format");
                System.err.println(e);
                return;
            }
            i.setDepartment(Department.CHILDREN);

            WebTarget resourceWebTarget = webTarget.path("impressions/");
            Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
            invocationBuilder.header("accept", "application/json");

            String json = gson.toJson(i, ImpressionTO.class);

            Response response = invocationBuilder.post(Entity.json(json));
            System.out.println(response.getStatus());
            bLoadActionPerformed(null);
        } catch (Exception e) {
            showServerError();
            System.err.println(e);
        }
    }//GEN-LAST:event_bInsertActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        try {
            if (impressions == null) {
                return;
            }
            Long id = impressions.get(lImpressions.getSelectedIndex()).getId();
            getImpressionModel().delete(lImpressions.getSelectedIndex());
            WebTarget resourceWebTarget = webTarget.path("impressions/" + id);
            System.out.println("Deleting impression with id " + id + " on position " + lImpressions.getSelectedIndex());

            Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
            invocationBuilder.header("accept", "application/json");

            Response response = invocationBuilder.delete();
            System.out.println(response.getStatus());
            System.out.println(response.readEntity(String.class));
            bLoadActionPerformed(null);//reload list
        } catch (Exception e) {
            showServerError();
            System.err.println(e);
        }
    }//GEN-LAST:event_bDeleteActionPerformed

    private void bLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoadActionPerformed
        try {
            WebTarget resourceWebTarget = webTarget.path("impressions");

            Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
            invocationBuilder.header("accept", "application/json");
            Response response = invocationBuilder.get();

            System.out.println(response.getStatus());
            String imString = response.readEntity(String.class);
            ImpressionTO[] ims = gson.fromJson(imString, ImpressionTO[].class);
            impressions = new ArrayList<ImpressionTO>(Arrays.asList(ims));
            getImpressionModel().setImpressions(impressions);
        } catch (Exception e) {
            showServerError();
            System.err.println(e);
        }
    }//GEN-LAST:event_bLoadActionPerformed

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
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bDelete;
    private javax.swing.JButton bInsert;
    private javax.swing.JButton bLoad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList lImpressions;
    private javax.swing.JTextField tfAuthor;
    private javax.swing.JTextField tfDate;
    private javax.swing.JTextField tfIsbn;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables

    private ImpressionListModel getImpressionModel() {
        return (ImpressionListModel) lImpressions.getModel();
    }

    private void showErrorDialog(String text) {
        JOptionPane.showMessageDialog(this, text, "Error.", JOptionPane.ERROR_MESSAGE);
    }

    private void showServerError() {
        showErrorDialog("Failed to connect to the server");
    }

    private static boolean isFieldEmpty(javax.swing.JTextField tf) {
        return tf.getText().trim().isEmpty();
    }
}
