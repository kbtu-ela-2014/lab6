using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace LAB_6
{
    // ПРИМЕЧАНИЕ. Команду "Переименовать" в меню "Рефакторинг" можно использовать для одновременного изменения имени интерфейса "IService1" в коде и файле конфигурации.
    [ServiceContract]
    public interface IService1
    {


     //   [OperationContract]
       // CompositeType GetDataUsingDataContract(CompositeType composite);

        [OperationContract]
        string Calculate(string number,string first,string action);


        [OperationContract]
        string Sin(string number);
        [OperationContract]
        string Cos(string number);
        [OperationContract]
        string Exp(string number);
    }


    // Используйте контракт данных, как показано в примере ниже, чтобы добавить составные типы к операциям служб.
    [DataContract]
    public class CompositeType
    {
        bool boolValue = true;
        string stringValue = "Hello ";

        [DataMember]
        public bool BoolValue
        {
            get { return boolValue; }
            set { boolValue = value; }
        }

        [DataMember]
        public string StringValue
        {
            get { return stringValue; }
            set { stringValue = value; }
        }

        [DataMember]
        public long first
        {
            get;
            set;
        }
        [DataMember]
        public long second
        {
            get;
            set;
        }
    }
}
