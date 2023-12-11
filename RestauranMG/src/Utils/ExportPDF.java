package Utils;

import Dao.MenuItemsDao;
import Dao.OrderDetailDao;
import Entity.Areas;
import Entity.Invoices;
import Entity.MenuItems;
import Entity.OrderDetail;
import Entity.Orders;
import Entity.Tables;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.MenuItem;
import java.awt.geom.Area;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

public class ExportPDF {

    public static void ExportInvoice(Invoices invoice, Orders order, Areas area, Tables tables) {
        try {

            String fileName = "src/Invoice/" + "HD" + invoice.getID_Invoice() + " - " + invoice.getInvoiceDate() + ".pdf";
            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            BaseFont baseFont = BaseFont.createFont("src/arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

            Font font32Bold = new Font(baseFont, 32, Font.BOLD);
            Font font18Normal = new Font(baseFont, 18, Font.NORMAL);
            Font font18Bold = new Font(baseFont, 18, Font.BOLD);
            Font font20Bold = new Font(baseFont, 20, Font.BOLD);
            Font font16Italic = new Font(baseFont, 16, Font.ITALIC);
            Font font14Normal = new Font(baseFont, 14, Font.NORMAL);
            Paragraph title = new Paragraph("3 Tràng Chai", font32Bold);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            Paragraph address = new Paragraph("Thôn 2, Ea H'leo, Ea H'Leo, Đắk Lắk", font14Normal);
            address.setAlignment(Element.ALIGN_CENTER);
            document.add(address);

            Paragraph empty = new Paragraph("\n\n");
            document.add(empty);

            Paragraph payment = new Paragraph("Hóa đơn thanh toán", font20Bold);
            payment.setAlignment(Element.ALIGN_CENTER);
            document.add(payment);

            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setWidthPercentage(100);
            infoTable.setSpacingBefore(10f);
            infoTable.setSpacingAfter(10f);

// Thêm nội dung vào bảng mới
            PdfPCell cell1 = new PdfPCell(new Paragraph(area.getAreaName(), font14Normal));
            cell1.setBorder(Rectangle.NO_BORDER); // Xóa đường viền của ô
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT); // Căn lề trái cho cột 1
            infoTable.addCell(cell1);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            PdfPCell cell2 = new PdfPCell(new Paragraph("Giờ ra: " + dateFormat.format(invoice.getInvoiceDate()), font14Normal));
            cell2.setBorder(Rectangle.NO_BORDER); // Xóa đường viền của ô
            cell2.setHorizontalAlignment(Element.ALIGN_RIGHT); // Căn lề phải cho cột 2
            infoTable.addCell(cell2);

            PdfPCell cell3 = new PdfPCell(new Paragraph("Bàn: " + tables.getTableName(), font14Normal));
            cell3.setBorder(Rectangle.NO_BORDER); // Xóa đường viền của ô
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT); // Căn lề trái cho cột 1
            infoTable.addCell(cell3);

            PdfPCell cell4 = new PdfPCell(new Paragraph("Mã HD: " + invoice.getID_Invoice(), font14Normal));
            cell4.setBorder(Rectangle.NO_BORDER); // Xóa đường viền của ô
            cell4.setHorizontalAlignment(Element.ALIGN_RIGHT); // Căn lề phải cho cột 2
            infoTable.addCell(cell4);

            document.add(infoTable);
            Paragraph soKhach = new Paragraph("Số khách: " + order.getNumberOfGuests(), font14Normal);
            document.add(soKhach);
            document.add(empty);

            //Bảng sẽ căn giữa các nội dung, và không cần đường viền ngoài của bảng, đường viền hãy để nét đứt
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(20f);
            table.setSpacingAfter(20f);

            PdfPCell Cell1 = new PdfPCell(new Phrase("Tên món", font18Bold));
            PdfPCell Cell2 = new PdfPCell(new Phrase("S.L", font18Bold));
            PdfPCell Cell3 = new PdfPCell(new Phrase("Đơn Giá", font18Bold));
            PdfPCell Cell4 = new PdfPCell(new Phrase("T.Tiền", font18Bold));
            // Tăng khoảng cách giữa các hàng
            Cell1.setPaddingBottom(10f);
            Cell2.setPaddingBottom(10f);
            Cell3.setPaddingBottom(10f);
            Cell4.setPaddingBottom(10f);
// Thiết lập căn giữa cho nội dung trong ô
            Cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            Cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            Cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            Cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);

// Xóa đường viền của ô đầu tiên
            Cell1.setBorder(Rectangle.NO_BORDER);
            Cell2.setBorder(Rectangle.NO_BORDER);
            Cell3.setBorder(Rectangle.NO_BORDER);
            Cell4.setBorder(Rectangle.NO_BORDER);

            table.addCell(Cell1);
            table.addCell(Cell2);
            table.addCell(Cell3);
            table.addCell(Cell4);
            List<OrderDetail> l = new OrderDetailDao().selectBySql("SELECT * FROM OrderDetail where ID_Order=?", order.getID_Order());
            for (int row = 0; row < l.size(); row++) {
                MenuItems m = new MenuItemsDao().selectById(l.get(row).getID_Item());

                PdfPCell itemCell1 = new PdfPCell(new Phrase(m.getItemName(), font14Normal));
                PdfPCell itemCell2 = new PdfPCell(new Phrase(l.get(row).getQuantity() + "", font14Normal));
                PdfPCell itemCell3 = new PdfPCell(new Phrase(m.getPrice() + "", font14Normal));
                PdfPCell itemCell4 = new PdfPCell(new Phrase(fNum.parseString(l.get(row).getTotalPrice()) + "đ", font14Normal));

                itemCell1.setHorizontalAlignment(Element.ALIGN_LEFT);
                itemCell2.setHorizontalAlignment(Element.ALIGN_CENTER);
                itemCell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
                itemCell4.setHorizontalAlignment(Element.ALIGN_RIGHT);

                itemCell1.setPaddingBottom(10f);
                itemCell2.setPaddingBottom(10f);
                itemCell3.setPaddingBottom(10f);
                itemCell4.setPaddingBottom(10f);

                itemCell1.setBorder(Rectangle.NO_BORDER);
                itemCell2.setBorder(Rectangle.NO_BORDER);
                itemCell3.setBorder(Rectangle.NO_BORDER);
                itemCell4.setBorder(Rectangle.NO_BORDER);
                table.addCell(itemCell1);
                table.addCell(itemCell2);
                table.addCell(itemCell3);
                table.addCell(itemCell4);
            }

// Thiết lập viền ở cuối bảng
            PdfPCell bottomLine = new PdfPCell();
            bottomLine.setColspan(4);

            bottomLine.setBorder(Rectangle.BOTTOM); // Chỉ hiển thị đường viền ở dưới
            bottomLine.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(bottomLine);

            document.add(table);

            Paragraph total = new Paragraph("Tổng cộng: " + fNum.parseString(invoice.getTotalAmount()) + "đ", font20Bold);
            total.setAlignment(Element.ALIGN_RIGHT);
            document.add(total);

            document.add(empty);

            Paragraph thankYou = new Paragraph("Xin cảm ơn quý khách, hẹn gặp lại!", font16Italic);
            thankYou.setAlignment(Element.ALIGN_CENTER);
            document.add(thankYou);

            document.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            msg.Error(e.getMessage());
        }

    }

}
