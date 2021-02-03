package cursojava.thread;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaTimeThread extends JDialog {

	
	private JPanel jPanel = new  JPanel(new GridBagLayout());
	private JLabel descricaoHora = new JLabel("Nome");
	private JTextField mostraTempo = new JTextField();
	
	private JLabel descricaoHora2 = new JLabel("Email");
	private JTextField mostraTempo2 = new JTextField();
	
	
	private JButton jButton = new JButton("Gerar Lote");
	private JButton jButton2 = new JButton("Stop");

	
	
	private implementacaoFilaThread fila = new implementacaoFilaThread();      
	
	public TelaTimeThread() {
        	
        
        	setTitle("Minha tela de time com Thread");
        	setSize(new Dimension(240, 240));
        	setLocationRelativeTo(null);
        	setResizable(false);
        	
        	
        	GridBagConstraints  gridBagConstraints = new GridBagConstraints();
        	gridBagConstraints.gridx = 0;
        	gridBagConstraints.gridy = 0;
        	gridBagConstraints.gridwidth = 2;
        	gridBagConstraints.anchor = GridBagConstraints.WEST;
        	gridBagConstraints.insets = new Insets(5, 10, 5, 5);
        	
        	
        	
        	descricaoHora.setPreferredSize( new Dimension(200, 25));
        	jPanel.add(descricaoHora, gridBagConstraints);
        	
        	mostraTempo.setPreferredSize(new Dimension(200, 25));
        	gridBagConstraints.gridy++;
        	jPanel.add(mostraTempo, gridBagConstraints);
        	
        	descricaoHora2.setPreferredSize(new Dimension(200, 25));
        	gridBagConstraints.gridy++;
        	jPanel.add(descricaoHora2, gridBagConstraints);
        	
        	mostraTempo2.setPreferredSize(new Dimension(200, 25));
        	gridBagConstraints.gridy++;
        	jPanel.add(mostraTempo2, gridBagConstraints);
        	
        	gridBagConstraints.gridwidth = 1;
        	
        	
        	jButton.setPreferredSize(new Dimension(92, 25));
        	gridBagConstraints.gridy++;
        	jPanel.add(jButton, gridBagConstraints);
        	
        	
        	jButton2.setPreferredSize(new Dimension(92, 25));
        	gridBagConstraints.gridx++;
        	jPanel.add(jButton2, gridBagConstraints);
        	
        	
        	jButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(fila == null) {
					fila = new implementacaoFilaThread();
					fila.start();		
					}
					
					for(int qtd = 0; qtd < 100; qtd++) {
					ObjetoFilaThread filaThread = new ObjetoFilaThread();	
				   filaThread.setNome(mostraTempo.getText());	
				   filaThread.setEmail(mostraTempo2.getText() + " - " + qtd);
				   
				   fila.add(filaThread);
					}
				}
			});
        	
        	jButton2.addActionListener(new ActionListener() {
				
				
				@Override
				public void actionPerformed(ActionEvent e) {
					fila.stop();
					fila = null;
				}
			});
        		
        	
        	
        	
            fila.start();
        	add(jPanel, BorderLayout.WEST);
        	
        	
        	
        	
        	setVisible(true);
  
        }
		


}
