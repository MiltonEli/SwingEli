import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.swing.*;

public class SwingControlDemo implements ActionListener {
    private JFrame mainFrame;
//    private JLabel headerLabel;
//    private JLabel statusLabel;


    private int WIDTH=800;
    private int HEIGHT=700;

    public JPanel P1;
    public JPanel P2;

    public JLabel UrlLabel;
    public JTextField UrlField;
    public JLabel WordLabel;
    public JTextField WordField;
//    public Button Ok;
    public JTextArea ResultsArea;

    public JScrollPane scroll;




    public SwingControlDemo() {
        prepareGUI();
    }

    public static void main(String[] args) {
        SwingControlDemo swingControlDemo = new SwingControlDemo();
        swingControlDemo.showEventDemo();

    }

    private void prepareGUI() {



        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(WIDTH, HEIGHT);

        mainFrame.setLayout(new GridLayout(2,1));

        //inside P1
        P1= new JPanel();
        P1.setLayout(new GridLayout(4,1));
        UrlLabel = new JLabel("     Please input url below:");
        P1.add(UrlLabel);
        UrlField = new JTextField("https://www.milton.edu/");
        P1.add(UrlField);
        WordLabel =new JLabel("     Please input word below:");
        P1.add(WordLabel);
        WordField = new JTextField();
        P1.add(WordField);

        mainFrame.add(P1); //add panel 1 to mainframe grid row 1

        //inside P2
        P2 =new JPanel();
        P2.setLayout(new BorderLayout());
//        Button Ok = new Button("Ok");
//        P2.add(Ok, BorderLayout.NORTH);
        ResultsArea = new JTextArea();
        ResultsArea.setEditable(true);

        scroll =new JScrollPane(ResultsArea);

       // scroll= new JScrollPane(ResultsArea);
//        ResultsArea.add(scroll);
        ResultsArea.setSize(400,400);
        ResultsArea.setLayout(null);




        P2.add(scroll, BorderLayout.CENTER);


        mainFrame.add(P2); //add panel 2 to mainframe grid row 2






//
//        cut = new JMenuItem("cut");
//        copy = new JMenuItem("copy");
//        paste = new JMenuItem("paste");
//        selectAll = new JMenuItem("selectAll");
//        cut.addActionListener(this);
//        copy.addActionListener(this);
//        paste.addActionListener(this);
//        selectAll.addActionListener(this);

//        mb = new JMenuBar();
//        file = new JMenu("File");
//        edit = new JMenu("Edit");
//        help = new JMenu("Help");
//        edit.add(cut);
//        edit.add(copy);
//        edit.add(paste);
//        edit.add(selectAll);
//        mb.add(file);
//        mb.add(edit);
//        mb.add(help);
//        mainFrame.add(mb);
//        mainFrame.setJMenuBar(mb);





//        headerLabel = new JLabel("", JLabel.CENTER);
//        statusLabel = new JLabel("", JLabel.CENTER);
//        statusLabel.setSize(30, 1);
//        ta2 = new JTextArea();
//        ta2.setBounds(50, 100, WIDTH-200, HEIGHT-300);
//        mainFrame.add(ta2);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });



//
//        mainFrame.add(statusLabel);
//        mainFrame.setVisible(true);
    }




    private void showEventDemo() {
//        headerLabel.setText("Control in action: Button");

        JButton goButton = new JButton("OK");

        goButton.setActionCommand("Ok");

        goButton.addActionListener(new ButtonClickListener());

//        panel1.add(goButton);

        P2.add(goButton, BorderLayout.NORTH);

        mainFrame.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == cut)
//            ta.cut();
//        if (e.getSource() == paste)
//            ta.paste();
//        if (e.getSource() == copy)
//            ta.copy();
//        if (e.getSource() == selectAll)
//            ta.selectAll();
    }

    public void HtmlRead() {
        int xResult = 0;
        int yResult = 0;

        try {
            URL url = new URL(UrlField.getText());
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String line;
            while ( (line = reader.readLine()) != null ) {


                if(line.contains("https:")){//&&line.endsWith("' id-'")
                    xResult = line.indexOf("https");
                    System.out.println(xResult);
                    String halfString;
                    halfString = line.substring(xResult,line.length());
                    if (halfString.contains("\"")){
                        yResult = line.indexOf("\"",xResult);
                        System.out.println(yResult);
                    }
                    else{
                        if(halfString.contains("'")){
                            yResult = line.indexOf("'",xResult);
                            System.out.println(yResult);
                        }
                        else{
                            if(halfString.contains("-")){
                                yResult = line.indexOf("-",xResult);
                                System.out.println(yResult);
                            }
                        }
                    }
                    System.out.println(line);
                    String Links = line.substring(xResult,yResult);
                    System.out.println("*****"+ Links);
                    if (Links.contains(UrlField.getText())){
                        ResultsArea.append(Links);
                        ResultsArea.append("\n");
                    }
                }
            }
            reader.close();
        } catch(Exception ex) {
            System.out.println(ex);
        }

    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("Ok")) {
//                statusLabel.setText("Ok Button clicked.");
                System.out.println("Ok button clicked");

                HtmlRead();
            }
//            else if (command.equals("Submit")) {
//                statusLabel.setText("Submit Button clicked.");
//            } else {
//                statusLabel.setText("Cancel Button clicked.");
//            }
        }
    }
}