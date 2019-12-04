using System;
using Microsoft.EntityFrameworkCore.Migrations;

namespace SNES.Migrations
{
    public partial class InitialCreate : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "SNES_CATEGORIES",
                columns: table => new
                {
                    CategoryId = table.Column<Guid>(nullable: false),
                    CategoryName = table.Column<string>(nullable: false),
                    CategoryPicture = table.Column<string>(nullable: true),
                    CategoryDetails = table.Column<string>(nullable: true),
                    CreatedOn = table.Column<DateTime>(nullable: false),
                    UpdatedOn = table.Column<DateTime>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_SNES_CATEGORIES", x => x.CategoryId);
                    table.UniqueConstraint("AlternateKey_CategoryName", x => x.CategoryName);
                });

            migrationBuilder.CreateTable(
                name: "SNES_RECEIPTS",
                columns: table => new
                {
                    RecID = table.Column<Guid>(nullable: false),
                    UserID = table.Column<string>(nullable: true),
                    ReceiptAmount = table.Column<double>(nullable: false),
                    ReceiptTaxAmount = table.Column<double>(nullable: false),
                    ReceiptTipAmount = table.Column<double>(nullable: false),
                    rec_img_id = table.Column<string>(nullable: true),
                    RawPicture = table.Column<byte[]>(nullable: true),
                    CategoryId = table.Column<string>(nullable: true),
                    ReceiptDate = table.Column<DateTime>(nullable: false),
                    CreatedOn = table.Column<DateTime>(nullable: false),
                    UpdatedOn = table.Column<DateTime>(nullable: false),
                    LongT = table.Column<string>(nullable: true),
                    LatiT = table.Column<string>(nullable: true),
                    StoreName = table.Column<string>(nullable: true),
                    StoreAddress = table.Column<string>(nullable: true),
                    StorePhoneNumber = table.Column<string>(nullable: true)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_SNES_RECEIPTS", x => x.RecID);
                });

            migrationBuilder.CreateTable(
                name: "SNES_USERS",
                columns: table => new
                {
                    UserID = table.Column<Guid>(nullable: false),
                    UserName = table.Column<string>(nullable: true),
                    UserEmail = table.Column<string>(nullable: false),
                    FirstName = table.Column<string>(nullable: true),
                    LastName = table.Column<string>(nullable: true),
                    Password = table.Column<string>(nullable: true),
                    UserPicture = table.Column<string>(nullable: true),
                    CreatedTime = table.Column<DateTime>(nullable: false),
                    UpdatedTime = table.Column<DateTime>(nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_SNES_USERS", x => x.UserID);
                    table.UniqueConstraint("AlternateKey_UserEmail", x => x.UserEmail);
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "SNES_CATEGORIES");

            migrationBuilder.DropTable(
                name: "SNES_RECEIPTS");

            migrationBuilder.DropTable(
                name: "SNES_USERS");
        }
    }
}
