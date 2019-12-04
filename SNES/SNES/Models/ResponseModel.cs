using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SNES.Models
{
    public class ResponseModel
    {
        public bool Success { get; set; }
        public DateTime TimeStamp { get; set; }
        public string SuccessID { get; set; }

        public ResponseModel(bool success, DateTime timeStamp, string successID)
        {
            Success = success;
            TimeStamp = timeStamp;
            SuccessID = successID;
        }

        public ResponseModel()
        {
        }
    }
}
