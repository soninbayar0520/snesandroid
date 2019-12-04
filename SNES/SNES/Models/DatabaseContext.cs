using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using SNES.Models;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;
namespace serviceNow.Models
{
    public class DatabaseContext : DbContext
    {
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            IConfigurationRoot configuration = new ConfigurationBuilder()
               .SetBasePath(AppDomain.CurrentDomain.BaseDirectory)
               .AddJsonFile("appsettings.json")
               .Build();
            optionsBuilder.UseSqlServer(configuration.GetConnectionString("DefaultConnection"));
        
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<User>()
                .HasAlternateKey(c => c.UserEmail)
                .HasName("AlternateKey_UserEmail");

            modelBuilder.Entity<Category>()
             .HasAlternateKey(c => c.CategoryName)
             .HasName("AlternateKey_CategoryName");
        }


        public DbSet<ReceiptModel> SNES_RECEIPTS { get; set; }
        public DbSet<User> SNES_USERS { get; set; }
        public DbSet<Category> SNES_CATEGORIES { get; set; }
      //  public DbSet<Location> SNES_LOCATIONS { get; set; }
        



    }
}
