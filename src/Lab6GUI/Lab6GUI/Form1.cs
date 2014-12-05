using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Lab6GUI
{
    public partial class Form1 : Form
    {
        public string action { get; set; }
        public string first { get; set; }
        public Form1()
        {
            InitializeComponent();
        }
        ServiceReference1.Service1Client client = new
        ServiceReference1.Service1Client();
    
        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {

            textBox1.Text = textBox1.Text + "1";
            
        }
        
        

        private void button2_Click_1(object sender, EventArgs e)
        {
            textBox1.Text = textBox1.Text + "2";
            
        }

        private void button3_Click(object sender, EventArgs e)
        {

            textBox1.Text = textBox1.Text + "3";

        }

        private void button4_Click(object sender, EventArgs e)
        {

            textBox1.Text = textBox1.Text + "4";

        }

        private void button5_Click(object sender, EventArgs e)
        {

            textBox1.Text = textBox1.Text + "5";

        }

        private void button6_Click(object sender, EventArgs e)
        {

            textBox1.Text = textBox1.Text + "6";

        }

        private void button7_Click(object sender, EventArgs e)
        {

            textBox1.Text = textBox1.Text + "7";

        }

        private void button8_Click(object sender, EventArgs e)
        {

            textBox1.Text = textBox1.Text + "8";

        }

        private void button9_Click(object sender, EventArgs e)
        {

            textBox1.Text = textBox1.Text + "9";

        }

        private void button14_Click(object sender, EventArgs e)
        {
            textBox1.Text = client.Sin(textBox1.Text);
        }

        private void button10_Click(object sender, EventArgs e)
        {
            this.first = textBox1.Text;
            this.action = "add";
            textBox1.Text =  "";
        }

        private void button11_Click(object sender, EventArgs e)
        {
            this.first = textBox1.Text;
            this.action = "sub";
            textBox1.Text = "";
        }

        private void button12_Click(object sender, EventArgs e)
        {
            this.first = textBox1.Text;
            this.action = "mult";
            textBox1.Text = "";
 
        }

        private void button13_Click(object sender, EventArgs e)
        {
            this.first = textBox1.Text;
            this.action = "div";
            textBox1.Text = "";

        }

        private void button15_Click(object sender, EventArgs e)
        {
            textBox1.Text = client.Cos(textBox1.Text);
        }

        private void button16_Click(object sender, EventArgs e)
        {
            textBox1.Text = client.Exp(textBox1.Text);
        }

        private void button17_Click(object sender, EventArgs e)
        {
            textBox1.Text = client.Calculate(textBox1.Text,this.first,this.action);
        }
    }
}
