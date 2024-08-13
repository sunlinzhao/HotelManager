/*
 * Created by JFormDesigner on Sun Mar 05 23:45:13 CST 2023
 */

package com.coder.hotel.ui.memberInfo;

import com.coder.hotel.entity.MemberInfo;
import com.coder.hotel.entity.MemberInfoQuery;
import com.coder.hotel.entity.MemberLevel;
import com.coder.hotel.service.MemberInfoService;
import com.coder.hotel.service.MemberLevelService;
import com.coder.hotel.util.UiUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
public class MemberInfoUpdateUi extends JFrame {
    private MemberInfoUpdateUi() {
        initComponents();
    }
    private static final MemberInfoUpdateUi UI=new MemberInfoUpdateUi();
    public static MemberInfoUpdateUi getInstance(){
        return UI;
    }
    private void idnumValFocusLost(FocusEvent e) {
        // TODO add your code here
        String text = idnumVal.getText();
        String regx="^[1-9]\\d{5}(18|19|2([0-9]))\\d{2}(0[0-9]|10|11|12)([0-2][1-9]|30|31)\\d{3}[0-9Xx]$";
        boolean b = Pattern.matches(regx, text);
        if (!b){
            JOptionPane.showMessageDialog(this,"身份证录入错误");
            return;
        }
        //112233198010122563
        String dateVal=text.substring(6,14);
        LocalDate localDate=LocalDate.parse(dateVal, DateTimeFormatter.ofPattern("yyyyMMdd"));
        Date date=Date.valueOf(localDate);
        birthVal.setText(date.toString());
        LocalDate now=LocalDate.now();
        long age = ChronoUnit.YEARS.between(localDate, now);
        ageVal.setText(String.valueOf(age));
        String c=text.charAt(16)+"";
        int i=Integer.parseInt(c);
        if (i%2==0){
            womanBtn.setSelected(true);
        }else{
            manBtn.setSelected(true);
        }
    }

    private void submit(ActionEvent e) {
        // TODO add your code here
        String name=nameVal.getText();
        String level=levelVal.getSelectedItem().toString();
        String idnum=idnumVal.getText();
        String tel=telVal.getText();
        String birth=birthVal.getText();
        String age=ageVal.getText();
        String gender;
        if (manBtn.isSelected())
            gender="男";
        else
            gender="女";
        memberInfo.setName(name);
        memberInfo.setIdnum(idnum);
        memberInfo.setBirth(Date.valueOf(birth));
        memberInfo.setAge(Integer.valueOf(age));
        memberInfo.setGender(gender);
        memberInfo.setTel(tel);
        levelService=new MemberLevelService();
        Integer levelid=levelService.selectByLevel(level);
        memberInfo.setLevelid(levelid);
        memberInfo.setLevel(level);
        MemberInfoService service=new MemberInfoService();
        int i = service.update(memberInfo);
        if (i > 0) {
            DefaultTableModel model= (DefaultTableModel) table.getModel();
            //重新查询一下数据库
            MemberInfoQuery infoQuery=new MemberInfoQuery();
            infoQuery.setName("");
            infoQuery.setGender("请选择");
            infoQuery.setLowAge(0);
            infoQuery.setHighAge(0);
            infoQuery.setLevel("请选择");
            Object[][] objects = service.selectExample(infoQuery,1);
            String[] column=new String[]{"id","姓名","身份证号","出生日期","年龄","性别","电话","等级id","等级"};
            model.setDataVector(objects,column);
            table.updateUI();
            JOptionPane.showMessageDialog(this, "修改成功");
            goBack(e);
        }
    }

    private void reset(ActionEvent e) {
        // TODO add your code here
        init();
    }

