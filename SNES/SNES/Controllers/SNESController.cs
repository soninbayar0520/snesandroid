using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using serviceNow.Services;
using SNES.Models;

namespace SNES.Controllers
{
    //[Authorize]
    [Route("[controller]")]
    [ApiController]
    public class SNESController : ControllerBase
    {

        private ISNESService _SNESService;
        public SNESController(ISNESService SNESService)
        {
            _SNESService = SNESService;
        }


        [HttpPost("CreateUser")]
        [Produces("application/json")]
        public async Task<IActionResult> CreateUser(User user)
        {
            ResponseModel resp = new ResponseModel();
            try
            {
                user.UpdatedTime = DateTime.Now;
                user.CreatedTime = DateTime.Now;
                resp.SuccessID = await _SNESService.SaveUserAsync(user);

                resp.Success = String.IsNullOrEmpty(resp.SuccessID) ? false : true;
                resp.TimeStamp = DateTime.Now;
                if (!resp.Success) { return StatusCode(StatusCodes.Status409Conflict); }
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error: {0}", ex.Message);
                resp.Success = false;
                return StatusCode(StatusCodes.Status400BadRequest);
            }
            return Ok(resp);
        }

        [HttpPost("CreateCategory")]
        [Produces("application/json")]
        public async Task<IActionResult> CreateCategory(Category category)
        {
            ResponseModel resp = new ResponseModel();
            try
            {
                category.CreatedOn = DateTime.Now;
                category.UpdatedOn = DateTime.Now;
                resp.SuccessID = await _SNESService.SaveCategoryAsync(category);

                resp.Success = String.IsNullOrEmpty(resp.SuccessID) ? false : true;
                resp.TimeStamp = DateTime.Now;
                if (!resp.Success) { return StatusCode(StatusCodes.Status409Conflict); }
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error: {0}", ex.Message);
                resp.Success = false;
                return StatusCode(StatusCodes.Status301MovedPermanently);
            }
            return Ok(resp);
        }

        [HttpPost("CreateReceipt")]
        [Produces("application/json")]
        public async Task<IActionResult> CreateReceipt(ReceiptModel receipt)
        {
            ResponseModel resp = new ResponseModel();
            try
            {
                receipt.CreatedOn = DateTime.Now;
                receipt.UpdatedOn = DateTime.Now;
                receipt.ReceiptDate = DateTime.Now;
                receipt.RawPicture = Convert.FromBase64String(receipt.rec_img_id);
                resp.SuccessID = await _SNESService.SaveReceiptsAsync(receipt);
                resp.Success = String.IsNullOrEmpty(resp.SuccessID) ? false : true;
                resp.TimeStamp = DateTime.Now;

                // Location location = new Location();
                //  location.Longitude = receipt.Long;
                //  location.Latitude = receipt.lati;
                //  location.UserID = receipt.UserID;
                //  location.UpdatedTime = DateTime.Now;
                //  location.CreatedTime = DateTime.Now;
                //  location.RecID = resp.SuccessID;
                // await _SNESService.SaveLocationAsync(location);
            }
            catch (Exception ex)
            {
                Console.WriteLine("Error: {0}", ex.Message);
                resp.Success = false;
                return StatusCode(StatusCodes.Status400BadRequest);
            }
            return Ok(resp);
        }

        [HttpGet("GetAllCategories")]
        [Produces("application/json")]
        public async Task<IActionResult> GetAllCategories()
        {
            List<Category> resp = new List<Category>();
            try
            {
                resp = await _SNESService.GetListOfCAtegories();

            }
            catch (Exception ex)
            {
                Console.WriteLine("Error: {0}", ex.Message);
                return StatusCode(StatusCodes.Status400BadRequest);
            }
            return Ok(resp);
        }
        [HttpGet("GetAllReceipts")]
        [Produces("application/json")]
        public async Task<IActionResult> GetAllReceipts()
        {
            List<ReceiptModel> resp = new List<ReceiptModel>();
            try
            {
                resp = await _SNESService.GetListAllReceipts("TEST");

            }
            catch (Exception ex)
            {
                Console.WriteLine("Error: {0}", ex.Message);
                return StatusCode(StatusCodes.Status400BadRequest);
            }
            return Ok(resp);
        }
        [HttpGet("GetUserInfo")]
        [Produces("application/json")]
        public async Task<IActionResult> GetUserInfo(string username)
        {
            User resp = new User();
            try
            {
                resp = await _SNESService.GetuserInfo(username);

            }
            catch (Exception ex)
            {
                Console.WriteLine("Error: {0}", ex.Message);
                return StatusCode(StatusCodes.Status400BadRequest);
            }
            return Ok(resp);
        }

        [HttpGet("ImageTobase64Info")]
        [Produces("application/json")]
        public async Task<IActionResult> ImageTobase64Info(string img_url)
        {
            string path = "1.png";
            using (System.Drawing.Image image = System.Drawing.Image.FromFile(path))
            {
                using (MemoryStream m = new MemoryStream())
                {
                    image.Save(m, image.RawFormat);
                    byte[] imageBytes = m.ToArray();
                    string   base64String = Convert.ToBase64String(imageBytes);
                    // return base64String;
                    return Ok(base64String);
                }
            }
        }
        [HttpGet("BytetoImage")]
        public System.Drawing.Image BytetoImage(string img_url)
        {
            byte[] imageBytes = Convert.FromBase64String("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");
            MemoryStream ms = new MemoryStream(imageBytes, 0, imageBytes.Length);
            ms.Write(imageBytes, 0, imageBytes.Length);
            System.Drawing.Image image = System.Drawing.Image.FromStream(ms, true);
            return image;
        }



        [HttpGet("Talk")]
        public string Talk()
        {
            return "Hi SNES API Talking..";
        }
    }
}
