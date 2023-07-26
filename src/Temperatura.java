package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;

public class Temperatura extends JFrame {


    private JLabel labelTitle = new JLabel("Introduce la temperatura a convertir");

    private JTextField textField = new JTextField();

    private JLabel labelType = new JLabel("Elije los tipos de grados a convertir");
    private Object [] selectConverts  = {"Celcius a Fahrenheit","Fahrenheit a Celcius","Celcius a Kelvin" ,"Fahrenheit a Kelvin",
                                         "Kelvin a Celcius","Kelvin a Fahrenheit"};

    private JButton buttonContinue = new JButton("Continuar");

    private JButton buttonClose = new JButton("Cancelar");

    private JComboBox comboBox =  new JComboBox(selectConverts);

    private int optionSelected = 0;

    public Temperatura() {
        super("JPanel Temperatura");

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
                BorderFactory.createEtchedBorder(), "Convertidor de Temperatura"));

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
            }
        });
        buttonContinue.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Main main =  new Main();

                if(main.validacion(textField)){
                    int i = Integer.parseInt(textField.getText());
                    JOptionPane.showMessageDialog(newPanel, conversor(i));

                }else{
                    JOptionPane.showMessageDialog(newPanel, "Introduce un valor numerico");
                }
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


    private String conversor(int valor){

        double convertion = 0;
        String resultado ="";
        switch(optionSelected) {
            case 0:
                convertion  = valor * 1.8 + 32;
                resultado   = convertion + " °F";
                break;
            case 1:
                convertion  = (valor-32) / 1.8;
                resultado   = convertion + " °C";
                break;
            case 2:
                convertion  =  valor + 273.15;
                resultado   = convertion + " °K";
                break;
            case 3:
                convertion  =  0.55 * (valor - 32)  + 273.15;
                resultado   = convertion + " °K";
                break;
            case 4:
                convertion  =  valor - 273.15;
                resultado   = convertion + " °C";
                break;
            case 5:
                convertion  =  1.8 * (valor - 273.15) + 32;
                resultado   = convertion + " °F";
                break;
            default:
                System.out.println("i es mayor a tres.");
        }


        return resultado;
    }
}
