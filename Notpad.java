
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

public class Notpad implements ActionListener 
{

    JFrame jf, replaceFrame,font_frame,dev_info_Frame,not_info_Frame;
    JMenuBar menubar;
    JMenu file, edit, format, help;
    JMenuItem neww, open, save, saveas, exit, page_setup, print;
    JMenuItem cut, copy, paste, date_time, replace;
    JMenuItem open_font_menu, font_color, text_areacolor;
    JCheckBoxMenuItem word_wrap;
    JMenuItem developer_info, notpad_info;

    JTextArea textarea;
    JFileChooser filechooser;

    JLabel jl1, jl2;
    JLabel devName,comName,devText,comText;
    JLabel appName,textName,version,verName,techUse,techText;
    JTextField jt1, jt2;
    JButton jb1,ok;
    JComboBox cb_font_families,cb_font_style,cb_font_size;
    String title = "Untitled - Notpad";
    File filee;

    public Notpad()
    {
        try {
                 //  UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                     // UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                  // UIManager.setLookAndFeel("fully qualified name of look and feel");
                   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            } 
        catch (Exception e) {
            System.out.println(e);
        }
       // Image icon=(new ImageIcon(getClass().getResource("C:\\Users\\HP\\OneDrive\\Documents\\NetBeansProjects\\NotpadEditor\\src\\Screenshot (1).png")).getImage());
       // jf.setIconImage(icon);

        jf = new JFrame(title);
        jf.setSize(500, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       //  Image icon=(new ImageIcon(getClass().getResource("C:\\Users\\HP\\OneDrive\\Documents\\NetBeansProjects\\NotpadEditor\\src\\Screenshot (1).png")).getImage());
       // jf.setIconImage(icon);
        menubar = new JMenuBar();


        //---------------------------------------For file menu --------------------------------------------
        file = new JMenu("File");

        neww = new JMenuItem("New");
        neww.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
        neww.addActionListener(this);
        file.add(neww);

        open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
        open.addActionListener(this);
        file.add(open);

        save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
        save.addActionListener(this);
        file.add(save);

        saveas = new JMenuItem("Save As");
        saveas.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.SHIFT_DOWN_MASK));
        saveas.addActionListener(this);
        file.add(saveas);

        file.addSeparator();

        page_setup = new JMenuItem("Page Setup");
        page_setup.addActionListener(this);
        file.add(page_setup);

