using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using serviceNow.Models;
using SNES.Models;

namespace serviceNow.Services
{
    public interface ISNESService
    {
        Task<string> SaveUserAsync(User user);
        Task<string> SaveCategoryAsync(Category category);
        Task<string> SaveReceiptsAsync(ReceiptModel receipt);
       // Task<string> SaveLocationAsync(Location location);
        Task<List<Category>> GetListOfCAtegories();
        Task<User> GetuserInfo(string username);
        Task<List<ReceiptModel>> GetListAllReceipts(string user);
    }

    public class SNESService : ISNESService
    {

        public async Task<string> SaveUserAsync(User user)
        {
            try
            {
                using (var saveContext = new DatabaseContext())
                {
                    await saveContext.SNES_USERS.AddAsync(user);
                    var res = await saveContext.SaveChangesAsync();
                    return user.UserID.ToString();
                }
            }
            catch (Exception e)
            {

                Console.WriteLine(e.Message);              
                return null;
            }
        }

        public async Task<string> SaveCategoryAsync(Category category)
        {
            try
            {
                using (var saveContext = new DatabaseContext())
                {
                    await saveContext.SNES_CATEGORIES.AddAsync(category);
                    await saveContext.SaveChangesAsync();
                    return category.CategoryId.ToString();
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                Environment.ExitCode = -1;
                return null;
                //EmailDelivery("Saving Course List failed due to: ", e.Message);
            }
        }

        public async Task<string> SaveReceiptsAsync(ReceiptModel receipt)
        {
            try
            {

                using (var saveContext = new DatabaseContext())
                {
                    await saveContext.SNES_RECEIPTS.AddAsync(receipt);

                    await saveContext.SaveChangesAsync();
                    return receipt.RecID.ToString();
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                Environment.ExitCode = -1;
                return null;
                //EmailDelivery("Saving Course List failed due to: ", e.Message);
            }
        }

       /** public async Task<string> SaveLocationAsync(Location location)
        {
            try
            {
                using (var saveContext = new DatabaseContext())
                {
                    await saveContext.SNES_LOCATIONS.AddAsync(location);
                    await saveContext.SaveChangesAsync();
                    return location.LocationdID.ToString();
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                Environment.ExitCode = -1;
                return null;
                //EmailDelivery("Saving Course List failed due to: ", e.Message);
            }
        }
    **/
        public async Task<List<Category>> GetListOfCAtegories()
        {
            try
            {
                using (var saveContext = new DatabaseContext())
                {
                    var data = saveContext.SNES_CATEGORIES.ToList();
                    await saveContext.SaveChangesAsync();
                    return data;
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                Environment.ExitCode = -1;
                return null;
                //EmailDelivery("Saving Course List failed due to: ", e.Message);
            }
        }
        public async Task<List<ReceiptModel>> GetListAllReceipts(string user)
        {
            try
            {
                using (var saveContext = new DatabaseContext())
                {
                    var data = saveContext.SNES_RECEIPTS.ToList();//.Where(u=>u.UserID=="1212");
                    await saveContext.SaveChangesAsync();
                    return data;
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                Environment.ExitCode = -1;
                return null;
                //EmailDelivery("Saving Course List failed due to: ", e.Message);
            }
        }

        public async Task<User> GetuserInfo(string username)
        {
            try
            {
                using (var saveContext = new DatabaseContext())
                {
                    var data = saveContext.SNES_USERS.FirstOrDefault(p => p.UserName == username);
                    await saveContext.SaveChangesAsync();
                    return data;
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                Environment.ExitCode = -1;
                return null;
                //EmailDelivery("Saving Course List failed due to: ", e.Message);
            }
        }
    }

}
