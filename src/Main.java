package src;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends JFrame {

    private JLabel labelTitle = new JLabel("Selecciona un tipo de convertidor");
    private Object [] selectConverts  = {"Conversor de Moneda","Conversor de Temperatura"};
    private JButton buttonContinue = new JButton("Continuar");
    private JButton buttonClose = new JButton("Cancelar");
    private JComboBox comboBox =  new JComboBox(selectConverts);

    private  JPanel newPanel;

    private int optionSelected = 0;


    public Main() {
        super("JPanel Main");

        // create a new panel with GridBagLayout manager
        newPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 10, 5, 10);
        constraints.weightx = 1;
        constraints.weighty = 1;

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        newPanel.add(labelTitle, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(comboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        newPanel.add(buttonContinue, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        newPanel.add(buttonClose, constraints);


        // set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Convertidor"));

        // add the panel to this frame
        add(newPanel);
        pack();
        setLocationRelativeTo(null);

        //add action listeners buttons and comboBox

        comboBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí hacemos lo que queramos hacer.
                optionSelected = comboBox.getSelectedIndex();
                System.out.println(optionSelected);
            }
        });
        buttonContinue.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeFrame(newPanel);
                if(optionSelected == 0){
                    new Divisa().setVisible(true);
                }else{
                    new Temperatura().setVisible(true);
                }
            }
        });

        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeFrame(newPanel);
            }
        });
    }

    public void closeFrame(JPanel jpanel){
        JFrame mainFrame = (JFrame) SwingUtilities.getWindowAncestor(jpanel);
        mainFrame.dispose();;
    }

    public Boolean validacion(JTextField textField) {

        String text = textField.getText();
        Pattern pattern = Pattern.compile("^\\+?(\\d*[1-9]+\\d*\\.?\\d*|\\d*\\.\\d*[1-9]+\\d*)$");
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }


    public static void main(String[] args) {
        // set look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });


    }
}
