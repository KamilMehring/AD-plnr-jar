

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class ListaTematow extends JPanel implements ActionListener, ListSelectionListener {//, FocusListener{//

	JPanel pnl1, pnl2, pnl3;
	JLabel hrLeft, msgs;
	JTextField ent1, ent2, ent3, ent4;
	JButton btnAdd, btnSav, btnExp;
	private String przedmiot;
	private Integer godzPrzedm = 0;
	private Integer godzTemat = 0;
	private Integer editing = -1;
	private ArrayList<Temat> tematy = new ArrayList<Temat>();
	public JList lst;
	public DefaultListModel data;

	ListaTematow(){
		JLabel lbl1, lbl2, lbl3, lbl5, lbl6;
		setLayout( new BoxLayout(this,BoxLayout.PAGE_AXIS));
		pnl1 = new JPanel();
		//pnl1.setAlignmentX(Component.LEFT_ALIGNMENT);
		pnl1.setPreferredSize(new Dimension(0,10));
		JPanel msgPnl = new JPanel();
		msgPnl.setPreferredSize(new Dimension(0,0));
		msgs = new JLabel("");
		msgPnl.add(msgs);
		lbl1 = new JLabel("Liczba godzin");
		ent1 = new JTextField("",3);
		lbl2 = new JLabel("Przedmiot");
		ent2 = new JTextField("",20);
		lbl3 = new JLabel("Pozostało godzin");
		hrLeft = new JLabel("-");
		ent1.addFocusListener(new FocusAdapter(){
			public void focusGained(FocusEvent e){
				displayTooltip("Liczba godzin w całym cyklu kształcenia");
			}
			public void focusLost(FocusEvent e){
				Boolean isZero = (godzPrzedm==0) ? true : false;
				try {
					godzPrzedm = Integer.parseInt(ent1.getText());
				} catch(NumberFormatException ex){
					godzPrzedm = 0;
				}
				if(isZero.equals(true))
					hrLeft.setText(godzPrzedm.toString());
				else
					countHoursLeft(0);
				displayTooltip("");
			}
		});
		add(msgPnl);
		pnl1.add(lbl1);
		pnl1.add(ent1);
		pnl1.add(lbl2);
		pnl1.add(ent2);
		pnl1.add(lbl3);
		pnl1.add(hrLeft);
		//pnl1.add(lbl4);
		pnl2 = new JPanel();
		//pnl2.setAlignmentX(Component.LEFT_ALIGNMENT);
		pnl2.setPreferredSize(new Dimension(0,10));
		lbl5 = new JLabel("Liczba godzin");
		ent3 = new JTextField("",3);
		lbl6 = new JLabel("Temat");
		ent4 = new JTextField("",20);
		btnAdd = new JButton("Dodaj");
		btnAdd.addActionListener(this);
		pnl2.add(lbl5);
		pnl2.add(ent3);
		pnl2.add(lbl6);
		pnl2.add(ent4);
		pnl2.add(btnAdd);
		pnl3 = new JPanel();
		lbl6 = new JLabel("Lista tematów");
		btnSav = new JButton("Zapisz");
		btnSav.addActionListener(this);
		btnExp = new JButton("Eksportuj");
		btnExp.addActionListener(this);
		// JLista
		data = new DefaultListModel();
		lst = new JList(data);
		lst.setVisibleRowCount(10);
		lst.addListSelectionListener(this);
		JScrollPane lstScr = new JScrollPane(lst);
		pnl3.add(btnSav);
		pnl3.add(btnExp);
		add(pnl1);
		add(pnl2);
		add(lbl6);
		add(lstScr);
		add(pnl3);
	}

	//private void countHoursLeft(Integer addHours){
		//godzTemat += addHours;
		//Integer pozGodz = godzPrzedm - godzTemat;
		//hrLeft.setText(pozGodz.toString());
	//}
	private void countHoursLeft(Integer addHours){
		Integer pozGodz = 0;
		if( (godzPrzedm - godzTemat - addHours) < 0)
		{	// mniejsze od zera - błąd
			displayTooltip("Błąd: więcej godzin zajęć, niż przewidziano godzin dla przedmiotu");
		} else {
			godzTemat += addHours;
			pozGodz = godzPrzedm - godzTemat;
			hrLeft.setText(pozGodz.toString());
		}
	}
	private Integer saveEntry(){
		Integer godzPrzedm = Integer.parseInt(ent3.getText());
		String temat = ent4.getText();
		Integer id = editing;
		if(editing>=0){
			tematy.get(id).setGodz(godzPrzedm);
			tematy.get(id).setTekst(temat);
		System.out.print("E>");
		System.out.println(tematy.get(id).toString());
		} else {
			id = tematy.size();
			Temat tm = new Temat(id, godzPrzedm, temat);
			tematy.add(tm);
		System.out.print("A+");
		System.out.println(tm.toString());
		}
		return id;
	}
	private void editEntry(Integer id, String item)
	{	// #TODO po edycji należy poprawić liczbę godzin
		editing = id;
		displayTooltip("Edytowana pozycja #"+editing);
		String elem[] = item.split(" \\(");
		Integer godz = Integer.parseInt(elem[1].replace(")",""));
		//godzTemat-= godz;
		ent3.setText(godz.toString());
		ent4.setText(elem[0]);
	}
	
	public void actionPerformed(ActionEvent e){
		Object src = e.getSource();
		if( src == btnAdd )
		{	// dodawanie tematu
			Integer id = saveEntry();	// dodaje/edytuje klasę Temat
			Integer godzPrzedm = tematy.get(id).getGodzin();//Integer.parseInt(ent3.getText());
			String temat = tematy.get(id).getTekst();	//ent4.getText();
			countHoursLeft(godzPrzedm);
			temat+= " ("+godzPrzedm+")";
			if(editing>=0){
				data.setElementAt(temat,editing);
				editing = -1;
			} else
				data.addElement(temat);
			ent3.setText("");	// po dodaniu tematu -> wyczyść z pola
			ent4.setText("");	// po dodaniu tematu -> wyczyść z pola
		}
		if( src == btnSav )
		{	// zapisywanie zmian
			pnl2.setBackground(Color.GREEN);
			Integer godzPrzedm = 0;
			Integer godzTemat = 0;
			godzPrzedm = Integer.parseInt(ent1.getText());
			godzTemat = Integer.parseInt(ent3.getText());
	//godzTemat = 13;//Integer.parseInt(13);
	//		Integer pozGodz = godzPrzedm-godzTemat;
			Integer pozGodz = godzPrzedm-godzTemat;
			System.out.println(pozGodz);
			hrLeft.setText(pozGodz.toString());
		}
		if( src == btnExp )
			pnl3.setBackground(Color.BLUE);
	}
	
	public void valueChanged(ListSelectionEvent e){
		Object src = e.getSource();
		if( src == lst ){
			if( e.getValueIsAdjusting() == false ){
			System.out.println(lst.getSelectedIndex());
				if( lst.getSelectedIndex() == -1 ){
					pnl1.setBackground(Color.RED);
				} else {
					String selItem = data.getElementAt(lst.getSelectedIndex()).toString();
					editEntry(lst.getSelectedIndex(), selItem);
				}
			}
		}
	}

	private void displayTooltip(String value){
		msgs.setText(value);
	}
}
