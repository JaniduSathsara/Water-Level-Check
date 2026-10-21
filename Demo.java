import javax.swing.*;
import java.awt.*;

class ControlCenter extends JFrame {

    private JButton btnAddDevice;
    private JLabel lblTitle;

    ControlCenter() {
        setTitle("Control Center");
        setSize(450, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        lblTitle = new JLabel("Water Level Alarm System");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);

        btnAddDevice = new JButton("Add Device");
        add(btnAddDevice, BorderLayout.CENTER);

        btnAddDevice.addActionListener(e -> {
            new DeviceWindow().setVisible(true);
        });

        setVisible(true);
    }
}

class DeviceWindow extends JFrame {

    private JLabel lblTitle;
    private JSlider slider;
    private JButton btnView;

    private AlertWindow alertWindow;
    private AlarmWindow alarmWindow;

    DeviceWindow() {
        setTitle("Device");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        lblTitle = new JLabel("Water Level: 10%");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);


        JPanel pnl1 = new JPanel(new GridLayout(4, 1, 5, 5));
        
        slider = new JSlider(0, 100, 10);
        pnl1.add(slider);
        
        btnView = new JButton("View Device");
        pnl1.add(btnView);
        
        add(pnl1,BorderLayout.CENTER);
        
        alertWindow = new AlertWindow(slider.getValue());
        alarmWindow = new AlarmWindow(slider.getValue());

        btnView.addActionListener(e -> {
            alertWindow.setVisible(true);
            alarmWindow.setVisible(true);
        });

        slider.addChangeListener(e -> {
            int level = slider.getValue();
            lblTitle.setText("Water Level: "+level+"%");

            alertWindow.updateLevel(level);
            alarmWindow.updateLevel(level);
        });

        setVisible(true);
    }
}

class AlertWindow extends JFrame {

    private JLabel lblTitle;
    private JLabel lblStatus;

    AlertWindow(int level) {
        setTitle("Water Level Alert");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1));

        lblTitle = new JLabel();
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setFont(new Font("",1, 20));
		add(lblTitle);

        lblStatus = new JLabel();
        lblStatus.setHorizontalAlignment(JLabel.CENTER);
        lblStatus.setFont(new Font("",1, 16));
        add(lblStatus);

        updateLevel(level);
    }

    void updateLevel(int level) {
        if (level >= 75) {
            lblStatus.setText("HIGH WATER LEVEL!");
            lblStatus.setForeground(Color.RED);
        } else {
            lblStatus.setText("Water Level Normal");
            lblStatus.setForeground(Color.GREEN);
        }
    }
}

class AlarmWindow extends JFrame {

    private JLabel lblTitle;
    private JLabel lblStatus;

    AlarmWindow(int level) {
        setTitle("Water Level Alarm");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1));

        lblTitle = new JLabel();
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));

        lblStatus = new JLabel();
        lblStatus.setHorizontalAlignment(JLabel.CENTER);
        lblStatus.setFont(new Font("Arial", Font.BOLD, 16));

        add(lblTitle);
        add(lblStatus);

        updateLevel(level);
    }

    void updateLevel(int level) {
        if (level >= 80) {
            lblStatus.setText("Alarm ON!");
            lblStatus.setForeground(Color.RED);
        } else {
            lblStatus.setText("Alarm OFF!");
            lblStatus.setForeground(Color.GREEN.darker());
        }
    }
}

class Demo {
    public static void main(String[] args) {
       new ControlCenter();
    }
}
