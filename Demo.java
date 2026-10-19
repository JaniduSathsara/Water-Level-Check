import javax.swing.*;
import java.awt.*;

class AlarmDisplay extends JFrame {

    private JLabel lblTitle;
    private JPanel devicePanel;
    private JButton btnAddDevice;
    int deviceCount;

    AlarmDisplay() {

        setSize(400,300);
        setTitle("Control Room");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));

        lblTitle = new JLabel("Water Level Alarm System");
        lblTitle.setFont(new Font("",1,20));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);

        add(lblTitle,BorderLayout.NORTH);

        devicePanel = new JPanel();
        devicePanel.setLayout(new GridLayout(0,1));

        add(new JScrollPane(devicePanel),BorderLayout.CENTER);

        btnAddDevice = new JButton("Add Device");
        add(btnAddDevice, BorderLayout.SOUTH);

        btnAddDevice.addActionListener(e -> {
            deviceCount++;
            String deviceId = "S00"+deviceCount;

            JLabel lblDevice = new JLabel(deviceId);
            lblDevice.setFont(new Font("",1,15));

            JButton btnOpen = new JButton("Open Device");

            JPanel row = new JPanel(new BorderLayout(10,5));
            row.add(lblDevice, BorderLayout.CENTER);
            row.add(btnOpen, BorderLayout.EAST);

            devicePanel.add(row);
            devicePanel.revalidate();
            devicePanel.repaint();

            btnOpen.addActionListener(event -> new DeviceWindow());
        });

        setVisible(true);
    }
}

class DeviceWindow extends JFrame {

    private JLabel lblTitle;
    private JLabel lblStatus;
    private JLabel lblAlarm;
    private JSlider slider;

    DeviceWindow (){

        setTitle("Device");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        lblTitle = new JLabel("Water Level: 10%");
        lblTitle.setFont(new Font("",1, 20));
        lblTitle.setHorizontalAlignment(JLabel.CENTER);

        add(lblTitle, BorderLayout.NORTH);

        slider = new JSlider(0,100);

        JPanel centerPanel = new JPanel(new GridLayout(0, 1));

        centerPanel.add(slider);

        lblStatus = new JLabel("Water Level Normal");
        lblStatus.setHorizontalAlignment(JLabel.CENTER);
        lblStatus.setFont(new Font("",1, 16));

        lblAlarm = new JLabel("Alarm: OFF");
        lblAlarm.setHorizontalAlignment(JLabel.CENTER);

        centerPanel.add(lblStatus);
        centerPanel.add(lblAlarm);

        add(centerPanel,BorderLayout.CENTER);

        slider.addChangeListener(e -> {
		int level = slider.getValue();

		lblTitle.setText("Water Level: " + level + "%");

		if (level >= 75) {
			lblStatus.setText("HIGH WATER LEVEL!");
			lblStatus.setForeground(Color.RED);

			lblAlarm.setText("Alarm: ON");
			lblAlarm.setForeground(Color.RED);

		} else if (level >= 60) {
			lblStatus.setText("HIGH WATER LEVEL!");
			lblStatus.setForeground(Color.RED);

			lblAlarm.setText("Alarm: OFF");
			lblAlarm.setForeground(Color.BLACK);

		} else {
			lblStatus.setText("Water Level Normal");
			lblStatus.setForeground(Color.GREEN.darker());

			lblAlarm.setText("Alarm: OFF");
			lblAlarm.setForeground(Color.BLACK);
		}
	});

        setVisible(true);
    }
}

class Demo {
    public static void main(String[] args) {
        new AlarmDisplay();
    }
}