    private void goBack(ActionEvent e) {
        // TODO add your code here
        UiUtil.indent(UI,MemberInfoUi.getInstance());
    }
    private  void init(){
        //回显数据
        nameVal.setText(memberInfo.getName());
        idnumVal.setText(memberInfo.getIdnum());
        telVal.setText(memberInfo.getTel());
        birthVal.setText(memberInfo.getBirth().toString());
        ageVal.setText(memberInfo.getAge().toString());
        String gender=memberInfo.getGender();
        if (gender.equals("男"))
            manBtn.setSelected(true);
        else 
            womanBtn.setSelected(true);
        //回显会员等级
        levelVal.setSelectedItem(memberInfo.getLevel());

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        panel1 = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        label6 = new JLabel();
        label7 = new JLabel();
        nameVal = new JTextField();
        MemberLevelService levelService=new MemberLevelService();
        List<MemberLevel> list = levelService.getList();
        List<String> collect = list.stream().map(MemberLevel::getLevel).collect(Collectors.toList());
        collect.add(0,"--请选择--");
        levelVal = new JComboBox(collect.toArray());
        idnumVal = new JTextField();
        telVal = new JTextField();
        birthVal = new JTextField();
        ageVal = new JTextField();
        label8 = new JLabel();
        manBtn = new JRadioButton();
        womanBtn = new JRadioButton();
        group=new ButtonGroup();
        group.add(manBtn);
        group.add(womanBtn);
        okBtn = new JButton();
        resetBtn = new JButton();
        backBtn = new JButton();
        label10 = new JLabel();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/img/\u9152\u5e97.png")).getImage());
        setTitle("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf");
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("\u9152\u5e97\u7ba1\u7406\u7cfb\u7edf_\u4f1a\u5458\u4fe1\u606f");
        label1.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 28));
        label1.setForeground(Color.white);
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(label1);
        label1.setBounds(0, 10, 800, 55);

        //======== panel1 ========
        {
            panel1.setBackground(Color.white);
            panel1.setLayout(null);

            //---- label2 ----
            label2.setText("\u4f1a\u5458\u59d3\u540d");
            label2.setHorizontalAlignment(SwingConstants.RIGHT);
            label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label2);
            label2.setBounds(75, 30, 80, 35);

            //---- label3 ----
            label3.setText("\u8eab\u4efd\u8bc1\u53f7");
            label3.setHorizontalAlignment(SwingConstants.RIGHT);
            label3.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label3);
            label3.setBounds(75, 115, 80, 35);

            //---- label4 ----
            label4.setText("\u8054\u7cfb\u7535\u8bdd");
            label4.setHorizontalAlignment(SwingConstants.RIGHT);
            label4.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label4);
            label4.setBounds(75, 155, 80, 35);

            //---- label5 ----
            label5.setText("\u51fa\u751f\u65e5\u671f");
            label5.setHorizontalAlignment(SwingConstants.RIGHT);
            label5.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label5);
            label5.setBounds(75, 200, 80, 35);

            //---- label6 ----
            label6.setText("\u4f1a\u5458\u5e74\u9f84");
            label6.setHorizontalAlignment(SwingConstants.RIGHT);
            label6.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label6);
            label6.setBounds(75, 240, 80, 35);

            //---- label7 ----
            label7.setText("\u4f1a\u5458\u7b49\u7ea7");
            label7.setHorizontalAlignment(SwingConstants.RIGHT);
            label7.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            panel1.add(label7);
            label7.setBounds(75, 75, 80, 35);
            panel1.add(nameVal);
            nameVal.setBounds(180, 30, 310, 35);
            panel1.add(levelVal);
            levelVal.setBounds(180, 75, 310, 35);

            //---- idnumVal ----
            idnumVal.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    idnumValFocusLost(e);
                }
            });
            panel1.add(idnumVal);
            idnumVal.setBounds(180, 115, 310, 35);
            panel1.add(telVal);
            telVal.setBounds(180, 155, 310, 35);

            //---- birthVal ----
            birthVal.setEditable(false);
            panel1.add(birthVal);
            birthVal.setBounds(180, 200, 310, 35);

            //---- ageVal ----
            ageVal.setEditable(false);
            panel1.add(ageVal);
            ageVal.setBounds(180, 240, 310, 35);

            //---- label8 ----
            label8.setText("\u4f1a\u5458\u6027\u522b");
            label8.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            label8.setHorizontalAlignment(SwingConstants.RIGHT);
            panel1.add(label8);
            label8.setBounds(75, 280, 80, 35);

            //---- radioButton1 ----
            manBtn.setText("\u7537");
            manBtn.setBackground(Color.white);
            manBtn.setEnabled(false);
            panel1.add(manBtn);
            manBtn.setBounds(new Rectangle(new Point(185, 290), manBtn.getPreferredSize()));

            //---- radioButton2 ----
            womanBtn.setText("\u5973");
            womanBtn.setBackground(Color.white);
            womanBtn.setEnabled(false);
            panel1.add(womanBtn);
            womanBtn.setBounds(new Rectangle(new Point(235, 290), womanBtn.getPreferredSize()));
        }
        contentPane.add(panel1);
        panel1.setBounds(90, 95, 600, 340);

        //---- okBtn ----
        okBtn.setText("\u786e\u5b9a");
        okBtn.addActionListener(e -> submit(e));
        contentPane.add(okBtn);
        okBtn.setBounds(470, 445, 78, 30);

        //---- resetBtn ----
        resetBtn.setText("\u91cd\u7f6e");
        resetBtn.addActionListener(e -> reset(e));
        contentPane.add(resetBtn);
        resetBtn.setBounds(545, 445, 78, 30);

        //---- backBtn ----
        backBtn.setText("\u8fd4\u56de");
        backBtn.addActionListener(e -> goBack(e));
        contentPane.add(backBtn);
        backBtn.setBounds(615, 445, 78, 30);

        //---- label10 ----
        label10.setIcon(new ImageIcon(getClass().getResource("/img/bg.jpg")));
        contentPane.add(label10);
        label10.setBounds(0, 0, 800, 530);

        contentPane.setPreferredSize(new Dimension(800, 530));
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JPanel panel1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JTextField nameVal;
    private JComboBox levelVal;
    private JTextField idnumVal;
    private JTextField telVal;
    private JTextField birthVal;
    private JTextField ageVal;
    private JLabel label8;
    private JRadioButton manBtn;
    private JRadioButton womanBtn;
    private JButton okBtn;
    private JButton resetBtn;
    private JButton backBtn;
    private JLabel label10;
    private MemberInfo memberInfo;
    private JTable table;
    private ButtonGroup group;
    private MemberLevelService levelService;
    public MemberInfo getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(MemberInfo memberInfo) {
        this.memberInfo = memberInfo;
        init();
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
