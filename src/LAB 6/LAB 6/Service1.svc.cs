using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace LAB_6
{
     public class Service1 : IService1
    {

        public string Calculate(string number,string first,string action)
        {
            if (action.Equals("add"))
            {
                first = (double.Parse(first) + double.Parse(number)).ToString();
                return first;
            }
            else if (action.Equals("mult"))
            {
                first = (double.Parse(first) * double.Parse(number)).ToString();
                return first;
         
            }
            else if (action.Equals("div"))
            {
                first = (double.Parse(first) / double.Parse(number)).ToString();
                return first;
         
            }else if (action.Equals("sub"))
            {
                first = (double.Parse(first) - double.Parse(number)).ToString();
                return first;
         
            }
            return number;
        }
       

         public string Sin(string number)
        {
            return Math.Sin(double.Parse(number)).ToString();
        }

         public string Cos(string number)
        {
            return Math.Cos(double.Parse(number)).ToString();
        }

       public string Exp(string number)
        {
            return Math.Exp(double.Parse(number)).ToString();
        }
    }
}



