import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class DadJokeAppTwo extends JFrame implements ActionListener
{
   private static final long serialVersionUID = 1L;
   private JButton goodBtn;
   private JButton badBtn;
   private JButton jokeBtn;
   private JLabel goodLabel;
   private JLabel badLabel;
   private JTextArea textArea;
   public DadJoke j;

   public DadJokeAppTwo() 
   {
      super("Dad Joke App");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(600, 400);
   
      JPanel panel = new JPanel();
      goodBtn = new JButton("Good");
      goodBtn.addActionListener(this);
      panel.add(goodBtn);
   
      goodLabel = new JLabel("0");
      goodLabel.setPreferredSize(new Dimension(50, 50));
      goodLabel.setHorizontalAlignment(SwingConstants.CENTER);
      panel.add(goodLabel);
   
      badBtn = new JButton("Bad");
      badBtn.addActionListener(this);
      panel.add(badBtn);
   
      badLabel = new JLabel("0");
      badLabel.setPreferredSize(new Dimension(50, 50));
      badLabel.setHorizontalAlignment(SwingConstants.CENTER);
      panel.add(badLabel);
   
      jokeBtn = new JButton("Get Joke");
      jokeBtn.addActionListener(this);
      panel.add(jokeBtn);
   
      textArea = new JTextArea();
      getContentPane().add(panel, BorderLayout.NORTH);
      getContentPane().add(textArea, BorderLayout.CENTER);
      setVisible(true);
   }

   public static void main(String[] args) 
   {
      new DadJokeAppTwo();
   }

   @Override
   public void actionPerformed(ActionEvent e) 
   {
      if (e.getSource() == jokeBtn) 
      {
         try
         {
            // Set up the URL for the API endpoint
            URL url = new URL("https://icanhazdadjoke.com/");
         
            // Set up the HTTP connection
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
         
            // Read the response from the API
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) 
            {
               response.append(inputLine);
            }
            in.close();
            Gson gson = new Gson();      
            j = gson.fromJson(response.toString(), DadJoke.class);
         
         
            // Display the random dad joke
            textArea.setText(j.joke);
         } catch (IOException ex) {
            ex.printStackTrace();
         }
      } else if (e.getSource() == goodBtn) {
         int currentGoodCount = Integer.parseInt(goodLabel.getText());
         currentGoodCount++;
         goodLabel.setText(Integer.toString(currentGoodCount));
      } else if (e.getSource() == badBtn) {
         int currentBadCount = Integer.parseInt(badLabel.getText());
         currentBadCount++;
         badLabel.setText(Integer.toString(currentBadCount));
      }
   }
}
