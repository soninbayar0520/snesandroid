using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace SNES.Models
{
    public class ReceiptModel
    {
        [Key]
        public Guid RecID { get; set; }
        public string UserID { get; set; }
        public double ReceiptAmount { get; set; }
        public double ReceiptTaxAmount { get; set; }
        public double ReceiptTipAmount { get; set; }
        public string rec_img_id { get; set; }
        public byte[] RawPicture { get; set; }
        public string CategoryId { get; set; }
        public DateTime ReceiptDate { get; set; }
        public DateTime CreatedOn { get; set; }
        public DateTime UpdatedOn { get; set; }   
        public string LongT {get;set;}     
        public string LatiT { get; set; }
        public string StoreName { get; set; }
        public string StoreAddress { get; set; }
        public string StorePhoneNumber { get; set; }

        public ReceiptModel(Guid recID, string userID, double receiptAmount, double receiptTaxAmount, double receiptTipAmount, string rec_img_id, byte[] rawPicture, string categoryId, DateTime receiptDate, DateTime createdOn, DateTime updatedOn, string longT, string latiT, string storeName, string storeAddress, string storePhoneNumber)
        {
            RecID = recID;
            UserID = userID;
            ReceiptAmount = receiptAmount;
            ReceiptTaxAmount = receiptTaxAmount;
            ReceiptTipAmount = receiptTipAmount;
            this.rec_img_id = rec_img_id;
            RawPicture = rawPicture;
            CategoryId = categoryId;
            ReceiptDate = receiptDate;
            CreatedOn = createdOn;
            UpdatedOn = updatedOn;
            LongT = longT;
            LatiT = latiT;
            StoreName = storeName;
            StoreAddress = storeAddress;
            StorePhoneNumber = storePhoneNumber;
        }

        public ReceiptModel()
        {
        }
    }

    public class User
    {
        public Guid UserID { get; set; }
        public string UserName { get; set; }       
        public string UserEmail { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public string Password { get; set; }
        public string UserPicture { get; set; }
        public DateTime CreatedTime { get; set; }
        public DateTime UpdatedTime { get; set; }

        public User(Guid userID, string userName, string userEmail, string firstName, string lastName, string password, string userPicture, DateTime createdTime, DateTime updatedTime)
        {
            UserID = userID;
            UserName = userName;
            UserEmail = userEmail;
            FirstName = firstName;
            LastName = lastName;
            Password = password;
            UserPicture = userPicture;
            CreatedTime = createdTime;
            UpdatedTime = updatedTime;
        }

        public User()
        {
        }
    }

    public class Category
    {
        public Guid CategoryId { get; set; }
        public string CategoryName { get; set; }
        public string CategoryPicture { get; set; }
        public string CategoryDetails { get; set; }
        public DateTime CreatedOn { get; set; }
        public DateTime UpdatedOn { get; set; }
        public Category(Guid categoryId, string categoryName, string categoryPicture, string categoryDetails, DateTime createdOn, DateTime updatedOn)
        {
            CategoryId = categoryId;
            CategoryName = categoryName;
            CategoryPicture = categoryPicture;
            CategoryDetails = categoryDetails;
            CreatedOn = createdOn;
            UpdatedOn = updatedOn;
        }

        public Category()
        {
        }
    }

}