        print = new JMenuItem("Print....");
        print.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_DOWN_MASK));
        print.addActionListener(this);
        file.add(print);

        file.addSeparator();

        exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke('E', InputEvent.CTRL_DOWN_MASK));
        exit.addActionListener(this);
        file.add(exit);

        menubar.add(file);

        // -----------------------------------For Edit Menu -----------------------------------
        edit = new JMenu("Edit");

        cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke('X',InputEvent.CTRL_DOWN_MASK));
        cut.addActionListener(this);
        edit.add(cut); 

        copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke('C',InputEvent.CTRL_DOWN_MASK));
        copy.addActionListener(this);
        edit.add(copy);

        paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke('V',InputEvent.CTRL_DOWN_MASK));
        paste.addActionListener(this);
        edit.add(paste);

        replace = new JMenuItem("Replace");
        replace.setAccelerator(KeyStroke.getKeyStroke('R',InputEvent.CTRL_DOWN_MASK));
        replace.addActionListener(this);
        edit.add(replace);

        date_time = new JMenuItem("Date/Time");
        date_time.addActionListener(this);
        edit.add(date_time);

        menubar.add(edit);
        // -----------------------------------For  Format Menu .-----------------------------------
        format = new JMenu("Format");

        word_wrap=new JCheckBoxMenuItem("Word Wrap");
        word_wrap.addActionListener(this);
        format.add(word_wrap);
        
        open_font_menu = new JMenuItem("Font");
        open_font_menu.addActionListener(this);
        format.add(open_font_menu);

        font_color = new JMenuItem("Font Colour");
        font_color.addActionListener(this);
        format.add(font_color);

        text_areacolor = new JMenuItem("Text Background Color");
        text_areacolor.addActionListener(this);
        format.add(text_areacolor);

        menubar.add(format);

        // ---------------------------------For Help Menu On Menubar-----------------
        help = new JMenu("Help");

        // Work on this part letter create another jframe and gives the information about notpad version ,  developer name .
        developer_info = new JMenuItem("Developer Info");
        developer_info.addActionListener(this);
        help.add(developer_info);

        notpad_info = new JMenuItem("Notpad Info");
        notpad_info.addActionListener(this);
        help.add(notpad_info);

        menubar.add(help);

        jf.setJMenuBar(menubar);

        // for the writting part we have add the text area .
        textarea = new JTextArea();
        JScrollPane scrollpane = new JScrollPane(textarea);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jf.add(scrollpane);

        jf.setVisible(true);
    }

   /* public static void main(String[] args) {
        new Notpad();
    }*/

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == neww)
        {
            NewNotpad();
        }
        if (e.getSource() == exit)
        {
            System.exit(0);
        }
        if (e.getSource() == save)
        {
            saveas();
        }
        if (e.getSource() == open) 
        {
            open();
        }
        if (e.getSource() == saveas)
        {
            saveas();
        }
        if (e.getSource() == page_setup)
        {
            pageSetup();
        }
        if (e.getSource() == print)
        {
            printEntirePage();
        }
        if (e.getSource() == cut) 
        {
            textarea.cut();
        }
        if (e.getSource() == copy)
        {
            textarea.copy();
        }
        if (e.getSource() == paste)
        {
            textarea.paste();
        }
        if (e.getSource() == date_time)
        {
            setDateTime();
        }
        if (e.getSource() == replace) 
        {
            replaceFrame();
        }
        if (e.getSource() == jb1)
        {
            replace();
        }
        if(e.getSource()==word_wrap) 
        {
            boolean b =word_wrap.getState();
            textarea.setLineWrap(b);
            
        }
        if (e.getSource() == font_color) 
        {
            setFontColor();
        }
        if (e.getSource() == text_areacolor)
        {
            setTextareaColor();
        }
        if(e.getSource()==open_font_menu)
        {
            openFontFrame();
        }
        if(e.getSource()==ok)
        {
             setFontToNotpad();
        }
        if(e.getSource()==developer_info)
        {
            setDeveloperInfo();
        }
        if(e.getSource()==notpad_info)
        {
            setNotpadInfo();
        }
    }

    public void NewNotpad() {
        String text = textarea.getText();
        int i = JOptionPane.showConfirmDialog(jf, "Do you want to save this file ?");
        if (!text.equals(" ")) {
            saveas();
            setTitle(title);
            textarea.setText(" ");

        }
    }

    public void save() {
        if (jf.getTitle().equals(title)) //  if out saved title is equals to title = Untitiled - Notpad then it going to call saveas method which means saves it .   
        {
            saveas();
        } else {
            try {
                String text = textarea.getText();
                byte[] b = text.getBytes();
                FileOutputStream fos = new FileOutputStream(filee);
                fos.write(b);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void saveas() {
        try {
            filechooser = new JFileChooser();
            int i = filechooser.showSaveDialog(jf);

            if (i == 0) {
                filee = filechooser.getSelectedFile();
                String text = textarea.getText();
                byte[] b = text.getBytes();
                FileOutputStream fos = new FileOutputStream(filee);
                fos.write(b);
                System.out.println(b);

                setTitle(filee.getName());
                fos.close();
            } else if (i == 1) {
                textarea.setText(" ");          // if  we cancel then current textarea will be null.
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void setTitle(String title) {
        jf.setTitle(title);
    }

    public void open() {

        try {
            int i = filechooser.showOpenDialog(jf);
            if (i == 0) {
                textarea.setText(" ");
                FileInputStream fis = new FileInputStream(filechooser.getSelectedFile());
                int k;
                while ((k = fis.read()) != -1) {
                    textarea.append(String.valueOf((char) k));      // This will typecast the chrachter string into String , for that we use String.valueOf() method .
                }
                fis.close();
                setTitle(filechooser.getSelectedFile().getName());      // this will set the title of open file in the jframe .
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void pageSetup() {
        // ----------------java provides this code ------------------------
        PrinterJob kp = PrinterJob.getPrinterJob();
        PageFormat kpp = kp.pageDialog(kp.defaultPage());

    }

    public void printEntirePage() {
        //------------------This code for printing the page-----------------
        PrinterJob kp = PrinterJob.getPrinterJob();
        if (kp.printDialog()) {
            try {
                kp.print();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void setDateTime() 
    {
        LocalDateTime ldt = LocalDateTime.now();
        String datatime = ldt.format(DateTimeFormatter.ISO_DATE);
        textarea.append(datatime);

    }

    public void replaceFrame()
    {
        replaceFrame = new JFrame("Replace");
        replaceFrame.setSize(500, 300);
        replaceFrame.setLayout(null);

        jl1 = new JLabel("Find_What: ");
        jl1.setBounds(100, 70, 70, 30);
        replaceFrame.add(jl1);

        jt1 = new JTextField();
        jt1.setBounds(200, 70, 190, 30);
        replaceFrame.add(jt1);

        jl2 = new JLabel("Replace With :");
        jl2.setBounds(100, 150, 110, 30);
        replaceFrame.add(jl2);

        jt2 = new JTextField();
        jt2.setBounds(200, 150, 190, 30);
        replaceFrame.add(jt2);

        jb1 = new JButton("Replace");
        jb1.addActionListener(this);
        jb1.setBounds(230, 200, 90, 30);
        replaceFrame.add(jb1);

        replaceFrame.setVisible(true);
    }

    public void replace() {
        String Find_What = jt1.getText();
        String Replace_With = jt2.getText();

        String text = textarea.getText();        // with the help of this method we get the entired text from the textfield.
        String new_text = text.replace(Find_What, Replace_With);
        textarea.setText(new_text);
        replaceFrame.setVisible(false);
    }

    public void setFontColor() {
        Color c = JColorChooser.showDialog(jf, "Select Font Colour", Color.darkGray);
        textarea.setForeground(c);
    }

    public void setTextareaColor() {
        Color C = JColorChooser.showDialog(jf, "Select Textarea Colour", Color.black);
        textarea.setBackground(C);
    }
    
    public void openFontFrame()
    {
             font_frame=  new JFrame("--Fonts--");      
             font_frame.setSize(600,300);
             font_frame.setLayout(null);
             
             String fonts[] = 
             GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
             cb_font_families=new JComboBox(fonts);
             cb_font_families.setBounds(40,70,100,30);
             font_frame.add(cb_font_families);
             
             String[] font_style={"Plain","Bold","Italic"};
             cb_font_style=new JComboBox(font_style);
             cb_font_style.setBounds(220,70,100,30);
             font_frame.add(cb_font_style);
             
             Integer[] font_size={8,10,12,14,16,18,20,22,24,26,28,30,32,34,36,38,40,42,44,46,48,50,52,54,56,58,60};
             cb_font_size=new JComboBox(font_size);
             cb_font_size.setBounds(400,70,100,30);
             font_frame.add(cb_font_size);
             
             ok=new JButton("Ok");
             ok.addActionListener(this);
             ok.setBounds(270,200,50,30);
             font_frame.add(ok);
             
             font_frame.setVisible(true);
    }
    public void setFontToNotpad()
    {
        String font_family=(String) cb_font_families.getSelectedItem();
        String font_style=(String)   cb_font_style.getSelectedItem();
        Integer font_size=(Integer) cb_font_size.getSelectedItem();
        int font_styl=0;                         // java provides already  some value for bold , italic ,plain.
        if(font_style.equals("Plain"))
        {
            font_styl=Font.PLAIN;
            System.out.println(font_style);
        }
        else if(font_style.equals("Bold"))
        {
            font_styl=Font.BOLD;
        }
        else if(font_style.equals("Italic"))
        {
            font_styl=Font.ITALIC;
        }
        Font f=new Font(font_family, font_styl, font_size);
        textarea.setFont(f);
        
        font_frame.setVisible(false);
        
    }
     
    public void   setDeveloperInfo()
    {
        dev_info_Frame=new JFrame("Developer Information");
        dev_info_Frame.setSize(500,200);
        dev_info_Frame.setBackground(Color.darkGray);
        dev_info_Frame.setLayout(null);
        
         devName=new JLabel("Developer Name : ");
         devName.setFont(new Font("Arial",1,16));
         devName.setBounds(50,50,140,30);
         dev_info_Frame.add(devName);
         
         String name="KUNAL WADHAI";
         devText=new  JLabel(name);
         devText.setForeground(Color.darkGray);
         devText.setFont(new Font("Arial",1,16));
         devText.setBounds(200,50,200,30);
         dev_info_Frame.add(devText);
         
          comName=new JLabel("Company Name : ");
         comName.setFont(new Font("Arial",1,16));
         comName.setBounds(50,110,140,30);
         dev_info_Frame.add(comName);
         
         String name_="KWTechnology.Pvt.Ltd";
         comText=new  JLabel(name_);
         comText.setFont(new Font("Arial",1,16));
         comText.setForeground(Color.darkGray);
         comText.setBounds(200,110,200,30);
         dev_info_Frame.add(comText);
        
        dev_info_Frame.setVisible(true);
                
   
    }
     public void   setNotpadInfo()
     {
         not_info_Frame=new JFrame("Notpad Information");
         not_info_Frame.setSize(500,320);
         not_info_Frame.setLayout(null);
         
        appName=new JLabel("Application Name : ");
        appName.setFont(new Font("Arial",1,18));
        appName.setBounds(50,50,180,30);
        not_info_Frame.add(appName);
        
        textName=new JLabel("Notpad");
        textName.setForeground(Color.darkGray);
        textName.setFont(new Font("Arial",1,18));
        textName.setBounds(240,50,140,30);
        not_info_Frame.add(textName);
        
         version=new JLabel("Version : ");
         version.setFont(new Font("Arial",1,18));
        version.setBounds(140,130,140,30);
        not_info_Frame.add(version);
        
        verName=new JLabel("N.27.07.03");
        verName.setForeground(Color.darkGray);
        verName.setFont(new Font("Arial",1,18));
        verName.setBounds(230,130,140,30);
        not_info_Frame.add(verName);
        
         techUse=new JLabel("Technology Use : ");
         techUse.setFont(new Font("Arial",1,18));
        techUse.setBounds(70,210,180,30);
        not_info_Frame.add(techUse);
        
        techText=new JLabel("J2SE(JDK.1.8.0)");
        techText.setForeground(Color.darkGray);
        techText.setFont(new Font("Arial",1,18));
        techText.setBounds(250,210,140,30);
        not_info_Frame.add(techText);
        
        
         
         not_info_Frame.setVisible(true);
     }
}
