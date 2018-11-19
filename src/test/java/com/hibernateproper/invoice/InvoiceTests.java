package com.hibernateproper.invoice;

import com.hibernateproper.invoice.dao.InvoiceDao;
import com.hibernateproper.invoice.dao.ItemDao;
import com.hibernateproper.invoice.dao.ProductDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest

public class InvoiceTests {
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    ItemDao itemDao;
    @Autowired
    ProductDao productDao;

    @Test
    public void testOnlyProductAndItem(){
        //Given
        Product prod1 = new Product();
        prod1.setName("Ciastko");
        Product prod2 = new Product();
        prod2.setName("Karmel");

        Item item1 = new Item(new BigDecimal(10),5, new BigDecimal(50));
        Item item2 = new Item(new BigDecimal(3),10,new BigDecimal(30));

        item1.setProduct(prod1);
        item2.setProduct(prod2);

        Invoice invoice = new Invoice("1234");

        invoice.getItemList().add(item1);
        invoice.getItemList().add(item2);

        item1.setInvoice(invoice);
        item2.setInvoice(invoice);

        //When
        invoiceDao.save(invoice);
        int id = invoice.getId();

        //Then
        Assert.assertEquals(2,invoice.getItemList().size());

        //Clean
        invoiceDao.deleteById(id);
    }
}
