package textEditor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class Notes {

	private JFrame frmNotes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notes window = new Notes();
					window.frmNotes.setResizable(false);
					window.frmNotes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/***
	 * Variable declaration
	 */
	JScrollPane scrollPane;
	JEditorPane editorPane;
	private JLabel wordsLabel;
	private JLabel linesLabel;
	
	JFileChooser filechooser;
	
	/**
	 * Create the application.
	 */
	public Notes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNotes = new JFrame();
		frmNotes.setTitle("Notes");
		frmNotes.setResizable(false);
		frmNotes.getContentPane().setLayout(null);
		frmNotes.setBounds(450, 120, 500, 450);
		frmNotes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		/***
		 * MENU
		 */
		JMenuBar menuBar = new JMenuBar();
		frmNotes.setJMenuBar(menuBar);
		
		JMenu jmFile = new JMenu("File");
		menuBar.add(jmFile);
		
		JMenuItem jmiNew = new JMenuItem("New", KeyEvent.VK_N);
		jmiNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		jmFile.add(jmiNew);
		jmiNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editorPane.setText(null);
				//main(null);
			}
		});
		
		JMenuItem jmiOpen = new JMenuItem("Open", KeyEvent.VK_O);
		jmFile.addSeparator();
		jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		jmFile.add(jmiOpen);
		jmiOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 filechooser = new JFileChooser();
                 if(filechooser.showOpenDialog(frmNotes) == JFileChooser.APPROVE_OPTION){
                	 	try{
                	 			FileInputStream fistream = new FileInputStream(filechooser.getSelectedFile());
                	 			BufferedReader br = new BufferedReader(new InputStreamReader(fistream));
                	 			StringBuffer text = new StringBuffer();
                	 			String read;
                	 			while((read = br.readLine()) != null){
                	 				text.append(read).append("\n");
                	 			}
                	 			editorPane.setText(text.toString());
                	 			br.close();
                	 	}
                	 	catch(IOException ie){
                	 		
                	 	}
                	 	finally {
                	 		
                	 	}
              }
				
			}
		});
		
		JMenuItem jmiSaveas = new JMenuItem("Save as..", KeyEvent.VK_S);
		jmFile.addSeparator();
		jmiSaveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		jmFile.add(jmiSaveas);
		jmiSaveas.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				filechooser = new JFileChooser();
				String str = editorPane.getText();
				if(filechooser.showSaveDialog(frmNotes) == JFileChooser.APPROVE_OPTION) {
					try {
						FileWriter fw = new FileWriter(filechooser.getSelectedFile());
						fw.write(str.toString());
						fw.close();
					
					}
					catch(IOException ie) {}
				}
				
			}
		});
		
		/*JMenuItem mntmSaveAs = new JMenuItem("Save as..");
		jmFile.addSeparator();
		jmFile.add(mntmSaveAs);*/
		
		JMenuItem jmiExit = new JMenuItem("Exit", KeyEvent.VK_W);
		jmFile.addSeparator();
		jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_DOWN_MASK));
		jmFile.add(jmiExit);
		jmiExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);				
			}
		});
		
		JMenu jmEdit = new JMenu("Edit");
		menuBar.add(jmEdit);
		
		JMenuItem jmiCopy = new JMenuItem("Copy", KeyEvent.VK_C);
		jmiCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		jmEdit.add(jmiCopy);
		jmiCopy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editorPane.copy();
				
			}
		});
		
		JMenuItem jmiCut = new JMenuItem("Cut", KeyEvent.VK_X);
		jmEdit.addSeparator();
		jmiCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
		jmEdit.add(jmiCut);
		jmiCut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editorPane.cut();
				
			}
		});
		
		JMenuItem jmiPaste = new JMenuItem("Paste", KeyEvent.VK_V);
		jmEdit.addSeparator();
		jmiPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
		jmEdit.add(jmiPaste);
		jmiPaste.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editorPane.paste();
				
			}
		});
		
		JMenu jmSearch = new JMenu("Search");
		menuBar.add(jmSearch);
		jmSearch.setToolTipText("This feature will be available in Notes V1.1. Thank you!");
		
		JMenu jmHelp = new JMenu("Help");
		menuBar.add(jmHelp);
		jmHelp.setToolTipText("About the application");
		
		JMenuItem jmiAbout = new JMenuItem("About");
		jmiAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frmNotes, "Notes V1.0 \n\nBy Everest Inc.", "About", 1);
			}
		});
		jmHelp.add(jmiAbout);
		
		JMenuItem jmiCredits = new JMenuItem("Credits");
		jmHelp.addSeparator();
		jmHelp.add(jmiCredits);
		jmiCredits.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frmNotes, "CEO : Prahlad Panthi\nCTO : Bikalpa Bhattarai\n"
						+ "Senior Engineer : Pratik Adhikari\nSenior Engineer : Ganesh Khatri\n\nEverest Inc.", "Credits", 1);
				
				
			}
		});

		
		// END OF MENU
		
		/***
		 * Editor 
		 */
		
		editorPane = new JTextPane();
		scrollPane = new JScrollPane(editorPane);
		scrollPane.setBounds(0, 0, 498, 370);
		frmNotes.getContentPane().add(scrollPane);
		editorPane.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				String str = editorPane.getText();
				int countLines = 1;
				int count = 0;
				
				//counting number of words
				for(int i = 0; i < str.length(); i++) {
					if((i > 0) && ((str.charAt(i) == ' ' || str.charAt(i) == '\n') && (str.charAt(i-1) != ' ' && str.charAt(i-1) != '\n')))
						count++;
				}
					wordsLabel.setText("Number of words : "+(count));		
				
				// counting number of lines
				for(int j = 0; j < str.length(); j++) {
					if(str.charAt(j) == '\n')
						++countLines;
				}
				linesLabel.setText("Number of lines : "+countLines);
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				String str = editorPane.getText();
				int countLines = 1;
				int count = 0;
				
				// counting number of words
				for(int i = 0; i < str.length(); i++) {
					if((i > 0) && ((str.charAt(i) == ' ' || str.charAt(i) == '\n') && (str.charAt(i-1) != ' ' && str.charAt(i-1) != '\n')))
						count++;
				}
				wordsLabel.setText("Number of words : "+(count));
				
				// counting number of lines
				for(int j = 0; j < str.length(); j++) {
					if(str.charAt(j) == '\n')
						++countLines;
				}
				linesLabel.setText("Number of lines : "+countLines);
			}
		});
		
		wordsLabel = new JLabel("");
		wordsLabel.setBounds(10, 373, 180, 15);
		frmNotes.getContentPane().add(wordsLabel);	
		
		linesLabel = new JLabel("");
		linesLabel.setBounds(306, 373, 180, 15);
		frmNotes.getContentPane().add(linesLabel);
	}
}
