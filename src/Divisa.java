package src;

import netscape.javascript.JSObject;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Divisa extends JFrame {

    private JLabel labelTitle = new JLabel("Introduce el monto a convertir");

    private JTextField textField = new JTextField();

    private JLabel labelType = new JLabel("a");

    private Object [] selectConverts  = {"MXN","USD","EUR","JPY"};

    private JButton buttonContinue = new JButton("Continuar");

    private JButton buttonClose = new JButton("Cancelar");

    private JComboBox comboBoxInit =  new JComboBox(selectConverts);

    private JComboBox comboBoxFinal =  new JComboBox(selectConverts);

    private String optionSelectedInit = "MXN" ;

    private String optionSelectedFinal = "USD";

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
        newPanel.add(comboBoxInit, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        newPanel.add(labelType, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        comboBoxFinal.setSelectedIndex(1);
        newPanel.add(comboBoxFinal, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        newPanel.add(buttonContinue, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        newPanel.add(buttonClose, constraints);


        // set border for the panel
        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Convertidor de Divisa"));

        // add the panel to this frame
        add(newPanel);

        pack();
        setLocationRelativeTo(null);

        //add action listeners buttons and comboBox

        comboBoxInit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí hacemos lo que queramos hacer.
                optionSelectedInit = String.valueOf(comboBoxInit.getSelectedItem());
                System.out.println(optionSelectedInit);
            }
        });

        comboBoxFinal.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                optionSelectedFinal = String.valueOf(comboBoxFinal.getSelectedItem());
                System.out.println(optionSelectedFinal);
            }

        });
        buttonContinue.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Main main =  new Main();

                if(main.validacion(textField)){
                    try{
                        String url = "https://api.currencyapi.com/v3/" +
                                     "latest?apikey=cur_live_rO3DwvWoqfYc5oCeuGWh9gTTnMYE0EEcNrOdkOsQ&"+
                                     "currencies="+ optionSelectedFinal + "&base_currency="+ optionSelectedInit;

                        JSONObject jsonResponse = new JSONObject (new HttpClientApp().invoke(url)).getJSONObject("data");


                        Double baseValue = Double.valueOf(jsonResponse.getJSONObject(optionSelectedFinal).get("value").toString());

                        Double valueToConvertion = Double.valueOf(textField.getText());

                        Double convertionFinal   = baseValue * valueToConvertion;

                        System.out.println(convertionFinal);

                        JOptionPane.showMessageDialog(newPanel, "Tienes " + convertionFinal + " " +  optionSelectedFinal);

                    } catch (Exception ex){
                        ex.printStackTrace();
                    }

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


}
