package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Divisa extends JFrame {

    private JLabel labelTitle = new JLabel("Introduce el monto a convertir");

    private JTextField textField = new JTextField();

    private JLabel labelType = new JLabel("Introduce la divisa a convertir");
    private Object [] selectConverts  = {"Dolares","Euros","Yen"};

    private JButton buttonContinue = new JButton("Continuar");

    private JButton buttonClose = new JButton("Cancelar");

    private JComboBox comboBox =  new JComboBox(selectConverts);

    private int optionSelected = 0;

    public Divisa() {
        super("JPanel Divisa");

        // create a new panel with GridBagLayout manager
        JPanel newPanel = new JPanel(new GridBagLayout());

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
        newPanel.add(textField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(labelType, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        newPanel.add(comboBox, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        newPanel.add(buttonContinue, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        newPanel.add(buttonClose, constraints);


        // set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Convertidor de Divisa"));

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

                System.out.println("Divisa selecciona" + optionSelected);
            }
        });

        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });


    }
}
