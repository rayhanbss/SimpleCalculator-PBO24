package com.rayhanbss;

import javax.swing.*;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.net.URL;

// Class untuk membuat tombol berbentuk lingkaran
class RoundedButton extends JButton {
    private final Color pressedColor;

    // Constructor inisialisasi tombol dengan label dan warna
    public RoundedButton(String label, Color originalColor) {
        super(label);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);

        // Menyimpan warna asli dan warna saat ditekan
        pressedColor = originalColor.darker(); // Warna saat ditekan

        setBackground(originalColor);

        // Tambahkan animasi klik dengan MouseListener
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(pressedColor); // Ganti warna saat diklik
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setBackground(originalColor); // Kembalikan ke warna asli saat dilepas
            }
        });
    }

    // Override paintComponent untuk tombol lingkaran
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set warna background
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 125, 125));

        super.paintComponent(g);
    }

    // Override paintBorder untuk mengganti border
//    @Override
//    protected void paintBorder(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g.create();
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        // Set warna border
//        g2.setColor(getForeground());
//        g2.draw(new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 100, 100));
//
//        g2.dispose();
//    }
}

// Class utama kalkulator
public class Main implements ActionListener{
    // Deklarasi frame dan text fields
    JFrame frame;
    JTextField mainTextField;
    JTextArea subTextArea;

    // Deklarasi array button angka dan operator
    JButton[] numberBunttons = new JButton[10];
    JButton[] operatorBunttons = new JButton[10];

    // Deklarasi button operator
    JButton addButton, subButton, mulButton, divButton, modButton,
            db0Button, decButton, eqlButton, aclButton, clrButton;

    // Deklarasi panel
    JPanel panel;

    // Deklarasi font
    Font subTextFont = new Font("San Francisco", Font.PLAIN, 40);
    Font mainTextFont = new Font("San Francisco", Font.PLAIN, 100);
    Font buttonFont = new Font("San Francisco", Font.PLAIN, 30);
    Font specialButtonFont = new Font("San Francisco", Font.PLAIN, 40);

    // Deklarasi warna
    Color operatorColor1 = new Color(248, 163, 4);
    Color operatorColor2 = new Color(95, 97, 112);
    Color numberColor = new Color(34, 34, 34);
    Color bkgColor = new Color(51, 50, 59);

    ImageIcon icon = new ImageIcon("src/main/java/com/rayhanbss/resources/icon.png");

