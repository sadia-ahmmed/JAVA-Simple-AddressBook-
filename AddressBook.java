package Simple_AddressBook;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;


public class AddressBook
{
    JFrame frame;

    JPanel main, panel1, panel2, panel3, panel4, panel5, panel6;

    JLabel nameL,  emailL, addressL, phoneNumberL;

    JTextField nameT,  emailT, addressT, phoneNumberT;

    JTextArea area;

    JButton addBtn, searchBtn, viewBtn,  editBtn;

    ArrayList<Persons> contact;


    AddressBook()
    {

        //arraylist
        contact = new ArrayList<Persons>();

        // Initialize
        frame = new JFrame("Address Book");
        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();
        panel4 = new JPanel();
        panel5 = new JPanel();
        panel6 = new JPanel();
        main = new JPanel();

        //text fields

        nameT = new JTextField(10);
        emailT = new JTextField(10);
        addressT = new JTextField(10);
        phoneNumberT = new JTextField(10);
        nameT.setBackground(Color.white);

        emailT.setBackground(Color.white);
        addressT.setBackground(Color.white);
        phoneNumberT.setBackground(Color.white);
        area = new JTextArea(20, 50);
        area.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //label

        nameL = new JLabel("    Enter Name: ");
        nameL.setFont(new Font(Font.SERIF, Font.BOLD, 18));
        emailL = new JLabel("    Enter Email: ");
        emailL.setFont(new Font(Font.SERIF, Font.BOLD, 18));
        addressL = new JLabel("    Enter Address: ");
        addressL.setFont(new Font(Font.SERIF, Font.BOLD, 18));
        phoneNumberL = new JLabel("    Enter Phone Number: ");
        phoneNumberL.setFont(new Font(Font.SERIF, Font.BOLD, 18));

        //buttons
        addBtn = new JButton("Add");
        addBtn.setFont(new Font(Font.SERIF, Font.BOLD, 28));
        searchBtn = new JButton("Search");
        searchBtn.setFont(new Font(Font.SERIF, Font.BOLD, 28));
        viewBtn = new JButton("View");
        viewBtn.setFont(new Font(Font.SERIF, Font.BOLD, 28));
        editBtn = new JButton("Edit");
        editBtn.setFont(new Font(Font.SERIF, Font.BOLD, 28));

        //panel
        panel1.add(nameL);
        panel1.add(emailL);
        panel1.add(addressL);
        panel1.add(phoneNumberL);
        panel1.setLayout(new GridLayout(4, 1));

        panel2.add(nameT);
        panel2.add(emailT);
        panel2.add(addressT);
        panel2.add(phoneNumberT);
        panel2.setLayout(new GridLayout(4, 1));


        panel3.add(panel1);
        panel3.add(panel2);
        panel3.setLayout(new GridLayout(1, 2));


        panel4.add(addBtn);
        panel4.add(editBtn);
        panel4.add(searchBtn);
        panel4.add(viewBtn);
        panel4.setLayout(new FlowLayout());

        panel5.add(scrollPane);

        // panel 6 with 3 and 4
        panel6.add(panel3);
        panel6.add(panel4);
        GridLayout l = new GridLayout(2, 0);
        panel6.setLayout(l);

        main.add(panel6);
        main.add(panel5);
        main.setLayout(new GridLayout(2, 0));

        frame.add(main);
        frame.setSize(550, 750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        addBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                addContact();
            }
        });

        editBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                editContact();
            }
        });

        searchBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String name = JOptionPane.showInputDialog(null, "Enter Name to search contact: ");
                int s = searchContact(name);

                if(s == -1)
                    JOptionPane.showMessageDialog(null, "404 Error! No record found.",
                            "ERROR!", JOptionPane.ERROR_MESSAGE);

                else
                {
                    nameT.setText(contact.get(s).name);
                    emailT.setText(contact.get(s).email);
                    addressT.setText(contact.get(s).address);
                    phoneNumberT.setText(contact.get(s).phoneNumber);
                }
            }
        });

        viewBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                viewContact();
            }
        });

    }

    void addContact()
    {
        String name = nameT.getText();

        String email = emailT.getText();

        String address = addressT.getText();

        String phoneNumber = phoneNumberT.getText();

        contact.add(new Persons(name,  email, address, phoneNumber));

        JOptionPane.showMessageDialog(null, "Contact added successfully",
                "ADDITION SUCCESSFUL", JOptionPane.INFORMATION_MESSAGE);
    }

    void editContact() {

        String f = JOptionPane.showInputDialog(null, "Enter Name to edit: ");

        int i = searchContact(f);

        if(i == -1)
            JOptionPane.showMessageDialog(null, " 404 Error! No record found on name: " + f,
                    "ERROR!", JOptionPane.WARNING_MESSAGE);
        else {

            int choice = Integer.parseInt(JOptionPane.showInputDialog(null, " 1. Email \n 2. Address \n " +
                    "3. Phone number" + "\n Enter your choice: "));
            if(choice == 1)
            {
                String data = JOptionPane.showInputDialog(null, "Update Email address: ");
                contact.get(i).setEmail(data);
            }

            else if(choice == 2)
            {
                String data = JOptionPane.showInputDialog(null, "Update Address : ");
                contact.get(i).setAddress(data);
            }

            else if(choice == 3)
            {
                String data = JOptionPane.showInputDialog(null, "Update Phone Number:  ");
                contact.get(i).setPhoneNumber(data);
            }

            else
                JOptionPane.showMessageDialog(null, "Invalid choice! ",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
        }}

    void viewContact()
    {
        area.setText("");
        //Sorting

        Collections.sort(contact);

        // Add the contact to text area

        for (Persons personInfo : contact) area.setText(area.getText() + personInfo);
    }

    int searchContact(String s)
    {

        for(int c = 0; c < contact.size(); c++)

            if(s.equalsIgnoreCase(contact.get(c).name))
                return c;

        return -1;
    }


    public static void main(String[] args)
    {
        new AddressBook();
    }
}