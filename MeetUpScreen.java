import javax.swing.*;
import java.awt.event.*;
public class MeetUpScreen extends JFrame
{
  private JSlider slider;
  private JTextField firstAddress;
  private JTextField secondAddress;
  //private Controller controller;
  /*
   * Very basic start to screen
   * Features 2 textFields the user will use to give the two desired endpoints(addresses)
   * A Calculate or Midpoint button that will give the controller the class both addresses and find the midpoint
   * This is merely an outline so the appearance and number of features is not final
   */
  public MeetUpScreen()
  {
    setTitle("Meet Up");
    setSize(400,400);
    /*
    slider = new JSlider(0,6,120);
    slider.setPaintTrack(true); 
        slider.setPaintTicks(true); 
        slider.setPaintLabels(true); 
  
        // set spacing 
        slider.setMajorTickSpacing(50); 
        slider.setMinorTickSpacing(5);
        */
    
    firstAddress = new JTextField(10);
    secondAddress = new JTextField(10);
    JButton midPointButton = new JButton("Midpoint");
    /* This occurs when the button is pressed
     */
    midPointButton.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent e) {
        /*
         * Button will give the controller the two address in both textFields and call its run method
        controller.setAddress(firstAddress.getText(),1);
        controller.setAdress(secondAddress.getText(),2);
        controller.run();
        */
        // Printers for testing
        if(firstAddress.getText().equals("") || secondAddress.getText().equals(""))
          System.out.println("Please enter both addresses!");
        else
        {
          System.out.println("First Address: "+firstAddress.getText());
          System.out.println("Second Address: "+secondAddress.getText());
        }
      }
    });
    JPanel panel = new JPanel();
    //midPointButton.setBounds(800, 800, 200, 100);
    panel.setLayout(null);
    midPointButton.setBounds(150, 100, 100, 60);
    firstAddress.setBounds(30,50,100,60);
    secondAddress.setBounds(270,50,100,60);
    panel.add(midPointButton);
    panel.add(firstAddress);
    panel.add(secondAddress);
   // panel.add(slider);
    this.getContentPane().add(panel);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    
  }
  public static void main(String[] args)
  {
    MeetUpScreen screen = new MeetUpScreen();
  } 
  
}