    // Constructor inisialisasi dan setup GUI kalkulator
    public Main(){
        frame = new JFrame("Simple Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(555, 1000);
        frame.setIconImage(icon.getImage());

        frame.getContentPane().setBackground(bkgColor);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        subTextArea = new JTextArea();
        subTextArea.setBounds(10, 40, 520, 120);
        subTextArea.setBackground(bkgColor);
        subTextArea.setCaretColor(bkgColor);
        subTextArea.setLineWrap(true);
        subTextArea.setWrapStyleWord(true);
        subTextArea.setBorder(null);
        subTextArea.setForeground(new Color(250, 249, 246));
        subTextArea.setFont(subTextFont);
        subTextArea.setEditable(false);

        mainTextField = new JTextField();
        mainTextField.setBounds(10,170,520, 120);
        mainTextField.setBackground(bkgColor);
        mainTextField.setCaretColor(bkgColor);
        mainTextField.setBorder(null);
        mainTextField.setForeground(new Color(250, 249, 246));
        mainTextField.setHorizontalAlignment(SwingConstants.RIGHT);
        mainTextField.setFont(mainTextFont);
        mainTextField.setEditable(false);

        addButton = new RoundedButton("+", operatorColor1);
        subButton = new RoundedButton("-", operatorColor1);
        mulButton = new RoundedButton("×", operatorColor1);
        divButton = new RoundedButton("÷", operatorColor1);
        modButton = new RoundedButton("%", operatorColor2);
        db0Button = new RoundedButton("00",numberColor);
        decButton = new RoundedButton(".", numberColor);
        eqlButton = new RoundedButton("=", operatorColor1);
        aclButton = new RoundedButton("AC", operatorColor2);
        clrButton = new RoundedButton("CL", operatorColor2);

        operatorBunttons[0] = addButton;
        operatorBunttons[1] = subButton;
        operatorBunttons[2] = mulButton;
        operatorBunttons[3] = divButton;
        operatorBunttons[4] = modButton;
        operatorBunttons[5] = db0Button;
        operatorBunttons[6] = decButton;
        operatorBunttons[7] = eqlButton;
        operatorBunttons[8] = aclButton;
        operatorBunttons[9] = clrButton;

        for(JButton operatorButton : operatorBunttons) {
            operatorButton.addActionListener(this);
            operatorButton.setForeground(new Color(250, 249, 246));
            operatorButton.setFont(specialButtonFont);
            operatorButton.setFocusable(false);
        }

        aclButton.setFont(buttonFont);
        clrButton.setFont(buttonFont);
        modButton.setFont(buttonFont);
        db0Button.setFont(buttonFont);

        for(int i = 0; i < 10; i++){
            numberBunttons[i] = new RoundedButton(String.valueOf(i), numberColor);
            numberBunttons[i].setForeground(new Color(250, 249, 246));
            numberBunttons[i].addActionListener(this);
            numberBunttons[i].setFont(buttonFont);
            numberBunttons[i].setFocusable(false);
        }

        panel = new JPanel();
        panel.setBounds(20, 300, 500, 640);
        panel.setLayout(new GridLayout(5,4,25,25));
        panel.setBackground(bkgColor);

        panel.add(aclButton);
        panel.add(clrButton);
        panel.add(modButton);
        panel.add(divButton);
        panel.add(numberBunttons[1]);
        panel.add(numberBunttons[2]);
        panel.add(numberBunttons[3]);
        panel.add(mulButton);
        panel.add(numberBunttons[4]);
        panel.add(numberBunttons[5]);
        panel.add(numberBunttons[6]);
        panel.add(subButton);
        panel.add(numberBunttons[7]);
        panel.add(numberBunttons[8]);
        panel.add(numberBunttons[9]);
        panel.add(addButton);
        panel.add(numberBunttons[0]);
        panel.add(db0Button);
        panel.add(decButton);
        panel.add(eqlButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(mainTextField, BorderLayout.SOUTH);
        frame.add(subTextArea, BorderLayout.SOUTH);
        frame.setVisible(true);
    }


    // Menangani semua event klik tombol kalkulator
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle input angka
        for(int i = 0; i < 10; i++) {
            if(e.getSource() == numberBunttons[i]) {
                if (subTextArea.getText().equals("Error: Division by zero") ||
                        subTextArea.getText().equals("Error: Invalid expression")) {
                    mainTextField.setText("");
                    subTextArea.setText("");
                }
                String currentText = mainTextField.getText();
                if (!currentText.isEmpty() && !currentText.contains(" ") && isResult(currentText)) {
                    mainTextField.setText("");
                    subTextArea.setText("");
                }
                mainTextField.setText(mainTextField.getText().concat(String.valueOf(i)));
                return;
            }
        }

        // Handle tombol '00'
        if(e.getSource() == db0Button) {
            if (subTextArea.getText().equals("Error: Division by zero") ||
                    subTextArea.getText().equals("Error: Invalid expression")) {
                subTextArea.setText("");
            }
            String currentText = mainTextField.getText();
            if(!currentText.isEmpty() && Character.isDigit(currentText.charAt(currentText.length() - 1))) {
                if (!currentText.contains(" ") && isResult(currentText)) {
                    mainTextField.setText("");
                    subTextArea.setText("");
                    currentText = "";
                }
                mainTextField.setText(currentText.concat("00"));
            }
            return;
        }

        // Handle tombol desimal
        if(e.getSource() == decButton) {
            if (subTextArea.getText().equals("Error: Division by zero") ||
                    subTextArea.getText().equals("Error: Invalid expression")) {
                subTextArea.setText("");
            }
            String currentText = mainTextField.getText();
            if (!currentText.isEmpty() && !currentText.contains(" ") && isResult(currentText)) {
                mainTextField.setText("");
                subTextArea.setText("");
                currentText = "";
            }
            if(currentText.isEmpty() || currentText.endsWith(" ")) {
                mainTextField.setText(currentText.concat("0."));
            }
            else if(!currentText.substring(currentText.lastIndexOf(" ") + 1).contains(".")) {
                mainTextField.setText(currentText.concat("."));
            }
            return;
        }

        // Handle tombol clear (CL)
        if(e.getSource() == clrButton) {
            if (subTextArea.getText().equals("Error: Division by zero") ||
                    subTextArea.getText().equals("Error: Invalid expression")) {
                subTextArea.setText("");
            }
            String currentText = mainTextField.getText();
            if(!currentText.isEmpty()) {
                if(currentText.endsWith(" ")) {
                    mainTextField.setText(currentText.substring(0, currentText.length() - 3));
                } else {
                    mainTextField.setText(currentText.substring(0, currentText.length() - 1));
                }
            }
            return;
        }

        // Handle tombol all clear (AC)
        if(e.getSource() == aclButton) {
            mainTextField.setText("");
            subTextArea.setText("");
            return;
        }

        // Handle tombol operator (+, -, ×, ÷, %)
        if(e.getSource() == addButton || e.getSource() == subButton ||
                e.getSource() == mulButton || e.getSource() == divButton ||
                e.getSource() == modButton) {
            if (subTextArea.getText().equals("Error: Division by zero") ||
                    subTextArea.getText().equals("Error: Invalid expression")) {
                subTextArea.setText("");
            }
            String currentText = mainTextField.getText();
            if(currentText.isEmpty()) return;
            if(currentText.endsWith(" ")) return;
            if(currentText.contains(" ")) return;

            String operator = e.getSource() == addButton ? " + " :
                    e.getSource() == subButton ? " - " :
                            e.getSource() == mulButton ? " × " :
                                    e.getSource() == divButton ? " ÷ " : " % ";
            mainTextField.setText(currentText.concat(operator));
            return;
        }

        // Handle tombol sama dengan (=)
        if(e.getSource() == eqlButton) {
            String expression = mainTextField.getText();
            if(expression.isEmpty() || expression.endsWith(" ")) {
                subTextArea.setText("Error: Invalid expression");
                return;
            }

            try {
                String[] parts = expression.split(" ");
                if(parts.length != 3) {
                    subTextArea.setText("Error: Invalid expression");
                    return;
                }

                double num1 = Double.parseDouble(parts[0]);
                double num2 = Double.parseDouble(parts[2]);
                String operator = parts[1];

                double result;

                switch (operator) {
                    case "+" -> result = num1 + num2;
                    case "-" -> result = num1 - num2;
                    case "×" -> result = num1 * num2;
                    case "÷" -> {
                        if(num2 == 0) {
                            subTextArea.setText("Error: Division by zero");
                            return;
                        }
                        result = num1 / num2;
                    }
                    case "%" -> result = num1 % num2;
                    default -> {
                        subTextArea.setText("Error: Invalid expression");
                        return;
                    }
                }

                String formattedResult = result % 1 == 0 ?
                        String.format("%.0f", result) :
                        String.format("%.8f", result).replaceAll("0*$", "").replaceAll("\\.$", "");
                subTextArea.setText(expression);
                mainTextField.setText(formattedResult);

            } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                subTextArea.setText("Error: Invalid expression");
            }
        }
    }

    // Mengecek apakah text adalah hasil kalkulasi sebelumnya
    private boolean isResult(String text) {
        try {
            Double.parseDouble(text);
            return !subTextArea.getText().isEmpty() &&
                    !subTextArea.getText().startsWith("Error");
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Main method untuk menjalankan aplikasi
    public static void main(String[] args) {
        new Main();
    }
}