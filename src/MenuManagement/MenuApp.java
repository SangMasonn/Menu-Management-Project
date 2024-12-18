package MenuManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuApp extends JFrame {

    private ArrayList<MenuItem> menuItems; // Danh sách món ăn
    private JTextField nameField; // Ô nhập tên món ăn
    private JTextField priceField; // Ô nhập giá món ăn
    private JTextArea menuDisplayArea; // Khu vực hiển thị menu

    public MenuApp() {
        menuItems = new ArrayList<>(); // Khởi tạo danh sách món ăn

        setTitle("Menu Manager"); // Tiêu đề cửa sổ
        setSize(500, 400); // Kích thước cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đóng ứng dụng khi tắt cửa sổ
        setLayout(new BorderLayout(10, 10)); // Sắp xếp các thành phần giao diện

        // Panel trên cùng cho nhập dữ liệu
        JPanel topPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        topPanel.setBorder(BorderFactory.createTitledBorder("Add New Item"));

        JLabel nameLabel = new JLabel("Item Name:");
        nameField = new JTextField();
        JLabel priceLabel = new JLabel("Item Price:");
        priceField = new JTextField();

        topPanel.add(nameLabel);//Thêm ô nhập liệu nameLabel vào topPanel
        topPanel.add(nameField);//Thêm ô nhập liệu nameField vào topPanel
        topPanel.add(priceLabel);//Thêm ô nhập liệu priceLabel vào topPanel
        topPanel.add(priceField);//Thêm ô nhập liệu priceField vào topPanel

        add(topPanel, BorderLayout.NORTH);

        // Khu vực hiển thị menu
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBorder(BorderFactory.createTitledBorder("Menu Items"));//Một khung (border) có tiêu đề "Menu Items" được thêm vào centerPanel để làm rõ mục đích hiển thị danh sách menu.
        menuDisplayArea = new JTextArea();
        menuDisplayArea.setEditable(false);
        menuDisplayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));//setFont thiết lập phông chữ dạng Monospaced, giúp các dòng hiển thị dễ đọc và nhất quán.
        centerPanel.add(new JScrollPane(menuDisplayArea), BorderLayout.CENTER);//JScrollPane bao bọc menuDisplayArea để cung cấp thanh cuộn nếu danh sách menu dài hơn không gian hiển thị.
        add(centerPanel, BorderLayout.CENTER);//Thành phần này được đặt ở vị trí trung tâm

        // Panel dưới cùng chứa các nút
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton addButton = new JButton("Add Item");
        JButton resetButton = new JButton("Reset");

        // Thêm sự kiện cho nút Add Item
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addMenuItem();
            }
        });

        // Thêm sự kiện cho nút Reset
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetMenu();
            }
        });

        bottomPanel.add(addButton);
        bottomPanel.add(resetButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true); // Hiển thị giao diện
    }

    private void addMenuItem() {
        String name = nameField.getText(); // Lấy tên món ăn
        String priceText = priceField.getText(); // Lấy giá món ăn

        try {
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Item name cannot be empty!", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            double price = Double.parseDouble(priceText); // Chuyển giá sang kiểu số
            if (price < 0) {
                JOptionPane.showMessageDialog(this, "Price cannot be negative!", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            MenuItem item = new MenuItem(name, price); // Tạo đối tượng món ăn
            menuItems.add(item); // Thêm vào danh sách

            nameField.setText(""); // Xóa ô nhập
            priceField.setText("");

            updateMenuDisplay(); // Cập nhật hiển thị menu
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price! Please enter a numeric value.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateMenuDisplay() {
        menuDisplayArea.setText("");
        for (MenuItem item : menuItems) {
            menuDisplayArea.append(item + "\n");
        }
    }

    private void resetMenu() {
        menuItems.clear(); // Xóa toàn bộ danh sách món ăn
        nameField.setText(""); // Xóa ô nhập
        priceField.setText("");
        updateMenuDisplay(); // Cập nhật lại giao diện
        JOptionPane.showMessageDialog(this, "Menu has been reset.", "Reset Complete", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuApp()); // Chạy ứng dụng
    }
}
